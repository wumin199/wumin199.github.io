---
layout: post
title: Android 源码分析 —— 从 Toast 出发
categories: Android
description: Android 源码分析，深入解析 Toast。
keywords: RTFSC, Android, Toast
---

本系列文章在 <https://github.com/mzlogin/rtfsc-android> 持续更新中，欢迎有兴趣的童鞋们关注。

![](/images/posts/android/toast.png)

（图 from Android Developers）

Toast 是 Android 开发里较常用的一个类了，有时候用它给用户弹提示信息和界面反馈，有时候用它来作为辅助调试的手段。用得多了，自然想对其表层之下的运行机制有所了解，所以在此将它选为我的第一个 RTFSC Roots。

本篇采用的记录方式是先对它有个整体的了解，然后提出一些问题，再通过阅读源码，对问题进行一一解读而后得出答案。

本文使用的工具与源码为：Chrome、插件 insight.io、GitHub 项目 [aosp-mirror/platform_frameworks_base][3]

**目录**

<!-- vim-markdown-toc GFM -->

* [Toast 印象](#toast-印象)
* [提出问题](#提出问题)
* [解答问题](#解答问题)
    * [Toast 的超时时间](#toast-的超时时间)
    * [能不能弹一个时间超长的 Toast？](#能不能弹一个时间超长的-toast)
    * [Toast 能不能在非 UI 线程调用？](#toast-能不能在非-ui-线程调用)
    * [应用在后台时能不能 Toast？](#应用在后台时能不能-toast)
    * [Toast 数量有没有限制？](#toast-数量有没有限制)
    * [`Toast.makeText(…).show()` 具体都做了些什么？](#toastmaketextshow-具体都做了些什么)
* [总结](#总结)
    * [补充后的 Toast 知识点列表](#补充后的-toast-知识点列表)
    * [遗留知识点](#遗留知识点)
    * [本篇用到的源码分析方法](#本篇用到的源码分析方法)
* [后话](#后话)

<!-- vim-markdown-toc -->

## Toast 印象





## 解答问题

### Toast 的超时时间






机制弄清楚了，再详细看一下应用接到通知 show 和 hide 一个 Toast 后是怎么做的：

文件 [platform_frameworks_base/core/java/android/widget/Toast.java][4]





## 后话

到此，上面提到的几个问题都已经解答完毕，对 Toast 源码的分析也告一段落。



<div align="center"><img width="192px" height="192px" src="https://mazhuang.org/assets/images/qrcode.jpg"/></div>

[1]: https://developer.android.com/reference/android/widget/Toast.html
[2]: https://developer.android.com/guide/topics/ui/notifiers/toasts.html
[3]: https://github.com/aosp-mirror/platform_frameworks_base
[4]: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/widget/Toast.java
[5]: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/services/core/java/com/android/server/notification/NotificationManagerService.java
[6]: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/services/core/java/com/android/server/policy/PhoneWindowManager.java
[7]: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/annotation/IntDef.java
[8]: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/view/WindowManager.java
[9]: https://github.com/aosp-mirror/platform_frameworks_base/commit/aa07653d2eea38a7a5bda5944c8a353586916ae9
[10]: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/res/res/layout/transient_notification.xml
[11]: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/services/core/java/com/android/server/notification/NotificationManagerService.java
