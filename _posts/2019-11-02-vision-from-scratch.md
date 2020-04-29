---
layout: post
title: 机器视觉入门
categories: 机器视觉
description: 及NEDO项目介绍
keywords: 机器视觉
---


**目录**


* [综述](#综述)
* [机器人学、机器视觉与控制--MATLAB算法基础](#机器人学、机器视觉与控制--MATLAB算法基础)
* [透视投影](#透视投影)
* [OpenCV](#openCV)
  * [OpenCV数据结构](#opencv数据结构)
  * [常见处理](#常见处理)
  * [opencv-python](#opencv-python)
* [相机介绍](#相机介绍)
* [realSense](#realSense)
    * [SDK2安装](#sdk2安装)
    * [相机标定](#相机标定)
    * [RA605_316工程讲解](#ra605_316工程讲解)
* [参考文献](#参考文献)




## 综述

1. 初步理解篇

| 课程| 介绍 | 说明 |
| -- | ---| ---|
| [MATLAB图像处理案例](https://edu.51cto.com/course/16757.html) | 通俗易懂，快速了解 ||


2. 基础理论篇

| 课程 | 介绍 | 说明 |
| --- | --- | --- |
| 书籍：[机器人学、机器视觉与控制--MATLAB算法基础](https://book.douban.com/subject/26915869/) | 基本理论介绍 | [源代码](http://petercorke.com/wordpress/rvc/) |
| 网易云课堂： [Python+OpenCV图像处理](https://study.163.com/course/courseMain.htm?courseId=1005317018&share=1&shareId=2493122) |||
| 网易云课堂：[用Opencv处理图像【Python版】](https://study.163.com/course/courseMain.htm?courseId=1209425909) |||
| 书籍：[学习OpenCV3](https://book.douban.com/subject/30302142/)||[源代码](https://github.com/oreillymedia/Learning-OpenCV-3_examples) |


## 机器人学、机器视觉与控制--MATLAB算法基础


- 第10章  基本概念

We have described the spectra of light in terms of power as a function of wavelength, but our own perception of light is in terms of subjective quantities such as **brightness** and **color**.

Our eyes contain two types of light sensitive cells as shown in Fig. 10.6. Cone cells respond to particular colors and provide us with our normal daytime vision. Rod cells are much more sensitive than cone cells but respond to intensity only and are used at night

The **brightness** we associate with a particular wavelengths is known as **luminosity** and is measured in units of **lumens per watt**.

Radiometric and photometric quantities. Two quite different sets of units are used when discussing light: radiometric and photometric. Radiometric units are used in Sect. 10.1 and are based on quantities such as power and are expressed in familiar SI units such as watts. 

**Photometric units are analogs of radiometric units but take into account the visual sensation in the observer**. 
**Luminous power** or **luminous flux**(光通量) is the perceived power of a light source and is measured in **lumens (abbreviated to lm) rather than watts**. A 1 W light source at 555 nm, the peak response, by definition emits a luminous flux of 683 lm. By contrast a 1 W light source at 800 nm emits a luminous flux of 0 lm – it causes no visual sensation at all.

A 1 W incandescent lightbulb however produces a perceived visual sensation of less than 15 lm or a luminous efficiency of 15 lm W–1. Fluorescent lamps achieve efficiencies up to 100 lm W–1 and white LEDs up to 150 lm W–1.

**Lightmeters**(光度计), **illuminance**(照度) and **luminance**(亮度). A photographic lightmeter measures luminous flux which has units of lm m–2 or lux (lx). The luminous intensity of a point light source is the luminous flux per unit solid angle measured in lm sr–1 or candelas (cd). For a point source of luminous intensity I the illuminance E falling normally onto a surface is where d is the distance between source and the surface. Outdoor illuminance on a bright sunny day is approximately 10000 lx whereas office lighting levels are typically around 1000 lx. The luminance or brightness of a surface is which has units of cd m–2 or nit (nt), and where Ei is the incident illuminance at an angle θ to the surface normal.


| 概念  | 单位 | 说明|
| --- | --- | --- |
| Radiometric quantities(辐射学)| W | 功率单位。包含所有的能量，包含可以看见的和不能看见的（比如热量） |
| photometric quantities(光度学)<br>即luminous flux(光通量) | 流明lumens（简写为lm） | 功率单位。指人眼所能感觉到的辐射功率。|
| 色度学|| RGB或者LAB等 |


**立体角和球面度概念**

![](/images/视觉/平面角.PNG)

![](/images/视觉/立体角.PNG)
(研究的是dS在某点下所张的角度，所以有涉及到cosθ))



参考资料：

* [半球积分](https://chengkehan.github.io/HemisphericalCoordinates.html)
* [平面角与立体角](https://wenku.baidu.com/view/32b09d1e866fb84ae45c8dcb.html)
* [辐射照度、辐射强度、光照度、发光强度（差异以及如何相互转换）（易懂讲解）](https://blog.csdn.net/a6333230/article/details/90036993)
* [发光强度/光通量/光照度/亮度/坎德拉/流明/勒克斯/尼特之间的关系和换算](https://blog.csdn.net/LEON1741/article/details/81237576)



**一元操作**

彩色图(RGB) --> 灰度化(0~255，代表亮度：0表示黑色，255表示白色) --> 二值化(0或1)

```python
import cv2
import numpy as np
o = cv2.imread('image\\contours.bmp')  
gray = cv2.cvtColor(o,cv2.COLOR_BGR2GRAY)    # 灰度化
ret, binary = cv2.threshold(gray,127,255,cv2.THRESH_BINARY)   #二值化，阈值是127，阈值以上取255，以下去0
image,contours, hierarchy = cv2.findContours(binary,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)  
co=o.copy()
r=cv2.drawContours(co,contours,2,(0,0,255),6)  
cv2.imshow("original",o)
cv2.imshow("contours",r)
cv2.waitKey()
cv2.destroyAllWindows()
```

从**机器人学、机器视觉与控制--MATLAB算法基础**这本书也知道，RGB对图像处理，并没有给出更多的信息。灰度值是RGB的加权值，并没有丢失RGB的信息。

RGB==>灰度图：一般用在模式识别、图像检索等场合的预处理。

## 透视投影

参考文献：


* [Opencv日常之Homography](https://blog.csdn.net/liuphahaha/article/details/50719275)
* [透视投影的原理和实现](https://blog.csdn.net/gloriazhang2013/article/details/69400152)(里面的LUD是姿态表示法)
* [findHomography](https://www.cnblogs.com/canyeweiwei/p/10597474.html)

## OpenCV

opencv一般用于图像处理，主要不是用于图形处理。

opencv中,图像表示是BGR，和一般说的RGB顺序反了


图像边缘不等于图像轮廓

图像轮廓(封闭的区间)是指将没有连在一起的图像边缘(散落的区间,小的线段的集合)连在一起。

图像梯度(sobel/scharr/leplacian算子)==>边缘检测(canny算子)==>图像轮廓(需要是二值图：彩色==>灰度==>二值)

![](/images/视觉/图像轮廓.PNG)

![](/images/视觉/图像轮廓2.PNG)

![](/images/视觉/图像轮廓3.PNG)



- 对图像进行傅里叶变换是什么意思


![](/images/视觉/傅里叶变换.png)

![](/images/视觉/傅里叶变换2.png)

![](/images/视觉/傅里叶变换3.png)

![](/images/视觉/傅里叶变换4.png)

![](/images/视觉/傅里叶变换5.png)
(x可以理解为时间，也可以理解为空间)

一维连续函数的傅里叶变换：

![](https://pic2.zhimg.com/v2-c74b91f76940126737d3216d4b377f31_r.jpg)

一维离散函数的傅里叶变换：

![](https://www.zhihu.com/equation?tex=%5Ccolor%7Bpurple%7D%7BX%7D_%7B%5Ccolor%7Bgreen%7D%7Bk%7D%7D+%3D%5Ccolor%7Bred%7D%7B%5Cfrac%7B1%7D%7BN%7D+%5Csum_%7Bn%3D0%7D%5E%7BN-1%7D%7D%5Ccolor%7Bblue%7D+%7Bx_n%7D%5Ccdot+e%5E%7B-i%5Cfrac+%7B%5Ccolor%7Bfuchsia%7D%7B2%5Cpi%7D+%5Ccolor%7Bgreen%7D%7Bk%7D%7D%7B%5Ccolor%7Bpurple%7D%7BN%7D%7D%5Ccolor%7Bpurple%7D%7Bn%7D%7D)

![](https://pic3.zhimg.com/v2-1398ee91d420d89ffab923a920110e0a_r.jpg)

![](/images/视觉/图像的傅里叶变换.png)

(白色的低频部分)

(右边图就是频率分布图谱，其中越靠近中心的位置频率越低，越亮（灰度值越高）的位置代表该频率的信号振幅越大。fft的结果是复数形式，保留了图像的全部信息，但去绝对值得到的频谱图只表现了振幅而没有体现相位。)

![](/images/视觉/图像的傅里叶变换2.png)

一般的波形或者说信号（Signal）都是基于时间尺度上的采样结果，因此也称为时域（Time Domain），而上面泡面的例子和我们将要处理的图像信号则是基于空间尺度上的采样，但好像并没有“空域（Space Domain）”这一说，毕竟我们对空间的感知仍然依赖于时间。不过在空间尺度上我们可以更直观地认为信号是静止，例如下面这张图像（灰度图），其实是由250x250个像素点组成，每个像素点的灰度值（$[0, 255]$）就是基于像素坐标的空间采样的结果:

![](https://upload-images.jianshu.io/upload_images/21342-0cd675f781c098fa.png?imageMogr2/auto-orient/strip|imageView2/2/w/900/format/webp)


跟一维信号处理一样，傅里叶变化，把图像从“空域”变为“频率”。对于一幅图像，高频部分代表了图像的细节、纹理信息；低频部分代表了图像的轮廓信息。如果对一幅精细的图像使用低通滤波器，那么滤波后的结果就剩下了轮廓了。这与信号处理的基本思想是相通的。如果图像受到的噪声恰好位于某个特定的“频率”范围内，则可以通过滤波器来恢复原来的图像。


![](/images/视觉/滤波.PNG)

参考链接:
* [如何理解 图像傅里叶变换的频谱图](https://blog.csdn.net/viatorsun/article/details/82387854)
* [图像的傅里叶变换](https://www.jianshu.com/p/2704b5d1d6bb)
* [图像的傅里叶变换](https://wenku.baidu.com/view/c5e2cca8fab069dc502201db.html)
* [图像的傅里叶变换](https://www.jianshu.com/p/89ce7fdb9e12)


### opencv数据结构


在OpenCV中,可以用C++语法的Mat类来表示一张图像,也可以用C语法的lpllmage或CvMat结构体来表示一张图像.

参考资料：[C++下的OpenCV中Mat类型存储的图像格式](https://segmentfault.com/a/1190000015653101), [OpenCV2:总结篇 cv::Mat 类](https://www.cnblogs.com/k5bg/p/11136926.html)

Matx 是个轻量级的Mat，必须在使用前确定其尺寸，比如一个2*3的float型Matx，可以声明为：Matx23f;

详见：[opencv 中常用的数据类](https://blog.csdn.net/qq_35859033/article/details/77367086) 和 [第四章 OpenCv的数据类型](http://www.manongjc.com/article/32578.html)




### 常见处理


阈值分割：

阈值分割其实就是图像分离，对于阈值内的你想如何操作，一个最简单的例子就是二值图像。

二值化算法是用输入像素的值I与一个值C来比较，根据比较结果确定输出值。

自适应二值化的每一个像素的比较值C都不同，比较值C由这个像素为中心的一个块范围计算在减去差值delta得到。

在图像阈值化操作中，更关注的是从二值化图像中，分离目标区域和背景区域，但是仅仅通过设定固定阈值很难达到理想的分割效果。而自适应阈值，则是根据像素的邻域块的像素值分布来确定该像素位置上的二值化阈值。

* [常见阈值分割处理](https://www.learnopencv.com/opencv-threshold-python-cpp/)(二值化、反二值化、截断等)
* [自适应二值化介绍](https://www.cnblogs.com/shangd/p/6094547.html)(概念介绍)
* [自适应阈值化操作](https://www.cnblogs.com/GaloisY/p/11037350.html)(配合了高斯滤波去除小噪声)


轮廓检测：

输入是单通道图像矩阵，可以是灰度图，但更常用的是二值图像，一般是经过Canny、拉普拉斯等边缘检测算子，或使用cv::threshold()或者cv::adaptiveThreshold()处理过的二值图像

这是检测出轮廓，提取出轮廓到vector是用cv::findContour

* [findContours轮廓检测](https://www.cnblogs.com/GaloisY/p/11062065.html)


最近一直忙着提取物体轮廓的相关算法问题，这里就说一下提取物体轮廓的常用的三种方法：sobel算子边缘检测，canny算子边缘检测，以及laplacian算子边缘检测。

[使用OPENCV提取物体轮廓](https://zhuanlan.zhihu.com/p/44855115)

soble算子：

![](https://pic2.zhimg.com/80/v2-a90225a066a509badfa6b325fd8e53ed_720w.jpg)

canny算子：

![](https://pic2.zhimg.com/80/v2-9d2dec7358f0ff0545daa3a11434f35d_720w.jpg)

laplacian算子：

![](https://pic3.zhimg.com/80/v2-57a98dd2e81af0ef44ebb773d558d3e6_720w.jpg)

注意以上只是在图片中提取了轮廓，并没有提取到vector中，要提取到vector中，需要使用findContours函数



展会轮廓提取（内外部轮廓)：

总体思路：
函数cv::findContour是从二值图像中来计算轮廓的，它可以使用cv::Canny()函数处理的图像，因为这样的图像含有边缘像素；也可以使用cv::threshold()或者cv::adaptiveThreshold()处理后的图像，其边缘隐含在正负区域的交界处

[【OpenCV3】图像轮廓查找与绘制——cv::findContours()与cv::drawContours()详解](https://blog.csdn.net/guduruyu/article/details/69220296)

为了提取轮廓，就涉及到图像处理的一些问题，还有轮廓如何精简的问题（轮廓点太密，如何设置稀疏一点etc）。细化好点后（仍然可以有很多点），最终都会转化为cmdlist



ContourVec（向量内每个元素保存了一组由连续的Point构成的点的集合的向量），每一组点集就是一个轮廓，有多少轮廓，contours就有多少元素





方法：

灰度图==>图像翻转(flimage函数：flip Y)==>自适应阈值化操作（二值化图)   --> findContours(输入：二值化图，输出：轮廓点，轮廓点模式：仅保存轮廓的拐点信息)--->轮廓点排序（应该是先画里面的轮廓，再画外面的轮廓，即现在的contour，先放最内圈轮廓，然后是最外圈轮廓。比较各个轮廓的第一个点，也就是机器人这个轮廓的第一个走点，x*x + y*y 比较小先走，保证大体是从左到右边画过去，从外到内画过去）-->轮廓点精简(approxPolyDP:approxPolyDP主要功能是把一个连续光滑曲线折线化,对图像轮廓点进行多边形拟合)



图像翻转：将图像绕y轴翻转。翻转的意思是对称。沿着y轴翻转图形，即沿着y轴对称图形，或者沿着竖直轴对称图形。

  目的：是从视觉图中恢复出原始图的样子

自适应阈值：在图像阈值化操作中，更关注的是从二值化图像中，分离目标区域和背景区域，但是仅仅通过设定固定阈值很难达到理想的分割效果。而自适应阈值，则是根据像素的邻域块的像素值分布来确定该像素位置上的二值化阈值。这样做的好处


提取内部轮廓：

思路1：还是上面提取轮廓的步骤，只是在cv::findContours()中，具有提取内部轮廓的选项，参考[【OpenCV3】图像轮廓查找与绘制——cv::findContours()与cv::drawContours()详解](https://blog.csdn.net/guduruyu/article/details/69220296)

下一个参数，轮廓的模式，将会告诉OpenCV你想用何种方式来对轮廓进行提取，有四个可选的值，具体如下图：

cv::RETR_EXTERNAL：表示只提取最外面的轮廓；

cv::RETR_LIST：表示提取所有轮廓并将其放入列表；

cv::RETR_CCOMP:表示提取所有轮廓并将组织成一个两层结构，其中顶层轮廓是外部轮廓，第二层轮廓是“洞”的轮廓；

cv::RETR_TREE：表示提取所有轮廓并组织成轮廓嵌套的完整层级结构。


思路2：



### opencv-python



## 相机介绍


[UVC=USB Video Class compliant devices in Linux。 This include a V4L2 kernel device driver and patches for user-space tools. The UVC specification covers webcams, digital camcorders, analog video converters, analog and digital television tuners, and still-image cameras that support video streaming for both video input and output.](http://www.ideasonboard.org/uvc/)

USB video class（又称为USB video device class or UVC）就是USB device class视频产品在不需要安装任何的驱动程序下即插即用，包括摄像头、数字摄影机、模拟视频转换器、电视卡及静态视频相机。usv在linux内核中已经实现了支持，所以如果一个设备支持usv，那么一般是免驱的。


ROS下usb摄像头的驱动方式有很多(大多数摄像头都支持uvc标准)，自己在开发摄像头驱动的说话，需要知道自己当前的摄像头属于哪一类，否则很容易安装错误的包。

下面是一些常见的[ros相机相关驱动功能包](https://github.com/ros-drivers)介绍。

|功能包|说明|其他|
|--|--|--|
|libuvc_camera|采用UVC标准的相机接口功能包，大部分摄像头都支持uvc标准|[ROS中UVC_Camera的使用。](https://blog.csdn.net/qq_24894159/article/details/82939542)|
|uvc_camera|因为有相对详细的相机设置功能，所以非常方便。此外，如果您因为有两个相机，所以考虑使用立体相机，那么这将是一个比较合适的功能包|[Linux学习之ROS的uvc camera（笔记本的摄像头）](https://blog.csdn.net/qq_43433255/article/details/89332667)|
|usb_cam|这是Bosch使用的非常简单的摄像头驱动程序|[使用usb_cam软件包调试usb摄像头](https://www.corvin.cn/535.html?v=1c2903397d88)。可以简单认为，如果相机支持uvc的，那么优先用uvc或libuvc功能包，否则只能用usb_cam功能包了|
|freenect_camara, openni_camera, openni2_camera|所有这3个功能包名称中都有相机，但是他们是深度相机(如Kinect或Xtion)的功能包。这些传感器也被称为RGB-D相机，因为它们也包含彩色相机。如果要利用彩色图像，则需要使用这些功能包||
|camera1394|它是使用FireWire(IEEE 1394接口)的相机的驱动程序||
|prosilica_camera|它被用于AVS的prosilica相机，被广泛用于研究目的||
|pointgrey_camera_driver|它是Point Grey Research公司的Point Gray相机的一个驱动程序，被关于用于科研||
|camera_calibration|应用了OpenCV的校准功能的相机校准功能包。许多相机相关的功能包需要这个功能包。||

![](/images/视觉/功能包.PNG)

对于很普通的usb相机，一般在ros中用到的会是usb_cam或者libuvc_cam的比较多。如果相机支持uvc，那么就用libuvc_camera，否则就用usb_cam。可供参考的有：[ROS中UVC_Camera的使用。](https://blog.csdn.net/qq_24894159/article/details/82939542)、[摄像头是usb_camera还是uvc_camera判定方法及ros usb_cam测试](https://www.cnblogs.com/qixianyu/p/6575276.html)、[Linux USB 摄像头驱动](https://www.cnblogs.com/alan666/p/8311898.html)。

在Doug的画图案例中，用到的相机信息如下：

![](/images/视觉/相机.jpg)

![](/images/视觉/相机2.jpg)

我们的这个相机是支持uvc的。

|launch文件|说明|其他|
|--|--|--|
|`usb.launch`|使用`libuvc_camera`启动|[测试视频](/images/视觉/libuvc_camera.mp4)和[ROS中libuvc_camera的使用。](https://blog.csdn.net/qq_24894159/article/details/82939542)|
|`usb_cam-test.launch`|使用`usb_cam`启动|测试视频：链接：https://pan.baidu.com/s/1OuZkmZtCE0jQHophyIiosQ  提取码：fi22和[使用usb_cam软件包调试usb摄像头](https://www.corvin.cn/535.html?v=1c2903397d88)。|
|`image.launch`|加载来自本地的图片，用到了`nodelet`|[测试视频](/images/视觉/image_from_file.mp4)|
|`rgb8.launch`|基于[ueye_cam](http://wiki.ros.org/ueye_cam)功能包，用于[IDS公司](https://cn.ids-imaging.com/home.html)的工业相机||

不过不管是用什么驱动，他们的标定一般都是类似的。如[使用usb_cam软件包调试usb摄像头](https://www.corvin.cn/535.html?v=1c2903397d88)(使用usb_cam启动)以及[Linux学习之ROS的uvc camera](https://blog.csdn.net/qq_43433255/article/details/89332667)(使用uvc_camera启动，注意不是libuvc_camera)。但是都采用了`camera_calibration `功能包进行了标定。



## realSense



|模块|说明|备注|
|--|--|--|
|[D435i](https://www.intelrealsense.com/depth-camera-d435i/)|![](/images/视觉/D435i.PNG)<br>![](/images/视觉/d435_inside_depth_camera.jpg)|[D435i参数](https://www.intelrealsense.com/depth-camera-d435i/)(包含分辨率，视野，帧率等)<br>[Intel RealSense Depth Camera D435i 开箱拆解](https://www.chiphell.com/thread-1945054-1-1.html)|
|[D415](https://www.intelrealsense.com/depth-camera-d415/)|D415的硬件包含了两个深度相机，一个RGB相机和一个结构光红外投影仪。深度卷帘相机（逐行扫描），红外结构光深度测距。<br>![](/images/视觉/D415.jpg)<br>![](/images/视觉/D415原理.png)(待定)|Doug用的相机|
|原理|![](/images/视觉/D400原理.PNG)<br>![](/images/视觉/realSense.jpg)<br>![](/images/视觉/D400集成.jpg)||
|说明|realsense的视野有范围限制，太近不行，太远也不行！！具体请看指标||

其他资料：

* [官网文档](https://dev.intelrealsense.com/docs)
* [哪款适合你？一览英特尔实感摄像头D415、D435、D435i、T265的差异](https://yivian.com/news/60075.html)
* [直击英特尔IDF2015：RealSense技术实现真正的3D图像识别](http://pieeco.com/news/586_1.html)(卖控制板，方便集成)


> KinectV2是ToF技术，除了对屏幕这种吸收红外光线的物体效果不好之外，精度相对最高，物体边缘也很准确。 Realsense是用双IR（红外）相机打出结构图案做stereo matching，类似于双镜头深度计算，精度是最差的（噪声最大），而且也很容易被物体边缘影响。但是这个方法的优点是可以做得很快，所以可以到90fps，kinect的帧率貌似是60fps，driver限制到了30fps。这俩设备的自带驱动有效距离都是1.5-8m左右，kinect用第三方驱动可以到16m。 只看分辨率我觉得没啥意思，就算直接把kinect的分辨率插值提高一倍，效果也要比realsense的好得多。大概就这样吧，你有机会拿个kinect和realsense对着同一个场景就能看出个大概了。如果室外使用的话，realsense对环境红外光的敏感度较低，kinect性能就下降很厉害。



> 用primesense做安防，也是好好笑，记得该公司创始人在描绘美好未来时说曾说在天安door布满他们的深度摄像头...我就日了狗了，他们做了2年难道不知道无论是primesense的红外结构光深度传感器还是intel那个tof传感器在日光下不可用么……更何况，贵公司那套primesense的方案rgbd的有效范围只有6米啊大哥......拿primesense在室外你搞个蛋的深度摄像头安防......该公司当年拿到钱的时候，我就觉得蹊跷，翻开来他们几个创始人的简历看......我只能说你们牛逼，你们太能吹了......

> 哈哈，确实是红外摄像头室外干扰太严重了，用time of fly方案硬件成本很高啊，看来在室外想获取三维信息貌似剩下双目立体的方案了

> 傻子也知道结构光不能用于室外，他们的室外产品用的是双目......


> 06年做三维重建，单结构光，多频相移，双目视觉，都用过了，目前深度探测不外乎这几个：1.微软Kinect就是个红外结构光（散点光斑）原理；2.后面随着处理器和硬件的发展，采用激光TOF的LiDAR在工业上应用比较多，比如徕卡，velodyne，北科天绘（国产）等；3.双目视觉一直都存在，但是需要足够的特征和要求严格的应用环境，但是不需要投射的被动采样有他的先天优势；4.建立在机器人，汽车上的需求，借助IMU（组合惯性单元）的绝对定位，单目系统也在特定的场合应用，比如，机器人和驾车辅助有的用单目，利用动态轨迹和高度不变来解算。

参考资料：[知乎](https://www.zhihu.com/question/27072526/answer/88130349)


> (1)结构光（structured-light），如Prime Sense，Kinect-1，Inter RealSense等；

> (2)双目视觉（Stereo），如Leap Motion，ZED，大疆；

> (3)光飞行 时间法(TOF)，Kinect-2，PMD，SoftKinect。

参考资料：[3D相机原理—结构光、双目视觉和光飞行时间法](https://zhuanlan.zhihu.com/p/48129306)

### SDK2安装

SDK2是简单调试用的，真正开发，应该是用wrapper开发的。

建议SDK2最新版本(build xxx)会领先于wrapper版本，建议SDK2版本和wrapper版本一致。

- windows平台：

|项目|配置|备注|
| --- | --- | --- |
|Viewer和SDK安装|在[Intel® RealSense™ SDK 2.0](https://github.com/IntelRealSense/librealsense/releases)中选择`Assets`下的Viewer和SKD直接进行安装(最新版本下，安装SDK的时候回同时安装viewer和案例等)<br>![](/images/视觉/SDK_installation.PNG)<br>具体参考[Windows Distribution](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_windows.md)|安装这2个的时候，需要同步查看下wrapper支持的版本，要根据wrapper的版本信息选择SDK和Viewer版本<br>![](/images/视觉/wrapper.PNG)<br>![](/images/视觉/wrapper2.PNG)<br>如果不打算在windows下开发，可以直接下最新版的|
|wrapper|以[python](https://github.com/IntelRealSense/librealsense/tree/development/wrappers/python)为例<br>用户自己尝试，这里不测试了||



- linux 平台：

由于使用到USB3.0, 而虚拟机对USB3.0的支持有限，所以不可以在Oracle的VM中使用，建议在实际的电脑上使用，一定要用也建议采用VMWare

安装测试见虚拟机：RealSense Test

|项目|说明|备注|
| --- | --- | --- |
|SDK安装|总的安装方法：[Setting up a 3D sensor](https://industrial-training-master.readthedocs.io/en/melodic/_source/demo3/Setting-up-a-3D-sensor.html) 或 [Ubuntu 16.04安装RealSense ROS功能包](https://blog.csdn.net/qq_38649880/article/details/91975100)或[ubuntu 16.04 lts 安装intel realsense 步骤](https://blog.csdn.net/smilestone_322/article/details/78066885)<br>以上可能写得不清楚，具体分步骤为：<br>1. linux环境配置:[ubuntu环境配置](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_linux.md#installing-the-packages) 或 [Ubuntu 16.04 安装Intel RealSense及环境配置之 CMake + OpenCV](https://blog.csdn.net/u012180635/article/details/82143340)<br>2. [SDK2 Linux Ubuntu Installation](https://github.com/IntelRealSense/librealsense/blob/master/doc/installation.md)或[Ubuntu16.04下Realsense环境搭建](https://blog.csdn.net/u010284636/article/details/80449116)|打开方法:`realsense-viewr`<br>![](/images/视觉/ubuntu-realsense1.png)<br>![](/images/视觉/ubuntu-realsense2.png)|
|wrapper|以[ros wrapper](https://github.com/IntelRealSense/realsense-ros/releases)为例<br>![](/images/视觉/wrapper.PNG)<br>![](/images/视觉/wrapper2.PNG)|![](/images/视觉/ubuntu-realsense3.png)|


|项目|说明|备注|
|--|--|--|
|[INTEL RealSense-D415 在 Ubuntu 16.04 开发流程 1](https://blog.csdn.net/weixin_38543989/article/details/80578405)<br>[INTEL RealSense-D415 在 Ubuntu 16.04 开发流程 2](https://blog.csdn.net/weixin_38543989/article/details/80695341)<br>[INTEL RealSense-D415 在 Ubuntu 16.04 开发流程 3](https://blog.csdn.net/weixin_38543989/article/details/80885173)<br>[INTEL RealSense-D415 在 Ubuntu 16.04 开发流程 4](https://blog.csdn.net/weixin_38543989/article/details/81537984)|1. 可以了解realsense中点云的处理<br>2. 可以了解realsense文件夹下`wrappers`,`tools`,`third-party`等的作用<br>**3. [了解动态标定工具(Calibration Tools)](https://dev.intelrealsense.com/docs/intel-realsensetm-d400-series-calibration-tools-user-guide)**|官方提供的动态标定工具用于标定相机**外参**,标定好外参后，会自动load到realsense的firmware中。不过这里的外参，指的是：<br> ![](/images/视觉/realsense-内外参.PNG)<br>相机的内参和真正的外参标定，需要用其他的功能包来标定<br>具体可以参考：[How can I calibrate D415 or D435](https://github.com/IntelRealSense/librealsense/issues/2329), [Calibration of D415 in ROS](https://github.com/IntelRealSense/realsense-ros/issues/508), [Calibration of D415 in ROS](https://github.com/IntelRealSense/librealsense/issues/2666)|
|[OpenCV-Python Camera Calibration and 3D Reconstruction](https://opencv-python-tutroals.readthedocs.io/en/latest/py_tutorials/py_calib3d/py_calibration/py_calibration.html)|1. 了解畸变、内外参的含义<br>||
||||
||||



### 相机标定

![](/images/视觉/相机标定.jpg)

(这个图里面不包含畸变模型，畸变模型也算在内参里面)

> 手眼标定的目的就是获取机器人坐标系和相机坐标系的关系，最后将视觉识别的结果转移到机器人坐标系下

> 手眼标定行业内分为两种形式，根据相机固定的地方不同，如果相机和机器人末端固定在一起，就称之为“眼在手”（eye in hand），如果相机固定在机器人外面的底座上，则称之为“眼在外”（eye to hand）。两者本质是一样的，对于(eye in hand)，相机拍照的时候，也不是随便在什么地方拍照的，而是在固定的地方(可能有几处)，所以对于(eye in hand)，实际上就是有多套(eye to hand)


参考资料：

* [让机械臂自动进行手眼标定---以Dobot Magician和Realsense为例](https://www.three.ml/2018/08/hand-eye-calibration/#%E5%8F%82%E8%80%83%E6%96%87%E7%8C%AE&%E7%9B%B8%E5%85%B3%E5%B7%A5%E5%85%B7)
* [深入浅出地理解机器人手眼标定](https://blog.csdn.net/qq_16481211/article/details/79764730)
* [手眼标定之基本原理](https://www.jianshu.com/p/3e302adc7aa5)(AX=XB)
* [手眼标定的两种方式](https://blog.csdn.net/u011089570/article/details/47945733)(标定坐标转换讲得最清楚,其中obj表示标定板)
* [计算机视觉：相机成像原理：世界坐标系、相机坐标系、图像坐标系、像素坐标系之间的转换](https://blog.csdn.net/chentravelling/article/details/53558096)(坐标系图)
* [机器人手眼标定Ax=xB（eye to hand和eye in hand）及平面九点法标定](https://blog.csdn.net/yaked/article/details/77161160)(杂项)


|功能包|说明|备注|
|--|--|--|
|**[industrial_calibration标定包](https://github.com/ros-industrial/industrial_calibration)**|||
|**[robot_cal_tools标定包](https://github.com/Jmeyer1292/robot_cal_tools)**|||
|**[robot_calibration标定包](http://wiki.ros.org/robot_calibration)**|包含内外参以及机器人关节零位标定，也是Doug中采用的(内外参)标定法|相机内参标定结果：yaml<br>其他标定结果：更新的URDF。[功能表讨论区](https://github.com/mikeferguson/robot_calibration/issues)有使用方法的一些讨论|
|**[image_pipeline](http://wiki.ros.org/image_pipeline?distro=melodic)**|该功能包包含内外参标定，内外参标([image_pipeline/camera_calibration](https://blog.csdn.net/xinwenfei/article/details/81235072))定支持2D相机和3D相机。也是古月居教学中采用的（内参标定）方法。||
|**[easy_handeye](https://github.com/IFL-CAMP/easy_handeye)**|古月中用到的外参标定法||


- robot_calibration用法

![](/images/视觉/robot_calibration.PNG)

```C++
/** \mainpage
 * \section parameters Parameters of the Optimization:
 *   - joint angle offsets 关节零位校准
 *   - frame 6DOF corrections (currently head pan frame, and camera frame) TCP校准（？）相机外参校准
 *   - camera intrinsics (2d & 3d) 相机内参校准
 *
 * \section residuals Residual Blocks:
 *   - difference of reprojection through the arm and camera
 *   - residual blocks that limit offsets from growing outrageously large
 *
 * \section modules Modules:
 *   - Capture: 拍照
 *     - move joints to a particular place   移动机器人到特定地方
 *     - wait to settle   等待机器人到达特定点（在calibration_poses.bag中规定的点位）
 *     - find target (led or checkerboard)   找到模板（led或者标定板）
 *     - write sample to bag file: joint angles, position of targets in camera. 将sample写到bag中（机器人关节值、在相机下的位置值）
 *   - Calibrate:校准
 *     - load urdf, samples from bag file.  加载urdf和samples（来自bag）
 *     - create arm and camera reprojection chains.  创建arm和相机的重映射链
 *     - create residual blocks.    创建误差块（解释见上面）
 *     - run calibration.   开始校准
 *     - write results to URDF.  更新结果到urdf
 */

/*
 * usage:
 *  calibrate --manual
 *  calibrate calibration_poses.bag  标定的机器人运动位置来自于calibration_poses.bag ，同时在每一步的时候，还需要重新采集（capturing）相机下的位置信息，构成calibration_data
 *  calibrate --from-bag calibration_data.bag (calibration_data.bag包含了机器人关节点位和相机识别下的点位：一一对应的)
 */

```


```yaml
I've been thinking about how to create a wizard for this, so that people don't have to create/update lots of YAML files manually (which is also error-prone). In the mean-time, this ticket serves as somewhat of a brief HOW-TO to manually create the files.

Load and parse the URDF (from parameter server?)
User selects the "base_link" which is the frame into which all points will be projected during calibration step.
User defines the "chains" by:
Entering a name for each
Selecting set of active joints that constitute the chain
Selecting the follow_joint_trajectory action namespace (introspect options from running system?)
Selecting the planning_group to use with MoveIt (if any)
User defines the models by adding a model of a given type and then configuring the following:
For "chain" - select end effector frame.
For "camera3d" - select camera frame, select topic (introspect options from running system?)
User defines the finders by adding a finder of a given type and then configuring the following:
PlaneFinder
Select "camera_sensor_name" from available "models"
Select "topic" to subscribe to
Select "transform_frame" (defaults to base_link selected above)
Set min/max x/y/z
Set "points_max"
Select true/false for "debug"
CheckerboardFinder
Select "camera_sensor_name" from available "models"
Select "chain_sensor_name" from available "models"
Select "topic" to subscribe to
Set points_x, points_y, size
Select true/false for "debug"
LedFinder - probably not worth adding to wizard (just preserve if it exists)
User defines error_blocks by selecting/adding by type first:
chain3d_to_chain3d - Select model_a, model_b from models.
chain3d_to_plane - Select model_a from models. Set a, b, c, d and scale.
plane_to_plane - Select model_a, model_b from models. Set scale_normal, scale_offset.
outrageous - Select param from list of possible free params. Set joint_scale, position_scale, rotation_scale.
User selects free params through a series of checkboxes
Available names are each joint name, fx/fy/cx/cy/z_offset/z_scaling for each camera)
User selects free frames through a series of checkboxes
For each free frame, select x/y/z/r/p/y. Also do check that 0, 1, or 3 of r/p/y are set (setting two to true doesn't do what you think -- these are axis-magnitude internally).
Finally have user manually move arm while both capturing data for the first calibration and creating the bag file for export.
As with the MoveIt wizard, we would want to be able to reload the YAML files exported and edit them.
```

|步骤|说明|备注|
|--|--|--|
|获取机器人关节位置+观察位置（如视觉观察/机械手观察)|视觉观察。比如：checkerboard上的corners点(相对相机坐标系)<br>机械手观察||
|计算|||
||||


![](/images/视觉/robot_calibration_pkg.PNG)

![](/images/视觉/calibrate1.PNG)

![](/images/视觉/calibrate2.PNG)



![](/images/视觉/robot_calibration_msgs.png)



该功能表涉及的数据类型：

`CameraParameter.msg`
相机其他信息
```
string name
float64 value
```


`ExtendedCameraInfo.msg`
相机相关信息(内参及其他信息)
```
sensor_msgs/CameraInfo  camera_info
CameraParameter[]       parameters
```


`CaptureConfig.msg`

```
# Pose the robot should be put in for this sample
sensor_msgs/JointState joint_states

# Names of feature detectors to use for this sample
string[] features
```

`Observation.msg`

观察点信息(传感器名称+点+相机信息+点云/图像信息)

```
# Name of the "sensor" that generate this data.
string sensor_name

# Features "detected" by the sensor.  其实就是用“相机”等观察下的点的信息
geometry_msgs/PointStamped[] features

# Sensor information
ExtendedCameraInfo ext_camera_info

# Debugging data (optional)
sensor_msgs/PointCloud2 cloud
sensor_msgs/Image image
```


`CalibrationData.msg`

包含关节位置信息和观察点信息（如用摄像头观察的点的信息）

```
# State of the robot when this data was collected
sensor_msgs/JointState  joint_states

# Observations, one entry per sensor that is collecting data
Observation[]           observations
```


-------

以下是涉及到的`sensor_msgs`


`sensor_msgs/JointState.msg`

```
# This is a message that holds data to describe the state of a set of torque controlled joints. 
#
# The state of each joint (revolute or prismatic) is defined by:
#  * the position of the joint (rad or m),
#  * the velocity of the joint (rad/s or m/s) and 
#  * the effort that is applied in the joint (Nm or N).
#
# Each joint is uniquely identified by its name
# The header specifies the time at which the joint states were recorded. All the joint states
# in one message have to be recorded at the same time.
#
# This message consists of a multiple arrays, one for each part of the joint state. 
# The goal is to make each of the fields optional. When e.g. your joints have no
# effort associated with them, you can leave the effort array empty. 
#
# All arrays in this message should have the same size, or be empty.
# This is the only way to uniquely associate the joint name with the correct
# states.


Header header

string[] name
float64[] position
float64[] velocity
float64[] effort
```

`sensor_msgs/CameraInfo.msg`

[主要是相机内参,可以存放矫正后的标定的内参](https://wiki.ros.org/image_pipeline/CameraInfo)

```
# This message defines meta information for a camera. It should be in a
# camera namespace on topic "camera_info" and accompanied by up to five
# image topics named:
#
#   image_raw - raw data from the camera driver, possibly Bayer encoded
#   image            - monochrome, distorted
#   image_color      - color, distorted
#   image_rect       - monochrome, rectified
#   image_rect_color - color, rectified
#
# The image_pipeline contains packages (image_proc, stereo_image_proc)
# for producing the four processed image topics from image_raw and
# camera_info. The meaning of the camera parameters are described in
# detail at http://www.ros.org/wiki/image_pipeline/CameraInfo.
#
# The image_geometry package provides a user-friendly interface to
# common operations using this meta information. If you want to, e.g.,
# project a 3d point into image coordinates, we strongly recommend
# using image_geometry.
#
# If the camera is uncalibrated, the matrices D, K, R, P should be left
# zeroed out. In particular, clients may assume that K[0] == 0.0
# indicates an uncalibrated camera.

#######################################################################
#                     Image acquisition info                          #
#######################################################################

# Time of image acquisition, camera coordinate frame ID
Header header    # Header timestamp should be acquisition time of image
                 # Header frame_id should be optical frame of camera
                 # origin of frame should be optical center of camera
                 # +x should point to the right in the image
                 # +y should point down in the image
                 # +z should point into the plane of the image


#######################################################################
#                      Calibration Parameters                         #
#######################################################################
# These are fixed during camera calibration. Their values will be the #
# same in all messages until the camera is recalibrated. Note that    #
# self-calibrating systems may "recalibrate" frequently.              #
#                                                                     #
# The internal parameters can be used to warp a raw (distorted) image #
# to:                                                                 #
#   1. An undistorted image (requires D and K)                        #
#   2. A rectified image (requires D, K, R)                           #
# The projection matrix P projects 3D points into the rectified image.#
#######################################################################

# The image dimensions with which the camera was calibrated. Normally
# this will be the full camera resolution in pixels.
uint32 height
uint32 width

# The distortion model used. Supported models are listed in
# sensor_msgs/distortion_models.h. For most cameras, "plumb_bob" - a
# simple model of radial and tangential distortion - is sufficient.
string distortion_model

# The distortion parameters, size depending on the distortion model.
# For "plumb_bob", the 5 parameters are: (k1, k2, t1, t2, k3).
float64[] D

# Intrinsic camera matrix for the raw (distorted) images.
#     [fx  0 cx]
# K = [ 0 fy cy]
#     [ 0  0  1]
# Projects 3D points in the camera coordinate frame to 2D pixel
# coordinates using the focal lengths (fx, fy) and principal point
# (cx, cy).
float64[9]  K # 3x3 row-major matrix

# Rectification matrix (stereo cameras only)
# A rotation matrix aligning the camera coordinate system to the ideal
# stereo image plane so that epipolar lines in both stereo images are
# parallel.
float64[9]  R # 3x3 row-major matrix

# Projection/camera matrix
#     [fx'  0  cx' Tx]
# P = [ 0  fy' cy' Ty]
#     [ 0   0   1   0]
# By convention, this matrix specifies the intrinsic (camera) matrix
#  of the processed (rectified) image. That is, the left 3x3 portion
#  is the normal camera intrinsic matrix for the rectified image.
# It projects 3D points in the camera coordinate frame to 2D pixel
#  coordinates using the focal lengths (fx', fy') and principal point
#  (cx', cy') - these may differ from the values in K.
# For monocular cameras, Tx = Ty = 0. Normally, monocular cameras will
#  also have R = the identity and P[1:3,1:3] = K.
# For a stereo pair, the fourth column [Tx Ty 0]' is related to the
#  position of the optical center of the second camera in the first
#  camera's frame. We assume Tz = 0 so both cameras are in the same
#  stereo image plane. The first camera always has Tx = Ty = 0. For
#  the right (second) camera of a horizontal stereo pair, Ty = 0 and
#  Tx = -fx' * B, where B is the baseline between the cameras.
# Given a 3D point [X Y Z]', the projection (x, y) of the point onto
#  the rectified image is given by:
#  [u v w]' = P * [X Y Z 1]'
#         x = u / w
#         y = v / w
#  This holds for both images of a stereo pair.
float64[12] P # 3x4 row-major matrix


#######################################################################
#                      Operational Parameters                         #
#######################################################################
# These define the image region actually captured by the camera       #
# driver. Although they affect the geometry of the output image, they #
# may be changed freely without recalibrating the camera.             #
#######################################################################

# Binning refers here to any camera setting which combines rectangular
#  neighborhoods of pixels into larger "super-pixels." It reduces the
#  resolution of the output image to
#  (width / binning_x) x (height / binning_y).
# The default values binning_x = binning_y = 0 is considered the same
#  as binning_x = binning_y = 1 (no subsampling).
uint32 binning_x
uint32 binning_y

# Region of interest (subwindow of full camera resolution), given in
#  full resolution (unbinned) image coordinates. A particular ROI
#  always denotes the same window of pixels on the camera sensor,
#  regardless of binning settings.
# The default setting of roi (all values 0) is considered the same as
#  full resolution (roi.width = width, roi.height = height).
RegionOfInterest roi

```



`sensor_msgs/Image.msg`

二位图像接口类型，和PointCloud2不一样，那是3维点云数据类型

```
# This message contains an uncompressed image
# (0, 0) is at top-left corner of image
#

Header header        # Header timestamp should be acquisition time of image
                     # Header frame_id should be optical frame of camera
                     # origin of frame should be optical center of camera
                     # +x should point to the right in the image
                     # +y should point down in the image
                     # +z should point into to plane of the image
                     # If the frame_id here and the frame_id of the CameraInfo
                     # message associated with the image conflict
                     # the behavior is undefined

uint32 height         # image height, that is, number of rows
uint32 width          # image width, that is, number of columns

# The legal values for encoding are in file src/image_encodings.cpp
# If you want to standardize a new string format, join
# ros-users@lists.sourceforge.net and send an email proposing a new encoding.

string encoding       # Encoding of pixels -- channel meaning, ordering, size
                      # taken from the list of strings in include/sensor_msgs/image_encodings.h

uint8 is_bigendian    # is this data bigendian?
uint32 step           # Full row length in bytes
uint8[] data          # actual matrix data, size is (step * rows)
```


`sensor_msgs/PointCloud2.msg`

PointCloud2是第2版的PointCloud表示法

```
# This message holds a collection of N-dimensional points, which may
# contain additional information such as normals, intensity, etc. The
# point data is stored as a binary blob, its layout described by the
# contents of the "fields" array.

# The point cloud data may be organized 2d (image-like) or 1d
# (unordered). Point clouds organized as 2d images may be produced by
# camera depth sensors such as stereo or time-of-flight.

# Time of sensor data acquisition, and the coordinate frame ID (for 3d
# points).
Header header

# 2D structure of the point cloud. If the cloud is unordered, height is
# 1 and width is the length of the point cloud.
uint32 height
uint32 width

# Describes the channels and their layout in the binary data blob.
PointField[] fields

bool    is_bigendian # Is this data bigendian?
uint32  point_step   # Length of a point in bytes
uint32  row_step     # Length of a row in bytes
uint8[] data         # Actual point data, size is (row_step*height)

bool is_dense        # True if there are no invalid points
```


`sensor_msgs/RegionOfInterest.msg`

```
# This message is used to specify a region of interest within an image.
#
# When used to specify the ROI setting of the camera when the image was
# taken, the height and width fields should either match the height and
# width fields for the associated image; or height = width = 0
# indicates that the full resolution image was captured.

uint32 x_offset  # Leftmost pixel of the ROI
                 # (0 if the ROI includes the left edge of the image)
uint32 y_offset  # Topmost pixel of the ROI
                 # (0 if the ROI includes the top edge of the image)
uint32 height    # Height of ROI
uint32 width     # Width of ROI

# True if a distinct rectified ROI should be calculated from the "raw"
# ROI in this message. Typically this should be False if the full image
# is captured (ROI not used), and True if a subwindow is captured (ROI
# used).
bool do_rectify
```


参考资料：

* [机器人操作系统ROS从入门到放弃(七):使用rosbag](https://www.jianshu.com/p/901c2ebb4e7f)
* [sensor_msgs](http://wiki.ros.org/sensor_msgs)
* [在ROS中使用相机](https://blog.csdn.net/wxflamy/article/details/79351102)


### RA605_316工程讲解

|工程|内容介绍|其他|
|--|--|--|
|RA605_315 365-2150.project||1.初始版本默认应该是3.16<br>2. |
||||


ros版本说明:

|版本|内容介绍|其他|
|--|--|--|
|0.0.8|只试用于V3.14<br>指令只支持：TTcPlcCommandInvalid := 0,<br>TTcPlcCommandLin := 1,<br>TTcPlcCommandOvl := 2,<br>TTcPlcCommandDyn := 3,<br>TTcPlcCommandWaitIsFinished := 4,<br>TTcPlcCommandPTP := 5,<br>TTcPlcCommandSync999 := 6,<br>TTcPlcCommandSetting := 7,<br>TTcPlcCommandTool := 8<br>|工博会上绘图机器人用的就是V3.14工程，对应的ROS版本为0.0.8|
|0.0.10|试用于V3.16a/b，V3.18应该也可以<br>|||


- ROS之IO通讯说明


用法：

**示教器程序**


`mapio:`

```java
// KAIROVersion 2.20
gROS_TC1.MapDO(0, IEC.DOut32)//IEC.Dout32要是全局变量，最终是关联到了硬件DO输出上了
gROS_TC1.MapDO(1, IEC.DOut33)//示教器内部:DOut[1] = MAP(IEC.DOut33)。共享内存连接上了
gROS_TC1.MapDO(1, IEC.DOut34)
// IEC.DOut32 := TRUE  (需要在Codesys的Symbol Configuration中设置可读可写，比如在线情况下设置：这样这一句才有效)
// IEC.DOut33 := FALSE (赋给DO的初始值，这样IEC对应的DOut33也会为False)
```

`ros_control:`

```java
// KAIROVersion 2.20
//Ramp(MINJERK)
//Lin(cpUnlock)
//Lin(cpUnlockApproach)
##PTP(apHome)
CALL mapio()
Dyn(dFast)
Ovl(os200)
gROS_TC1.Begin()
WHILE TRUE DO
   gROS_TC1.PlcControl()
   ##WaitJustInTime(ASSUREOVL)
END_WHILE
```

说明：

1. 目前不支持ros和控制器的di交互，只支持do交互
2. 实际中与ros交互，用到几个DO，就要在示教器上MAP几个DO
3. ros端使用方法：

```C++
  robot_movement_interface::CommandList cmd_list;
  robot_movement_interface::Command cmd;

  auto current_pose_place = move_group.getCurrentPose().pose;
  current_pose_place.position.z += 0.040;

  cmd.command_type = "IO_OUT";
  cmd.pose_reference = "DO";
  cmd.pose.push_back(0);//或push_back(XX)

  cmd_list.commands.push_back(wait_is_finished());
  cmd_list.commands.push_back(cmd);

  cmd_list.commands.push_back(wait_is_finished());

    if (!execute_rmi_and_wait(cmd_list))//execute_rmi_and_wait是对pub_rmi_.publish(cmd_list);的封装
    return 0;

  // pub_rmi_.publish(cmd_list);

```

4. 说明：ros端不能单独set或reset某个do，设置时，必须把示教器上的MAP到的东西，全部下发设置一遍

假设示教器上MAP如下：

```java
gROS_TC1.MapDO(0, IEC.my0)//示教器内部:DOut[0] = MAP(IEC.my0)。共享内存连接上了
gROS_TC1.MapDO(1, IEC.my1)//my0~my1最终关联硬件DO上
gROS_TC1.MapDO(2, IEC.my2)
```

|说明|DO映射|DO映射|DO映射|DO映射|
|--|--|--|--|--|
|IEC(Codesys)上的||my2|my1|my0|
|示教器上的|其他未Map|DOut[2]|DOut[1]|DOut[0]|
|如果想DO输出||1|1|1|
|ROS端的值(2#111)||1|1|1|
|如果想DO输出||0|1|1|
|ROS端的值(2#11)||0|1|1|



**CheckBit**

Checks if a specific bit is set in the specified word.
```
CheckBit (
val : LWORD
bitNr : DINT
) : BOOL
```

Parameter:

|版本|内容介绍|
|--|--|
|valBit| Value which shall be checked for the given bit|
|bitNr|Bit to be checked (least significant bit = bit 0)|

Example:

```
d :=17 // 16#0011
IF CheckBit(d, 4) THEN     // check bit 4
Info("Bit 4 is set")    // display result as info
END_IF
```


参考资料：[KeMotion KAIRO Language Reference Programming manual V3.16](/images/视觉/docs/KeMotion3_KAIRO_LanguageReference_en.pdf)



- 汇总

![](/images/视觉/RA605_315/Content.PNG)

![](/images/视觉/RA605_315/Content2.PNG)

|工程|内容介绍|其他|
|--|--|--|
|RA605_315 365-2150.project||1.初始版本默认应该是3.16<br>|
||||

- IO文件夹



- Hiwin文件夹


- FromHiwin文件夹


- 增加对LinRelTCP的支持

![](/images/视觉/LinRelTCP_program.png)

![](/images/视觉/LinRelTCP.PNG)

![](/images/视觉/distance_.PNG)

![](/images/视觉/distance_1.PNG)

![](/images/视觉/distance_2.PNG)

1. TeachControl

```java
TYPE
   TTcPlcCommand :(
      TTcPlcCommandInvalid := 0,
      TTcPlcCommandLin := 1,
      TTcPlcCommandOvl := 2,
      TTcPlcCommandDyn := 3,
      TTcPlcCommandWaitIsFinished := 4,
      TTcPlcCommandPTP := 5,
      TTcPlcCommandSync999 := 6,
      TTcPlcCommandSetting := 7,
      TTcPlcCommandTool := 8,
      TTcPlcCommandSetDO := 9,
      TTcPlcCommandReadDI := 10,
      TTcPlcCommandLinRelTCP := 11 //TODO
   );
END_TYPE

ELSE (iCommandNum = TTcPlcCommandLinRelTCP) THEN
  IF NOT HasError THEN                
      IF HasDyn AND HasOvl THEN
        ovlIec.GetOvl();
        LinRelTCP(pos1, dynIec1, ovlIec.mOvl);
      ELSIF HasDyn THEN            
        LinRelTCP(pos1, dynIec1);
      ELSIF HasOvl THEN
        ovlIec.GetOvl();
        LinRelTCP(pos1, ,ovlIec.mOvl);
      ELSE
        LinRelTCP(pos1);
      END_IF;
  END_IF;

```

2. ROS.library

```java
//GVL_ROS
VAR_GLOBAL CONSTANT
	g_RMILibVersion	: STRING := '0.0.12';
END_VAR

//TTcPlcCommand(ENUM)
{attribute 'strict'}
TYPE TTcPlcCommand :
(
	TTcPlcCommandInvalid := 0,
	TTcPlcCommandLin := 1,
	TTcPlcCommandOvl := 2,
	TTcPlcCommandDyn := 3,
	TTcPlcCommandWaitIsFinished := 4,
	TTcPlcCommandPTP := 5,
	TTcPlcCommandSync999 := 6,
	TTcPlcCommandSetting := 7,
	TTcPlcCommandTool := 8,
   TTcPlcCommandSetDO := 9,
   TTcPlcCommandReadDI := 10,
   TTcPlcCommandLinRelTCP := 11
);
END_TYPE


//TCartDistIEC
{ attribute 'teachcontroldatatype' := 'CARTDISTIEC' }
TYPE TCartDistIEC :
STRUCT
	dx : REAL :=0;
	dy : REAL :=0;
	dz : REAL :=0;
	da : REAL :=0;
	db : REAL :=0;
	dc : REAL :=0;
	dp1 : TAdditionalPositionType := TAdditionalPositionType.eAddPosDefault;
	dp2 : TAdditionalPositionType := TAdditionalPositionType.eAddPosDefault;
	daux1 : REAL :=0;
	daux2 : REAL :=0;
	daux3 : REAL :=0;
	daux4 : REAL :=0;
	daux5 : REAL :=0;
	daux6 : REAL :=0;		
END_STRUCT
END_TYPE

//ROS_TC
mCartDistIECSharedMem : K_Base.KSharedMemory;
mpCartDistIECSharedMem : POINTER TO TCartDistIEC;

//ROS_TC==>Init()
IF NOT mCartDistIECSharedMem.IsValid THEN
	mCartDistIECSharedMem.InitShMem(CONCAT(strMemName, '.cartDistIec'), SIZEOF(TCartDistIEC));
	IF mCartDistIECSharedMem.IsValid THEN
		mpCartDistIECSharedMem := mCartDistIECSharedMem.Addr;
	ELSE
		RETURN;
	END_IF
END_IF

//SetCartDist
METHOD SetCartDist : BOOL
VAR_IN_OUT CONSTANT
	cartDist : TCartDistIEC;
END_VAR

SetCartDist := FALSE;
IF mpCartDistIECSharedMem <> 0 THEN
	mpCartDistIECSharedMem^ := cartDist;
	SetCartDist := TRUE;
END_IF

//SetHasCartDist
METHOD SetHasCartDist : BOOL
VAR_INPUT
	HasCartDist : BOOL;
END_VAR

VAR_OUTPUT
	//Done : BOOL;
END_VAR

SetHasCartDist := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.HasCartDist := HasCartDist;
	SetHasCartDist := TRUE;
END_IF

//ROS_TRosControlIEC

//{ attribute 'teachcontroldatatype' := 'ROSCONTROLIEC' }
TYPE ROS_TRosControlIEC :
STRUCT
	Version	: STRING(16);
	CommandNum	: DINT;
	Execute	: BOOL;
	ExecuteLocked: BOOL;	
	HasDyn	: BOOL;
	HasOvl	: BOOL;
	Ready	: BOOL;	//Set by TC when Begin is called.  
	HasError	: BOOL;	//Set by TC
	HasCartPos  : BOOL;
	HasAxisPos  : BOOL;	
	HasCartDist : BOOL; //support CartDist
   
   DOut  : LWORD;
   DIn   : LWORD;
END_STRUCT
END_TYPE

//ROS_TC==>ResetFlags

ResetFlags := FALSE;
IF SetExecute(FALSE) AND_THEN SetExecuteLocked(FALSE) (*AND_THEN SetAbort(FALSE)*) AND_THEN SetHasDyn(FALSE) 
	AND_THEN SetHasCartPos(FALSE) AND_THEN SetHasAxisPos(FALSE) AND_THEN SetHasOvl(FALSE) AND_THEN SetHasCartDist(FALSE) THEN 
	
	ResetFlags := TRUE;
END_IF


// FB_LinRelTCP

METHOD FB_INIT : BOOL
VAR_INPUT
	bInitRetains : BOOL; // TRUE: the Retain-variables are initialized (reset warm / reset cold)
 	bInCopyCode : BOOL;  // TRUE  the instance will be copied to the copy-code afterward (online change)
END_VAR

iCmdNum_ := TTcPlcCommand.TTcPlcCommandLinRelTCP;

FUNCTION_BLOCK FB_LinRelTCP
VAR_IN_OUT
	RosTC	: ROS_TC;
END_VAR

VAR_INPUT
	pCartDist : POINTER TO TCartDistIEC;
	pDyn : POINTER TO TDynamic;
	pOvl : POINTER TO TOvlIEC;
	Execute : BOOL;
END_VAR

VAR_OUTPUT
	ExecuteLocked : BOOL;
	Error : BOOL;
END_VAR

VAR
	iState : DINT;
	iCmdNum_ : DINT;
END_VAR

IF Execute THEN
	CASE iState OF
	0:
		ExecuteLocked := FALSE;
		IF RosTC.Busy THEN
			RETURN;
		END_IF
		
		//Check that a CartDist has been set
		Error := NOT(pCartDist <> 0);
		IF Error THEN
			iState := 40;
			ExecuteLocked := TRUE;
			RETURN;
		END_IF
		
		//reset flags
		RosTC.ResetFlags();
		
		//set cartDist flag
		RosTC.SetHasCartDist(TRUE);
		RosTC.SetCartDist(pCartDist^);
		
		IF pDyn <> 0 THEN
			RosTC.SetDyn(pDyn^);
			RosTC.SetHasDyn(TRUE);
		END_IF
		
		IF pOvl <> 0 THEN
			RosTC.SetOvl(pOvl^);
			RosTC.SetHasOvl(TRUE);
		END_IF
		
		RosTC.SetCommandNum(CommandNum := iCmdNum_);
		RosTC.SetExecute(TRUE);
		iState := 10;
	10:
		ExecuteLocked := RosTC.ExecuteLocked;
		IF ExecuteLocked THEN
			iState := 40;
		END_IF		
	END_CASE
ELSE
	iState := 0;
	ExecuteLocked :=FALSE;
	Error := FALSE;	
END_IF


//TRMI_Flags(STRUCT)
//Flags used to exec the TC blocks in ROS_RobotMovementInterface.  Abort is special and gets to exist outside of this.
TYPE TRMI_Flags :
STRUCT
	bExecLinFlex : BOOL;
	bExecPTPFlex : BOOL;
	bExecLinRelTCP : BOOL;//新增
	bExecSync : BOOL;
	//bExecDyn : BOOL;	//Not used
	bExecSetting	: BOOL;
	bExecWaitIsFinished : BOOL;
	bExecTool	: BOOL;
   bExecSetDO   : BOOL;
END_STRUCT
END_TYPE


//ROS_RobotMovementInterface
fbLinRelTCP : FB_LinRelTCP;


fbLinRelTCP(
	RosTC := TC,
	pCartDist := pCdItf,
	pDyn := pDyn_,
	pOvl := pOvl_,
	Execute := flags_.bExecLinRelTCP,
	ExecuteLocked=> ,
	Error =>);

IF fbLinRelTCP.ExecuteLocked THEN
	flags_.bExecLinRelTCP := FALSE;
	
	IF fbLinRelTCP.Error THEN
		strSend := 'error$n';
	ELSE 
		strSend := 'done$n';
	END_IF
	
	fbLinRelTCP(
		RosTC := TC,
		pCartDist :=0,
		pDyn := 0,
		pOvl := 0,
		Execute := flags_.bExecLinRelTCP,
		ExecuteLocked=> ,
		Error =>);
		
	bSend := TRUE;		
END_IF
	

```




## 参考文献


