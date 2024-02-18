#set heading(numbering: "1.")

#show link: underline

#set text(
  font: "Noto Serif CJK SC",
  // font: "simsun",
  size: 12.5pt
)

#show raw.where(block: true): block.with(
  fill: luma(240),
  inset: 10pt,
  radius: 4pt,
)

#set math.equation(numbering: "(1)")


= 常见公式

== 乘法公式

#figure(
  image("乘法公式.png", width: 80%),
  caption: [乘法公式],
) <multiplication_formula>


参考资料:
- #link("https://zh.wikipedia.org/zh-tw/%E4%B9%98%E6%B3%95%E5%85%AC%E5%BC%8F")[
  wiki: 乘法公式]

== 三角函数

#figure(
  image("trigonometric_functions.jpg", width: 80%),
  caption: [特殊角三角函数值],
) <特殊角三角函数值>


#figure(
  image("特殊角三角函数值.png", width: 80%),
  caption: [特殊角三角函数值],
) <special_angle_rigonometrics>



参考资料:
- #link("https://www.geogebra.org/m/UtWkeRE5")[GeoGebra特殊角三角函数值]
- #link("https://zh.wikipedia.org/zh-sg/%E4%B8%89%E8%A7%92%E5%87%BD%E6%95%B0")[
  wiki: 三角函数]
- #link("https://www.baike.com/wikiid/295765833290522275?view_id=1vh67qncay2o00")[特殊三角函数值]



== 泰勒展开


无穷可微函数$f(x)$的泰勒展开式为:

$ 
f(x) = sum_(n=0)^oo  (f^n(a)) /(n!) (x-a)^n
$

$f^(n)(a)$表示函数$f$在$a$处的$n$阶导数,如果$a=0$,也把这个级数叫做麦克劳林级数.

#line(length: 100%, stroke: 0.2pt )

#set text(size: 14pt)
$ 
e ^ x = sum_(n=0)^oo x ^n /(n!) 
= 1+ x + x^2/(2!) + x^3/(3!) + ... + x^n/(n!) + ...  "  "
forall  ("对所有X都成立")
$

#line(length: 100%, stroke: 0.2pt )

$
sin x = sum_(n=0)^oo  (-1)^n /((2n+1)!) x^(2n+1)
= x - x^3/(3!) + x^5/(5!) - ... "      " forall
$

#line(length: 100%, stroke: 0.2pt )


$
cos x = sum_(n=0)^oo  (-1)^n /((2n)!) x^(2n)
= 1 - x^2/(2!) + x^4/(4!) - ... "      " forall
$

#line(length: 100%, stroke: 0.2pt )

参考资料:
- #link("https://zh.wikipedia.org/zh-sg/%E6%B3%B0%E5%8B%92%E7%BA%A7%E6%95%B0")[
  wiki: 泰勒级数]






== 常见求导公式

#figure(
  image("prime.png", width: 80%),
  caption: [求导法则],
) <求导法则>


#line(length: 100%, stroke: 0.2pt )

$ (e^x)^prime = e^x $

$ (e^(f(x)))^prime = f^prime(x)  e^(f(x)) $

#line(length: 100%, stroke: 0.2pt )

参考资料：
- #link("https://zh.wikipedia.org/zh-sg/%E5%AF%BC%E6%95%B0%E5%88%97%E8%A1%A8")[wiki: 导数列表]



= 公式理解

== 泰勒展开

=== $sin x$

```py

# 首先，计算 sin(x) 在 x=0 处的函数值和各阶导数。根据正弦函数的定义，有：
sin(0) = 0

# 对正弦函数求一阶导数，得到：

d/dx sin(x) = cos(x)
cos(0) = 1

# 对正弦函数求二阶导数，得到：
d^2/dx^2 sin(x) = -sin(x)
-sin(0) = 0

# 对正弦函数求三阶导数，得到：
d^3/dx^3 sin(x) = -cos(x)
-cos(0) = -1

# 对正弦函数求四阶导数，得到：
d^4/dx^4 sin(x) = sin(x)
sin(0) = 0

# 以此类推，可以得到：

d^5/dx^5 sin(x) = cos(x)
cos(0) = 1

d^6/dx^6 sin(x) = -sin(x)
-sin(0) = 0

d^7/dx^7 sin(x) = -cos(x)
-cos(0) = -1

d^8/dx^8 sin(x) = sin(x)
sin(0) = 0

# 根据泰勒级数展开式，将这些导数带入公式：
sin(x) = f(0) + f'(0)x + f''(0)x^2/2! + f'''(0)x^3/3! + ...


```

=== $e^x$


```py
e^x的导数还是e^x
e^0 = 1
...

带入到泰勒公式中得：

e^x = 1 + x + x^2/2! + x^3/3! + x^4/4! + x^5/5! + x^6/6! + ...

```


== 复数域内指数函数


#set text(size: 14pt)

$ z = a + i b "  复数的直角坐标表示" $

$ z = r e^(i theta) "  极坐标表示" $

$ r e^(i x)  = r cos x + r * i *sin x $

#figure(
  image("复指数函数.png", width: 80%),
  caption: [复指数函数],
) <复指数函数>





当 $r=1, theta = pi$时，这就是欧拉公式：

$ e^(i pi)  + 1 =0 $

常见的值如下：

$ e^((2 i pi) /3)  = cos ((2 pi )/3) + i * sin((2 pi)/3) = -1/2 + i sqrt(3) /2 $  <cubic_root_formula>

它的意思是在复平面上以1为起点，沿着单位圆逆时针旋转120度到达的点。


根据 @复指数函数 的指数运算可得：

$ (e^((2 i pi) /3))^3 =  e^(2 i pi) = 1 $ <120复根>

可以理解为走了3个120°

根据 @复指数函数 的指数运算可得：

$ (e^((2 i pi) /3))^2 =  e^((4 i pi)/3 )  $ 

可以理解为走了2个120°


== 一元三次方程

一元二次方程有0,1,2个实根

一元3次方程，最多有3个实根，至少有1个实根


$
a x^3 + b x^2 + c x + d = 0, a eq.not 0 $  <一元三次方程>

令：
$ x = t - b/(3a) $ <一元三次方程求x>
$ p = (3 a c - b^2)/(3a^2) $
$ q = (2b^3 - 9 a b c + 27 a^2 d)/(27a^3) $

可化简的 @一元三次方程 为：
$ t^3 + p t = q = 0 $ <quadratic_equation>



有很多方法可以求解 @quadratic_equation

=== 卡尔达诺(Cardano)公式



$ u = root(3, (-q/2 + sqrt(q^2/4 + p^3/27))) $

$ v =  root(3, (-q/2 - sqrt(q^2/4 + p^3/27))) $

简写以下：

$ Delta =  sqrt(q^2/4 + p^3/27) $

$ u = root(3, (-q/2 + sqrt(Delta))) $

$ u = root(3, (-q/2 - sqrt(Delta))) $

$ w = e^((2 i pi) /3)  = cos ((2 pi )/3) + i * sin((2 pi)/3) = -1/2 + i sqrt(3) /2 $

$ (e^((2 i pi) /3))^2 =  e^((4 i pi)/3 )  = cos ((4 pi )/3) + i * sin((4 pi)/3) = -1/2 - i sqrt(3) /2 $ 

则：

$ t_1 = u + v $

$ t_2 = w u + w ^2 v  = -(u + v)/2 + i sqrt(3)/2 (u - v) $

$ t_3 = w^2 u + w v  = -(u + v)/2 - i sqrt(3)/2 (u - v) $

其中的 $0$, $w$, $w^2$  可以根据 @120复根，理解为复平面内圆半径为1的三个点

然后根据 @一元三次方程求x 可得 $x$

=== 判别式(Discriminant)


#figure(
  image("一元三次方程判别式.png", width: 100%),
  caption: [一元三次方程判别式],
) <一元三次方程判别式>


参考资料:
- #link("https://zhuanlan.zhihu.com/p/137077558")[
  知乎: 一元三次方程的求根公式]
- #link("https://zh.wikipedia.org/zh-sg/%E4%B8%89%E6%AC%A1%E6%96%B9%E7%A8%8B#%E5%8D%A1%E5%B0%94%E8%BE%BE%E8%AF%BA%E6%B3%95")[wiki: 一元三次方程，含推导和例子]
- #link("https://zhuanlan.zhihu.com/p/413976771")[从一元二次方程到群论(4)：卡尔达诺公式]