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

## 目标

Windows平台下：

1. .NET8, WPF，NuGet
   1. .NET 8 (C#12 + WPF), MSBuild+NuGet做构建和依赖管理
2. Python及基础库（vcpkg的python、system python、conda的python）
   1. python 3.12
   2. pyside
   3. pyqt
3. C++ 17, vcpkg， msvc2022， CMake
   1. qt
4. Visual Studio
5. Visual Studio Code
6. Docker
7. Winget/Chocolatey

- 直接按照VS2022
- 每部都是最少安装


选择安装开关：支持安装

1. Visual Studio
2. Winget/Chocolatey
3. vcpkg python/ system python




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
    docker info
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


### 其他

WinGet 命令行工具仅在 Windows 10 1709（版本 16299）或更高版本上受支持。

WinGet the Windows Package Manager is available on Windows 11, modern versions of Windows 10, and Windows Server 2025 as a part of the **App Installer**. The App Installer is a System Component delivered and updated by the Microsoft store on Windows Desktop versions, and via Updates on Windows Server 2025.



## VirtualBox

### 下载Win10/Win11镜像

镜像：

- [Windows镜像](https://www.microsoft.com/zh-cn/software-download)
- [Windows Image](https://www.microsoft.com/en-us/software-download/windows10ISO)
- [iTellYou提供的Windows各版本镜像](https://next.itellyou.cn/)
- [ROS-Industrial Training Images](https://rosi-images.d10.us.swri.org/)(需要用Firefox下载)


<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/win11_download.png" alt="" style="width:100%;">
</div>

下载后的原始镜像保存到了百度网盘： `通用软件/images`。这里面还放了不断优化的现成的win10和win11的virtualbox虚拟机。

百度网盘：通用软件 -> win10_x64_wm199_20250126.ova: ubuntu下的定制化过的win10虚拟机
百度网盘：通用软件 -> win11_x64_wm199_20250126.ova: ubuntu下的定制化过的win10虚拟机
百度网盘：通用软件 -> rosi_training_foxy_latest.ova: Foxy (ROS2) + Noetic (ROS1)
百度网盘：通用软件 -> rosi_training_humble_latest.G0PVZOAY.ova: Humble Hawksbill (ROS2)
百度网盘：通用软件 -> win10_64_ubuntu_basic_20250130.ova: ubuntu下的基础的win10，没有进行过任何设置
百度网盘：通用软件 -> win11_64_ubuntu_basic_20250130.ova: ubuntu下的基础的win11，没有进行过任何设置

### 用VirtualBox制作虚拟机

下载VirtualBox和Extension Pack并安装： [virtual box](https://www.virtualbox.org/wiki/Downloads)


视频教程：

Ubuntu下安装Win10： 百度网盘：通用软件 -> win10_ubuntu_20250130.mkv
Ubuntu下安装Win11： 百度网盘：通用软件 -> win11_ubuntu_20250130.mkv


要点：

- Win10，不要启用 “显示” -> 硬件加速“启用3D加速”
- Win11下，不同平台下可能有点不一样
  - Ubuntu下，如果启动不起来，则需要选择：“系统” -> 芯片组：ICH9
  - EFI，有些说要勾选，有些说不用
- 作为开发的话建议用专业版，一般用家庭版即可
  - 但是我这里给出的都是家庭版

主要参考：

- [VirtualBox 安装 Windows10 22H2 教程](https://icxzl.com/4449.html)，[pdf版本](https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/VirtualBox%20%E5%AE%89%E8%A3%85%20Windows10%2022H2%20%E6%95%99%E7%A8%8B.pdf)
- [VirtualBox 安装 win11 虚拟机](https://blog.csdn.net/u010953609/article/details/122430836)
- [VM VirtualBox虚拟机如何安装WIN11](https://zhuanlan.zhihu.com/p/548015254), [pdf版本](https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/VM%20VirtualBox%E8%99%9A%E6%8B%9F%E6%9C%BA%E5%A6%82%E4%BD%95%E5%AE%89%E8%A3%85WIN11.pdf)


基础设置：

1. 显示大小
   Windows桌面 -> 右键 -> 显示设置 -> 缩放设置 设置为 150%

2. 最大化界面
   
   虚拟机中进入到Windows，VirtualBox菜单栏：设备 -> 安装增强选项，然后选中Windows的“此电脑”，和C盘并列的，可以看到**CD驱动器(D:)VirtualBox Guest Addition** -> 运行 VBoxWindowsAdditions -> 重启。

   Ubuntu镜像的操作同理，主要参考：[在 VirtualBox 中设置 Ubuntu 共享文件夹 - Windows 文件共享](https://unclesnote.com/zh/231106120100/seamless_windows_folder_sharing_with_ubuntu_vm_simplify_file_exchange), [pdf](https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/%E5%9C%A8%20VirtualBox%20%E4%B8%AD%E8%AE%BE%E7%BD%AE%20Ubuntu%20%E5%85%B1%E4%BA%AB%E6%96%87%E4%BB%B6%E5%A4%B9%20-%20Windows%20%E6%96%87%E4%BB%B6%E5%85%B1%E4%BA%AB.pdf)

   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/install_addition_1.png" alt="安装上面的视频教程，这一步应该已经有了" style="width:50%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/install_addition_2.png" alt="需要管理员权限，2选一执行" style="width:50%;">
    </div>

   Ubuntu下还需要在terminal中执行:

   ```shell
   $USER
   whoami

   sudo gpasswd -a $USER vboxsf
   ```

3. 共享文件夹
   
   在“最大化界面”设置的基础上，设置如下：

      <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/dual_setting.png" alt="允许拷贝" style="width:33%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/ubuntu_in_windows.png" alt="ubuntu_in_windows" style="width:33%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/windows_in_ubuntu.png" alt="windows_in_ubuntu" style="width:33%;">
      </div>

4. Ubuntu下安装输入法
   
   主要参考：[Ubuntu安装搜狗输入法](https://www.notion.so/wumin199/12d514e8cada807c96a2c4fc60322e42)


## 虚拟机下安装环境

需要安装的有：

- Windows terminal
- WinGet
  - [使用 WinGet 工具安装和管理应用程序](https://learn.microsoft.com/zh-cn/windows/package-manager/winget/)
  - [安装应用安装程序:APP Installer](https://learn.microsoft.com/zh-cn/windows/msix/app-installer/install-update-app-installer)
  - [使用 WinGet 工具安装和管理应用程序](https://learn.microsoft.com/zh-cn/windows/package-manager/winget/)
- Chocolatey


### .NET 8

参考资料：

- [在 Windows 上安装 .NET](https://learn.microsoft.com/zh-cn/dotnet/core/install/windows?WT.mc_id=dotnet-35129-website#install-with-windows-package-manager-winget)
- [下载 .NET](https://dotnet.microsoft.com/zh-cn/download/dotnet)


> 与 .NET Framework 不同的是，.NET 与 Windows 版本无关。 只能在 Windows 上安装单个版本的 .NET Framework。 但 .NET 是独立的，可以安装在计算机的任何地方。 
> 
> 有些应用可能包含自己的 .NET 副本。
> 
> 默认情况下，.NET 安装在计算机上的 Program Files\dotnet 目录中，除非安装方法选择了不同的目录。
> .NET 由运行时和 SDK 组成。 运行时用于运行 .NET 应用，而 SDK 则用于创建应用。
>
> 为了确保能够在 Windows 上运行所有 .NET 应用，请同时安装 ASP.NET Core Runtime 和 .NET Desktop Runtime。 ASP.NET Core Runtime 可运行基于 Web 的应用，而 .NET Desktop Runtime 可运行桌面应用，如 Windows Presentation Foundation (WPF) 或 Windows Forms 应用。



```bash
winget search WindowsTerminal
winget search DotNet
winget search DotNet | Select-String SDK
winget install --id Microsoft.WindowsTerminal -e
# 可以安装多版本
winget install --id  Microsoft.DotNet.SDK.8 -e # 安装8的最新版本
winget install --id  Microsoft.DotNet.SDK.8 -e --version 8.0.405
winget install --id  Microsoft.DotNet.SDK.9 -e

winget list --name WindowsTerminal # or "WindowsTerminal"
winget list --name "SDK"
winget list --name "SDK" | Select-String ".NET"

winget show --id  Microsoft.WindowsTerminal
winget show --id  Microsoft.DotNet.SDK.8
winget show --id  Microsoft.DotNet.SDK.9
dotnet --list-sdks
dotnet --list-runtimes
dotnet --info
where.exe dotnet

winget uninstall --id Microsoft.WindowsTerminal
winget uninstall --id Microsoft.DotNet.SDK.8 -e
winget uninstall --id Microsoft.DotNet.SDK.8 -e --version 8.0.405
winget uninstall --id Microsoft.DotNet.SDK.9 -e

```

### 远程到虚拟机

宿主机：Ubuntu
虚拟机：里面安装win10
目标：宿主机中的vscode远程到虚拟机的win10


参考：

- [virtualbox 网络配置（局域网内任一主机ping通虚拟机）](https://blog.csdn.net/wqgdfkafj/article/details/131763157)(主要参考这篇)
- [VirtualBox用RoboGuide测试fanuc机器人](https://www.notion.so/wumin199/Fanuc-893981e0a3d444f9bf1c6f3c00d423bf?pvs=4#b549441dd182489cbaee8a86708bc731)(同时参考这篇)
- [Get started with OpenSSH for Windows](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh_install_firstuse)(Enable OpenSSH章节中有windows下的防火墙脚本设置，主要是让22端口允许通过)
- [Win10系统VScode远程连接VirtualBox安装的Ubuntu20.04.5](https://blog.csdn.net/five_east_west/article/details/137575488)
- [linux宿主机ssh访问windows10虚拟机](https://blog.csdn.net/qq_38969070/article/details/124150764)

**设置virtualbox网络**

Virtualbox的网络中，需要设置2个网卡：一个用于虚拟机上网，一个用于宿主机ssh到虚拟机。

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/vb_nat.png" alt="用于虚拟机上网" style="width:33%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/vb_bridge.png" alt="用于宿主机ssh到虚拟机" style="width:33%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/wsl.png" alt="桥接到宿主机正在使用的网卡" style="width:33%;">
</div>

**Windows虚拟机中的设置**

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/network_in_win10.png" alt="" style="width:100%;">
</div>

在windows下执行：

```bash
# 查看虚拟机的username，xxxx\username
whoami
# 查看虚拟机的ip地址
ipconfig
```

从而得到：`wumin@192.168.2.18`


除此之外，还需要在windows下开启ssh服务（宿主机是ssh客户端）

手动启动windows的ssh服务参考：[Windows 10 开启ssh服务](https://blog.csdn.net/pariese/article/details/111604340), [适用于 Windows 的 OpenSSH 入门](https://learn.microsoft.com/zh-cn/windows-server/administration/openssh/openssh_install_firstuse)

这部分已经封装成ps1脚本，执行 `WM-TEST-CASE`下的`openssh.ps1` 即可

**宿主机中的设置**

打开vscode，**Ctrl+Shift+P**，先配置远程的ssh地址

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/remote-ssh-setting1.png" alt="" style="width:33%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/remote-ssh-setting2.png" alt="配置文件保存地址，请看下文重要提示！" style="width:33%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/remote-ssh-setting3.png" alt="" style="width:33%;">
</div>

重要提示：

由于ssh默认的端口号是22，因此把配置文件保存在 `~/.ssh/config`中，可能会影响到git的使用。


如果远程连接到虚拟机后，本地git无法push或pull，可能的原因是上面配置的config文件把github的ssh配置给覆盖掉。

```shell
ssh -T git@ssh.github.com
ssh -T -p 443 git@ssh.github.com
```

可以在config文件下添加类似代码

```bash
Host github.com
    Hostname ssh.github.com
    Port 22 # or 443
    User git
```

在虚拟机启动的情况下，远程连接到虚拟机

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/remote-ssh-setting4.png" alt="" style="width:50%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/remote-ssh-setting5.png" alt="" style="width:50%;">
</div>

弹窗中做对应的选择

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/remote-ssh-setting6.png" alt="" style="width:50%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2025/wsl-docker/remote-ssh-setting7.png" alt="" style="width:50%;">
</div>

其中的密码，是宿主机的开机密码。（或者宿主机的登录密码）

首次登录，vscode会在虚拟机中安装vscode server，大概会下载500M的内容，比较久，需要等待。

之后就可以在Vscode的菜单栏中"File" -> "Open Folder"来打开虚拟机中对应的文件夹，然后在vscode的Extension中安装对应的插件并进行开发。