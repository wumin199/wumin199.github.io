---
layout: post
title: 软件安装说明
categories: 其他
description: 软件安装说明
keywords: 软件安装
---


![](/images/posts/android/toast.png)



**目录**


* [安装汇总](#安装汇总)
    * [OpenCV3安装](#OpenCV3安装)
* [参考文献](#参考文献)





## 安装汇总

安装环境：win10，64位

|软件|软件下载地址|基本配置要点|其他说明|
|--|--|--|--|
|git|[Git For Windows下载地址](https://www.git-scm.com/download/win)|`git config --global  user.name 'wumin199'`<br>`git config --global  user.email 'wumin199@126.com'`|[git第一节----git config配置](https://www.cnblogs.com/kkz-org/p/9312035.html)|
|matlab 2018a|链接<br>https://pan.baidu.com/s/1iSSJYEyZWvuw0AS3uiz9Rw<br>提取码：yrzm |关闭杀毒软件（含360）|安装说明见安装包|
|visual studio Community 2019|[VS下载地址](https://visualstudio.microsoft.com/zh-hans/?rr=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DWSZGEpjqOZGj9oYQdHkpgfYZFptSsBgFhOAQZcjDP9uYWxVjAUof94ch1rnqEkQi%26wd%3D%26eqid%3Dd60b7d54000cfd96000000045dbe4d46)|![](/images/软件安装/python环境变量.PNG)|同时也把python3安装了|
|OpenCV3|[OpenCV3 下载地址](https://opencv.org/releases/)|||
|intel RealSense SDK2|[Intel® RealSense™ SDK 2.0](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_windows.md)||直接安装|



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

这一点需要说明的有：visual studio中的工程，如果电脑是64位的，那么就不要用x86运行；其次是debug和release都可以且推荐都配置好；另外`opencv_world340d.lib`和`opencv_world340.lib`是根据opencv的版本来的，如果是3.4.7，则以上数字为`xxx347d.lib`和`xxxx347.lib`




## 参考文献




