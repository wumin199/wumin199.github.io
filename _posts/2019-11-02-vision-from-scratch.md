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
    * [OpenCV3安装](#OpenCV3安装)
* [realSense](#realSense)
    * [SDK2 安装](#SDK2 安装)
* [参考文献](#参考文献)


## 综述

1. 初步理解篇

|课程|介绍|说明|
|--|--|--|
|[MATLAB图像处理案例](https://edu.51cto.com/course/16757.html)|通俗易懂，快速了解||


2. 基础理论篇

|课程|介绍|说明|
|--|--|--|
|书籍：[机器人学、机器视觉与控制--MATLAB算法基础](https://book.douban.com/subject/26915869/)|基本理论介绍|[源代码](http://petercorke.com/wordpress/rvc/)|
|网易云课堂： [Python+OpenCV图像处理](https://study.163.com/course/courseMain.htm?courseId=1005317018&share=1&shareId=2493122)|||
|网易云课堂： [用Opencv处理图像【Python版】](https://study.163.com/course/courseMain.htm?courseId=1209425909)|||
|书籍：[学习OpenCV3](https://book.douban.com/subject/30302142/)||[源代码](https://github.com/oreillymedia/Learning-OpenCV-3_examples)|


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


|概念|单位|说明|
|--|--|--|
|Radiometric quantities(辐射量)|W|功率单位。包含所有的能量，包含可以看见的和不能看见的（比如热量）|
|photometric quantities(光度量)<br>即luminous flux(光通量)|流明lumens（简写为lm）|功率单位。指人眼所能感觉到的辐射功率。|
|Lightmeters(光照度)|lm m–2 or lux (lx勒克斯)|测量光通量luminous flux。每平米上的光通量|
|Lightmeters(光度计)|lm m–2 or lux (lx勒克斯)|可以测量光通量(luminous flux)|
||||
||||
||||




intensity：一般表示功率(w或者lm)或者功率/m2之类的
illumination：照度
luminance≈luminosity:发光性(亮度)

brightness≈intensity≈luminance≈luminosity(亮度或者光强或者明度或明亮程度)

color:

**立体角和球面度概念**

![](/images/视觉/平面角.PNG)

![](/images/视觉/立体角.PNG)
(研究的是dS在某点下所张的角度，所以有涉及到cosθ))





参考资料：

* [半球积分](https://chengkehan.github.io/HemisphericalCoordinates.html)
* [平面角与立体角](https://wenku.baidu.com/view/32b09d1e866fb84ae45c8dcb.html)
* [辐射照度、辐射强度、光照度、发光强度（差异以及如何相互转换）（易懂讲解）](https://blog.csdn.net/a6333230/article/details/90036993)
* [发光强度/光通量/光照度/亮度/坎德拉/流明/勒克斯/尼特之间的关系和换算](https://blog.csdn.net/LEON1741/article/details/81237576)

## OpenCV

### OpenCV3安装




## realSense

### SDK2 安装

参考资料：[Intel® RealSense™ SDK 2.0](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_windows.md)




参考资料：

* [Intel RealSense Depth Camera D435i 开箱拆解](https://www.chiphell.com/thread-1945054-1-1.html)






## 参考文献


