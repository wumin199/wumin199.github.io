---
title: 矩阵知识快查
date: 2024-01-18 22:06:48
tags: 读书笔记
toc: true
comment: true
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3


---


矩阵直觉和知识快查

<!-- more -->

<article class="message message-immersive is-primary" style="margin: 10px;">
<div class="message-body">
可以从 <矩阵力量> 笔记中做进一步检索
</div>
</article>


## 参考

- [在线白板](https://excalidraw.com/)(书写50%，看：30%~40%)


## 坐标变换

1. 线性变换和坐标变换
   - 网格线平行且等距，@TODO:补充3Blue1Brown
   - 对坐标系的“变换” or “拉扯”，坐标系中的图像也跟着“变换”或“拉扯”
   
   <div style="display: flex; justify-content: center; align-items: center;">
   <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_note/coordinate_transform.png" alt="" style="width:100%;">
   <div style="width: 100%;"></div>
   </div>

   [坐标变换.excalidraw](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/coordinate_transform.excalidraw)

2. 齐次变换T含义
   - 齐次矩阵/齐次变换含义：表示先旋转再平移，而不是先平移再旋转
     - y=Ax+b；而不是y=A(x+b)
      <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_note/euclidian_trans.png" alt="" style="width:100%;">
      <div style="width: 100%;"></div>
      </div>
   - [notion: 仿射变换 4x4 Affine transformation 链式法则](https://www.notion.so/wumin199/6511ccacc7bd42298c1554ba79d354f7?pvs=4#3760afb9e2264a2ba257d12ade122926)
   - [notion: 线性变换 欧式变换 刚体变换 仿射变换 齐次矩阵 Isometry](https://www.notion.so/wumin199/6511ccacc7bd42298c1554ba79d354f7?pvs=4#15e5ef09d85240b89b4023ec6d0e14ff)
3. Sxyz和Rxyz
   1. s:static; r:relative
      - RPY = Sxyz，少部分厂家的RPY定义比较奇葩
      - 一般说欧拉角，最有可能说的是relative类型的(但也不一定)
   2. 对多次进行旋转操作 → 叠加的最终的旋转矩阵 → 左固右动
      - 左乘 → (固)定轴     < - - - - - - - -
         - 固定轴：左乘
      - 右乘 → 动轴 →  - - - - - - - - >
         - 旋转轴：右乘
   3. sxyz和rxyz
      <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_note/rot_maxtix.png" alt="" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_note/rxyz_szyx.png" alt="" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_note/rxyz_szyx_summary.png" alt="" style="width:100%;">
      </div>

      - [notion: 欧拉角与旋转](https://www.notion.so/wumin199/6511ccacc7bd42298c1554ba79d354f7?pvs=4#3a51f6f96dc64e53894e47b084e6a008)

4. 注册抓取/跟踪抓取
   [注册抓取.excalidraw](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/register_pick.excalidraw)

   1. 物体在哪里(vision捕捉)，就去哪里抓
   2. LeapMotion(跟踪抓取)：手势在哪里，机械臂就走到哪里
5. 点动机器人；眼在手上和眼在手外标定
   
   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_note/jog_abc.png" alt="点动机器人" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_note/camera_calib.png" alt="眼在手上和眼在手外标定" style="width:100%;">
    </div>

   - [notion:点动机器人](https://www.notion.so/wumin199/6511ccacc7bd42298c1554ba79d354f7?pvs=4#5b28af54a9ce43b3b311ec580fcb5b6f)
   - [notion: 眼在手上和眼在手外标定](https://www.notion.so/wumin199/6511ccacc7bd42298c1554ba79d354f7?pvs=4#d4a7fd160d00442485caade091bc79f5)

## 正定/二次型/圆锥曲线/圆/椭圆/优化问题/距离/面积(体积)/行列式

<article class="message message-immersive is-primary" style="margin: 10px;">
<div class="message-body">
相关案例：x^x, x^TQx, (x-μ)^TQ(x-μ), (x-μ)^TΣ^(-1)(x-μ)

x^TQx是一般的二次曲面，根据Q的不同，可以是单位正圆、旋转椭圆等，Q可以是单位矩阵、Σ^(-1)等
</div>
</article>

[特征值分解和椭圆.excalidraw](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/EVD_ellipse.excalidraw)

1. 距离：x^Tx，general版本都可以往距离上考虑：x^TQx，(x-μ)^TQ(x-μ)  -> 这里有二次型
   1. 一些变种：x^Ty, (x-μ)^T(Σ)^(-1)(x-μ)
   
   案例：

   2. 两个点(a,b)的欧式距离 d = sqrt((a-b)*(a-b)) = (a-b)^T(a-b) = sqrt(△x1 * △x1 + △x2 * △x2 + △x3 * △x3)
      1. 这里的点向量形如(x,y,z)，如长宽高
   3. 还有一种情况，就是x向量的每一行，其实是一个样本，含义都一样，而不是类似长宽高
      1. 这时候 x^Tx，含义是所有样本点的值^2的和，或者说距离平分和，因为开根号以后也可以理解成距离
      2. 本质上其实也是类似上面的d，只不过这里是把所有样本点的距离平方都加起来后再开根号
      3. `方差，协方差都是来自于距离的概念：距离平分`
   4. `<矩阵力量> P471` 多元高斯分布中的马氏/马哈距离。马哈距离的独特之处在于，它通过引入协方差矩阵在计算距离时考虑了数据的分布
2. 体积：行列式：|A|
   
   行列式相关：
   1. 只有方阵才有行列式
   2. 行列式的几何含义：以A的各列构成的面积/体积，是为行列式的值，可正可负
   3. `<矩阵力量> P104` 通过特征值分解，可以将平行四边形/平行六面体变成长方形/长方体（特征向量都互相垂直），也即行列式的值等于特征值的乘积
   
   案例：

   4. `<矩阵力量> P473` 高斯多元分布中的分母的|Σ|^(1/2)，这里就是用到行列式中体积变换的含义（这里进行了开根号）
3. 圆、椭圆、椭球等，都是圆锥曲线的一种，都可以用二次型来表示 -> 这里有二次型
   
   x^Tx是正圆，x^TQx是一般的旋转椭圆，从这个角度理解(x-μ)^TΣ^(-1)(x-μ)，是个一般的旋转椭圆

   案例：

   1. `<矩阵力量> P517~P521` 给出了 xΣx，即协方差矩阵和椭圆的关系，及椭圆图像和样本点的直观关系
   2. `<矩阵力量> P468~469` 给出的多元高斯分布中 (x-μ)^TΣ^(-1)(x-μ) 和样本点的图形表示

4. 在圆变成椭圆的过程中，圆上的某些点构成了特征向量，这些特征向量经过旋转和缩放后，还是和原来的向量方向一致，二者正好是椭圆的长轴和短轴。这就是特征值分解和椭圆曲线的联系之一。
5. 只有可对角化的矩阵才能进行特征值分解，而且如果矩阵是恰好直接是对称矩阵，则可以得到谱分解
6. 矩阵正定性主要和二次型 有关系，即x^TAx，其中的x是多元向量，如x=(x1,x2,x3)。而这正好和优化问题产生关系，因为多元泰勒展开的二次项，即黑塞矩阵，就是二次型！ -> 这里有二次型
7. 判断正定性的方法：
   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch21/check_possitive.png" alt="判定正定矩阵，注意特征值又和行列式有关系" style="width:100%;">
    <div style="width: 100%;"></div>
    </div>
    第一点，如果不满秩，则特征值会有0的部分，则不是正定矩阵，最多半正定！
8.  旋转矩阵是正交矩阵
9.  对称矩阵起到缩放作用


## 特殊矩阵

- 雅克比矩阵
- 黑塞矩阵
  多元函数的二阶偏导数构成的矩阵，黑塞矩阵描述了函数的局部曲率，可以在优化问题中用到黑塞矩阵判断极值点
- Hermit矩阵

## 各种分解

1. 常见矩阵分解的使用场景
   
   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/decomposition_1.png" alt="" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/decomposition_2.png" alt="" style="width:100%;">
   </div>

2. <矩阵力量>中用到的会被进行矩阵分解的相关数据
   `P554` 介绍了本书中用到的会对其进行分解的矩阵，如X，Σ，G等

   <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/IrisDataSet.png" alt="数据矩阵X" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/X_notation.png" alt="" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/related_X.png" alt="" style="width:100%;">
      <div style="width:100%;"></div>
   </div>

3. 谱分解、特征值分解、SVD分解
   
   - **对A特征值分解和奇异值分解和最优化关系：A可以分解为一些二次型及其变种，其中含有`对角阵`：特征值矩阵/奇异值矩阵；而特征值/奇异值有大小之分，对这些分解展开后，然后放缩法可以找到最大最小边界，最大的边界一般是特征值/奇异值最大的时候；从几何角度理解，这些分解都可以找到一些正交投影矩阵，数据往这些投影矩阵投影的一些效果和特征值/奇异值大小有关；最优解利用了对应的特征值/奇异值下的特征向量或奇异向量**
  
   - `<矩阵力量> P552` 谱分解、特征值分解和奇异值分解的关系

      <div style="display: flex; justify-content: center; align-items: center;">
         <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch24/relation_decomposition.png" alt="" style="width:100%;">
         <div style="width:100%;"></div>
      </div>
   
   - 谱分解：针对对称矩阵的特征值分解
     - 对称矩阵有：X^TX，Σ矩阵，Z范数等
     - `<矩阵力量> P324-P325` 谱分解总要特性：Λ=V^TAV，其中A是对称矩阵
     - 如果A是X^TX，并通过Y=XV过度，可以得到 Y^TY=Λ，如果选取v，则y=Xv，最大的y^Ty对应最大的特征值
     - 这就是对X^TX谱分析的几何含义：找到一个单位向量v，使得X 在v 上投影结果y 的模最大
     - `<矩阵力量> P561`如果A是协方差Σ矩阵，则对应的几何含义：对协方差矩阵特征值分解，就是要找到一个单位向量v，使得中心化数据Xc在v 上投影结果yc 的方差最大
   - `<矩阵力量> P340 P420~P424, P551` 列出了一些和这2个分解有关的最优化的问题，如瑞利商等
   - SVD和特征值分解
    `<矩阵力量> P539` 特征值分解可以知道SVD分解中的U和V，且特征值非零部分都一样（其他的都是零，零的数量不一样）
      - X的格拉姆矩阵有G_(DxD)=X^TX和H_(nxn)=XX^T
      - 对G特征值分解 -> V，获取行空间和零空间，即特征空间(花瓣长、宽。。)
      - 对H特征值分解 -> U，获取列空间和左零空间
      - 对G和H特征值分解的结果：2个分解的非零特征值都一样，且和X的奇异值是开根号的关系
   
## 优化/正交/投影

集合了投影，正定性等的综合结论

[4个空间.excalidraw](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/4_spaces.excalidraw)(包含对XV和X^TU的理解，以及`<矩阵力量> P538`对X^TX和XX^T理解)

[XV和XVV^T的理解.drawio](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/power-of-matrix.drawio)



优化问题也和统计问题有关系，比如示教TCP工具，就用到4点法，可以理解为4个原本

1. 泰勒展开可以用于一元/多元函数的逼近，包括一次逼近(线性逼近)和二次逼近。
   
   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/single_value_taylor_expansion.png" alt="一元函数的泰勒展开" style="width: 100%; height: auto;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/mv_linear_2.png" alt="从一元到二元的线性逼近" style="width: 100%; height: auto;">
    </div>

   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/mv_linear.png" alt="多元函数的线性逼近(一次逼近)" style="width: 100%; height: auto;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/second_order.png" alt="多元函数的二次逼近" style="width: 100%; height: auto;">
    </div>


2. 多元函数的一阶导数 -> 梯度**向量**； 多元函数的二阶导数 -> 黑塞**矩阵**
   
   梯度下降方向 -- 下山方向
   
   梯度向量（gradient vector） -- 上山方向

   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/Ch01-gradient-vector.png" alt="梯度定义" style="width:100%;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/Ch01-gradient-vector-2.png" alt="梯度向量--上山方向" style="width:100%;">
   </div>


   <div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch17/hessian_matrix.png" alt="黑塞矩阵" style="width:100%;">
    <div style="width: 100%;">黑塞矩阵，图片中的(19)指的是对f(x)=x^TQx记性对x求二阶偏导</div>
   </div>

3. 可以根据梯度向量和黑塞矩阵，判断多元函数极值。要判断 1/2(△x)^T* H* △x 的正负值，就需要判断黑塞矩阵的正定性
4. 多项式回归(拟合)，可以从投影的角度理解。
   
   **投影向量和原来的向量的残差是最小的**

   - 1个点(是个向量)，往一个方向，如坐标轴x方向回归/拟合 -> 一元线性回归

      <div style="display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_4.png" alt="一元线性回归" style="width:100%;">
        <div style="width: 100%;">1个点的一元线性回归(二维)，hat(y)=bx，其中x,y都是向量！，x表示横坐标，如用向量(1,0)表示, y是空间一点，比如（4，5）, hat(y)表示y点往x正交投影的结果为hat(y), 如(4,0)。当然中间会有残差，就是y-hat(y)</div>
      </div>

   - 1个点(是个向量)，往多个方向，如x1方向(第一个方向),x2方向(第二个方向)回归/拟合 -> 多元线性回归

      <div style="display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_6.png" alt="多元线性回归" style="width:100%;">
        <div style="width: 100%;">是个三维空间，往二维平面投影。注意hat(y)=Xb，表示y往X各列span的空间投影，得到的线性组合是b</div>
      </div>

   - 现实中，可能是采样了很多个点(x1时刻的y1，x2时刻的y2，x3时刻的y3)，然后绘制这个曲线，发现不太像直线，可能考虑用多项式拟合 -> 这时候也可以转换为正交投影理解

      可以通过列成矩阵的形式，转换为正交投影的理解。然后利用多元线性回归的相关工具，求出线性组合b。详细见 `<矩阵力量>中的正交投影 章节`



      <article class="message message-immersive is-primary" style="margin: 10px;">
      <div class="message-body">
      3次多项式拟合n个数据，设计矩阵X就是个n x (3+1)，3次多项式可以贡献4个特征(方向)：b0,b1,b2,b3。n就是样本点个数。这里正交投影就是hat(y)=Xb,找到这样的b，使得hat(y)和y的gap最小。用到了帽子矩阵来求b，因为y-hat(y),即 y-正交投影的y,误差肯定是最小的！
      </div>
      </article>

      <div style="display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_8.png" alt="多项式拟合" style="width:100%;">
        <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch09/summary_9.png" alt="采集到的其实是y，而不是hat(y)，两者之间其实有gap，就是残差。注意hat(y)=Xb的公式同上面，图片中最左边的是hat(y)]，就是[y1,y2,y3..]^T。注意这个公式和多元线性回归的图是一致的" style="width:100%;">
      </div>
5. 拉格朗日乘子法：拉格朗日乘子法就是一种能够把有约束优化问题转化成无约束优化问题的方法

6. 特征值分解和奇异值分解和最优化关系：
   - 特征值分解和奇异值分解和最优化关系：找正交投影矩阵，且特征值/奇异值有大小之分
   - `<矩阵力量> P420~P424, P551` 列出了一些和这2个分解有关的最优化的问题

## 方差/标准差/协方差

[协方差.excalidraw](https://github.com/wumin199/wm-blog-image/blob/raw/main/images/2023/power-of-matrix/covariance.excalidraw)

1. 涉及到这些统计量的，都是有很多个样本。一个典型的数据矩阵X是[x1, x2, x3, ...] 或者 [x, y, z, ...]，其中x1/x2/x3或者x/y/z都是列向量，每一列都是一个特征的n个样本集合。比如x是150个样本的花瓣长列向量， y是150个样本的花瓣宽列向量
   

   <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/IrisDataSet.png" alt="数据矩阵X" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2023/power-of-matrix/ch01/X_notation.png" alt="" style="width:100%;">
   </div>

2. 记住一个：统计学中x, y都是某个特征的n个样本集合：**相同特征都放到一个列向量中即可，每个特征都是由很多个样本组成的**
3. 我们可以单独研究某个特征，比如均值，方差；我们也可以研究2个特征之间的关系（如研究花瓣长和花瓣宽之间的关系），如协方差
   1. 所以涉及这些的计算，用代数表示的话会包含Σ求和，用矩阵的话包含x^Tx运算，因为所有样本点都要参与计算。
   2. 具体可以参考前文关于距离的论述