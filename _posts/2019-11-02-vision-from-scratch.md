---
layout: post
title: 机器视觉入门
categories: 机器视觉
description: 
keywords: 机器视觉
---


![](/images/posts/xxx/xx.png)


**目录**


* [综述](#综述)
* [机器人学、机器视觉与控制--MATLAB算法基础](#机器人学、机器视觉与控制--MATLAB算法基础)
* [OpenCV](#OpenCV)
  * [opencv-python](#opencv-python)
* [realSense](#realSense)
    * [SDK2 安装](#SDK2 安装)
* [参考文献](#参考文献)


## 综述

1. 初步理解篇

| 课程                                                          | 介绍               | 说明 |
| ------------------------------------------------------------- | ------------------ | ---- |
| [MATLAB图像处理案例](https://edu.51cto.com/course/16757.html) | 通俗易懂，快速了解 |      |


2. 基础理论篇

| 课程                                                                                                                          | 介绍         | 说明                                                                 |
| ----------------------------------------------------------------------------------------------------------------------------- | ------------ | -------------------------------------------------------------------- |
| 书籍：[机器人学、机器视觉与控制--MATLAB算法基础](https://book.douban.com/subject/26915869/)                                   | 基本理论介绍 | [源代码](http://petercorke.com/wordpress/rvc/)                       |
| 网易云课堂： [Python+OpenCV图像处理](https://study.163.com/course/courseMain.htm?courseId=1005317018&share=1&shareId=2493122) |              |                                                                      |
| 网易云课堂： [用Opencv处理图像【Python版】](https://study.163.com/course/courseMain.htm?courseId=1209425909)                  |              |                                                                      |
| 书籍：[学习OpenCV3](https://book.douban.com/subject/30302142/)                                                                |              | [源代码](https://github.com/oreillymedia/Learning-OpenCV-3_examples) |


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


| 概念                                                      | 单位                   | 说明                                                             |
| --------------------------------------------------------- | ---------------------- | ---------------------------------------------------------------- |
| Radiometric quantities(辐射学)                            | W                      | 功率单位。包含所有的能量，包含可以看见的和不能看见的（比如热量） |
| photometric quantities(光度学)<br>即luminous flux(光通量) | 流明lumens（简写为lm） | 功率单位。指人眼所能感觉到的辐射功率。                           |
| 色度学                                                    |                        | RGB或者LAB等                                                     |


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

![](/images/视觉/图像的傅里叶变换.png)

(白色的低频部分)

(右边图就是频率分布图谱，其中越靠近中心的位置频率越低，越亮（灰度值越高）的位置代表该频率的信号振幅越大。fft的结果是复数形式，保留了图像的全部信息，但去绝对值得到的频谱图只表现了振幅而没有体现相位。)

![](/images/视觉/图像的傅里叶变换2.png)

一般的波形或者说信号（Signal）都是基于时间尺度上的采样结果，因此也称为时域（Time Domain），而上面泡面的例子和我们将要处理的图像信号则是基于空间尺度上的采样，但好像并没有“空域（Space Domain）”这一说，毕竟我们对空间的感知仍然依赖于时间。不过在空间尺度上我们可以更直观地认为信号是静止，例如下面这张图像（灰度图），其实是由250x250个像素点组成，每个像素点的灰度值（$[0, 255]$）就是基于像素坐标的空间采样的结果:

![](https://upload-images.jianshu.io/upload_images/21342-0cd675f781c098fa.png?imageMogr2/auto-orient/strip|imageView2/2/w/900/format/webp)


跟一维信号处理一样，傅里叶变化，把图像从“空域”变为“频率”。对于一幅图像，高频部分代表了图像的细节、纹理信息；低频部分代表了图像的轮廓信息。如果对一幅精细的图像使用低通滤波器，那么滤波后的结果就剩下了轮廓了。这与信号处理的基本思想是相通的。如果图像受到的噪声恰好位于某个特定的“频率”范围内，则可以通过滤波器来恢复原来的图像。


参考链接:
* [图像的傅里叶变换](https://www.jianshu.com/p/2704b5d1d6bb)
* [图像的傅里叶变换](https://wenku.baidu.com/view/c5e2cca8fab069dc502201db.html)
* [图像的傅里叶变换](https://www.jianshu.com/p/89ce7fdb9e12)




### opencv-python

## realSense

### SDK2 安装

参考资料：[Intel® RealSense™ SDK 2.0](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_windows.md)




参考资料：

* [Intel RealSense Depth Camera D435i 开箱拆解](https://www.chiphell.com/thread-1945054-1-1.html)






## 参考文献


