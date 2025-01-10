---
title: Hello World
toc: true
date: 2023-03-07 12:49:29
# cover: /gallery/covers/cover.jpg
# thumbnail: /gallery/thumbnails/thumbnail.jpg
# excerpt: 可以在这里写摘要，也可以用
# password: hello
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

十分钟入门Github Page

<!-- more -->

## hexo Start

```bash
# 确认并安装npm
npm -v
# 如果不存在，则安装npm
# https://nodejs.org/en/download/


#
# 安装project dependencies
#
cd  wumin199.github.io/
npm install # 安装packages.json中的所有依赖， install all the dependencies in your project



#
# 安装npm包举例：
#

cd  wumin199.github.io/
# Set-ExecutionPolicy Bypass -Scope Process -Force # 有些Windows电脑powershell没有权限，则需要执行这句
npm install hexo-cli  # 安装到执行终端的目录
# npm install -g hexo-cli  # -g 表示将 hexo-cli安装在全局目录
hexo -v

```

说明：

1. 在windows下按照nodejs，如果不是安卓在C盘下，执行 `npm -v`的时候会报错：

    ```powershell
    PS C:\Users\wumin> npm -v
    npm : 无法加载文件 D:\installed\nodejs\npm.ps1，因为在此系统上禁止运行脚本。有关详细信息，请参阅 https:/go.microsoft.co
    m/fwlink/?LinkID=135170 中的 about_Execution_Policies。
    所在位置 行:1 字符: 1
    + npm -v
    + ~~~
        + CategoryInfo          : SecurityError: (:) []，PSSecurityException
        + FullyQualifiedErrorId : UnauthorizedAccess
    ```

    解决方法是在powershell中执行： `Set-ExecutionPolicy Bypass -Scope Process -Force`

    不过后续也会有其他问题，比如 npm install 会安装到其他目录中，而不是项目文件夹下

2. Windows下可以通过程序与管理，卸载Nodejs


```bash
# 
#
hexo clean
hexo g  # 重新generate
hexo s  # 启动server
# windows下如果启动不起来，则：
# 1. 用Edge浏览器
# 2. 防火墙中允许npm: 控制面板 -> “系统和安全” > “Windows 防火墙” -> “允许应用通过 Windows 防火墙” -> “更改设置” -> “允许其他应用...” -> “添加” -> "C:\Program Files\nodejs\node.exe" -> 确保“公用”和“专用”两个复选框都被选中 -> “确定” -> 再用Edge浏览器打开

# 
# 新建post
hexo new "my_new_pose"

# add and push
git add .
git commit -am ""
git push

```

有时候需要切换端口

```bash
hexo server -p 5612
# 或者
hexo s -p 5612
```

有时候打不开网页，可以尝试如下方法：
- 切换到Edge浏览器或者Firefox浏览器
- ``https://localhost:5612/`` -> ``http://localhost:5612/``


## Page说明

1. 图片是放到 `wm-blog-image` 这个repo的main分支下

## 选择Hexo

* [X] 选择Jeklly或者Hexo -> Hexo胜出

  * jeklly在docker中安装老是出问题


```bash
# Node.js® is an open-source, cross-platform JavaScript runtime environment.
# npm全称Node Package Manager，是node的包管理工具，是用JavaScript写出来的工具 ，被内置进了node中，是随同NodeJS一起安装的包管理和分发工具，它可以很方便的让前端开发者下载/安装/上传以及管理已经安装的包

###############################################
# docker中install Node.js v18.x:
###############################################

curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash - && sudo apt-get install -y nodejs
node -v
npm -v

# uninstall nodejs
apt-get purge nodejs && rm -r /etc/apt/sources.list.d/nodesource.list

###############################################
# install Hexo
###############################################
npm install -g hexo-cli
echo 'PATH="$PATH:/usr/lib/node_modules/hexo-cli/node_modules/.bin"' >> ~/.profile
source ~/.profile
```

```bash
# [首次执行]cd到blog的root目录下，初始化文件夹
# 只需要第一次执行即可
mkdir website
cd website
hexo init [folder]  # hexo init .

# [不推荐，有bug]安装thems: icarus
# 这个方法运行起来有问题，原因是node版本过高
# https://zhuanlan.zhihu.com/p/410382603
cd website/themes
mkdir icarus
cd icarus
git clone https://github.com/ppoffice/hexo-theme-icarus.git --depth 1

# [推荐][首次执行]安装themes: icarus
# 安装完以后，hexo-theme-icarus和hexo-renderer-inferno被安装到 node_modules/下
cd website # root of this repo
npm install -S hexo-theme-icarus hexo-renderer-inferno
npm install --save hexo-blog-encrypt  # 用于文章加密
updatedb
npm list

# [首次执行]修改配置
_config.yml -> theme: icarus

# 生成新post
# 删除的话，直接删除source/xx.md 后重新 generate
hexo new "post title"
hexo new page --path about/me "About me"  # source/about/me.md

# 生成静态文件
# hexo clean
hexo g

# 启动本地server
hexo server  # http://localhost:4000/

# [首次执行] git处理
cd website
git init 
git add .
git commit -am "update"
git remote add origin git@github.com:wumin199/wumin199.github.io.git
git branch -M main
git push -u origin main

# [首次执行] 配置 github workflows
# 添加.github/workflows/pages.yml  -> see: https://hexo.io/zh-cn/docs/github-pages.html
# pages.yml 在本docker中可以用下方的
# 当部署作业完成后，产生的页面会放在储存库中的 gh-pages 分支

# 在储存库中前往 Settings > Pages > Source，并将 branch 改为 gh-pages

# 之后在master下进行push
git add .
git commit -am ""
hexo g
hexo s # 本地测试好以后
git push  # master下，此时gh-pages也会被push到remote

```

自动部署： `.github/workflows/pages.yml`

```yaml
name: Pages

on:
  push:
    branches:
      - main # default branch

jobs:
  pages:
    runs-on: ubuntu-latest
    permissions:
      contents: write
    steps:
      - uses: actions/checkout@v2
      - name: Use Node.js 18.x
        uses: actions/setup-node@v2
        with:
          node-version: "18"
      - name: Cache NPM dependencies
        uses: actions/cache@v2
        with:
          path: node_modules
          key: ${{ runner.OS }}-npm-cache
          restore-keys: |
            ${{ runner.OS }}-npm-cache
      - name: Install Dependencies
        run: npm install
      - name: Build
        run: npm run build
      - name: Deploy
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: ./public
```

## 参考资料

* [Hexo中文文档](https://hexo.io/zh-cn/docs/) | [Hexo Themes](https://hexo.io/themes/)
* [Hexo指令](https://hexo.io/zh-cn/docs/commands)
* [nodejs install](https://nodejs.org/en/download/package-manager/) | [NodeSource Node.js Binary Distributions](https://github.com/nodesource/distributions/blob/master/README.md)
* [hexo-theme-icarus](https://github.com/ppoffice/hexo-theme-icarus) | [icarus快速上手](https://ppoffice.github.io/hexo-theme-icarus/uncategorized/icarus%E5%BF%AB%E9%80%9F%E4%B8%8A%E6%89%8B/#install-npm) | [Icarus用户指南 - 主题配置](https://ppoffice.github.io/hexo-theme-icarus/Configuration/icarus%E7%94%A8%E6%88%B7%E6%8C%87%E5%8D%97-%E4%B8%BB%E9%A2%98%E9%85%8D%E7%BD%AE/) | [在 GitHub Pages 上部署 Hexo](https://hexo.io/zh-cn/docs/github-pages.html)
* [文章加密](https://github.com/D0n9X1n/hexo-blog-encrypt)