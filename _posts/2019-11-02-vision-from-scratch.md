---
layout: post
title: 机器视觉入门
categories: 机器视觉
description: 
keywords: 机器视觉
---


**目录**


* [综述](#综述)
* [机器人学、机器视觉与控制--MATLAB算法基础](#机器人学、机器视觉与控制--MATLAB算法基础)
* [OpenCV](#OpenCV)
  * [opencv-python](#opencv-python)
* [realSense](#realSense)
    * [SDK2安装](#SDK2安装)
    * [相机标定](#相机标定)
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




### opencv-python

## realSense

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



参考资料：

* [Intel® RealSense™ SDK 2.0](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_windows.md)
* [Intel RealSense Depth Camera D435i 开箱拆解](https://www.chiphell.com/thread-1945054-1-1.html)



### 相机标定

![](/images/视觉/相机标定.jpg)


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






## 参考文献


