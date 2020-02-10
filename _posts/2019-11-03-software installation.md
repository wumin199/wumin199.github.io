---
layout: post
title: 软件安装说明
categories: 其他
description: 
keywords: 软件安装
---



**目录**


* [Windows安装汇总](#windows安装汇总)
    * [OpenCV3安装](#opencv3安装)
* [Ubuntu安装汇总](#ubuntu安装汇总)
* [参考文献](#参考文献)





## Windows安装汇总

安装环境：win10，64位

|软件|软件下载地址|基本配置要点|其他说明|
|--|--|--|--|
|git|[Git For Windows下载地址](https://www.git-scm.com/download/win)|`git config --global  user.name 'wumin199'`<br>`git config --global  user.email 'wumin199@126.com'`|[git第一节----git config配置](https://www.cnblogs.com/kkz-org/p/9312035.html)|
|matlab 2018a|[matlab 2018a下载地址](https://pan.baidu.com/s/1iSSJYEyZWvuw0AS3uiz9Rw), 提取码：yrzm |关闭杀毒软件（含360）|安装说明见安装包|
|qtcreator|[下载地址](https://www.qt.io/)|直接安装|1. 可以用来当个轻量级C++的IDE|
|python3|[python3下载](https://www.python.org/downloads/)|1. 可以在安装visual studio community的时候，直接安装python，那样python的可执行程序一般在`...\Microsoft Visual Studio\Shared\Python37_64\python`中<br>2. 如果不安装visual studio，则可以直接安装Python下载包即可。<br>3. 添加python环境变量，这样powershell就可以用了<br>![](/images/软件安装/环境变量.PNG)|1. 在vs code中执行python程序时，要保证Terminal路径在当前脚本路径下，否则有可能会执行出错|
|visual studio Community 2019|[VS下载地址](https://visualstudio.microsoft.com/zh-hans/?rr=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DWSZGEpjqOZGj9oYQdHkpgfYZFptSsBgFhOAQZcjDP9uYWxVjAUof94ch1rnqEkQi%26wd%3D%26eqid%3Dd60b7d54000cfd96000000045dbe4d46)|![](/images/软件安装/python环境变量.PNG)|同时也把python3安装了|
|OpenCV3|[OpenCV3 下载地址](https://opencv.org/releases/)||这个是C++库|
|opencv-python||`python -m pip install --user opencv-python`<br>同时因为经常要绘图和用到数组，可以同步安装如下两个库<br>`python -m pip install --user numpy` <br> `python -m pip install --user matplotlib`|下载的是最新版的opencv，比如4.1.1.26<br>查看python库版本的方法<br>`python -m pip list`(所有库)<br>`python -m pip | grep opencv`(查看单一库，grep是linux下指令)<br>`python -m pip | findstr opencv`(查看单一库，findstr是windows下指令)<br>在python中，用法都是`import cv2`(不代表是opencv2)|
|intel RealSense SDK2|[Intel® RealSense™ SDK 2.0](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_windows.md)||直接安装|
|导入虚拟机||问题1：导入后，报虚拟机不支持USB设备<br>方法1：![](/images/软件安装/虚拟机设置.PNG)<br>方法2:安装[VM VirtualBox Extension Pack](https://www.virtualbox.org/wiki/Downloads),但公司电脑可能不给安装权限，家里电脑可以。<br>-----<br>问题2：启动导入的虚拟机后，提示"VT-x is not available"<br>方法：[VirtualBox VT-x is not available ](https://blog.csdn.net/imilano/article/details/83038682)和 [Win10怎么以管理员身份运行CMD命令提示符](https://jingyan.baidu.com/article/ceb9fb10b53ab88cac2ba05b.html)||
|V3.16a/V3.16b||1.虚拟机不需要自己安装！默认会帮你安装<br>2.生成simulation的时候，可能会报虚拟化相关错误，需要进行如下设置：<br>![](/images/软件安装/KeMotion-HyperV.PNG)<br>具体看help文档:右键右下角的`simulation service systray`后点`help`|账号:Administrator<br>密码:pass|
|V3.18||V3.18支持C5，安装完后需要license，否则只能试用30天||
|freeCAD|[freeCAD](https://www.freecadweb.org/)|直接安装|1. 免费<br>2. 支持常见的3D格式，如`dae`,`stp`,`step`等<br>stl不能打开，得用3D Max或者Pro/E或者SolidWorks或者Win10自带的画图3D软件打开。STL是最多快速原型系统所应用的标准文件类型。STL是用三角网格来表现3D CAD模型|
|PDF Acrobat|[百度网盘](https://pan.baidu.com/s/1JyvlfKGKE_ryiY0P1dfOAw), 提取码：3jpe||1. 版本：PDF 2019<br>2. 支持加密、PDF各类编辑等操作|
|录屏破解软件：APowersoft Screen Record Pro|[百度网盘](https://pan.baidu.com/s/1GV9YolS_4Jyw2sIj8q3rUw), 提取码：ka9j|![](/images/软件安装/录屏软件设置.PNG)<br>该值如果设置比较大，则录屏会比较高清，但是视频就会比较大|1. 支持录屏，录音及视频的简单剪辑|
|录屏软件：KKSetup|[百度网盘](https://pan.baidu.com/s/1cy1QyNkH7IwUuaj9pwO97A),提取码：fyn0|-|1. 适用于需要装正版的电脑|
|截图软件HyperSnap|[百度网盘](https://pan.baidu.com/s/1aTrF0KFn-d7SaohIRAeeEA), 提取码：6zad|直接解压到C盘program file目录下，然后把可执行文件设置成快捷方式即可|1. 支持各类截图及截图后的图片备注编辑等功能<br> 2. 在线拾色器功能|
|XMind思维导图|[ZEN破解版](https://pan.baidu.com/s/1xTKj_GXpNkrwdmjiNP3zKw), 提取码：5axy |1. 绿色软件，直接解压后点击`XMind ZEN`即可使用，有弹窗提示更新则不要进行更新<br>2. 可以解压后放到C盘下，或者`C:\\Program Files`下，然后发送快捷键到桌面。<br>3. 如果解压到`C:\\Program Files (x86)`，打开软件会找不到模板，打开其他xmind文件会崩溃|1. 支持导入图片<br>2. 导出图片无水印等|
|pdf加密软件|[下载地址](https://pan.baidu.com/s/1rf5AbBrAr_cjJ0s18JYfEg)||1. 店家说win7和win10都测试过<br>2. 可以一机一码|




### OpenCV3安装



- 安装要点

1. 确保软件下载完全，否则安装时会出现如下错误：

![](/images/软件安装/opencv安装.PNG)

![](/images/软件安装/opencv安装2.PNG)

(下载不完全后失败，软件也显示exe，也可以点开进行安装，但是会报错)

![](/images/软件安装/opencv安装3.PNG)

(比如完整版位209M，但是上一张图片却只有144M)

参考[OpenCV 第一课(安装与配置)](https://www.cnblogs.com/YiXiaoZhou/p/5901681.html)中的说明即可，环境配置啥的，不用参考这里。


2. 一定要以管理员身份安装，否则安装过程中会报错

3. 解压到某个地方（比如`C:\Program Files`)，会自动新增opencv文件夹，不需要自己事先建一个opencv文件夹

4. 环境变量配置，参考[opencv3.4的安装（VS2017及win10）](https://blog.csdn.net/qq_34463441/article/details/82669628)

这一点需要说明的有：visual studio中的工程，如果电脑是64位的，那么就不要用x86运行，要选择X64，否则程序会报错；其次是debug和release都可以且推荐都配置好；另外`opencv_world340d.lib`和`opencv_world340.lib`是根据opencv的版本来的，如果是3.4.7，则以上数字为`xxx347d.lib`和`xxxx347.lib`

5. 说明，在添加环境变量的时候，可以添加到系统中，也可以添加到用户中。由于keba的电脑，添加到系统后，除非用管理员权限运行，否则不会执行系统参数，所以建议环境变量在系统的Path和用户的Path中都添加。

![](/images/软件安装/环境变量.PNG)


6. opencv-python 的安装方法见上方！ 同时要记住，在用vs code执行python程序的时候，一定要保证Terminal是在当前脚本文件夹下~

同时可以vs code下的opencv常见问题：[解决vscode报错: Module 'cv2' has no 'imshow' member](https://blog.csdn.net/qq_23676873/article/details/88537420)


## Ubuntu安装汇总

安装环境：ubuntu18.04

### 录屏软件

`sudo apt-get install kazam`

`sudo apt-get install mplayer`

`sudo apt-get install imagemagick`

参考文献：[Ubuntu16.0.4 kazam 的安装和使用](https://blog.csdn.net/uniqueyyc/article/details/81210243)


### 视频播放软件

`sudo apt-get install totem`

## 参考文献




