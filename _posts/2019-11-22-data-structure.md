---
layout: post
title: 数据结构与算法
categories: 数据结构
description: 
keywords: 数据结构
---


**目录**


* [综述](#综述)
    * [复杂度](#复杂度)
* [参考文献](#参考文献)


## 综述

主要参考资料：浙江大学陈越[数据结构(第2版)](https://book.douban.com/subject/26955385/)以及MOOC视频教程、[算法（第4版）](https://book.douban.com/subject/19952400/)

|课程|参考资料|备注|
|--|--|--|
|[数据结构(第2版)](https://book.douban.com/subject/26955385/)|[上课笔记(含源码)](https://github.com/callmePicacho/Data-Structres)||
||||



### 复杂度

空间复杂度：可能用到的最大内存是多少

![](/images/数据结构/空间复杂度.PNG)

(递归调用的时候，PrintN(100000)时，需要递归调用PrintN(99999),只有等PrintN(99999)执行完，才会执行PrintN(100000)后面的printf()。所以此时需要一块内存区，来保持当前的状态，极为“100000”，依次进行)

所以一般少用递归，数据量大的说话，递归很耗内存。一旦内存爆掉了，程序就会内存泄漏会非正常退出。

时间复杂度：执行所需的时间（由于计算机做加减法比乘除法快得多，所以可以忽略掉加减法，只统计乘除法次数就行）

![](/images/数据结构/时间复杂度.PNG)

(同时当n很大的时候，n^2>>n)

一般用最坏情况的复杂度来度量，而不是用平均复杂度来度量

总结：

![](/images/数据结构/复杂度.PNG)

S(n)具有同T(n)类似的概念

其中的f(n)是关于n的函数

如例3图中的f(n)=C1n^2+C2n

![](/images/数据结构/复杂度规模.PNG)




## 参考文献

- [五分钟学算法](https://www.cxyxiaowu.com/)
- [LeetCodeAnimation](https://github.com/MisterBooo)
- [visualgo](https://visualgo.net/en)
