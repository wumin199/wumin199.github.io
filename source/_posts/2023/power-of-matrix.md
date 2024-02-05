---
title: 《矩阵力量》课程笔记
date: 2023-07-16 22:05:15
tags: 笔记
toc: true
comment: true
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3

---

有数据的地方，必有矩阵！有矩阵的地方，更有向量！
有向量的地方，就有几何！有几何的地方，皆有空间！
有数据的地方，定有统计！

<!-- more -->

---

## 概要

- [《矩阵力量》--姜伟生,清华大学出版社,2023年6月第一版](https://book.douban.com/subject/36424128/)(本课程基于此书)
- [github: power-of-matrix](https://github.com/Visualize-ML/Book4_Power-of-Matrix)(含勘误表)
- [配套B站视频](https://space.bilibili.com/513194466)
- [notion：矩阵力量](https://wumin199.notion.site/b4913149815448a4bcbc5b41c690fec6)
- [github：wm-power-of-matrix](https://github.com/wumin199/wm-power-of-matrix)
- [Typst: power-of-matrix](https://typst.app/project/pIVVvBW-Q4xUWGthZc6PjN)
- [github: wm-test-case](https://github.com/wumin199/wm-test-case)(python测试案例)

重点：第5章

再出一章：极简版，一句话概述

---


## 速记

1. 投影分向量投影和标量投影。方向向量只提供方向，其大小不管是多少都会被归一化。标量投影和投影向量的方向有关和大小无关，向量投影 = 标量投影*单位投影向量。
2. 向量投影：有些是xv，有些是vx写法，只要结果是个标量就行了！


## 课程笔记

### 前言

- `VI页` 介绍了如何用python包 [streamlit](https://streamlit.io/)制作数学动画，并配套了
  - [Streamlit做数学动画、机器学习App](https://www.bilibili.com/video/BV1oV4y1E7GZ/?spm_id_from=333.999.0.0&vd_source=991bc0898d44d84ddbbb0469ce816e70)
  - [运动的椭圆---Streamlit做数学动画、机器学习APP](https://www.bilibili.com/video/BV1CT411J7Ey/?spm_id_from=333.999.0.0&vd_source=991bc0898d44d84ddbbb0469ce816e70)
  - [圆周率 0~9 出现频率---Streamlit做数学动画、机器学习APP](https://www.bilibili.com/video/BV1nd4y1D7f7/?spm_id_from=333.999.0.0&vd_source=991bc0898d44d84ddbbb0469ce816e70)
- 纸质书有一些错误，可以参考开源的pdf

  - 参考
    - [python 画图 matplotlib, sympy, mpmath与 Matlab, R 比较](https://blog.csdn.net/robert_chen1988/article/details/80465255)
    - [一个 Python 库（ mpmath 库）的 plot 函数](https://blog.csdn.net/yong1585855343/article/details/115547039)

---

### 不止向量

- `P8` 基本概念：花萼长度（sepal length）、花萼宽度（sepal width）、花瓣长度（petal length）、花瓣宽度（petal width），以及最重要的本书的鸢尾花数据矩阵X，并给出了数据矩阵列向量和行向量的符号表示
  ![鸢尾花数据矩阵X](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/IrisDataSet.png)
  ![行向量，用上标；列向量，用下标](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/X_notation.png)
- `P12` 正交是线性代数的概念，是垂直的推广
- `P15` 成对特征散点图的纵坐标画错了，纵坐标从低到高应该是P、P、S、S。这幅图的详细介绍可以看《数学要素》P420。成对特征散点图可以用来可视化4个特征的数据集（花萼长度、花萼宽度、花瓣长度、花瓣宽度）。对角线的4幅图叫概率密度估计曲线。作者将在《统计至简》中讲述概率密度估计。
- `P16` 将数据云的质心平移到原点，这个过程叫去均值化过程
- `P17` 代数视角：矩阵乘法代表线性映射，具体参考[Typst: power-of-matrix](https://typst.app/project/pIVVvBW-Q4xUWGthZc6PjN)
- `P17` 几何视角：矩阵完成的是线性变换，平面是由矩阵各列的base（基底）张成的
  ![可以利用这个变化，将单位圆转换为椭圆](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/Ch01-linear-transformation.png)
- `P18` 利用矩阵A，可以将单位圆转化为旋转椭圆，要了解椭圆的信息，需要用到特征值分解。需要注意的是，鸢尾花数据矩阵不能完成特征值分解，但是格拉姆矩阵（对称矩阵）可以完成特征值分解
- `P18` 不同于特征值分解，不管形状如何，任何实数矩阵都可以完成奇异值分解（SVD）
- `P20` 多个特征之间的关系，如花萼长度、花萼宽度、花瓣长度、花瓣宽度，可以使用格拉姆矩阵（方阵）、协方差矩阵、相关性系数矩阵等矩阵来描述。而某个特征内部，可以用均值、均方差、概率密度估计进行表征。
- `P21` 总结了鸢尾花数据矩阵X（nx4）衍生出的各种矩阵，并在后续章节中介绍
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/Ch01-derived-matrix.png)  

---

### 向量运算

- `P24` 向量运算汇总
  ![向量运算汇总](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-vector-calculations.jpg)
- `P26` 使用`pylot.quiver()`绘制矢量箭头图
  `plt.quiver(X, Y, U, V, angles='xy', scale_units='xy')` 中，
  X 和 Y：箭头的起始位置坐标，可以是数组、列表或网格。
  U 和 V：箭头的水平和垂直分量，可以是数组或列表。它们的长度应与 X 和 Y 相同。
- `P27` 自然界的风、水流、电磁场，在各自空间的每一个点上对应的物理量既有强度、也有方向。将这些既有大小又有方向的场抽象出来便得到**向量场(vector field)**
  本书中，我们会使用向量场来描述函数在一系列排列整齐点的梯度向量。
  梯度下降方向 -- 下山方向
  梯度向量（gradient vector） -- 上山方向
  ![梯度定义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/Ch01-gradient-vector.png)
  ![梯度向量--上山方向](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/Ch01-gradient-vector-2.png)
- `P28` 观察数据矩阵的两个视角
  ![行列向量](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-matrix-view.png)
  - `P28` 用numpy构造行向量，`numpy的array默认是行向量`，测试可以看[github: wm-test-case](https://github.com/wumin199/wm-test-case)，下同
  - `P29` 数据分析偏爱用行向量表达样本点，`本书默认的向量指列向量`
  - `P30` 用numpy构造列向量
  - `P30` 可以用numpy.zeros()或者numpy.ones()生成全零/全1向量
  - `P31` 向量长度又叫向量模(norm)，欧几里得距离(Euclidean distance)、欧几里得范数(Euclidean norm)或L^2范数(L2-norm)
  - `P32` 函数np.linalg.norm默认计算L^2范数
  - `P33` python中绘制等高线
- `P35` 理解向量a-b
  ![向量减法](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-vector-subtraction.png)
- `P37` 向量内积(dot product/inner product)：结果为标量
  - 向量内积 (inner product)，又叫标量积 (scalar product)、点积 (dot product)、点乘
  - 定义、公式（符号）、常见公式
    ![内积公式](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-inner-product.png)
    ![常见内积案例](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-inner-product-cases.png)
  - `P38` python的内积
    - np.inner(a,b)
    - np.dot(m1,m2) -> 矩阵乘积
      - np.dot(a,b) -> 向量内积
    - m1 @ m2 -> 矩阵乘积，向量也是特殊的矩阵
      - 行向量a @ 列向量b -> 向量内积
    - np.vdot(a,b) 或这 np.vdot(m1,m2)  -> 都是向量内积，会把矩阵转换为向量。
  - `P39` 几何意义
    - 从几何角度看，向量内积相当于两个向量的模 (L^2 范数) 与它们之间夹角余弦值三者之积
      ![内积几何定义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-inner-product-geometric-perspective-2.png)
      ![内积几何意义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-inner-product-geometric-perspective.png)
  - `P39` 柯西-施瓦茨不等式的推导，核心推导公式是(2.42)
    ![内积取值范围](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-cauchy-derivation.png)
  - `P42` 两点之间的欧式距离
  - `P42` 向量内积无处不在，比如：样本方差公式、样本协方差公式
    ![样本X的方差、协方差。这里的n是样本点个数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/inner_product_in_stats_1.png)
    ![样本X的方差、协方差。这里的n是样本点个数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/inner_product_in_stats_2.png)
    ![样本X的方差、协方差。这里的n是样本点个数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/inner_product_in_stats_2.png)
    ![广播原则](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/numpy_broadcast.png)


- 向量夹角：反余弦
  - `P44` 2个单位向量的内积就是夹角的余弦值
  - `P44` 2个向量内积为0，则互相正交
- 余弦相似度和余弦距离
  - `P45` 机器学习中，余弦相似度用向量夹角的余弦值度量样本数据的相似度
  - `P46` 余弦距离基于余弦相似度，等于1-余弦相似度，取值范围是[0,2]。和L^2范数一样也是一种常见的距离度量
  - `P47` 脚本案例中使用 scipy.spatial 可以直接计算余弦距离。可以学习下from sklearn中的datasets类，iris = datasets.load_iris()返回的是Bunch()类，可以直接用iris.data。Bunch类是基于Dict来实现getter/setter的
- 向量积(vector product)：结果为向量
  - `P47` 向量积(vector product)也叫叉乘(cross product)，向量积结果为向量。也就是说，向量积一种“向量→向量”的运算规则
  - a x b的结果c,方向垂直于a和b, 大小为a和b构成的平行四边形面积
  - `P49` 叉乘的常见性质、正交向量之间的叉乘、任意两个向量之间的叉乘
  - `P49` python的叉乘
    - np.cross()
  - 有些教材把向量积叫做外积(outer product)，有些教材也把张量积叫做外积，注意区分。
- 逐项积（piecewise product）或者阿达玛乘积（Hadamard product）
  - `P50` 记住符号，圆圈中一个点 ⊙
  - `P51` python中的逐项积
    - np.multiply(a,b)
    - python中a*b
- 张量积：张起网格面
  - `P51` 张量积(tensor product) 又叫克罗内克积(Kronecker product)，符号：⊗
  - `P51` 向量张量积是一种“向量→矩阵”的运算规则，同时列出了一些性质
  - `P51` 向量的矩阵表达法
  - `P53` 几何视角：2个向量张起空间，新的空间中的行列和2个向量之间有相似性
    - a ⊗ b 的秩为1； a ⊗ a 为对称阵
    ![张量积的几何视角](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-tensor-product.png)
    ![张量积](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch02/Ch02-tensor-product-2.png)
  - `P53` python实现
    - np.outer
  - `P54` 2个离散随机变量的联合概率，可以看成是张量积
- streamlit中使用KATEX打印数学公式
  - `P54` 中的Streamlit_Bk4_Ch2_13.py，使用了KATEX来打印数学按公式；同时展示了streamlit会绘制plotly图

---

### 向量范数

- overview
  ``P58``
  ![向量范数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch03/Ch03-overview.png)
- L^p范数
  - ``P58``，p>=1时才是范数，L^p范数非负，代表距离，是一种“向量”->"标量"的运算
  - ``P60``，对同一个向量，Lp范数随p增大而减小，Lp范数丈量一个向量的“大小”。p取值不同时，丈量的方式略有差别。在数据科学、机器学习算法中，Lp范数扮演重要角色，比如距离度量、正则化(regularization)
- L^p范数和超椭圆的联系
  - 等高线的含义是：z = f(x,y)，是个三维图，然后投影到取不同的z值，投影到x,y平面中
    ``P62``
    ![Lp等高线](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch03/Ch03-contour.png)
  - ``P63`` p>=1时， Lp范数具有次可加性
  - ``P63`` 代码中给出了用plotly绘制2维/3维可缩放图的方法，并且在streamlit中用到了可折叠的方法(expander)
- 常见距离汇总
  - ``P72`` python实现各类距离，使用plotly，在一张图中绘制等高线和散点图
    ![常见距离](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch03/Ch03-distance.png)
- 高斯核函数：从距离到亲进度
  - ``P73`` 在很多应用场合，我们需要把“距离”转化为“亲近度”，就好比上一章余弦距离和余弦相似度之间的关系。为了把距离||p−x||_q转化成亲近度，我们需要借助复合函数这个工具。本系列丛书《数学要素》一册介绍过高斯函数(Gaussian function)
  ![高斯核函数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch03/Ch03-Gausian.png)
  ![e^(-x)](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch03/Ch03-e-x.png)

---

### 矩阵

- python中的矩阵构造方法
  ``P81``, np.matrxi()和np.array()都可以
- 矩阵形状
  - ``P83`` 对角矩阵也可以是长方形矩阵， 这在SVD分解中有用到
  - ``P84`` 如果矩阵A为可逆矩阵(invertible matrix, non-singularmatrix)，A可以通过LU分解变成一个下三角矩阵L与一个上三角矩阵U的乘积
  - ``P84`` 计算时，长方形矩阵的形状并不“友好”。比如，很多矩阵分解都是针对方阵。可以将长方形矩阵转换为方阵（格拉姆矩阵）
    ![格拉姆矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch03/Ch03-gram-matrix.png)
  - ``P84`` 处理长方形矩阵有一个利器，这就是奇异值分解(SVD)
- 矩阵乘法
  - ``P88`` 矩阵两大主要功能：1)表格；2) 线性映射。线性映射就体现在矩阵乘法中。比如Ax= b完成→xb的线性映射；反之，如果A可逆，A−1完成→bx的线性映射。
  - ``P89`` 矩阵乘法是一种“矩阵→矩阵”的运算规则
  - ``P89`` python中矩阵乘法：
    - np.matmul(A, B)
    - A @ B
    - 如果是 np.array: * -> 逐元素相乘； 如果是np.matrix: * -> 矩阵乘法
    - 对np.array: **2 -> 逐元素平方； np.matrix： **2 -> 矩阵的幂
  - ``P90`` 列出常见矩阵乘法性质，其中：不满足交换律
- 两个视角解析矩阵乘法
  - ``P90~P91`` 常规视角（第一视角）：标量积展开；第二视角：外积（张量积）展开，也可以参考第6章的矩阵乘法第一视角和第二视角(`P150`)
- 转置矩阵
  - ``P93`` 列向量和自身的张量积，是对称矩阵
  - ``P93`` 列出了矩阵转置的一些性质，和内积的一些关系
    - <span id="ch04_transpose">向量内积的矩阵表示法，以及矩阵平方和</span>
      ![转置](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch03/Ch03-transpose.png)
- 逆矩阵：相当于除法运算
  - ``P94`` 矩阵可逆(invertible) 也称非奇异(non-singular)；否则就称矩阵不可逆(non-invertible)，或称奇异(singular)
  - ``P94`` 矩阵求逆“相当于”除法运算，但是两者有本质上的区别。矩阵的逆本质上还是矩阵乘法。
  - ``P94`` 逆矩阵常见运算
  - ``P94`` 正交矩阵：A^T=A^-1
- 迹：主对角元素之和
  - ``P96``  “迹”这个运算是针对“方阵”定义的
  - ``P96`` 迹的一些性质
- 逐项积，或者阿达玛乘积
  - ``P97`` A ⊙ B
  - ``P97`` numpy:
    - np.multiply(A, B)
    - A * B
- 行列式：将矩阵映射到标量值
  - ``P98`` 每个“方阵”都有自己的行列式(determinant),如果方阵的行列式值非零，方阵则称可逆或非奇异
  - ``P98`` 矩阵的行列式值可正可负，也可以为0。列出了行列式的一些性质
  - ``P99`` 向量积可以通过行列式得到
  - ``P101`` 列出了特殊的2x2方阵的行列式以及图形，可以看到几何意义
    - ``P100`` 以a1和a2为两条边构造得到一个平行四边形。这个平行四边形的面积就是A的行列式值。
    - ``P102`` 代码，在streamlit中用Katex显示出矩阵，并绘制坐标网格，以及变化后的平行且等距的网格线，以及网格线变换和行列式几何意义，用np.stack拼接矩阵
  - ``P102`` 3×3方阵的行列式值的几何意义
    - 对于任意3×3方阵A，它的行列式值的几何含义就是由其三个列向量a1、a2、a3构造的平行六面体的体积。注意，这个体积值也有正负。
    - 单位矩阵I的行列式为1
    - 如果a3在a1、a2构造的平面中，平行六面体体积为0，即方阵A行列式值为0，这种情况下，a1、a2、a3线性相关，A的秩为2
    - 在线性变换中，变换矩阵的行列式值代表面积或体积缩放比例
  - ``P103`` 对角方阵的行列式为对角元素的乘积
  - ``P103`` 平行四边形到矩形：可对角化矩阵和特征值分解
    - 我们遇到的方阵大部分不是对角方阵，计算其面积或体积显然不容易。有没有一种办法能够将这些方阵转化成对角方阵？也就是说，把平行四边形转化成矩形，把平行六面体转化为立方体？
    - 并不是所有的方阵都可以转化为对角方阵，能够完成对角化的矩阵叫可对角化矩阵
    - 如果可以对角化，则对角化方法为：**特征值分解**，而且前后方阵的trace一样
  - [线性代数为什么要研究行列式？](https://www.zhihu.com/question/615552517/answer/3151269382)
    矩阵的行列式与矩阵的关系类似向量的模长与向量的关系。模长是向量的某种几何尺度，是向量非零端与零点之间的距离，而矩阵的行列式也是由矩阵的列向量们围成的几何体的“体积”
    ![线性代数为什么要研究行列式？](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch04/ch04-determinant.png)

---

### 矩阵乘法

- overview
  - ``106``
    ![矩阵乘法](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/overview.png)
- <span id="ch05_vector_vector">向量和向量</span>
  - ``P107`` 向量·向量的几何含义是向量内积，`向量内积的运算中间有点，矩阵相乘中间没有点`, 同[矩阵转置](#ch04_transpose)
    ![向量内积的矩阵乘法表示](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/vector_in_matrix.png)
  - ``P108`` 全1列向量具有复制功能：全1列向量1乘行向量a，相当于对行向量a进行复制、向下叠放。列向量b乘全1列向量1转置，相当于对列向量b复制、左右排列
  - ``P109`` 全1对列向量x元素求和；向量x·向量x，可以求元素平方和。这在统计学的方差、协方差中有用到
    ![向量元素和](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/vector_mean.png)
    ![向量元素平方和](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/vector_variance.png)
  - ``P110`` 向量相乘（·） -- 内积（<>） -- 矩阵相乘(没有点或者是@) -- 范数 -- 标量
    提醒大家注意，但凡遇到矩阵乘积结果为标量的情况，请考虑是否能从“距离”角度理解这个矩阵乘积
    ![向量相乘](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/vector_inner_matrix.png)
  - ``P111`` 向量的张量积也可以写成矩阵形式
    ![向量的张量积](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/vector_tensor.png)
- 全1列向量在求和方面的用途
  - ``P112`` 全1矩阵具有复制的功能：可以用于求数据矩阵的行元素的和、列元素的和、所有元素的和
    ![每列元素求和](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/sum_in_column.png)
    ![每行元素求和](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/sum_in_row.png)
    ![所有元素求和](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/sum_in_matrix_all.png)
    ![应用：去均值](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/one_vector_mean.png)
    ![应用：成绩分布、成绩变化趋势](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/application_in_sum.png)
  - ``P114`` 张量积1⊗1是个n×n方阵，矩阵的元素都是1
- 矩阵乘向量：线性方程组
  - ``P117`` 解的个数:若线性方程组有唯一一组解，矩阵A可逆；如果A^TA可逆，则可以用广义逆或伪逆来求解；如果A^TA非满秩，则A^TA不可逆，这种情况需要用摩尔-彭若斯广义逆(Moore–Penrose inverse)。函数numpy.linalg.pinv() 计算摩尔-彭若斯广义逆。这个函数用的实际上是奇异值分解获得的摩尔-彭若斯广义逆。
    ![广义逆、伪逆](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/pseudoinverse.png)
  - ``P117`` 从向量、几何、空间、数据等视角理解Ax= b。
    - ``P118`` A的列向量的线性组合系数x构成线性组合
    - ``P118`` 其中如果x和b在同一个空间，没有降维，则成为线性映射
    - ``P119`` 几何变换：x经过各类线性变换（缩放/平移/旋转/...）后变成b
      ![旋转矩阵1和矩阵乘向量理解1](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/geo_view.png)
      ![旋转矩阵1和矩阵乘向量理解2](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/geo_view2.png)
    - ``P119`` 向量模：x^TA^TAx这种矩阵乘法的结果为非负标量，其中A^TA叫做A的格拉姆矩阵。x^TA^TAx就是下一节要介绍的二次型
- 向量乘矩阵乘向量：二次型 x^TQx = q，其中Q为对称矩阵
  ![二次型，Q是对称矩阵， 单项式变量的最高次数是2，这就是x^TQx被成为二次型的原因](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/quadratic_form.png)
  - y=f(x) -> 一元函数
  - z=f(x,y) 或 y=f(x1,x2) -> 二元函数
  ![二次型和二元函数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/quadratic_form_curve.png)
  - 三个方阵连乘
    - ``P122`` V^TΣV, V和Σ都是D×D方阵，上式结果也是D×D方阵。特别地，实际应用中V多为正交矩阵。矩阵(i,j)元素v_i^TΣv_j便是一个二次型，viTΣvj对应的运算示意图如图21所示。这说明，上式包含了D×D个二次型。
- 方阵乘方阵：矩阵分解
- 对角阵：批量缩放
  - ``P124`` 左乘、右乘、行乘、列乘、左右都乘
    ![Λ的对角线元素相当于缩放系数，分别对矩阵X的每一列数值进行不同比例缩放。如果缩放系数都是1，且对角阵的每列顺序有交换则就起到置换矩阵交换每列的作用](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/dia_matrix.png)
    ![Λ的对角线元素分别对矩阵X 的每一行数值进行批量缩放。如果缩放系数为1，且对角镇的每行顺序发生交换，则起到置换矩阵交换每行的作用](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/dia_matrix_2.png)
    以上对Permutation矩阵也是一样的道理。口诀：单行矩阵@单列矩阵=数，多行矩阵@单列矩阵=单列矩阵，多行矩阵@多列矩阵=多行多列矩阵
  - ``P126`` 特殊的二次型，只有x_1^2项目，没有x1*x2项
- 置换矩阵：调换元素顺序
  - `P127` 行向量a乘副对角矩阵，如果副对角线上元素都为1，得到左右翻转的行向量。完成左右翻转的方阵是置换矩阵(permutation matrix) 的一种特殊形式。
  - `P127` 置换矩阵是由0和1组成的方阵。置换矩阵的每一行、每一列都恰好只有一个1，其余元素均为0。置换矩阵的作用是调换元素顺序。
  - 可以用来调整列向量顺序、调整行向量顺序，这一点和上面的对角线的左右乘效果类似的。置换矩阵可以用来简化一些矩阵运算。
- 矩阵乘向量：映射到一维
  这里的矩阵在左边，是样本点数据矩阵
  视角：矩阵各列的线性组合 -> 一维
  ![矩阵乘向量的理解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/matrix_vector.png)
  - `P129` 以鸢尾花数据为例理解矩阵乘向量
- 矩阵乘矩阵：映射到多维
  这个案例中，样本点矩阵在左边，右边的矩阵是方向矩阵（或者叫新的坐标系的各个坐标轴。方向矩阵还是以列为基础表示坐标，和数据矩阵是用行还是列表示无关）
  ![样本点矩阵乘矩阵：2个方向映射的理解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/matrix_matrix.png)
  ![样本点矩阵乘新矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/matrix_matrix_many.png)
  理解：每一行是一个样本，变换后的每一行是一个样本在新的坐标系下的坐标值。原来是一行代表一个点，现在也是一行代表一个点。但是方向矩阵/旋转矩阵依然是以列来衡量的
  ![一个样本点，可以写成列形式或行形式](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/matrix_matrix_vector.png)
  - `P132` 约定俗成，各种线性代数工具定义偏好列向量；但是，在实际应用中，更常用行向量代表数据点。两者之间的桥梁就是——转置。
  **如果样本点的矩阵是：一个行向量代表一个样本点，则一般是XM=Z，这里X和Z都是行向量代表一个点。但是线性代数中的工具样本点一般每一列代表一个样本点，则MX=Z，X和Z都一个列向量代表一个样本点！！**
- 长方阵：奇异值分解、格拉姆矩阵、张量积
  - `P133` 格拉姆矩阵G=X^TX,其对角线元素含有L^2范数信息；每个元素也可以从向量内积角度考虑
  - `P133` 格拉姆矩阵之所以重要，一方面是因为它集成了向量长度(L2范数)和相对夹角(夹角余弦值)两部分重要信息。另一方面，格拉姆矩阵G为对称矩阵。一般情况，数据矩阵X都是“细高”长方形矩阵，矩阵运算时这种形状不够友好。比如，细高的X显然不存在转置。而把X转化为方阵G(=XTX)之后，很多运算都变得更加容易
  - `P136` 矩阵元素平方和，理解连续2个求和符号：相当于程序中的连续2个for循环
    ![求和符号](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/sum_in_matrix.png)
  - `P136` 一个矩阵的所有元素平方和、再开方叫做矩阵F-范数
- 爱因斯坦求和约定
  - `P136` Python中Xarray专门用来存储和运算高阶矩阵
- 矩阵乘法的几个雷区
  - `P139` A(B-C)=O，不能直接得出B= C，这是因为矩阵A不一定可逆
  - `P139` 求逆和转置的顺序
    ![求逆和转置的顺序](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/sequence_in_inv_trans.png)
  - `P139` 逆矩阵和转置矩阵中带乘法和标量的情况；如果分子、分母上都出现同一个矩阵，绝不能消去

---

### 分块矩阵
- `P144` 分块矩阵overview
- 分块矩阵：横平竖直切豆腐
  - `P147` 分块矩阵的转置有2层
- 矩阵乘法的2个视角
  - `P149～P150` 标量积展开 和 外积(张量积)展开
    ![第一视角：标量积展开](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch06/view1.png)
    ![第一视角：标量积展开](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch06/view2.png)
    ![第二视角：外积(张量积)展开](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch06/view3.png)
  - 展开的意思是：一个值是由很多个值叠加起来的
  - 它这里的外积，是指有多个：列矩阵和行矩阵乘法运算后得到的矩阵的叠加
    - 外积展开的思路：将A看成一系列的列向量，B看成一系列的行向量，之后就是这些列和行各自的外积/张量积的展开
    - 在这个基础上，将列矩阵和行矩阵看成是向量，然后张量积是2个列向量的运算，所以对第二个行向量要转置成列向量
    - `P153` 还给出了矩阵运算的可视化图来帮助理解
    - 这个思路对于特征值分解(Eigen Decomposition)、奇异值分解(Singular Value Decomposition, SVD)、主成分分析(Principal Component Analysis, PCA) 非常重要。
    - 学好特征值分解、奇异值分解的关键就是“多视角”——数据视角、向量视角、几何视角、空间视角、统计视角等等。
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch06/matrix_in_tensor.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch06/matrxi_in_tensor_2.png)
- 矩阵乘法更多视角：分块多样化
  `P154` C=AB，分块完，都可以化为以上2个视角来理解！
  - B切成列向量：相当于每个列向量是个样本，A的各列相当于坐标系
  - A切成一组行向量：相当于A的每行是个样本点（鸢尾花数据矩阵），B的每行相当于坐标系向量
- 分块矩阵的逆
  `P160` 分块矩阵的逆将会用在协方差矩阵上，特别是在求解条件概率、多元线性回归时。
- 克罗内克积：矩阵张量积
  `P160` 克罗内克积(Kronecker product)，也叫矩阵张量积，是两个任意大小矩阵之间的运算，运算符为⊗
  - `P161` numpy.kron()可以用来计算矩阵张量积。克罗内克积讲究顺序，一般情况A⊗B≠B⊗A。同时列出了一些性质。
    - 克罗内克积相当于向量张量积的推广；反过来，向量张量积也可以看做克罗内克积的特例。但两者稍有不同，为了方便计算，两个2×1列向量的张量积定义为a⊗b=ab^T

---

### 向量空间

- 向量空间：从直角坐标系说起
  - `P167` 向量空间定义：给定域F，F上的向量空间V是一个集合。集合V非空，且对于加法和标量乘法运算封闭。如果V连同上述加法运算和标量乘法运算满足如下公理（结合律/交换律/过原点等），则称V为向量空间
  - `P168` v1、v2...vD所有线性组合的集合称作v1、v2...vD的张成(span)，记做span(v1,v2...vD)  -> 展成一个空间
    相关：你的条件有多余的，有些是做了重复的工作，有冗余量
    无关：每个都是独立的，有贡献的，不能完全被其他表示出来的
    ![线性相关和线性无关](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch07/linearly-independant.png)
  - `P169` 一个矩阵X的列秩(columnrank) 是X的线性无关的列向量数量最大值。类似地，行秩(row rank) 是X的线性无关的行向量数量最大值.
  - `P169` 极大线性无关组的元素数量r为V={x1, x2, ...,xD}的秩，也称为V的维数或维度。 r <=D
    ![rank(X)的秩和span(X)的空间](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch07/span_rank.png)
  - `P169` 矩阵的列秩和行秩总是相等的，因此就叫它们为矩阵X的秩(rank)，记做rank(X)。rank(X)小于等于min(D, n)
  - `P169` 当rank(X)的秩取不同值时，span(X) 所代表的空间: 1维/2维/3维/...
    - 特别地，若矩阵X的列数为D，当rank(X) = D时，矩阵X列满秩，列向量x1, x2, ...,xD线性无关。
  - `P170` 秩的性质：乘法中/转置矩阵中的秩
  - `P170` 一个向量空间V的基底向量(basis vector)指V中线性无关的v1、v2 ... vD，它们张成(span) 向量空间V，即V= span(v1,v2,...,vD)
  - `P170` 向量空间的维数(dimension) 是基底中基底向量的个数，本书采用的维数记号为dim()
    ![维度为2的向量空间](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch07/dim_2.png)
  - `P172` “过原点”这一点对于向量空间极为重要。向量空间平移后得到的空间叫做仿射空间(affinespace)，几何变换中点仿射变换涉及这一点
  - `P173` 基底中基底向量若两两正交，该基底叫正交基(orthogonal basis)。如果正交基中每个基底向量的模都为1，则称该基底为规范正交基(orthonormal basis)。更特殊的是，[e1, e2]叫做平面2的标准正交基(standard orthonormal basis)，或称标准基(standardbasis)。“标准”这个字眼给了[e1, e2]，是因为用这个基底表示平面2最为自然。[e1, e2]也是平面直角坐标系最普遍的参考系。
  - `P174` 基底转换(change of basis)完成不同基底之间变换，而标准正交基是常用的桥梁
    ![坐标系和坐标值](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch07/coordinate-values.png)
    ![坐标系和坐标值](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch07/matrix-basis.png)
- 给向量空间涂颜色：RGB色卡
  - `P178` 强调一下，红、绿、蓝不是调色盘的涂料。RGB中，红、绿、蓝均匀调色得到白色；而在调色盘中，红、绿、蓝三色颜料均匀调色得到黑色
  - `P178` 在三原色模型这个空间中，任意一个颜色可以视作基底 [e1, e2, e3] 中三个基底向量构成线性组合。RGB三原色可以用10进制/8进制/16进制表示，每个颜色的10进制分量为0 ~ 255 之间整数
- 张成空间：线性组合红、绿、蓝三原色
  - `P182` 一种特殊情况，e1、e2 和e3 这三个基底向量以均匀方式混合，得到的便是灰度：α(e1+e2+e3),这些灰度颜色在原点(0, 0, 0)和(1, 1, 1) 两点构成的线段上：(0, 0, 0)：黑色，(1, 1, 1)：白色。
  - `P183` streamlit中借助pandas的DataFrame和plotly的Scatter3d绘制带颜色的空间散点图
- 非正交基底：青色、品红、黄色
  - `P184` e1([1, 0, 0]T red)、e2([0, 1, 0]T green) 和e3([0, 0, 1]T blue) 这三个基底向量任意两个组合构造三个向量v1([0, 1, 1]T cyan)、v2([1, 0, 1]T magenta) 和v3([1, 1, 0]T yellow)
  - `P184` v1、v2 和v3 线性无关，因此 [v1, v2, v3] 也可以是构造三维彩色空间的基底
  - `P184` 印刷四分色模式 (CMYK color model) 就是基于基底 [v1, v2, v3]。CMYK 四个字母分别指的是青色 (cyan)、品红 (magenta)、黄色 (yellow) 和黑色 (black)
  - `P185` v1、v2 和v3 并非两两正交。经过计算可以发现v1、v2 和v3 两两夹角均为60°，[v1, v2, v3] 为非正交基底
- 基底转换：从红、绿、蓝，到青色、品红、黄色
  - `P187` 通过矩阵A，基底向量 [e1, e2, e3] 转化为基底向量 [v1, v2, v3]。 [v1, v2, v3] = A[e1, e2, e3]

---

### <span id="ch08_geometric_ransformation">几何变换</span>

- `P189` 线性变换的特征是原点不变、平行且等距的网格
- 线性变换：线性空间到自身的线性映射
  - `P191` 线性映射是指从一个空间到另外一个空间的映射，且保持加法和数量乘法运算。如三维物体投影到一个平面上，得到这个杯子在平面上的映像
  - `P191` 线性变换是线性空间到自身的线性映射，是一种特殊的线性映射。白话说，线性变换是在同一个坐标系中完成的图形变换。从几何角度来看，线性变换产生“平行且等距”的网格，并且原点保持固定。原点保持固定，这一性质很重要，因为大家马上就会看到“平移”不属于线性变换
  - `P192` 非线性变换。如产生平行但不等距网格、产生“扭曲”网格
  - `P193` <span id="ch08_transform">常见几何变换</span>。包含了以列向量形式表达坐标点，和以行向量形式表达坐标点。平移并不是线性变换，平移是一种仿射变换(affine  transformation)，对应的运算为y = Ax + b。几何角度来看，仿射变换是一个向量空间的线性映射 (Ax) 叠加平移 (b)，变换结果在另外一个仿射空间。b ≠ 0，平移导致原点位置发生变化。因此，线性变换可以看做是特殊的仿射变换。
- 平移：仿射变换，原点变动
  - `P196` 使用matplotlib绘制带有颜色填充的多边形，conner做标记
- 缩放：对角阵
  - `P198` 只有行列式值不为 0 的方阵才存在逆矩阵
- 旋转：行列式值为1
  - `P201` 旋转矩阵 R 的行列式值为1，也就是说旋转前后面积不变
  - `P203` 旋转 → 缩放”过程是主成分分析 (principal component analysis, PCA) 的思路。反向来看，“缩放  →  旋转”将单位圆变成旋转椭圆的过程，代表利用满足IID  N(0,  I2  ×  2)  二元随机数产生具有指定相关性系数、指定均方差的随机数。IID 指的是独立同分布  (Independent  and Identically Distributed)。
- 矩阵乘法不满足交换律
  - `P205` 但是两个2 × 2 缩放矩阵连乘满足交换律，两个2 × 2 旋转矩阵连乘满足交换律
- 镜像：行列式值为负
  - `P206` 几种镜像：第一种镜像用切向量来完成、第二种镜像通过角度定义、关于横纵轴镜像
- 投影：降维操作
  - `P207` 本书默认是正交投影：包含沿切向量投影、沿横轴坐标投影
- 再谈行列式值：几何视角 
  - `P210` 从二维矩阵角度：行列式值决定了变换前后面积缩放比例。可正可负可零。如果矩阵 A 行列式值为负，几何上来看，图形翻转。几何变换前后，逆时针来看，蓝色箭头和红色箭头“先后次序”发生调转
  - `P213` Streamlit 应用中，我们看到如何产生不同“平行且等距网格”。在此基础上，本章Streamlit 应用增加了矩阵A 对单位圆的线性变换

---

### 正交投影

投影的直觉理解：太阳往地面照射，物体的影子。太阳有早上、中午、晚上之分

正交投影：正午的太阳往地面照射，物体的影子。

投影是个矢量，有大小（标量投影）和方向（向量投影）。大小就是影子的长度，方向就是规定的方向（用向量表示，方向都会用单位向量来衡量）。注意影子大小和规定方向的大小无关。但是整体来说就是大小*单位投影方向。

![正交投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/projection_intuition.png)


可以往单一方向正交投影，这个单一方向可以构造出投影矩阵出来，这个投影矩阵可以作用到某个向量上完成这个方向的投影计算。

也可以同时往很多方向投影（往一个有续基构成的平面/超平面投影），这个有续基可以是正交也可以不是正交的。本章主要研究这个有序基，而且是规范正交基础的情况。同时也在“投影视角看回归”这一节给出了往一个有序基（不管是不是正交）投影的公式，可以是一个点或一系列点往这个有序基投影（线性回归）。且给出了如果是规范正交基，那么往这个规范正交基投影的公式会更简洁和清晰，也间接说明了需要施密特正交化的重要性。

往标准正交基的各个方向的标量投影就是坐标值，坐标值和正交投影的关系就是这么来的。

一个规范正交基可以当成一个坐标系，也可以当成的一个运动（变换），其还有一些自己的特点，如：（V是正交矩阵）

- V的格拉姆矩阵是 G = V^TV -> 含有一系列向量模和两两夹角的信息（向量指的是正交矩阵的各个基向量）
- VV^T=I --> v1⊗V1 + v2⊗V2 + v3⊗V3 + ..  
  规范正交矩阵的每个基（方向）构造出来的投影矩阵的和，构成单位矩阵（或者说单位矩阵可以分解为）。

一个或者多个样本点（每个样本点有多个特征），可以往一个或者多个方向（特征方向）进行正交投影。这可以完成降维或者重新设置特征的效果。

正交投影还可以用于求镜像向量，以及用于施密特正交化（套用往单一方向正交投影的公式）。

为了理解往一个有序基投影（不再是单一方向，而是多个方向），作者从回归的角度给出了解释。就一个点来说，y(向量)和x（向量）可以看成是一元线性回归: y=bx。y和x1,x2可以看成是二元线性回归：y=b1x1+b2x2；同理多元线性回归是y = b1x1+b2x2+b3x3+...。从正交投影角度理解多元线性回归就是y往超平面span(x0,x1,x2,...,xd)方向投影得到的hat(y)和 x0,x1,x2,...,xd 之间的系数关系， y - hat(y)是和span(x0,x1,x2,...,xd)垂直的部分，也就是残差部分。

对于只有1个自变量x和1个因变量y点的情况，可以用对 y=bx

对于2个自变量x1,x2，一个因变量y的情况，可以用y  = b1x1+b2x2

对于n个x，一个y的情况，可以套用 y = b1x1+b2x2+b3x3+...

以上求系数b矩阵的方法，类似单个方向的投影矩阵，在这里叫帽子矩阵(hat matrix)，这个矩阵是由设计矩阵 X = [x1, x2, ..., xd]构造出来的。最后结论是 hat(y) = 帽子矩阵* y

作者也补充提到了多项式回归，只要能写出设计矩阵，就可以把他看成线性变换，同时利用类似的公式求出b。

思考：n个方程n个未知数，如果上面的线性回归中，点数过多，可能就要想怎么最小化残差部分了。

往多个方向投影，标量和方向向量都可以从1个方向上投影推广。帽子矩阵（多个方向的投影矩阵）也是从投影矩阵（一个方向）推广过来的。同时注意如果投影向量是单位正交向量，那么帽子矩阵具有简洁形式（投影矩阵也有简洁形式）。

![summary_1：将x扩大到数据集X，如果v是单位向量，则Xv表示往v方向的投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_1.png)
![summary_2：这里x是列向量。但在数据矩阵中x一般用行向量表示，所有后续拓展x到数据集X的时候是Xv](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_2.png)
![summary_3。注意这里的Z=Xv中的X是行表示的数据集,v是单位向量，这样Xv才是X向v方向正交投影的投影公式。否则投影公式复杂一点，见后面。n个样本点(每个样本点一行)往单位v方向正交](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_3.png)
![summary_4](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_4.png)
![summary_5：b是投影矩阵的理解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_5.png)
![summary_6：往多个方向的正交投影 == 往各个方向的正交投影的线性组合](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_6.png)
![summary_7](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_7.png)
![summary_8](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_8.png)
![summary_9：对个采样点y构成一个向量，这个向量有n个维度。这就将多个点转换为一个具有多个特征值的点](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_9.png)
![summary_10:简化的前提是要求X的列向量两两正交且列向量都是单位向量，但不要求X是标准正交基，即X可以不是方阵，即X^TX=I，但XX^T不一定是I。如果X是方阵的话，那么可以进一步简化：hat(y) = XX^Ty=(x1⊗x1+x2⊗x2+...)y=Iy=y](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_10.png)
![summary_11：往多个方向的正交投影 == 往各个方向的正交投影的线性组合](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_11.png)

![summary_12：理解Z=XV](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/general_projection.png)


- 标量投影：结果为标量
  - `P218` 标量投影公式，注意正交投影和向量内积不一样！！！，标量投影和方向向量的大小无关！！
    ![标量投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/projection.png)
    ![标量投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/projection_2.png)

  - 标量：坐标系的含义。往i轴的标量投影，就是i的坐标；往j轴的标量投影，就是j轴的坐标
- 向量投影：结果为向量
  - `P218` 向量投影公式，包含推导点投影到切向量的公式

    ![标量投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/vector_projection.png)

  - `P218/P219` 向量投影公式。向量x在v方向的投影公式，包括如果v是单位向量的公式和v不是单位向量的公式
    向量投影 = 标量投影 * 单位向量方向（注意是单位向量方向，方向都会被归一化）
    如果v是单位向量:proj_v(x) = < x ,v>v = (x·v)v=(v·x)v=(x^Tv)v=(v^Tx)v = (v⊗v)x -> (v⊗v)叫投影矩阵，此时的v是单位向量
  - `P221` python中，用matplotlib.pyplot的plot绘制点，线；以及用plt.quiver绘制箭头
    - plot([x1, x2], [y1, y2]) -> 绘制直线(x1,y1) -> (x2, y2)
    - 如果plot只有一个点，则绘制的是marker
  - `P221` 如果v为单位向量，我们称v⊗v为投影矩阵 (projection matrix)，会在proj_v(x)，即列向量x向列向量v投影中会用到。任意向量x向v投影的公式也可以得到
    - 列向量只能向列向量投影，一般意义上行向量不能向列向量投影。书里面的行向量(X)向列向量投影，是因为把行向量看成了列向量(或者说把X处理成左乘还是右乘来解决)，然后套用的还是列向量投影公式
    - 投影矩阵可以将 列向量x向**单位列向量v**的向量投影(内积x·v)v，变成矩阵运算 -> (v⊗v)x
    - `P222` 向量x 向v 方向(v方向表示特征方向，如花瓣长度方向，或者组合方向)投影，这可以视作x 向v 张起的向量空间span(v) 投影。v如果是个向量，span(v)就是沿着这条向量的向量空间
    ![投影矩阵；注意v一直是列向量或者列矩阵或者多列矩阵。(22/23)和(25)区别是x是行还是列代表样本。标量投影Z=Xv，可以从行*列=值来理解 -> 向量投影Z=(Xv)v^T=X(v⊗v)；行向量X的投影可以从转置的角度理解；如果X是列向量代表一个样本点，则标量Z=v^TX,向量Z=(v^TX)v= v(v^TX)=(v⊗v)X](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/projection_matrix.png)
    ![理解XVV^T](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/xvvt.png)
- 正交矩阵：一个规范正交基
  - 正交矩阵的各列向量都是单位向量
  - `P222` 向量x 向一个列 v 方向投影，这可以视作x 向v 张起的向量空间span(v) 投影。同理，向量x 也可以向一个有序基构造的平面/超平面投影。这个有序基可以是正交基，可以是非正交基。数据科学和机器学习实践中，最常用的基底是**规范正交基**。正交矩阵的本身就是规范正交基。正交矩阵的每列向量都互相正交且都是单位向量，所以正交矩阵可以看成一些规范列正交基构成的矩阵，也就是张成的向量空间。
    向一个列向量v投影 -> 扩展到向多个方向投影：[v1, v2, v3, v4]。如果v1,v2,v3,v4满足一定条件（两两正交且都是单位向量），则成[v1, v2, v3, v4]是正交矩阵
  - `P222` 正交矩阵V: VV^T = V^TV=I，V是方阵。性质有V^T=V^-1, V^TV=VV^T=I -> V^T也是正交矩阵
  - `P223` 旋转矩阵，镜像矩阵，置换矩阵都是正交矩阵
  - `P224` G=V^TV 相当于正交矩阵V 的格拉姆矩阵，格拉姆矩阵包含原矩阵（的各个列向量）的所有向量模、向量两两夹角这两类信息
  - `P223` 正交矩阵乘法理解的第一视角：V^TV
    ![正交矩阵乘法第一视角](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/orthogonal_view1.png)
  - `P225` 正交矩阵乘法理解的第二视角: VV^T

    VV^T = V^TV=I

    理解XVV^T，先从(xv)v^T开始，
    这里x是1xn的行向量，代表一个样本点，有n个特征值。v是nx1的方向向量（列向量）。
    在这里xv就相当于是标量的作用

    1个样本点，1个方向向量：
    1x4 * 4x1 = 1x1，(1个样本点，4个特征) * 4x1的方向向量  = 1x1
    
    2个样本点，1个方向向量：
    2x4 * 4x1 = 2x1, (2个样本点，4个特征) * 4x1的方向向量  = 2x1 （2个样本点各自在方向向量下的标量）

    1个样本点，2个方向向量：
    1x4 * 4x2 = 1x2, (1个样本点，4个特征) * 2个方向向量(第1列的方向向量，第2列的方向向量)

    2个样本点，2个方向向量：
    2x4 * 4x2 = 2x2, (1个样本点，4个特征) * 2个方向向量(第1列的方向向量，第2列的方向向量) 

    XV -> X是nx4, V是4xb，结果是nxb  -> n个样本点在b个方向向量的各自的投影标量，就是nxb

    投影向量还需要加一个方向： (XV)V^T, V^T是bxn，所以是nxb x (bxn)  = nxn. 之所以方向是V^T，是因为X是每行代表一个数据点。

    ![理解XVV^T](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/xvvt.png)

    ![正交矩阵乘法第二视角](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/orthogonal_view2.png)
    ![正交矩阵乘法第二视角](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/orthogonal_view3.png)
    ![正交矩阵乘法第二视角](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/orthogonal_view4.png)
    ![正交矩阵乘法第二视角](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/orthogonal_view5.png)
- 规范正交矩阵的性质
  ![线性变换](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/understand_transform.png)

  `P227` 向量x经过正交矩阵V线性变换后，具有：1. 向量长度不变 2. 向量夹角不变

  `P227` 向量模的计算，学习下2个向量的运算:

  ![向量模长](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/vector_dot_in_module.png)

  `P228` 正交矩阵的行列式为1或者-1，可以学到行列式运算的一些应用。（直接用到了行列式的一些性质）
- 从投影角度看镜像：x关于对称轴(τ)的镜像得到z

  `P229` 可以学到，利用张量积(投影矩阵)，向量x在单位切向量τ方向的投影向量，进而得到镜像向量

  `P230` z(镜像向量) = H(豪斯霍尔德矩阵)x(原向量) -> H利用到了和对称轴τ正交的向量v，v的方向是反射面所在方向。 矩阵H完成豪斯霍尔德反射，也叫初等反射
- 格雷格-施密特正交化
  `P231` 正交化过程，注意里面的proj_v(x)中：x往v方向的投影向量，这个向量和和v的大小无关
- 投影视角看回归
  > 什么是回归和线性回归
  >  
  回归（Regression）是一种用于预测和建模的统计分析方法，通过观察变量之间的关系来推断一个或多个自变量与因变量之间的关系。回归分析可用于探索变量之间的相关性、预测未来趋势以及评估自变量对因变量的影响程度。
  >  
  线性回归（Linear Regression）是回归分析中最简单且常用的一种方法，它假设自变量与因变量之间存在线性关系。线性回归的目标是拟合出一条直线（在一维空间中）或一个超平面（在高维空间中），使得这条直线或超平面能够最好地拟合数据点，即最小化预测值与实际观测值之间的误差。线性回归可以用于解决连续型因变量的预测问题，例如预测房价、销售量等。
  >  
  非线性回归包括多项式回归等。


  `P236` 的帽子公式给出了从线性回归角度理解的往有序基础（多个方向）投影的投影矩阵，只不过这里叫帽子矩阵
  `P237` 讲了多项式回归，通过列好设计矩阵X，也可以转换到帽子矩阵方法求系数矩阵b
  `P238` 如果有续基是正交基，那么帽子矩阵具有非常优雅的写法，也简洁说明了需要正交化的重要性

  ![理解多元线性回归](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/regression.png)
  ![理解多元线性回归](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/linear_reg_in_formula.png)
  ![帽子矩阵类似投影矩阵，也可以通过帽子矩阵求得系数矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/linear_reg_in_formula_2.png)
  ![多项式回归的理解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/linear_reg_in_formula_3.png)
  ![多项式回归](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/linear_reg_in_formula_4.png)
  ![正交矩阵的投影具有简洁的公式，和往一个单位方向投影的投影矩阵一样简洁](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/linear_reg_in_formula_5.png)

---


### 数据投影

本章主要讲解Z=XVV^T的意义，这里V各列必须是单位向量且互相正交，则其意义是XV表示X在V方向的标量投影，XVV^T表示向量投影（在X原来的空间下的表示）

如果V恰好又是个方阵(标准正交基的情况），则VV^T=I -> XVV^T=X

- 从一个矩阵乘法运算Z=XV说起

  `P242` 如果X为行向量代表数据点，V各列互相正交且是单位向量，则Z=XV表示X向V的各列有续基的标量投影，Z表示X在新的单位正交基下的坐标。需要记住这个结论，具体逻辑看上一章

  ![Z的每一行的每个值，表示X在v1~vd下的投影坐标](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/Z%3DXV.png)

  ![从上一章延伸而来：从一般性投影到Z=XV](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/general_projection.png)


- 二特征数据投影：标准正交基[e1, e2]
  
  如果X恰好又是方阵，则Z=XVV^T=XI=X

  XV表示标量投影和XVV^T表示向量投影，且恰好VV^T=I

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_in_h_1.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_in_h_2.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_in_h_3.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_in_h_4.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_in_v_1.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_in_v_2.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_combination_1.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_combination_2.png)
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/projection_combination_3.png)

- 二特征数据投影：规范正交基和[e1, e2]类似，但旋转一定角度
  
  XV:表示往V方向的标量投影，XVV^T表示向量投影（在X原来的空间下的表示）

  ![点A往v1,v2投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/proj_in_v1.png)

  ![点A在v1方向的标量投影是5.33](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/proj_in_v1_2.png)

  ![点A在v1方向的向量投影，在原来A的坐标系下的表示](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/proj_in_v1_3.png)

  ![点A在v2方向的标量投影和向量投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/proj_in_v2.png)

  ![点A在v1/v2方向的向量投影，在A原来的坐标系下的表示， 还是等于点A](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch10/proj_in_all.png)

- 数据正交化
  `P270` 原始数据X，其格拉姆矩阵G= X^TX，它不是一个对角阵。而经过V以后（V要求两两正交且是单位向量，但不一定要求V是方阵），即Z=XV后，Z的格拉姆矩阵Z^TZ=Λ就是个对角阵！不过这2个格拉姆矩阵的迹都是一样的。同时也要知道Z=XV以后，Z的列向量两两正交了！！

  `P271` 利用以上特点，可以将原始数据X的格拉姆矩阵，分解为3个特殊矩阵的乘积  G=VΛV^T,即V正交矩阵， Λ对角阵。

---

### 矩阵分解

![矩阵分解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/matrix_decomposition.png)

- 矩阵分解：类似因式分解
  
  `P278` 矩阵分解 (matrix decomposition) 将矩阵解构得到其组成部分，类似代数中的因式分解。
  从矩阵乘法角度，矩阵分解将矩阵拆解为若干矩阵的乘积。
  从几何角度，矩阵分解结果可能对应缩放、旋转、投影、剪切等等各种几何变换。而原矩阵的映射作用就是这些几何变换按特定次序的叠加

- LU分解：上下三角
  
  `P279` LU分解可以视为高斯消元法的矩阵乘法形式。 A = LU

  `P280` scipy.linalg.lu()函数可以进行LU分解，默认进行的是PLU分解，即A=PLU，其中P是置换矩阵，作用是交换矩阵的行、列。注意所有的方阵都可以进行PLU 分解。

  ![PLU分解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/PLU.png)


- Cholesky分解：适用于正定矩阵
  
  `P280` Cholesky分解 (Cholesky decomposition) 是LU 分解的特例。丛书在讲解协方差矩阵(covariance matrix)、数据转换、蒙特卡洛模拟等内容都会使用Cholesky 分解。

  Cholesky 分解把矩阵分解为一个下三角矩阵以及它的转置矩阵的乘积 A = LL^T .Numpy 中进行Cholesky 分解的函数为numpy.linalg.cholesky()

  `P281` Cholesky 分解可以进一步扩展为LDL分解 A = LDL^T。其中，L 为下三角矩阵，但是对角线元素均为1；D 为对角矩阵，起到缩放作用；几何角度来看，L 的作用就是“剪切”。也就是说，矩阵A 被分解成“剪切 → 缩放 → 剪切”。 LDL 分解的函数为scipy.linalg.ldl()

- QR分解：正交化
  
  `P282` QR分解 (QR decomposition, QR factorization) 和本书第9 章介绍的格拉姆-斯密特正交化联系紧密。QR 分解有两种常见形式：完全型 (complete)，Q 为方阵；缩略型 (reduced)，Q 和原矩阵形状相同。

  完全型QR分解： X_(nxd) = Q_(nxn)R_(nxd)，其中 Q是方阵且是正交矩阵（正交矩阵的含义是方阵，且列向量两两正交且列向量都是单位向量）。QR分解结果不唯一，但是，如果X 列满秩，且R 的对角元素为正实数的情况下QR 分解唯一。

  ![完全型QR分解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/complete_QR.png)

  ![缩略型QR分解。此时Q不是方阵，但各列两两正交](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/reduced_QR.png)

  ![P285 QR分解的几何意义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/understanding_QR.png)


- 特征值分解：刻画矩阵映射的特征
  
  `P286` 特征值和特征向量的定义。不是所有方阵都可以进行特征值分解，只有可对角化矩阵才能进行特征值分解

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/eigen_decompsition.png)

  ![Ax=0，如果x不是零向量的话，则A的行列式比==0， 否则A就是各列线性无关，这种情况x必须是0](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/Ax_0.png)

  对称矩阵 V^T = V^-1, 前提是逆矩阵存在

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/eigen_decomposition_2.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/characteristic_equation.png)

  ![普分解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/spectral_decomposition.png)

  ![对称矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/explain_symmetric_matrix.png)

  ![特征值分解的几何视角](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/understanding_eigen_decomposition.png)


- 奇异值分解：适用于任何实数矩阵
  
  如果特征值分解是“大菜”，奇异值分解绝对就是矩阵分解中的“头牌”

  ![svd定义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/svd_definition.png)

  ![svd和特征值的关系](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch11/characteristic_equation.png)

  `P291` 给出了手算svd的一个容易理解的案例

---

### Cholesky分解


- Cholesky分解
  `P296` 定义(A=LL^T或者A=R^TR)，以及LDL分解(A=LDL^T,D是方阵，L的对角线元素都是1），并对LDL分解进一步写法，可以导出A的平方根项
   
- 正定矩阵才可以进行Choleskey分解

  `P297` 正定矩阵的定义，同时指出只有正定矩阵才能进行Cholesky分解。正定矩阵都是对称方阵，且特征值都>0，且必满秩(正定矩阵满秩，都是线性无关的各列)
  
  ![正定矩阵定义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch12/positive_define_matrix.png)

  `P298` 给出了常见的正定型对应的几何图形

  

- 几何角度：开合
  `P299` 本节针对一个特殊的矩阵P进行Cholesky分解 P = R^TR，分解完的R几何变换作用是“开合”，或者说R的作用可以把一个圆变成椭圆。

  `P305` 这种类型的P矩阵，是本书之后要讲到的相关性系数矩阵，其中的余弦值相当于相对性系数

- 几何变换：缩放 -> 开合
  
  `P302/P305` 以Σ矩阵（协方差矩阵）为例来讲Cholesky分解，分解后的  R_Σ的几何作用是：先(对原坐标系进行)缩放 -> 再(对缩放后的新的坐标系进行）开合

- 推广到三维空间

  `P305` 将P矩阵从上面的2维扩展到3维来理解Cholesky分解，也是继续探讨分解后的R的几何作用

- 从格拉姆矩阵到相似度矩阵
  
  `P309~P312`鸢尾花数据集X=[x1, x2, x3, x4]，总共有4个特征，150个样本点（150行）。可以用X的格拉姆矩阵G=X^TX=4x4矩阵来表示x1,x2,x3,x4之间的关系。这里格拉姆矩阵是个4x4矩阵，包含4个向量两两长度和夹角余弦信息。对G矩阵再次进行Cholesky分解，可以得到更好更简洁的R矩阵(4x4)，包含信息等价于上面的格拉姆矩阵。

  ![格拉姆矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch12/grammar_matrix.png)

  `P311` 由于格拉姆矩阵含有向量长度信息，余弦相似度矩阵C只包含列向量的两两夹角cos信息。 相似度矩阵S和格拉姆矩阵的转换公式是: C= S^-1GS^-1

  ![余弦相似度矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch12/cosine_similarity_matrix.png)

---

### 特征值分解

`P315` 本书第8章讲解线性变换时提到，几何视角下，方阵对应缩放、旋转、投影、剪切等几何变换中一种甚至多种的组合，而矩阵分解可以帮我们找到这些几何变换的具体成分。本章要讲的特征值分解能帮我们找到某些特定方阵中“缩放”和“旋转”这两个成分。

可对角化方阵才可以进行特征值分解。

`P286` 一般的特征值分解是： A = VΛV^-1，且特征向量矩阵的各列（特征向量）一般是单位向量

`P289` 如果A本身是对称矩阵，则 A=VΛV^T，即VV^T=I一个典型的对称矩阵是格拉姆矩阵XX^T和X^TX，各自得到的特征向量矩阵都是正交矩阵

`P324~326` A为对称矩阵的这种特征值分解也叫谱分解，因为特征值分解将矩阵拆解成一系列特征值和(特征向量张量积)之和。同时在这个基础上，将X往v（来源于格拉姆矩阵的v）投影，可以得到yj=Xv的投影结果，其结果和特征值λ有关！



- 几何角度看特征值分解
  
  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/transform_exam_1.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/transform_exam_2.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/transform_exam_3.png)


- 旋转 -> 缩放 -> 旋转
  
  `P318-P320` 特殊的矩阵：对称方阵的特征值分解，即谱分解来理解其几何意义

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/roate_scale_rotate.png)

  ![Aw=VΛV^-1w：对w先旋转V^-1在缩放Λ，再旋转V。图中的v1,v2是特征向量，所以结果这个过程后，方向一致，只是大小变量，其他单位圆上的向量方向都不一致。](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/roate_scale_rotate_2.png)

  ![特征值分解各个分量的含义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/understandint_ev_meaning.png)

  `P288` 特征向量一般是单位向量，除非特殊说明

- 再谈行列式值和线性变换
  
  `P321` 如果A 可以进行特征值分解，矩阵A 的行列式值等于A 的所有特征值之积。特征值可以是正数/负数/0/复数

- 对角化、谱分析
  
  `P323` 方阵可对角化概念，以及只有可对角化的矩阵才能特征值分解。

  如果A可以对角化，则可以利用特征值分解，方便计算矩阵A的n次幂

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/diagonalizable.png)

  `P324` 如果矩阵A不仅可对角化，而且是对称矩阵，则可以将特征值分解 A=VDV^-1写成 A=VDV^T -> 这就是谱分解

  其中D是特征值矩阵。格拉姆矩阵就符合可对角化且是对称矩阵，所以会被用于谱分解
    - 谱分解可以得到特征值矩阵Λ=V^TAV,这个正好是二次型（含有距离平方的含义）
    - 用格拉姆矩阵G=X^TX带Λ=V^TAV中的A且令y_i=Xv_i，可以得到 (y_i)^Ty_i =  λ_i
    - 可以用于解释||y_i||_2 = ||Xv_i||_x = λ_i。
    - 这个可以用于最优化

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/spectral_decomposition.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/spectral_decomposition_2.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/spectral_decomposition_3.png)


  `P325` 同时以格拉姆矩阵为例来说明谱分析（以格拉姆矩阵为对称矩阵进行的案例）

  `P326` 从几何视角来理解格拉姆矩阵（以格拉姆矩阵为对称矩阵的案例）的谱分析，来说明特征值大小对特征值分解的重要性

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/glammer_spec_decomposition.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/glammer_spec_decomposition_2.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/spec_decomposition_exam1.png)

  ![案例：这里的X是数据矩阵, vj是X的格拉姆矩阵的特征值分解的V的各个特征向量。yj=Xvj，V各个列向量是相互垂直的。同时Xvj，把X分解开就是各个样本点, y1 = x1vj; y2 = x2vj ; ...](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/spectral_decomposition_4.png)

- 聊聊特征值
  
  `P328` 从几何角度来看，对角化实际上就是，平行四边形转化为矩形，或者，平行六面体转化为立方体的过程，这恰好和行列式联系起来。 作者这里用的是举例计算并推广的方法，从而得出 det(A)=det(Λ)。

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/dia_in_geo.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch13/dia_in_geo2.png)


  `P329` 列出了矩阵A的特征值的一些重要性质，如λA, A^n, A^-1的特征值和行列式的关系

- 特征值分解中的复数现象
  
  `P330` 本书前文讲述的向量矩阵等概念都是建立在R^n 上，我们可以把同样的数学工具推广到复数空间C^n 上

  `P331` 给出了特殊矩阵的例子并给出几何视角

--- 


### 深入特征值分解

`P334` 汇总了特征值分解的应用场景

![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch14/ev_application.png)

- 方阵开方

  `P335` A^p =VΛ^pV^-1, p可以是1/2, 3

- 矩阵指数
  
  `P335~337` 可以从标量指数e^a的泰勒展开开始，类比得到 e^A

- 斐波那契数列：求通项式
  
  `P337~339` 介绍如何使用特征值分解推导得到斐波那契数列通项式，主要是要写出用矩阵表达的通项公式，就可以得到解析解

- 马尔科夫过程的平稳状态
  
  `P339` 介绍了什么是马尔科夫过程：每晚有30%的小鸡变成小兔，其他小鸡不变；同时，每晚有20%小兔变成小鸡，其余小兔不变。这个转化的过程叫做马尔科夫过程。

  `P340` 马尔科夫过程可以用转移矩阵表示，只要写出转移矩阵: Tπ(k) = π(k+1)， T是转移矩阵。平稳态就是Tπ=π，这个正好就是特征向量的定义，就可以用特征值分解的方法求取。

- 瑞利商
  
  `P342` 瑞利商的定义

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch14/rayleigh_quotient.png)

  `P343` 后面用分母为1，即x_1^2 + x_2^2 = 1来举例，求瑞利商的最大值和最小值。

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch14/rayleigh_quotient_2.png)


- 再谈椭圆：特征值分解
  
  `P346~P351` 一个圆，经过变换后会变成椭圆。这个变换过程是个缩放->旋转的过程，正好对应特征值分解的过程，椭圆形的长短轴信息和缩放有关,也即和特征值有关（等于特征值的开根号）。

  也可以把这个过程理解为先缩放-> 剪切的过程，这个正好对应LDL分解。最后的结论用图标表示如下：

  ![这里的Λ以及V不是来自对称矩阵A的特征值分解，而是来自Q=(AA^T)^-1的特征值分解。书中也讲解了最后A对z的作用，即x=Az，相当于x=VΛ^(-1/2)z。这里没提到平移，平移是最后的。](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch14/tuoyuan_1.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch14/tuoyuan_2.png)

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch14/tuoyuan_3.png)

---

### 奇异值分解

奇异值分解的U和V和特征值分解中的特征向量有关，奇异值和特征值有关，详见下文介绍。

- 几何视角：旋转(V^T) -> 缩放(S) -> 旋转(U)

  X = USV^T

  ![奇异值定义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch15/svd_definition.png)

  `P356` 任何实数矩阵都可以进行奇异值分解


- 不同类似的SVD分解
  
  SVD 分解分为完全型 (full)、经济型 (economy-size, thin)、紧凑型 (compact) 和截断型(truncated) 四大类。本节将简要介绍完全型和经济型两种奇异值分解之间的关系。下一章将深入讲解这四种SVD分解。

  `P359~P360` 介绍了完全型 、经济型

- 左奇异向量矩阵U
  
  `P360` U^TU=I
  `P361` 对方阵XX^T 进行特征值分解，可以发现U 的列向量是特征向量，而SS^T 是XX^T 的特征值矩阵
  `P362` XX^T 进行特征值分解得到正交矩阵U = [u1, u2, ..., un] 是个规范正交基，张起的空间为R^n。对完全型SVD分解，U和V都是正交矩阵，详见上面的定义。

  ![奇异值定义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch15/svc_qr.png)

- 右奇异向量矩阵V
  
  `P363` 值得强调的是，凡是满足V^TV = VV^T = I 的方阵V 都是正交矩阵 (orthogonal matrix)，对应规范正交基。前文提过，并不是所有正交矩阵都是旋转矩阵 (rotation matrix)。只有det(V) = 1 的正交矩阵才叫旋转矩阵，这种矩阵也叫特殊正交矩阵 (special orthogonal matrix)。也就是旋转前后面积不变。而一般正交矩阵的行列式值为±1，即det(V) = ±1。当det(V) = −1 时，V 对应的几何操作为“旋转 + 镜像”。这也告诉我们，SVD 分解中V 和U 并不唯一，V 和U 的列向量都可以取负。当det(V) = det(U) = −1 时，V 和U 都是“旋转 + 镜像”。但是为了方便，完全型SVD 结果中的V 和U，我们还是管它们的几何操作叫“旋转”

  `P364` 对X^TX 进行特征值分解，S^TS 为特征值矩阵。X^TX 进行特征值分解得到正交矩阵V = [v1, v2, ..., vD]，它也是个规范正交基，张起的空间为R^D

  `P364` 奇异值分解不但可以分解各种形状实数矩阵，并且一次性获得U = [u1, u2, ..., un] 和 V = [v1, v2, ..., vD] 两个规范正交基

- 两个视角：投影和数据投影
  
  `P365` XV=US -> Xv_j= s_j * u_j -> X 向vj 投影，结果为s_j * u_j

  ![PCA主成分分析](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch15/pca.png)

  `P366` X可以看成是通过叠加还原原始数据矩阵，或者张量积的和


---

### 深入奇异值分解

本章深入介绍这四种奇异值分解：完全型、经济型、紧凑型、截断型

`P372` 经济型可以由完全型转变而来：完全型的S去掉零矩阵变成方阵

`P323~324` 当X非满秩时，奇异值有0的存在，进而可以由经济型转换为紧凑型。只有X 为非满秩情况下，才存在紧缩型SVD 分解。紧缩型SVD 分解中，U 和V 都不是方阵

`P374` 截断型svd：近似

![截断型svd](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/trunctated_svd.png)

![截断型svd](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/trunctated_svd_2.png)

`P375~P378` 经济型SVD 分解可以看成是一种张量积的和

![svd的分解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/thin_1.png)

![svd中X的组成部分](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/thin_2.png)

![svd的张量积](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/thin_3.png)

![svd的正交投影理解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/thin_4.png)

`P379~P381` 估计与误差：截断型SVD。把数据矩阵X 对应的热图看做一幅图像，本节介绍如何采用较少数据尽可能还原原始图像，并准确知道误差是多少，这也是主成分分析。同时有鸢尾花照片的PCA分析例子。

`P382` 用SVD找到一个V，来理解Z=XV,即X->Z的过程是正交化的过程：Z各列两两正交

![从SVD角度理解XV](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/orth_1.png)

![从SVD角度理解XV](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch16/orth_2.png)

---

### 多元函数微分


一阶偏导数 -> 梯度向量 -> 上山方向

二阶偏导 -> 黑塞矩阵

- 偏导：特定方向的变化率
  
  `P388` 一个多变量的函数的偏导数是函数关于其中一个变量的导数，而保持其他变量恒定。通俗地说，偏导数关注曲面某个特定方向上的变化率。换个角度，一元函数导数这个工具改造成偏导数后，可以用在多元函数上

  ![P389~390 二元函数一阶偏导数的向量形式，也叫梯度向量(gradient vector)，在2个方向上的偏导数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/partial_derivative_vec_form.png)

  `P389~P391` 分别介绍一次多元函数(x1,x2项最高是一次方)，二次多元函数（最高项是二次方），以及二次型的(x^TQx)的矩阵形式的一阶偏导数有个总结，方便和Maxtrix Cookbook对照

  `P391~P392` 黑塞矩阵 (Hessian matrix) 是一个多元函数的二阶偏导数构成的方阵，黑塞矩阵描述了函数的局部曲率。本书后续会在优化问题中用到黑塞矩阵判断极值点

  ![黑塞矩阵定义以及二阶偏导数顺序，以及二次型的黑塞矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/hessian_matrix.png)

- 梯度向量：上山方向
  
  ![梯度向量：Nabla算子](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/gradient_vector_definition.png)
  
  `P393` 几何视角来看梯度向量，如图 2 所示，在坡面P 点处放置一个小球，轻轻松开手一瞬间，小球沿着坡面最陡峭方向 (绿色箭头) 滚下。瞬间滚动方向在平面上的投影方向便是梯度下降方向(direction of gradient descent)，也称“下山”方向。 数学中，下山方向的反方向即梯度向量方向，也称作“上山”方向。
  
  `P393` 梯度向量场的理解

  ![梯度下降方向](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/gradient_descent.png)

  ![梯度向量场](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/gradient_vector_field.png)

  `P393~P395` 分别以一次函数、二次函数和复合函数的例子来解释梯度向量，来理解“局部”最大值和最小值。（局部最大值点附近，梯度向量均指向局部最大值点。局部最小值点附近，梯度向量均背离局部最小值点）

  `P394` 如果我们现在处于曲面上某一点，沿着下山方向一步步行走，最终我们会到达最小值点处。这个思路就是基于梯度的优化方法。当然，我们需要制定一个下山的策略。比如，下山的步伐怎么确定？路径怎么规划？怎么判定是否到达极值点？不同的基于梯度的优化方法在具体下山策略上有差别。这些内容，我们会在本系列丛书后续分册中讨论。

- 法向量：垂直于切平面
  
  `P396` 给出了求法向量的公式，非常重要！重要视角：法向量向[x1, x2, x3...] 平面投影便得到f(x) 的梯度向量

  ![法向量公式，之所以是-1，是因为▽F(x,y)是F对x求偏导，和对y求偏导](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/n_vector.png)

  ![梯度向量都是在自变量平面内](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/n_vector_2.png)

  `P397` 还举例了不同的函数的法向量场以及在水平面上的投影，来说明最大最小值的梯度向量的特点

- 方向性微分：函数任意方向的变化率
  
  `P398` 光滑曲面 f(x1, x2) 某点的切线有无数条，而偏导数仅仅分析了其中两条切线的变化率，它们分别沿着x1 和x2 轴方向。方向性微分 (directional derivative)，它可以分析光滑曲面某点处不同方向切线的变化率

  ![方向性微分](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/directional_derivative.png)

  `P398~402` 以曲面上的P点到Q点为例，如果根据P点近似得到Q点呢：可以用多元函数泰勒一阶展开来近似：在P点做一个切平面，然后沿着这个切平面移动△x1,△x2，就近似得到Q点

  ![二元函数泰勒一阶展开](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/directional_derivative_2.png)

  ![沿着x1,x2这个方向](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/directional_derivative_3.png)

  ![方向还是用单位向量来表示](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/directional_derivative_4.png)

  ![方向性微分和偏导数之间的关系：注意▽底下有个v](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/directional_derivative_5.png)


- 泰勒展开：一元到多元
  
  泰勒展开可以用于一元/多元函数的逼近，包括一次逼近(线性逼近)和二次逼近。
  
  `P402` 回顾一元函数泰勒展开

  ![一元函数泰勒展开](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/single_value_taylor_expansion.png)

  `P403` 多元函数的线性逼近，即一阶泰勒展开，并和一元函数的对比

  ![多元函数的线性逼近(一次逼近)](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/mv_linear.png)

  ![线性逼近](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/mv_linear_2.png)


  `P404` 给出了多元函数的二次逼近，即二阶泰勒展开，里面涉及到黑塞矩阵, 也部分涉及到正定性。注意二阶泰勒展开中的二次型是： 1/2(△x)^T* H* △x，这和黑塞矩阵的正定性有关联

  ![多元函数的线性逼近(二次逼近)](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/second_order.png)

  `P404~405` 利用法向量推导了二次曲线在某一点处的切平面公式并举例说明

  ![二次曲面切平面](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/quad_curve_1.png)

  ![二次曲面切平面](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/quad_curve_2.png)


### 拉格朗日乘子法


拉格朗日乘子法就是一种能够把有约束优化问题转化成无约束优化问题的方法

- 回归优化问题
  
  `P409` 一般情况下，标准优化问题都是最小化优化问题。最大化优化问题的目标函数取个负号便转化为最小化优化问题

  ![含约束最小化优化问题](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/optimal_question.png)


- 等式约束条件
  
  不管是线性等式还是非线性等式约束，都可以通过构造拉格朗日函数: L(x,λ) = f(x) + λh(x)来将其转换为无约束优化问题

  `P410~P413` 给出了这种情况下获取最优解必须满足的条件：拉格朗日函数L(x,λ)的一阶偏导数为零，即 ▽f(x) + λ▽h(x) = 0，即f(x) 和h(x) 在驻点x 处梯度同向或者反向。
  
  根据该式求得的驻点(偏导数为零的点)x, 进一步判断驻点是极大值、极小值还是鞍点

  ![理解拉格朗日乘子法：梯度一定是基于等高线的！](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/understanding_Lagrange_Multiplier.png)

  ![理解拉格朗日乘子法](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/understanding_Lagrange_Multiplier_2.png)


- 线性等式约束
  
  `P414` 用一个线性等式约束例子来讲解拉格朗日乘子法。其中也有对（等高）直线的梯度垂直于直线的理解。

  `P415` 给出了拉格朗日函数的另一种计法：L(x,λ) = f(x) - λh(x)

- 非线性等式约束

  `P415` 用一个非线性等式的例子来讲解拉格朗日乘子法

- 不等式约束
  
  本节介绍如何用KKT (Karush-Kuhn-Tucker) 条件将本章前文介绍的拉格朗日乘子法推广到不等式约束问题

  `P417~419` 总结了只有不等式约束 + 既有不等式又有等式约束的最小值问题的KKT条件

- 再谈特征值分解：优化角度
  
  `P420` 这一节介绍一些线性代数中会遇到的含约束优化问题。利用拉格朗日乘子法，它们最终都可以用特征值分解求解。包含二次型、瑞利商等的最优解。这回用到后面的矩阵范数中。

  ![这个优化问题，会用到后面的矩阵范数中](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/SVD_in_opt.png)

  ![矩阵范数是max，这里是min。不过没关系，后面的svd中会讲到max/min对应最大特征值](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/SVD_in_opt_2.png)

  特征值分解和奇异值分解有关系，特征值和奇异值是平方关系，特征值分解的分解矩阵也有关系，具体看SVD分解。



- 再谈SVD：优化视角
  
  数据矩阵X 中任意行向量x(i) 在单位v向量 上投影，得到标量投影结果为y(i)。y(i) 就是x(i)在v 上坐标，h(i) 为x(i) 到v 的距离。整个数据矩阵X 在v 上投影得到向量y：Xv= y

  ![一个样本点在某个方向的投影：这个方向必须是单位向量才是xv=y。(身高、体重、年龄)在(1/2身高、1体重、1年龄)方向上的投影：是个标量](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/SVD_in_opt.png)

  ![数据矩阵X在v上的投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/SVD_in_opt_2.png)

  `P424` 在Xv=y中， 从优化问题角度，SVD 分解等价于最大化y(i) 平方和。其中X为数据矩阵，v为优化变量（只有一个方向）。

  `P424-425` 对一个数据矩阵X，SVD分解得到的V的含义是，X（所有样本点）往v1(V中的第一列，对应最大特征值λ1)投影等价于最大化投影结果y（||y1||==||Xv1||）的摸，且其值为开根号(λ1)。有了v1以后，再构造优化问题，求取X往v2投影的最大值: ||Xv2||=||y2||，满足条件是v1⊥v2且是单位向量。

  ![特征值分解和SVD分解是有联系的，详细查看SVD分解那一章](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/understandint_ev_meaning.png)

  `P425` 数据矩阵X，其中心化数据矩阵表示为Xc。对中心化数据矩阵Xc进行SVD分解，会和协方差矩阵联系到一起。

- 矩阵范数：矩阵 -> 标量，矩阵“大小”
  
  `P426` 矩阵范数借鉴了向量范数。向量范数代表某种距离，矩阵A的范数，也需要向量x的参与计算

  ![矩阵范数定义，这用到了上面提到的优化问题，只不过那边是min，这里是max，正好是对应最小/最大特征值](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/matrix_norm.png)

  `P428` 介绍了矩阵F范数。一些知识点：矩阵A 的所有元素平方和就是A 的格拉姆矩阵的迹。矩阵的迹等于其特征值之和。

- 再谈数据正交投影：优化视角
  
  `P428` 数据矩阵X(假设是150x2，150个样本点，2个特征)，可以往一个规范正交基V=[v1, v2]上投影，往v1方向标量投影得到y1=Xv1, 往v2方向标量投影得到y2=Xv2。y1是个150x1的列向量，y2也是如此。

  ![数据正交投影](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/X_proj_in_x1_x2.png)

  `P430` y1,y2向量有内积，夹角，余弦值可以用来衡量y1,y2之间的关系。y1和y2两个列向量随theta(v1和x轴夹角)而变换，即上述几个量值(内积、夹角、余弦值)会随着theta变换。有了变化，就会有最大值、最小值，这就进入了优化视角。

  `P430` Y=[y1,y2]=XV，Y也有格拉姆矩阵G_Y， G_Y和G_X和v1,v2有关 -> G_y = V^TG_xV

  `P430` 统计视角，y1,y2本身有均值(E(y1), E(y2))，方差(var(y1),var(y2))，标准差(std(y1), std(y2))；而y1, y2之间也有协方差cov(y1,y2)，相关性系数corr(y1,y2)。上述所有这些统计量同样随着theta变换。

  ![y1,y2的统计特征](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/y1_y2_character_vector.png)

  ![y1,y2的统计特征](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/cov_y1_y2.png)

  `P432` 列出了y1,y2各个量化指标随正交投影的投影方向theta的变化图。其中一些曲线特点，用到了矩阵trace特性，也讲到如果V是来自特征值分解的一些曲线特征。

  ![y1,y2的统计特征](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/understanding_y1_y2.png)


- (补充<数学要素>)标准差：离散程度
  
  `P424` 标准差描述一组数值以均值 µ 为基准的分散程度

  ![方差，最后取均值](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/standard_d.png)


- (补充<数学要素>)协方差：联合变化程度
  
  `P425-P427` 协方差 (covariance) 描述的是随机变量联合变化程度。白话讲，以花瓣长度和宽度数据关系为例，我们发现如果样本数据的花瓣长度越长，其花瓣宽度很大可能也越宽。这就是联合变化。而协方差以量化的方式来定量分析这种联合变化程度。备注：花瓣长度用的是一个列向量表示（一组随机变量）、花瓣宽度同理。协方差描述的就是这2组样本特征向量的联合变化关系。

  ![协方差定义：是个平均值的概念。统计的时候是每朵花的2个特征和这2个特征的均值差做面积，然后再取平均值](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/covariance_definition.png)

  ![协方差](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/understanding_covariance.png)

  ![协方差矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/covariance_matrix_1.png)

  ![协方差](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/covariance_matrix_2.png)


- (补充<数学要素>)线性相关系数：线性关系强弱
  
  `P429` 可以根据两个随机变量（随机变量特征1的样本向量，随机变量特征2的样本向量）的协方差，定义线性相关系数。线性相关系数也叫皮尔逊相关系数，它刻画随机变量线性关系的强度。线性相关系数相当于协方差归一化，归一化的线性相关系数比协方差更适合横向比较。

  ![线性相关系数](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/linear_corr_coef_1.png)

  ![线性相关系数：几何含义](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/linear_corr_coef_2.png)

  `P430` 相关性系数构成的矩阵叫做相关性系数矩阵

  ![相关性系数矩阵](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/correlation_matrix_1.png)

  ![相关性系数矩阵2](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch18/correlation_matrix_2.png)


---

### 直线到超平面

- 切向量：可以用来定义直线
  
  `P439~P440` 切向量用τ表示，单位切向量用hat(τ)。切向量可以用来描述平面直线和三维空间直线等

  ![切向量定义直线](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch19/line_in_plane.png)

  ![切向量定义直线](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch19/line_in_3d_plane.png)


- 法向量：定义直线、平面、超平面
  
  `P442` 给定平面(2维平面or超平面)上一点和平面法向量n，可以确定一个平面。n维平面只有一个方程。

- 超平面：一维直线和二维平面的推广
  
  `P443` 超平面定义： w^Tx+b=0，其中w是列向量，也是超平面的法向量！用内积形式表示是：w·x + b = 0

  `P445` 超平面是个多元一次函数


  理解：w^T[x+(w^T)^-1*b]=0，即法向量垂直于平面内的直线

- 平面与梯度向量
  
  `P446` 超平面函数，即多元一次函数 f(x)=w^Tx+b的梯度向量正好是w，即超平面的法向量

  ![平面与梯度向量](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch19/plane_grandient.png)


- 中垂线：用向量求解析式
  
  `P451` 利用向量求解中垂线的解析式

  ![中垂线：向量减法的直观理解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch19/perpendicular_bisector.png)

- 用向量计算距离
  
  `P453~P456` 列出了点面距离、正交投点坐标、向量在过原点平面内投影、平行面距离的公式 

---

### 再谈圆锥曲线

- 无处不在的圆锥曲线
  
  `P459` 圆锥曲线是二次曲线，同时给出了其矩阵表达： (1/2)x^TQx + w^Tx + F = 0, Q的行列式决定了圆锥曲线的性质（椭圆、抛物线、双曲线），其中Q是对称矩阵，具体值（来自于解析式）

  `P460` 对双曲线矩阵求一阶导数（矩阵求导），利用到了 Matrxi Cook中的相关公式。圆锥曲线中心点的坐标推导用到了矩阵一阶导数

- 正圆：从单位圆到任意正圆
  
  `P460` 单位圆的解析式： x^Tx -1 = 0

  `P461` 半径为r的正圆解析式 x^Tx -r^2 = 0  ->  x^T [S^-1] [S^-1]x - 1 = 0  -> 从这里可以分析S起到的缩放作用

  `P461` 圆形位于 [c1, c2]^T的任意正圆表达式的矩阵形式和熟悉的解析式/代数形式  -> 上面的缩放 + 圆形位置的平移

  `P462~P463` 给出了：单位圆通过“缩放+平移”，得到圆心位于c且半径为r的圆的图解。

  ![z和x的变换关系，带动了原来的正圆到一般正圆的图形变换](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/z_x_relations_in_circle.png)
  ![从单位圆到一般正圆：z1/z2是正圆在坐标系，x1/x2是一般正圆的坐标系, x1/x2和z1/z2有变换关系：z先等比例缩放再平移得到x](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/unit_circle_to_general_circle.png)


- 单位圆到旋转椭圆：缩放 -> 旋转 -> 平移

  `P463` 正圆坐标系z变换到椭圆坐标系x下的公式

  ![z和x的变换关系，带动了原来的正圆到椭圆的图形变换: z->x的过程是缩放->旋转->平移的过程](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/axix_with_elippse.png)

  ![从正圆到椭圆的变换：z是单位正圆，x是椭圆坐标系](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/transform_to_ellipse.png)

  `P465` 椭圆x->单位正圆z的过程是：平移->旋转->缩放的过程

  ![x->z的过程，以及椭圆解析式](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/x_to_z.png)

  `P465` 已经知道z和x的关系，这里进行带入，可以得到旋转椭圆x的表达式，化简为 (x-c)^T Q(x-c) - 1 = 0

  `P465` 对Q进行特征值分解研究，发现Q的特征值矩阵和缩放矩阵S有关系，也即得到椭圆半长轴和半短轴长度和矩阵Q 特征值之间的关系

  `P466` 给出一系列不同旋转角度椭圆解析式和对应Q 的特征值分解。表中给出了不同椭圆的半长轴和半短轴长度保持一致，唯一变化的就是旋转角度。大家如果对几个不同Q 特征值分解，容易发现它们特征值完全相同，也就是椭圆的半长轴、半短轴长度一致。 

  `P467` 给出了没有平移项的旋转椭圆解析式，会发现解析式特别负责。而用矩阵形式的解析式 (x-c)^T Q(x-c) - 1 = 0 就特别简洁，而且还能利用矩阵工具分析出椭圆的几何特点，如中心位置、长短轴长度、旋转等。

- 多元高斯分布：矩阵分解、几何变换、距离
  
  `P468` 介绍如何用上一节介绍的“平移 → 旋转 → 缩放”解剖多元高斯分布。“平移 → 旋转 → 缩放” 是椭圆变单位正圆的过程。

  `P468` 多元高斯分布的概念，其中有部分结构和旋转椭圆解析式一致，其中的Σ取不同形态还会一想到椭圆的形状

  ![多元高斯分布，其中Σ是协方差矩阵，P468](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/PDF_1.png)

  `P469` 对协方差矩阵进行特征值分解，并化简多元高斯分布中的椭圆解析式。化简后的椭圆解析式中的平移、旋转、缩放参数和特征值分解的V和Λ有关，并用一元高斯分布的概率密度函数做举例。其实都是前面二次型而二次曲线的关系的应用。

  ![对PDF中的部分进行特征值分解](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/PDF_2.png)

  ![从N(μ,Σ) -> N(0,I)](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/PDF_3.png)

  `P470` 可以将 （x-μ）^TΣ^(-1)(x-μ) 写成L^2范数平方的形式，从而得到马氏距离。注意马氏距离的表达式，其实是欧拉距离x^Tx的扩宽，总之都有距离信息。马氏距离，也叫马哈距离 (Mahal distance)，全称马哈拉诺比斯距离 (Mahalanobis distance)。马氏距离是机器学习中重要的距离度量。马氏距离的独特之处在于，它通过引入协方差矩阵在计算距离时考虑了数据的分布。此外，马氏距离无量纲量 (unitless 或dimensionless)，它将各个特征数据标准化

  ![马氏距离定义，针对多元高斯分布的](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/definition_mahal_distance.png)

  `P471` 中用鸢尾花的2个特征为案例，对比了欧氏距离和马氏距离

  ![欧氏距离和马氏距离](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/mahal_distance.png)

  `P472` 左图所示的两个同心圆距离质心μ 为1 cm 和2 cm。欧氏距离显然没有考虑数据之间的亲疏关系。举个例子，左图中红色点 ● 距离质心的欧氏距离略大于1 cm。但是对于整体样本数据，● 显得鹤立鸡群，格格不入。右图中红色点 ●，它的马氏距离远大于2。也就是说，考虑整体数据分布亲疏情况，红色点 ● 离样本数据“远的多。可以用scipy.spatial.distance.mahalanobis() 函数计算马氏距离，Scikit-Learn 库中也有计算马氏距离的函数。

  `P472` 将马氏距离d 代入多元高斯分布概率密度函数，看到高斯函数把“距离度量”转化成“亲近度”

  ![高斯函数和马氏距离](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/gaussian_function.png)

  `P473` 解释了多元高斯分布的概率密度函数的**分母**中的|Σ|^(1/2)的理解，，对应到分子就是|Σ|^(-1/2)。这里关键是|Σ^(-1)|表示行列式，注意行列式是这个变换(Σ^-1)的体积

  `P473` 介绍了父母中的常数项的作用：分母中(2π)^(D/2) 一项起到归一化作用，为了保证概率密度函数曲面和整个水平面包裹的体积为1，即概率为1

  `P474` 汇总了PDF的直观理解

  ![高斯函数和马氏距离。分母的|Σ|的理解，关键是知道行列式是表示体积](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/understanding_pdf.png)

- 从单位双曲线到旋转双曲线
  
  `P474` 前面讲过怎么从单位正圆到一般椭圆的变换，这一节将如何从单位双曲线到一般双曲线的转换。总结就是对单位正圆的z，首先对z 通过S 缩放，再通过R 逆时针旋转θ，最后平移c，得到的x坐标系，就会将z下的单位双曲线变换为一般双曲线


- 切线：构造函数，求梯度向量
  
  `P476` 给出了二次椭圆、二次双曲线在任意点的切线的解析式。法向量即为二元函数的梯度，然后通过切向量和法向量垂直的关系，可以求得切向量。

  `P478` 给出了一般圆锥曲线(二次)的切线解析式

- 法线：法向量垂直于切向量
  
  `P479` 给出了标准椭圆上点P(p1,p2)处的切向量τ并推导了法线公式。同时也给出了一般圆锥曲线的切向量和法线方程

  补充知识：椭圆切向量(一般圆锥曲线切向量)的理解

  ![椭圆切向量公式](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/understand_tangent.png)

  ![切向量理解1：直接求导](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/tangent_1.png)

  ![切向量理解1：直接求导](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/tangent_2.png)

  ![切向量理解2：对构造的f进行求导的理解，注意切线和f没关系，是x1,x2之间的关系](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch20/tangent_3.png)


---

### 曲线和正定性

- 正定性
  
  `P483` 正定性的定义，以及判定矩阵是否为正定矩阵的2种方法：特征值分解(要求对称矩阵)和Cholesky分解

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch21/check_possitive.png" alt="判定正定矩阵" style="width:100%;">
    <div style="width: 100%;"></div>
  </div>


  `P484` 格拉姆矩阵至少是半正定矩阵
  
- 几何视角看正定性
  
  `P485` 不同正定性的几何意义

- 开口朝上抛物面：正定

- 山谷面：半正定
  
- 开口朝下抛物面：负定
  
- 山脊面：半负定
  
- 双曲抛物面：不定
  
- 多极值曲面：局部正定性
  
  `P497~498` 一般多元函数，在梯度向量为0的xp处，根据黑塞矩阵的正定性判断xp是极大值还是极小值


  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch21/Hessian_positive.png" alt="一般多元函数判断，联合梯度和黑塞矩阵判断极值点" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch21/understanding_extream_value.png" alt="黑塞矩阵正定性和特征值分析" style="width:100%;">
  </div>

---

### 数据与统计

本章以鸢尾花数据为例，给出了均值、质心、中心化、方差、协方差、相关性系数等的例子


- 统计+线性代数：以鸢尾花数据为例
- 均值：线性代数视角
  `P503` 均值，以鸢尾花数据集X为例，就是每个特征的均值，有4个特征（4列），所以有4个均值。 E(x_j) = E(Xj)= 标量。 j表示第几个特征，或者第几列。

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/centroid_1.png" alt="均值的定义" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/understand_Ex_I.png" alt="xj向量在1方向的投影和均值E(xj) 关系" style="width:100%;">
  </div>

- 质心：均值排列成向量
  
  主要讲了本书中质心的符号表示

  `P506-507` 质心是各个特征的均值构成的列向量, μX是列向量。这本书还将E(X)定义为行向量，等于 (μX)^T。但同时本书也说明了，E(chi)还是列向量。

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/centroid_2.png" alt="质心的列向量符号约定" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/centroid_3.png" alt="质心的行向量符号约定" style="width:100%;">
  </div>


- 中心化：平移
  
  `P508` 介绍质心 Xc=X-E(X)，注意Xc还是150x4，相当于每个样本(的4个特征) - 均值

  `P508` 介绍了中心化矩阵Xc=MX=X-Ex，M就是中心化矩阵。中心化矩阵M可用后后面的协方差的计算(`P515`)

  `P509` 介绍了对Xc进一步标准化，就是Z分数，含义是距离均值若干倍的标准差偏移。比如说，标准化得到的数值为3，也就是说这个数据距离均值3 倍标准差偏移。数值的正负表达偏移的方向。

  `P509` 介绍了数据惯性(inertia)SSD的概念

- 分类数据：加标签
- 方差：均值向量没有解释的部分
  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/variance.png" alt="方差和均值的关系" style="width:100%;">
    <div style="width:100%;"></div>
  </div>
- 协方差和相关性系数
  
  `P514` 协方差定义。 x^Ty=y^Tx=标量。理解的时候，把x和y理解成数据矩阵X的一列(e.g, 第一列，第3列)，x和y的每行都是一个样本的某个特征值

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/deduce_cov_2.png" alt="协方差的矩阵写法和代数写法.(40)中也给出了协方差和方差/相关系数的关系" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/deduce_cov.jpg" alt="协方差矩阵推导" style="width:100%;">
  </div>

  `P515~P516` 给出了相关性系数、协方差的定义及向量写法。同时说明了相关性系数和余弦相似度类似，取值范围都是[-1,1]。最后总结了2个随机变量的方差和协方差的关系、这个关系和余弦定理类似。或者说余弦定理可以用在向量内积和协方差上。

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/cosine_rule.png" alt="余弦定理可以用在向量内积和协方差上" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

  

- 协方差矩阵和相关性系数矩阵
  
  `P517` 给出了协方差矩阵的定义和用数据矩阵X来计算Σ

  `P517` 对协方差矩阵求二次型，x^TΣx，其图像是一般椭圆(不带平移，带平移的话就是(x-μ)$^TΣ(x-μ))，然后对Σ进行特征值分解后带入x^TΣx并新增变换y=V^Tx进行简化，会得到一般旋转椭圆(x坐标系)到正椭圆(y坐标系)的几何转换

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/covariance_maxtrix_definition.png" alt="" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch22/covariance_matrix_curve.png" alt="" style="width:100%;">
  </div>

  `P518` 相关性系数矩阵定义

---

### 数据空间

- 从数据矩阵X说起
  
  `P526 ` 再次明确了本书的各类符号
  
  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch23/notation.png" alt="" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

- 向量空间：从SVD分解角度理解
  
  介绍X列向量和行向量张成的四个空间以及它们之间关系

  `P527` 列向量：列空间、左零空间；行向量：行空间、零空间

  `P528` 总结前面所学，再次来认识完全型SVD分解

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch23/understand_svd.png" alt="S叫奇异值矩阵" style="width:100%;">
    <div style="width:100%;"></div>
  </div>
  

- 紧凑型SVD分解：剔除零空间

  `P529~531` 从紧凑型SVD分解讲解4个空间
  [4个空间.excalidraw](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/4_spaces.excalidraw)

- 几何视角说空间
  
  `P532~536` 用实际例子解释了 XV=US 以及 X^TU=VS^T。如何找到U和V呢，可以用SVD和特征值分解，注意特征值分解也可以得到U和V，不是过不是直接对X特征值分解，而是对XX^T特征值分解 -> U; X^TX特征值分解可以得到V

  需要重新理解下：[XV和XVV^T的理解.drawio](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/power-of-matrix.drawio)

  可以对任意矩阵进行SVD并得到UVS，而只能对可对角化矩阵进行特征值分解，所以这里构造了XX^T和X^TX来得到U和V

  从前文的SVD分解中得知，SVD的U和V就是和特征值分解是有关系的。


- 格拉姆矩阵：向量模、夹角余弦值的集合体
  
  `P538` X的格拉姆矩阵有G=X^TX和H=XX^T。

  格拉姆矩阵G包含信息：X 列向量的模、列向量两两夹角余弦值
  格拉姆矩阵H包含的信息：X 行向量的模、行向量之间两两夹角余弦值

  `P539` 特征值分解可以知道SVD分解中的U和V，且特征值非零部分都一样（其他的都是零，零的数量不一样）
  对G特征值分解 -> V
  对H特征值分解 -> U
  对G和H特征值分解的结果：2个分解的非零特征值都一样，且和X的奇异值是开根号的关系

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch23/gram_matrix.png" alt="" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

- 标准差向量：以数据质心为起点
  
  `P540` 协方差矩阵可以看成特殊的格拉姆矩阵。有X，X_c, Z_x，他们各自有4个空间
  `P541` 介绍了标准差向量，可以用于协方差矩阵中。标准差向量是认为构造出来的

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch23/standard_deriva_vector.png" alt="标准差向量：以E(x)为起点，以x1为终点，长度为标准差的向量。其中的sqrt(n-1)应该是没有用的，是为了让其平方就等于δ^2" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

- 白话说空间：以鸢尾花数据为例
  
  `P543-P546` 以鸢尾花数据为例，解释了4个空间，其中的行空间用到了y=Xv的欧氏距离等于对应的特征值的前置知识。


---

### 数据分解

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/data_decomposition.png" alt="" style="width:100%;">
  <div style="width:100%;"></div>
</div>

- 为什么要分解矩阵

  `P551` 总结了4中常见矩阵分解的前提，几何视角，特殊类型，向量空间和优化视角


  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/decomposition_1.png" alt="" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/decomposition_2.png" alt="" style="width:100%;">
  </div>

  `P552` 谱分解、特征值分解和奇异值分解的关系
  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/relation_decomposition.png" alt="" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

  `P554` 介绍了本书中用到的会对其进行分解的矩阵，如X，Σ，G等

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/related_X.png" alt="" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

  `P555` 各类矩阵(X、Xc、Zx、G、Σ、P)和矩阵分解之间的关系

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/matrix_and_matrix_decomposition.png" alt="" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

- QR分解：获得正交系

  `P555` 对X进行QR分解，Q 的列向量 [q1, q2, q3, q4] 是规范正交基。[q1, q2, q3, q4] 相当于 [x1, x2, x3, x4] 正交化的结果，x1 和q1 平行。QR 分解和格拉姆-施密特正交化 (Gram–Schmidt process) 之间联系

- Cholesky：找到列向量的坐标
  
  `P557` X是n x D，通过对G=X^TX进行 Cholesky分解，可以得到R(DxD的矩阵)，R可以装下nxn的所有数据
  `P558` 协方差矩阵再次用到标准差向量来表示

- 特征值分解：获得行空间和零空间
  
  对数据的格拉姆矩阵、协方差矩阵、相关性系数矩阵进行特征值分解，并从优化角度解释其含义

  `P560` 特征值分解格拉姆矩阵对应的优化问题——找到一个单位向量v，使得X 在v 上投影结果y 的模最大。另有：特征值分解得到的特征值之和，等于原矩阵对角线元素之和
  `P561` 对协方差矩阵特征值分解，就是要找到一个单位向量v，使得中心化数据Xc在v 上投影结果yc 的方差最大。另有：Σ 的特征值之和，等于X 的每列数据方差之和
  `P562` 也对相关性系数矩阵进行特征值分解并解释其含义，不过不太懂


- SVD分解：获得四个空间

  `P563~P564` 分别对原始数据矩阵X，中心化数据Xc，标准化数据Zx，进行SVD分解，可以获取其4个空间

---

### 数据应用

- 从线性代数到机器学习
  
  `P570` 介绍了有监督学习(回归、分类)、无监督学习(降维、聚类)的概念。其中《数据有道》讲回归、分类；无监督学习讲降维、聚类。

  本书是：一元到多元的基础，如多元微积分、多元概率统计、多元优化等

- 从随机变量的线性变换说起
  
  `P572` 介绍了随机变量X的**线性变换**，可以得到Y，并得到期望和方差之间的关系。如Y=aX+b，则E(Y)=aE(X)+b, var(Y)=var(aX+b)=a^2var(X)
  `P572` 介绍了二元随机变换，如果是线性映射，其期望和方差之间的关系

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch25/2_variable_X.png" alt="" style="width:100%;">
    <div style="width:100%;"></div>
  </div>

  `P573` 扩展到D维随机变量：可以认为X的一列就是1个随机变量，一般可能有4列

- 单方向映射
  
  hat(y) = Xv

  `P574-577` 数据矩阵X的每一列被当成了随机变量，然后从线性变换的列空间(投影)和行空间(降维)视角看单方向映射，并给出了期望、方差及几何视角

  var(hat(y)) = v^T(Σ_X)v

  <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch25/single_direction.png" alt="投影和降维" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch25/Ex_single_direction.png" alt="期望" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch25/var_single_direction.png" alt="方差，是个变量" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch25/understandign_single_direction.png" alt="几何视角" style="width:100%;">
  </div>

- 线性回归
  
  上一节是投影，没有残差。这一节是回归，有残差。

  y = Xb + ε

  目标是找到这样的b，使得ε最小。

  `P579~582` 分别从投影视角、QR分解、奇异值分解、最优化(最小二乘法OLS，即求导法)角度给出b的值

  其中用最小二乘法，用到了矩阵求导：一阶和二阶求导，参考了`<the matrix cookbook>` 的(69)(81)(98)

- 多方向映射
  
  Y=XV

  `P582~583` 给出了X向多个方向投影的向量空间理解，以及期望，协方差

  协方差：Σ_Y=V^T(Σ_X)V

- 主成分分析(PCA)
  
  `P584`

  主成分分析就是多方向映射

  通过线性变换，PCA 将多维数据投影到一个新的正交坐标系，把原始数据中的最大方差成分提取出来。PCA 也是数据降维的重要方法之一。

  作为重要的降维工具，PCA 可以显著减少数据的维数，同时保留数据中对方差贡献最大的成分。另外对于多维数据，PCA 可以作为一种数据可视化的工具

  给出了PCA六条主要技术路线，其中用到了奇异值分解、特征值分解两种矩阵分解工具。矩阵分解的对象对应六种不同矩阵，这六种矩阵都衍生自原始数据矩阵X


## 费曼笔记

- 线性代数中的线性是什么意思


- 矩阵运算符号

  列向量·列向量  -> 逐项积/内积/标量积/点积，< v1,v2 > == < v2, v1> == 列向量v1·v2 == v2·v1 == 矩阵乘法：(v1)^T(v2) == (v2)^T(v1) == (a_1 * b_1 + a_2 * b_2) == ||v1||||v2||cos(θ) (`P218`)  -> 有转置的是矩阵运算，有·的是向量内积运算
  列矩阵 @ 行矩阵 或者 (列矩阵)(行矩阵)（中间没有点）-> 矩阵乘法，是个长方形矩阵  -> 有@符号或者没有·的运算直接相乘，都是矩阵运算，即使是写成向量方式
  列向量 ⊗ 列向量  -> 张量积，是个矩阵，同 列矩阵 @ 行矩阵
  列向量 x 列向量  -> 叉积，结果是个向量，方向是右手法则

- 左乘还是右乘

  - 取决于是行向量还是列向量
    - 如果 x是列向量，则 BAx = b 的意思是先进行A再进行B
    - 在上面的基础上进行转置，则是(x^T)(A^T)(B^T)=b^T，此时x^T是行向量，此时先进行A^T变换，再进行B^T变换，得到b^T
  - 在运动学中，分为绕固定轴或者旋转轴
    - 以列向量代表点位，则：左固右动

- 样本点、行向量/列向量与矩阵M

  约定俗成，各种线性代数工具定义偏好列向量；但是，在实际应用中，更常用行向量代表数据点。两者之间的桥梁就是——转置。

  如本书中的：

  ![鸢尾花数据矩阵X](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/IrisDataSet.png)

  我们常见的Ax=b -> 这里的x是一个列向量，即一个样本点（花萼长宽/花瓣长宽），b也是一个列向量(样本点)。 A的每一列可以看成是新的坐标系的坐标轴的值，b是x在新坐标系下的每个坐标的坐标值。如果是很多个样本点，则是AX=B。

  <div style="display: flex;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch07/coordinate-values.png" alt="坐标值的理解" style="width: 100%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch07/matrix-basis.png" alt="坐标系和坐标值" style="width: 100%;">
  </div>

  <div style="display: flex;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/geo_view.png" alt="旋转矩阵1和矩阵乘向量理解1" style="width: 100%;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/geo_view2.png" alt="旋转矩阵1和矩阵乘向量理解2" style="width: 100%;">
  </div>

  但如果用行向量表示一个样本点，从上面的(Ax)^T=b^T = (x^T)(A^T)=b^T，此时x^T是行向量，表示一个样本点， b^T也是一个行向量，表示样本点。 A^T和原来的A也已经是转置的关系。
  
  想想鸢尾花数据矩阵X，每一行代表一个样本点，每一列代表一个特征。这一点和Ax=b的理解恰好相反。

  

  - 向量
    - 可以表示：坐标系的坐标轴(标准的如e1/e2，也可以是非标的)、方向（法线、方向...）
    - 一个样本点，可以是列向量或者行向量，取决于相关领域的习惯
  - 矩阵
    - 矩阵M：(坐标系的)线性变换
      - (坐标系的)线性变换特点：平行且等距，原点保持不变
      - 坐标系发生线性变换，那么依赖于原先坐标系的x，也会发生相应的线性变换(y) -> Mx=y  (x这里是列向量，M是线性变换或者新的坐标系)
      - 旋转矩阵、缩放矩阵、置换矩阵、镜像矩阵、方程组的系数矩阵...
      - [几何变换](#ch08_geometric_ransformation)
      ![线性变换](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/understand_transform.png)
    - 数据矩阵X：
      - 鸢尾花数据矩阵，可以是每行表示一个样本点（花瓣长宽、花萼长宽）；也可以是每列表示一个样本点
    - 高阶含义：
      - 各类特殊矩阵
      - 格拉姆矩阵：其对角线元素含有L^2范数信息，在二次型中会用到
      - 雅克比矩阵：导数信息
      - 海森矩阵：二阶导数信息
      - 。。。
  - 矩阵和向量
    - 矩阵表示线性变换
      假设这里的向量都是列向量（x,y,b）
      - Mx = y
        - 含义：M表示变换, x是某个样本点，含义是样本点x经过M变换后得到y
        - 也即在标准坐标系下的x，将标准坐标系变换(线性变换)到M（e1,e2 -> 新坐标轴(M的第一列和第二列)）后，原来的点x也同步变成了y（其各个坐标值也是用标准坐标系下的分量来表示）
      - 线性方程组：Ax=b
      - 几何变换：y=Ax=RSx = Mx
      - ...
    - 矩阵表示数据
      假设这里的数据矩阵X是每行表示一个样本点，类似鸢尾花数据矩阵。
      - Xv=z
        - 是个降维的过程：将n个样本点，每个样本点有4维（4个特征值：花瓣长宽、花萼长宽），降维到还是n个样本点，但是每个样本点只有1维的z，降维方法是v
        - 降维是降低样本点特征的维度，样本点的数量不会减少
        - 这里的v和z仍然是列向量
        - v是表示如何将多维数据矩阵X变换为一维，z表示降低为1维的每个样本点的新数据
          ![P128](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/Xv.png)
        - 一个数据和很多个样本数据，降维运算是一样的
          - 1x4(X) * 4x1(v) = 1x1(z)   <--  --> 1个样本点，4个维度  * v（4x1） = 1个样本点，1个维度
          - 150x4(X) * 4x1(v) = 150x1(z)   <--  --> 150个样本点，4个维度  * v（4x1） = 1个样本点，1个维度

  - 矩阵和矩阵
    - 一个矩阵表示数据矩阵，一个矩阵表示维度变换方式矩阵
      - Xv=z --> XV=Z
          - 1x4(X) * 4x2(V) = 1x2(Z)   <--  --> 1个样本点，4个维度  * v（4x2） = 1个样本点，2个维度
          - 150x4(X) * 4x2(V) = 150x2(Z)   <--  --> 150个样本点，4个维度  * v（4x2） = 150个样本点，2个维度
          - 1x4(X) * 4x4(V) = 1x4(Z)   <--  --> 1个样本点，4个维度  * v（4x4） = 1个样本点，4个维度
          - 150x4(X) * 4x4(V) = 1x4(Z)   <--  --> 150个样本点，4个维度  * v（4x4） = 150个样本点，4个维度
        ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/XV.png)
        ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/XV2.png)
      - 将上面的V看成M，则 XV=Z
    - 一般理解的MX=Y，这里的X是每列是一个样本点
      - (MX)^T=Y^T = X^TM^T=Y^T  -> 这样就把X^T存在左边了
      ![](https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch05/MX.png)



  其他参考：

  - [常见几何变换](#ch08_transform)





- 向量的矩阵表达法


  **参考**
  - [矩阵转置](#ch04_transpose)
  - [向量和向量](#ch05_vector_vector)

- 矩阵求导
  
  如果想真正学习矩阵求导的话，建议把 Old and New Matrix Algebra Useful for Statistics from Thomas P. Minka 过一遍。矩阵求导麻烦就在于很多时候，直接用链式法则不管用，强行用的话需要做很多转置、reshape的变换，才能让矩阵之间的维度匹配。而Thomas这本书走的是另一个路子，写出矩阵的“微分形式”，把这一套学到手后，基本任何形式的矩阵求导的推导都不再是问题，也不需要再死记硬背了。
  参考：[关于矩阵（矩阵求导、矩阵范数求平方之类）](https://www.zhihu.com/question/338548610/answer/835833420)



  可以看看这个教程：The Matrix Calculus You Need For Deep Learning，ANTLR之父和Jeremy Howard写的如果只是想计算矩阵微积分，可以参考里奇微积分(Ricci Calculus)：一种计算向量求导，矩阵求导，张量求导的简单方法，在matrixcalculus.org上直接就能计算矩阵微积分如果想培养一下数学直觉，也可以看看《The mathematics you missed》的第一到三章，后面的章节对于机器学习也很有帮助。
  参考：[关于矩阵（矩阵求导、矩阵范数求平方之类）](https://www.zhihu.com/question/338548610/answer/835833420),[里奇微积分](https://zhuanlan.zhihu.com/p/63176747)


  书籍：[Old and New Matrix Algebra Useful for Statistics](https://tminka.github.io/papers/matrix/minka-matrix.pdf)、[The Matrix Calculus You Need For Deep Learning](https://arxiv.org/abs/1802.01528)、数学符号理解手册、[在线版Matrix Calculus](https://www.matrixcalculus.org/matrixCalculus)、那些年你没学明白的数学：攻读研究生必知必会的数学


- 欧拉角中的Sxyz和Rzyx的关系

- 一些口诀
  - 单行矩阵@单列矩阵=数，多行矩阵@单列矩阵=单列矩阵，多行矩阵@多列矩阵=多行多列矩阵
  - 行(向量)·列(向量) = 数字，“行列式”，可能是距离/缩放信息：cos距离，范数，投影距离，行列式等等
  - 单列矩阵@单行矩阵 = 矩阵  -> 对应到列向量的张量积

---

## Cheat Sheet

- [The Matrix Cookbook](https://www.math.uwaterloo.ca/~hwolkowi/matrixcookbook.pdf)
- [Old and New Matrix Algebra Useful for Statistics](https://tminka.github.io/papers/matrix/minka-matrix.pdf)
- [The Matrix Calculus You Need For Deep Learning](https://arxiv.org/abs/1802.01528)
- [在线版Matrix Calculus](https://www.matrixcalculus.org/matrixCalculus)
- [线性代数的艺术](https://github.com/kenjihiranabe/The-Art-of-Linear-Algebra)(Graphic notes on Gilbert Strang's "Linear Algebra for Everyone")
  - [线性代数的艺术中文版](https://github.com/kf-liu/The-Art-of-Linear-Algebra-zh-CN)
- [Thesaurus of Mathematical Languages, or MATLAB synonymous commands in Python/NumPy](https://mathesaurus.sourceforge.net/)(网站整理了常用MATLAB-R-Python命令、函数之间关系)
- [Matrix calculus](https://en.wikipedia.org/wiki/Matrix_calculus)
- [Old and New Matrix Algebra Useful for Statistics](https://tminka.github.io/papers/matrix/minka-matrix.pdf)(矩阵的“微分形式”)

---

## 参考资料

- [streamlit](https://streamlit.io/)
  - [Streamlit documentation](https://docs.streamlit.io/)
- [KATEX](https://katex.org/docs/supported.html)(streamlit.latex和notion中可以用到)
  - [Table Generator](https://www.tablesgenerator.com/html_tables)
- [符号表](https://typst.app/docs/reference/symbols/sym/)
- [3Blue1Brown](https://space.bilibili.com/88461692?spm_id_from=333.337.0.0)
- [immersive linear algebra](http://immersivemath.com/ila/index.html)
- [GeoGeBra](https://www.geogebra.org/)
- [ghProxy](https://gh-proxy.com/)
- [wm-blog-image](https://github.com/wumin199/wm-blog-image)
