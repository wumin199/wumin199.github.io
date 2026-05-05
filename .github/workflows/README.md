# `pages.yml` 工作流说明

这个目录里的 `*.yml` 文件会被 GitHub Actions 识别为工作流。当前的 `pages.yml` 用来在代码推送到 `main` 分支后，自动构建 Hexo 静态站点，并把构建结果发布到 GitHub Pages。

它不是单纯的“跑测试”CI，而是一个 CI/CD 流程：

- CI：安装依赖并执行构建，用来验证当前代码能否正常生成静态站点。
- CD：把构建后的 `public` 目录发布出去，让 GitHub Pages 使用新的站点内容。

## 整体流程

当前流程可以理解为：

```text
push 到 main
  -> GitHub Actions 触发 pages.yml
  -> GitHub 分配一台临时 Ubuntu runner
  -> checkout 仓库代码
  -> 安装/切换到 Node.js 24
  -> npm ci 安装依赖
  -> npm run build 构建 Hexo 站点
  -> 发布 ./public 到 GitHub Pages
```

## CI/CD、Runner、环境、Build 的关系

- Workflow：`pages.yml` 本身就是一个 GitHub Actions workflow，定义“什么时候运行、在哪里运行、运行哪些步骤”。
- CI：`npm ci` 和 `npm run build` 属于 CI 部分，用来确认依赖可安装、项目可构建。
- CD：`peaceiris/actions-gh-pages@v3` 属于 CD 部分，用来把构建产物发布到 GitHub Pages。
- Runner：`runs-on: ubuntu-latest` 表示这个 job 在 GitHub 提供的 Ubuntu runner 上执行。
- 环境：runner 是一台临时虚拟机，预装了常见构建工具，例如 Git、Bash、Node.js、npm、Python、Docker 等；具体版本会随 GitHub runner image 更新，以每次 Actions 日志和官方 runner image 文档为准。
- Node.js 环境：虽然 runner 可能预装 Node.js，但这里通过 `actions/setup-node@v4` 明确指定使用 Node.js 24，因此构建不会依赖镜像默认 Node 版本。
- Build：`npm run build` 会执行 `package.json` 里的 `build` 脚本，本仓库中对应的是 `hexo generate`，输出目录是 `public`。
- Deploy：部署步骤读取 `./public`，把里面的静态文件推送到 GitHub Pages 使用的发布分支；`peaceiris/actions-gh-pages` 默认发布到 `gh-pages` 分支。

## `pages.yml` 参数解释

```yaml
name: Pages
```

`name` 是工作流在 GitHub Actions 页面显示的名字。

```yaml
on:
  push:
    branches:
      - main
```

`on` 定义触发条件。这里表示只有当代码 push 到 `main` 分支时才会运行这个 workflow。

```yaml
jobs:
  pages:
```

`jobs` 定义要运行的任务。`pages` 是 job id，可以理解为这个任务的内部名字。

```yaml
runs-on: ubuntu-latest
```

`runs-on` 定义 job 运行在哪种 runner 上。`ubuntu-latest` 是 GitHub-hosted runner 的标签，表示使用 GitHub 当前推荐的 Ubuntu 镜像。它不是固定版本，具体 Ubuntu 版本可能会随 GitHub 更新。

```yaml
permissions:
  contents: write
```

`permissions` 定义 `GITHUB_TOKEN` 的权限。`contents: write` 允许 workflow 向仓库写入内容。部署 GitHub Pages 时需要这个权限，因为发布步骤要把 `public` 目录推送到发布分支。

```yaml
steps:
```

`steps` 是 job 里的执行步骤，按顺序运行。如果前面的步骤失败，后面的步骤默认不会继续执行。

```yaml
- uses: actions/checkout@v4
```

`uses` 表示使用一个现成的 GitHub Action。`actions/checkout@v4` 会把当前仓库的代码检出到 runner 上，否则后续命令看不到项目文件。

```yaml
- name: Use Node.js 24.x
  uses: actions/setup-node@v4
  with:
    node-version: "24"
    cache: npm
```

这个步骤配置 Node.js 环境：

- `name` 是步骤显示名。
- `actions/setup-node@v4` 用来安装或切换 Node.js。
- `node-version: "24"` 表示使用 Node.js 24。
- `cache: npm` 表示启用 npm 缓存，加速后续安装依赖；缓存的是 npm 下载缓存，不是直接缓存 `node_modules`。

```yaml
- name: Install Dependencies
  run: npm ci
```

`run` 表示在 runner shell 中执行命令。`npm ci` 会根据 `package-lock.json` 安装依赖，适合 CI 环境；它比 `npm install` 更强调可重复构建。

```yaml
- name: Build
  run: npm run build
```

这个步骤执行构建命令。本仓库的 `package.json` 中：

```json
"build": "hexo generate"
```

所以实际执行的是 Hexo 静态站点生成命令，构建结果输出到 `public` 目录。

## `npm ci`、`npm run build`、`hexo g` 的关系

这些命令处在同一条构建链路里，但职责不同：

```text
npm ci
  -> 根据 package-lock.json 安装依赖到 node_modules
  -> 安装出本项目自己的 Hexo CLI 和插件
  -> 生成 node_modules/.bin/hexo 这个本地命令

npm run build
  -> 读取 package.json 里的 scripts.build
  -> 本仓库的 build 等于 hexo generate
  -> npm 会自动把 node_modules/.bin 放到 PATH 里
  -> 所以这里调用的是本项目安装的 Hexo，而不是系统全局 Hexo

hexo generate
  -> 生成静态站点文件到 public
  -> hexo g 是 hexo generate 的缩写
```

因此，CI 里写：

```bash
npm ci && npm run build
```

意思是“先安装依赖；只有安装成功，才继续构建”。`&&` 是 shell 的短路执行符号，前一个命令失败时，后一个命令不会执行。

在当前 `pages.yml` 里，这两个命令被拆成了两个步骤：

```yaml
- name: Install Dependencies
  run: npm ci

- name: Build
  run: npm run build
```

效果和 `npm ci && npm run build` 类似：如果 `npm ci` 失败，GitHub Actions 默认会停止 job，不会进入 `Build` 步骤。

`hexo install` 不是 Hexo 常规构建链路里的标准命令。安装 Hexo、主题、插件、渲染器等依赖，通常用的是 npm：

```bash
npm install
npm install <package>
npm ci
```

本仓库在 CI 中应该使用 `npm ci`，因为它会严格按照 `package-lock.json` 安装依赖，更适合可重复构建。`npm install` 更适合本地开发时新增或升级依赖，因为它可能更新 `package-lock.json`。

如果只是手动生成站点，在依赖已经安装好的情况下，可以直接执行：

```bash
npm run build
```

或等价地执行：

```bash
npx hexo generate
npx hexo g
```

```yaml
- name: Deploy
  uses: peaceiris/actions-gh-pages@v3
  with:
    github_token: ${{ secrets.GITHUB_TOKEN }}
    publish_dir: ./public
```

这个步骤负责部署：

- `peaceiris/actions-gh-pages@v3` 是用于发布 GitHub Pages 的第三方 action。
- `github_token: ${{ secrets.GITHUB_TOKEN }}` 使用 GitHub 自动注入的临时 token，不需要手动创建 secret。
- `publish_dir: ./public` 表示要发布的目录是构建生成的 `public`。

## 维护建议

- 如果 GitHub Actions 提示某个 action 版本 deprecated，优先升级到当前主版本，例如 `actions/checkout@v4`、`actions/setup-node@v4`。
- 如果要升级 Node.js，例如从 22 升到 24，只需要改 `node-version`，但应先确认 `npm ci` 和 `npm run build` 在新版本 Node.js 下都能通过。
- 不建议依赖 `ubuntu-latest` 里预装的 Node.js 版本；继续使用 `setup-node` 固定版本更稳定。
- 如果希望 runner 环境完全固定，可以把 `ubuntu-latest` 改成明确版本，例如 `ubuntu-24.04`。
