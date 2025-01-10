---
title: Windows WSL与Docker
date: 2025-01-10 09:45:08
tags: 编程
toc: true
comment: false
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

WSL用于在windows下跑ubuntu，Windows Docker用来跑Windows Container

<!-- more -->

更新日期：20250110
测试平台：Win10


## WSL

- [Windows Subsystem for Linux Documentation](https://learn.microsoft.com/en-us/windows/wsl/)
- [适用于 Linux 的 Windows 子系统文档](https://learn.microsoft.com/zh-cn/windows/wsl/)
- [WSL 中 ML 的 GPU 加速入门](https://learn.microsoft.com/zh-cn/windows/wsl/tutorials/gpu-compute)
- [使用 WSL 2 和 Visual Studio 2022 生成和调试 C++](https://learn.microsoft.com/zh-cn/cpp/build/walkthrough-build-debug-wsl2?view=msvc-170)


### 一些笔记

- WSL就是可以让你在Windows下跑Ubuntu、Debian等系统
- WSL支持GUI
- WSL支持GPU加速
- 建议配合Windows Terminal


### Why WSL

使用 WSL（Windows Subsystem for Linux）有许多好处，特别是对于开发者和需要在 Windows 环境中运行 Linux 工具的用户。以下是使用 WSL 的主要原因和优势：

1. 在 Windows 上轻松运行 Linux 工具和命令行

    WSL 允许你在 Windows 操作系统上运行 Linux 的命令行工具、脚本和程序，而无需安装完整的虚拟机或双系统。你可以直接在 Windows 中使用 Linux 的丰富生态系统，例如：

    - Bash shell
    - grep, awk, sed 等命令行工具
    - git、curl、wget 等开发者工具

2. 无需虚拟机，性能更高
   
    与传统的虚拟机（如 VirtualBox、VMware）相比，WSL 的性能更高且资源占用更少，因为它直接利用 Windows 内核运行 Linux，不需要额外的虚拟化层。这意味着：

    - 更快的启动速度
    - 更低的内存和 CPU 消耗
    - 更高的文件系统性能（特别是 WSL2）
  
3. WSL2 提供真正的 Linux 内核
   
    WSL2 使用真正的 Linux 内核（通过轻量级虚拟化技术），与原生 Linux 系统一样支持完整的系统调用。这带来了更好的兼容性和性能，支持运行更复杂的程序，例如：

    - Docker 容器
    - 数据库（如 MySQL、PostgreSQL）
    - 开发框架和工具（如 Node.js、Python、Ruby）

4. 便捷的跨平台开发
   
    如果你需要在 Windows 和 Linux 系统之间切换，WSL 提供了很好的桥梁。你可以在 Windows 中使用你熟悉的工具（如 VS Code、Notepad++），同时利用 Linux 环境的强大功能。这种结合在以下情况下非常有用：

    - Web 开发（例如 Node.js、Ruby on Rails 等需要 Linux 环境的框架）
    - 跨平台应用开发（例如 Windows 上开发，然后部署到 Linux 服务器）
    - 测试和运行 Linux 特定的工具链或脚本

5. 简单的安装和集成

    WSL 的安装和使用非常简单，且与 Windows 系统深度集成：

    - 通过 Windows Store 安装 Linux 发行版（如 Ubuntu、Debian、Fedora 等）
    - 无缝访问 Windows 文件系统（通过 /mnt/c 或直接访问路径）
    - 支持使用 Windows 应用程序直接编辑 Linux 文件，反之亦然


6. 支持 GUI 应用程序

    WSL（尤其是 WSL2）现在支持运行 Linux GUI 应用程序（通过 WSLg）。这意味着你可以在 Windows 上运行 Linux 的图形界面程序，例如：

    - 图形化的 IDE（如 PyCharm）
    - 数据可视化工具
    - 图形化的 Linux 应用程序

7. Docker 和容器开发的支持
    WSL2 对 Docker 和容器开发提供了原生支持，因为它具有完整的 Linux 内核。你可以使用 Docker Desktop for Windows 来管理容器，而 Docker 引擎实际上运行在 WSL2 的 Linux 环境中，这解决了以往在 Windows 上运行 Docker 的兼容性问题。

8. 免费且开源
  
    WSL 是免费的，且内置于 Windows 10 和 Windows 11 中（需通过设置开启）。它是一个开源项目，微软和社区不断更新和优化。



以下是 WSL 的一些典型使用场景：

- Web 开发：在 Windows 中使用 Linux 的开发环境（如 LAMP、Node.js 等）。
- 数据科学：运行 Python 和 R 等语言需要的 Linux 工具。
- 云开发：测试和开发与 Linux 服务器一致的环境。
- 学习 Linux：对于希望在不离开 Windows 的情况下学习 Linux 的用户来说，WSL 是一个理想的选择。

总结

WSL 是一个强大的工具，让开发者可以轻松地在 Windows 上使用 Linux 的强大功能，而无需额外的硬件或复杂的配置。通过 WSL，你可以享受两种世界的最佳体验：Windows 的便利性和 Linux 的灵活性。


### 安装

现在最新的是wsl2，安装参考：[如何使用 WSL 在 Windows 上安装 Linux](https://learn.microsoft.com/zh-cn/windows/wsl/install)

以Win10为，用**管理器权限**打开

```shell

# 查看当前是否装有wsl
wsl -v  # wsl --version

# 如果没有安装，则
wsl --install

# 如果已经安装，则建议升级一下wsl
wsl --version  # wsl -v ，如果没有跳出来版本信息，则需要更新wsl到最新：
wsl --set-default-version 2 # 设置成wsl2
wsl --update # 保持wsl最新,  建议最好执行一下，否则后续在装Docker Desktop的时候会让
wsl -v
wsl --shutdown # 重启
```

**安装Ubuntu**

```shell
wsl --update #
wsl --list --online
wsl --install -d Ubuntu-20.04 
wsl --unregister Ubuntu-22.04 # 卸载并删除
```


**使用GUI**

参考：

- [Run Linux GUI apps on the Windows Subsystem for Linux](https://learn.microsoft.com/en-us/windows/wsl/tutorials/gui-apps)
- [在适用于 Linux 的 Windows 子系统上运行 Linux GUI 应用](https://learn.microsoft.com/zh-cn/windows/wsl/tutorials/gui-apps)

用于 Linux 的 Windows 子系统 (WSL) 现在支持在 Windows 上运行 Linux GUI 应用程序（X11 和 Wayland），提供了完全集成的桌面体验。

WSL 2 使 Linux GUI 应用程序在 Windows 上使用起来原生且自然。

- 从 Windows 的“开始”菜单启动 Linux 应用
- 将 Linux 应用固定到 Windows 任务栏
- 使用 alt-tab 在 Linux 应用和 Windows 应用之间切换
- 跨 Windows 应用和 Linux 应用剪切并粘贴

```shell

# 这个功能对windows版本和驱动有一定要求，请看上面连接

sudo apt update

# 
# 安装 Gnome 文本编辑器
# GNOME 文本编辑器取代 gedit 成为 Ubuntu 22.10 中 GNOME/Ubuntu 的默认文本编辑器。
sudo apt install gnome-text-editor -y
# gnome-text-editor ~/.bashrc

#
# 安装 GIMP
# GIMP 是一种免费的开源光栅图形编辑器，用于图像操作和图像编辑、自由形态绘图、不同图像文件格式之间的转码，以及更专业的任务。
# 启动：gimp
sudo apt install gimp -y

#
# 安装 Nautilus
# Nautilus 也称为 GNOME Files，是 GNOME 桌面的文件管理器。 （类似于 Windows 文件资源管理器）
# 启动：nautilus
sudo apt install nautilus -y

#
# 安装 VLC
# VLC 是一种免费的开源跨平台多媒体播放器和框架，可播放大多数多媒体文件
# 启动：vlc
sudo apt install vlc -y


#
# 安装 X11 应用
# X11 是 Linux 窗口管理系统，这是随它一起提供的各种应用和工具的集合，例如 xclock、xcalc 计算器、用于剪切和粘贴的 xclipboard、用于事件测试的 xev 等
# 启动：xcalc、xclock、xeyes
sudo apt install x11-apps -y

#
# Google Chrome
# 启动：google-chrome 
cd /tmp
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo apt install --fix-missing ./google-chrome-stable_current_amd64.deb
# *--fix-missing 选项用于修复安装过程中可能出现的缺少依赖项
```


**常见指令**

- [Install Debian](https://wiki.debian.org/InstallingDebianOn/Microsoft/Windows/SubsystemForLinux)

```shell

wsl --update # 更新wsl的同时，更新各种在线的Linux版本供后面的install
wls --list # 查看已经安装的Linux系统
wsl --list --online  # wsl -l -o # 查看当前支持的子系统，最新的可以从MicroSoft Store中下载
wsl -l -v # 查看当前正在跑的wsl

wsl --install -d Ubuntu-20.04
# wsl --install -d Debian
wsl --unregister Ubuntu-22.04 # 卸载并删除

wsl --shutdown  # 关闭所有正在运行的，如果只想关闭一个，则 wsl --shutdown -d Ubuntu-22.04
wsl -d  Ubuntu-22.04 # 运行，也可以在开始菜单点击运行(Ubuntu22.04)
# wsl -d Ubuntu-22.04 -u <username>

wsl pwd # 查看当前宿主机挂载到wsl中的目录



# 同时登录Debian和Ubuntu
# 开Windows Terminal并登录即可
```

### 开发要点

1. 建议配合Windows Terminal，比Powershell更好用
2. vscode
3. 共享目录
4. 


## Windows Docker

### 笔记

