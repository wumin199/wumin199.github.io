---
title: WSL与Windows Docker
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

WSL用于在windows下跑ubuntu，Windows Docker用来跑Windows/Linux based Container

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
- 最新是wsl2
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


### 使用教程

现在最新的是wsl2，安装参考：[如何使用 WSL 在 Windows 上安装 Linux](https://learn.microsoft.com/zh-cn/windows/wsl/install)

以Win10为，用**管理器权限**打开 Powershell or Windows Terminal

```bash

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

```bash
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

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/wsl-gui.png" alt="" style="width:100%;">
</div>

```bash

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
- [WSL 的基本命令](https://learn.microsoft.com/zh-cn/windows/wsl/basic-commands)

```bash

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

# 同时登录Debian和Ubuntu
# 开Windows Terminal并登录即可
```


**挂载宿主机磁盘**

随着wsl和windows的升级，相关方法也可能升级

概念：磁盘和分区

磁盘是物理磁盘，如2块硬盘(SSD，机械硬盘)
分区：一块SSD分区为C盘，一块机械硬盘分区为D盘和E盘

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/disk_partition.png" alt="" style="width:100%;">
</div>

系统默认挂载了所有的磁盘和分区

```bash
# 查看系统当前(默认)挂载了哪些磁盘
wsl ls /mnt

# 在Unbuntu/Debian中可以
cd /mnt
```

详细研究参考：

- [在 WSL 2 中装载 Linux 磁盘](https://learn.microsoft.com/zh-cn/windows/wsl/wsl2-mount-disk)
- [在 Windows 和 WSL 2 中访问 Linux 文件系统](https://linux.cn/article-12608-1.html)


**VSCode**

通过"Remote Expore"，选择WSL Target进去即可。

进去以后，根据需要在wsl中装对应的插件。

vscode中切换目录可以通过vscode的 File -> Open Folder来切换vscode当前的显示目录


## Windows Docker

目标环境：

1. python 3.12
2. .NET 8 (C#12 + WPF), MSBuild+NuGet做构建和依赖管理
3. C++17 + CMake + vcpkg + msvc2022


### 笔记

- Docker 并非是一个通用的容器工具，它依赖于已存在并运行的 Linux 内核环境
- 因此，Docker 必须部署在 Linux 内核的系统上。如果其他系统想部署 Docker 就必须安装一个虚拟 Linux 环境。
  
  Windows下的虚拟环境包括

  - WSL2
  - virtualbox or VMWare
  - Hyper-V

- windows container on windows 可以编译带GUI的api，但是跑不起来GUI
  - 这一点和Linux不一样，Linux可以通过.x11跑起来GUI
    - [Using Windows Containers to "Containerize" Existing Applications](https://learn.microsoft.com/en-us/virtualization/windowscontainers/quick-start/lift-shift-to-containers)
    - [使用 Windows 容器“容器化”现有应用程序](https://learn.microsoft.com/zh-cn/virtualization/windowscontainers/quick-start/lift-shift-to-containers)
    - [Is it possible to containerize a Windows GUI application on a Windows host?](https://stackoverflow.com/questions/54292215/is-it-possible-to-containerize-a-windows-gui-application-on-a-windows-host)

    其他参考：

    - [Run GUI app in linux docker container on windows host](https://dev.to/darksmile92/run-gui-app-in-linux-docker-container-on-windows-host-4kde)
    - [把 Windows 装进 Docker 容器里](https://blog.csdn.net/soulteary/article/details/136620602)


### Windows Docker 安装

参考：

- [Install Docker Desktop on Windows](https://docs.docker.com/desktop/setup/install/windows-install/)


分为Linux容器模式和Windows容器模式

Linux容器模式：docker中跑的是linux的image。相当于是在Windows上开发Linux的应用程序

Windows容器模式：docker中跑的是windows的image（[Container Base Images](https://learn.microsoft.com/en-us/virtualization/windowscontainers/manage-containers/container-base-images))，相当于是在Windows上开发Windows的应用程序

使用Windows container条件：Win10 or Win11的Professional or Enterprise edition版本
使用Linux container条件：Windows Home or Education editions only allow you to run Linux containers


- Windows容器模式：HyperV
  - 一旦启用，QEMU、VirtualBox 或 VMWare Workstation 15 及以下版本将无法使用！如果你必须在电脑上使用其他虚拟机（例如开发 Android 应用必须使用的模拟器），请不要使用 Hyper-V （但测试后发现可以使用Virtualbox，可能是因为Windows新版本进行了升级？）
  - 但如果你是要在windows的docker下跑windows的image，则需要用到Hyper-V
- Linux容器模式：WSL2

Containers and images created with Docker Desktop are shared between all user accounts on machines where it is installed. This is because all Windows accounts use the same VM to build and run containers. Note that it is not possible to share containers and images between user accounts when using the Docker Desktop WSL 2 backend.

---

使用指令安装步骤：

- 下载 [Docker Desktop for Windows - x86_64](https://docs.docker.com/desktop/release-notes/)
- 准备脚本 `install_windows_docker.ps1`
  
  ```powershell
    # 定义Docker的文件夹路径
    # 之后docker pull的镜像也是放在这里的
    $docker_dir = "D:/windows-docker"

    # 创建目录，-Force 参数用于确保无论如何都会创建该目录
    mkdir $docker_dir/overlay-ports -Force


    # Enable HyperV
    # Windows docker engine needs this one!
    Enable-WindowsOptionalFeature -Online -FeatureName $("Microsoft-Hyper-V", "Containers") -All

    # 启动 Docker Desktop 安装程序
    Start-Process 'Docker Desktop Installer.exe' -Wait -ArgumentList @(
        "install",
        "--quiet",
        "--accept-license",
        "--installation-dir=$docker_dir",
        "--backend=hyper-v"
    )

    # 以下设置有问题，必须启动Docker Desktop才行
    # default settings
    # invalid settings, must open and log in Docker Desktop first
    # $docker_cli_path = "$docker_dir/DockerCli.exe"
    # option: -SwitchLinuxEngine
    # Start-Process $docker_cli_path -ArgumentList -SwitchWindowsEngine
  ```

- 将 `install_windows_docker.ps1` 放在和 `Docker Desktop Installer.exe`一个目录下
- **管理员权限**打开powershell or Windows Terminal，执行
  
  ```powershell
    # step1: 
    Set-ExecutionPolicy Bypass -Scope Process -Force

    # step2: install docker for desktop
    ./install_windows_docker.ps1

    #
    # step3: switch to windows docker mode
    #
    # set in install_windows_docker.ps1
    # D:\windows-docker\DockerCli.exe --help
    # & D:\windows-docker\DockerCli.exe -SwitchWindowsEngine
    # & D:\windows-docker\DockerCli.exe -SwitchLinuxEngine
  ```

- 测试是否安装成功
  
  ```powershell
    docker --version
    docker version
    docker images
  ```

- 卸载
  
  ```powershell
  docker rmi mcr.microsoft.com/windows/servercore:ltsc2019
  # 退出Docker Desktop
  # Windows控制面板下卸载Docker Desktop
  ```

### 基础windows镜像

参考：

- [容器基础映像](https://learn.microsoft.com/zh-cn/virtualization/windowscontainers/manage-containers/container-base-images), [Container Base Images](https://learn.microsoft.com/en-us/virtualization/windowscontainers/manage-containers/container-base-images)
- [dockerhub-dotnet](https://hub.docker.com/r/microsoft/dotnet)


Microsoft 提供多个映像（称为基础映像），你可以从其着手构建自己的容器映像：

- Windows - 包含整套 Windows API 和系统服务（服务器角色除外）。
  - 3.4 GB
- Windows Server - 包含整套 Windows API 和系统服务。
  - 3.1 GB
- Windows Server Core - 一个较小的映像，包含部分 Windows Server API - 即完整的 .NET Framework。 它还包括大多数（但不是所有）服务器角色，例如不包含传真服务器。
- Nano Server - 最小的 Windows Server 映像，包括支持 .NET Core API 和某些服务器角色

大小：Windows(3.4 GB) > Windows Server(3.1 GB) > Windows Server Core()


如前所述，容器映像由一系列层组成。 每个层包含一组文件，这些文件重叠在一起时表示容器映像。 由于容器的分层特性，你不需始终以某个基础映像为目标来构建 Windows 容器， 而是可以以另一个已经携带你所需框架的映像为目标。 例如，.NET 团队发布了一个携带 .NET Core 运行时的 .NET Core 映像。 有了它，用户就不需重复 .NET Core 安装过程，只需重复使用该容器映像的层即可。 .NET Core 映像本身在 Nano Server 基础上构建。

Windows Server 映像 (3.1 GB) 的大小略小于 Windows 映像 (3.4 GB)。 Windows Server 映像还继承了 Server Core 映像的所有性能和可靠性改进，支持 GPU，并且没有 IIS 连接限制。 要使用最新的 Windows Server 映像，需要安装 Windows Server 2022。 Windows Server 2022 不支持 Windows 映像。


@X@Y@Z中使用的是：

```docker
FROM mcr.microsoft.com/windows/servercore:ltsc2019
```



**Quick Start**



``` powershell

# 不需要管理员权限

docker images
docker pull mcr.microsoft.com/windows/servercore:ltsc2019   # xyz中使用的版本
# docker rmi mcr.microsoft.com/windows/servercore:ltsc2019


docker run --help

# docker run -it mcr.microsoft.com/windows/servercore:ltsc2019 powershell   # 启动较慢
# docker run -it mcr.microsoft.com/windows/servercore:ltsc2019 cmd.exe  # 启动较慢
docker run -it --name wm_w10 -v D:\docker_shared:C:\Users\ContainerUser\ws --workdir C:\Users\ContainerUser mcr.microsoft.com/windows/servercore:ltsc2019  powershell


# 打印当前目录和文件夹
dir
ls
# cd home
cd ~ 
# 显示网络配置信息
ipconfig
whoami

exit

docker ps -a
docker start xxx    # 启动较慢
docker attach xxx

# attach在容器启动命令的终端，不会启动新的进程。
# exec则是在容器中打开新的终端，并且可以启动新的进程。
# 如果想直接在终端中查看启动命令的输出，用attach；其他情况使用exec

```

测试中发现：

1. 用普通权限的PowerShell执行 docker run -it 效果比PowerShell ISE好，PowerShell的ISE可能会乱码。当然用Windows Terminal效果最好。
2. Windows Docker启动较慢：`docker run; docker start`




### .NET8 安装

参考：

- [在 Windows 上安装 .NET](https://learn.microsoft.com/zh-cn/dotnet/core/install/windows?WT.mc_id=dotnet-35129-website#install-with-windows-package-manager-winget)


与 .NET Framework 不同的是，.NET 与 Windows 版本无关。 只能在 Windows 上安装单个版本的 .NET Framework。 但 .NET 是独立的，可以安装在计算机的任何地方。 有些应用可能包含自己的 .NET 副本。

默认情况下，.NET 安装在计算机上的 Program Files\dotnet 目录中，除非安装方法选择了不同的目录。

.NET 由运行时和 SDK 组成。 运行时用于运行 .NET 应用，而 SDK 则用于创建应用。




### C++环境安装

- MSVC，2022
- c++17
- CMake


### python 安装



## All In One 安装

- 直接按照VS2022
- 每部都是最少安装


选择安装开关：支持安装

1. Visual Studio
2. Winget/Chocolatey
3. vcpkg python/ system python