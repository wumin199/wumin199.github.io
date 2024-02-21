---
title: 矩阵微积分
date: 2024-02-05 20:11:00
tags: 笔记
toc: true
comment: false
katex: true
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

Matrix Calculus

<!-- more -->

## 学习顺序

### 学习目标

- 怎么对向量函数求导，到底要不要转置，如何统一一元和多元求导（Fréchet derivative）



### 快速入门

- [B站：矩阵求导——标量函数的偏导数矩阵](https://www.bilibili.com/video/BV13V4y1U7qM/?spm_id_from=333.999.0.0&vd_source=991bc0898d44d84ddbbb0469ce816e70)
  - [矩阵求导的本质与分子布局、分母布局的本质（矩阵求导——本质篇）](https://zhuanlan.zhihu.com/p/263777564)(文字版)
  - [矩阵求导公式的数学推导（矩阵求导——基础篇）](https://zhuanlan.zhihu.com/p/273729929)(利用定义推导)
  - [矩阵求导公式的数学推导（矩阵求导——进阶篇）](https://zhuanlan.zhihu.com/p/288541909)(利用trace推导)
  - [对称矩阵的求导，以多元正态分布的极大似然估计为例（矩阵求导——补充篇）](https://zhuanlan.zhihu.com/p/305171795)
- [The Matrix Calculus You Need For Deep Learning](https://arxiv.org/abs/1802.01528)
  - [中文翻译版](https://blog.csdn.net/zqbin524101/article/details/109066033#t8)
- [Old and New Matrix Algebra Useful for Statistics](https://tminka.github.io/papers/matrix/minka-matrix.pdf)
- [知乎：矩阵求导术（上）](https://zhuanlan.zhihu.com/p/24709748)
- [知乎：矩阵求导术（下）](https://zhuanlan.zhihu.com/p/24863977)


### 系统学习

- [EECS127](https://eecs127.github.io/)(综合课程：线性代数+机器学习+最优化，网上有作业答案)
  
  他会涉及到

  1.线性代数上面范数， SVD等

  2.矩阵求导，向量求导等

  3.一些基础的优化方法

  也就是说，包含机器学习需要的大部分数学线代微积分前提知识，优化问题上给你打下不错的基础，在你后续学SVM，PCA，梯度下降等算法的时候一定会有很大的帮助。

- [Mathematics for Machine Learning](https://mml-book.github.io/)(不错，有例子，简洁易懂)

- Foundations of Applied Mathematics, Volume 1_ Mathematical Analysis-SIAM-Society for Industrial and Applied Mathematics(Jeffrey Humpherys, Tyler J. Jarvis, Emily J. Evans)

- Vector Calculus, Linear Algebra, and Differential Forms_ A Unified Approach(John Hubbard_ Barbara Burke Hubbard)


### 进阶学习

- 一个是代数、一个是分析，往往不会两者都好好写一起，一般都是分析中用一用代数而已
- 其实微积分和线性代数的结合就是functional analysis啦
- 如果线代和数分已经OK了，我推荐你直接去看Evans的偏微分方程第一部分，完全没问题，之所以不再推荐相关的数分或者代数教材，是因为我认为他们的联系，应该在更高年级课程里体现出来。

### Cheat Sheet

- [Online Matrix Calculus](https://www.matrixcalculus.org/matrixCalculus)(当矩阵变元是对称矩阵时，结果是错误的。（对于非对称的矩阵变元，可以放心使用）)
- [Calculus Cheat Sheet](https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/calculus_cheat_sheet.pdf)
- [The Matrix Cookbook](https://www.math.uwaterloo.ca/~hwolkowi/matrixcookbook.pdf)
- [矩阵求导知识点总结](https://www.cnblogs.com/gyhhaha/p/11782212.html)

### 其他资料

- 《Book: Apostol - Calculus Vol.2》( 側重multivariable，難度點到即止，初學友善。BTW他的Calculus, Vol. 1也不錯)
- 《A Visual Introduction to Differential Forms and Calculus on Manifolds-Fortney》(豆瓣说有一些typos,里面有关于多元微积分和线性代数的联系)
- 百度网盘：[ 永久笔记 ] -> [ wm-git-io ] -> [ matrix calculus]


## 笔记

### B站：矩阵求导——标量函数的偏导数矩阵


1. (01:40) 符号说明

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/types_notation.png" alt="" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/types_notation_2.png" alt="" style="width:100%;">
    </div>

2. (02:30) 案例：标量函数对向量自变量的导数，自变量可以写成行或列的形式
3. (04:20) 分子和分母布局（谁是列向量就是谁的布局），并有举例

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/vector_f_vector_x.png" alt="" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

4. (05:24) 向量函数对向量的求导距离，利用到分子和分母布局
5. (06：45) 推导了∂[ x^Ta ]/∂x = ∂[ a^Tx]/∂x = a

   这里这里的x和a都是向量，而 x^Ta 和 a^Tx 都是标量

6. (06:55) 推导了 ∂(x^TAx)/∂(x) = Ax + A^Tx
7. (09:05) 标量函数微分的几个性质

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/four_properties_of_scalar_funcition.png" alt="" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

8. (11:11) 矩阵乘法不满足交换律，但是套上trace后，满足交换律了。且trace是个标量

    总结：trace运算满足乘法交换律

    tr(AB)=tr(BA)

    tr(A)=tr(A^T)

    tr(AB^T)=tr(B^TA)=tr(A^TB)=tr(BA^T)
  
    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/trace.png" alt="AB^T的tr含义：只关注对角线" style="width:100%;">
      <div style="width: 100%;">tr(AB^T)=tr(B^TA)=tr(A^TB)=tr(BA^T); tr(AB)=tr(BA); tr(A)=tr(A^T)</div>
    </div>

    同时，通过 < The Matrix Cookbook >可知，tr(AB)=tr(BA), tr(A)=tr(A^T)

9.  (12:56) 矩阵函数dFmxn(X)微分的几个性质

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/differential_matrix.png" alt="" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

10. (13:59) 标量函数df(X)的全微分
    
    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/scalar_function_X.png" alt="标量函数df(X)的全微分。可以将等式右边看成2个矩阵乘积的trace" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/complete_differential.png" alt="x此时是向量" style="width:100%;">
    </div>

11. (14:09) 标量函数全微分的3个常见结果
    
    几个常见的标量函数全微分，有助于后续的求复杂偏导数的化简：

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/properties_of_differential_of_matrices.png" alt="矩阵微分性质" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/three_commonly_used_results.png" alt="这个结论可以用户后续复杂函数求偏导数的推导工具，证明见后文" style="width:100%;">
    </div>

    几种常见标量函数：f(X) = AXB; f(X) = |X|; f(X) = X^-1

    (14:39) 矩阵行列式(f(X) = |X|)的微分推导。推导时用到了行列式展开成代数余子式方式，并用到伴随矩阵的性质。
    
    (16:57) 逆矩阵(f(X) = X^-1)的微分推导。

12. (18:00) 标量函数全微分的一个重要推导
    
    推导方法就是上面对df(X)写成矩阵形式，发现和trace有关

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/df(X).png" alt="" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

13. (20:08) 标量函数求偏导数矩阵的五个步骤

    先要判断是不是标量函数！用到了上面的 标量函数全微分的一个重要推导 和 trace的一些性质

    对标量f(X)而言 f(X)=tr(f(X)) -> df(X)=d(tr(f(X)))=tr(d(f(X)))

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/manual_devivation.png" alt="" style="width:100%;">
      <div style="width: 100%;">Step4中：对标量而言，trace和d是可以互换的：df(X)=d(tr(f(X)))=tr(d(f(X)))：这是因为f(X)是标量，所以f(X)=tr(f(X))；然后用Step4的结论，将df(X)写成Step3的方式</div>
    </div>
    
    (21:03) 案例1

    (24:15) 案例2

    (27:09) 案例3

    (28:39) 案例4

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/example2_1.png" alt="案例1：这里直接化简，没有用到Step4的内容。对标量而言，tr和d可以直接交换" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/example2_2.png" alt="案例2: tr(AB)=tr(BA)" style="width:100%;">
    </div>

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/example2_3.png" alt="案例3" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/bili_introduction/example2_4.png" alt="案例4" style="width:100%;">
    </div>

---

### Old and New Matrix Algebra Useful for Statistics




1. (P1) 6种标准型
   
    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/6_derivatives.png" alt="" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/6_canonical_forms.png" alt="6种规范型，其中df=tr(AdX)和上面的df(X)=tr(AdX)含义一致" style="width:100%;">
    </div>

    其中的关于微分的理解：

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/understand_dy.png" alt="A是一阶偏导数，梯度向量，或者说Jacobian，或者说雅克比矩阵" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/differential_properties.png" alt="常见的微分性质，可用于后文推导" style="width:100%;">
    </div>
  

2. (P3) 有一些常见标量函数的导数
   
   根据tr(AB)=tr(BA)；tr(AB^T)=tr(B^TA)=tr(A^TB)=tr(BA^T)；tr(A)=tr(A^T)并结合规范型中的dy=tr(AdX)进行推导
   
   用到一个重要结论：``对标量f(X)而言 f(X)=tr(f(X)) -> df(X)=d(tr(f(X)))=tr(d(f(X)))``，然后利用tr的一些交换律和已有的一些常见导数结果，一直化简到规范型tr(AdX)，其中A就是偏导数的结果（应该有个transpose）


    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/some_differential_examples.png" alt="" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>
    

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_15.png" alt="(15)证明" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_16.png" alt="(16)证明" style="width:100%;">
    </div>

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_17_19.png" alt="(17-19)证明" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_20.png" alt="(20)证明" style="width:100%;">
    </div>  

3. (P4) 对称矩阵的偏导数
   
    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/symmetric_matrix.png" alt="log|X|的证明可以看 < 对称矩阵的求导，以多元正态分布的极大似然估计为例（矩阵求导——补充篇）>" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

4. (P6) 讲了Kronecker product，用到了再详细理解
   
   vec(ABC) = (C ′ ⊗ A)vec(B)

5. (P7) 讲了vec-transpose
   
   It is essential for expressing derivatives of Kronecker products and is also useful for expressing multilinear forms.
   
    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/vec_transpose_properties.png" alt="tr(A^TB)=vec(A)^Tvec(B)" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

    ---

    举例如下：

    假设我们有一个 2x2 的矩阵 dX，可以将其表示为：

    {% katex %}
    dX = \begin{bmatrix} a & b \\ c & d \end{bmatrix}
    {% endkatex %}

    根据之前的等式 {% katex %} 2tr(dX' dX) = 2vec(dX)' vec(dX) {% endkatex %}，我们可以进行计算和验证：

    计算左边的表达式：

    首先，计算矩阵 dX 的转置：{% katex %} dX' = \begin{bmatrix} a & c \\ b & d \end{bmatrix} {% endkatex %}
    然后将这两个矩阵相乘：

    {% katex %}
    dX' dX = \begin{bmatrix} a^2 + b^2 & ac + bd \\ ac + bd & c^2 + d^2 \end{bmatrix}
    {% endkatex %}

    最后计算迹（trace）：

    {% katex %}
    [ tr(dX' dX) = a^2 + b^2 + c^2 + d^2 ]
    {% endkatex %}

    计算右边的表达式：

    将矩阵 dX 向量化：{% katex %} vec(dX) = [a, c, b, d]^T {% endkatex %}
    求向量化后的矩阵的转置：{% katex %} vec(dX)' = [a, c, b, d] {% endkatex %}
    计算向量化后的向量自身的乘积：{% katex %} vec(dX)' vec(dX) = a^2 + b^2 + c^2 + d^2 {% endkatex %}
    因此，根据上述示例计算可得，左边的表达式等于右边的表达式，这进一步证实了该等式在这个 2x2 矩阵的情况下是成立的。

    ---
   
6. (P8) Multilinear forms，没看太懂
7. (P10) 讲了Hadamard product，用到再看
8. (P15) Hessian矩阵
   
    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/Hessian_matrix.png" alt="黑塞矩阵" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

    (P16) 几个求Hessian的案例

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_101.png" alt="(101)证明" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_102.png" alt="(102)证明" style="width:100%;">
    </div>

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_106_107.png" alt="(106_107)证明" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/old_and_new/proof_108.png" alt="(108)证明" style="width:100%;">
    </div>


    (P17) 如果标量函数y(X)中的参数X是对称矩阵，则黑塞矩阵有特殊性质。不过这个推导我没看懂

---

### The Matrix Calculus You Need For Deep Learning

1. (P6) gradient的介绍
2. 雅克比矩阵的介绍
   
   Note that there are multiple ways to represent the Jacobian. We are using the so-called numerator layout but many papers and software will use the denominator layout.

   Jacobian的可能形式

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/you_need_for_dl/jacobian_shapes.png" alt="" style="width:100%;">
      <div style="width: 100%;"></div>
    </div>

    同时参考：[深度学习中的矩阵求导入门（一）](https://zhuanlan.zhihu.com/p/538062723)，可以加深矩阵导数的理解和Jacobian的理解

    <div style="display: flex; justify-content: center; align-items: center;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/you_need_for_dl/understand_derivative_1.png" alt="" style="width:100%;">
      <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/matrix_calculus/you_need_for_dl/understand_derivative_2.png" alt="" style="width:100%;">
    </div>

  



## topics

### 雅可比矩阵

### 黑塞矩阵

### 条件数



