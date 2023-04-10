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


= 常见公式

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



== 三角函数


参考资料:
- #link("https://zh.wikipedia.org/zh-sg/%E4%B8%89%E8%A7%92%E5%87%BD%E6%95%B0")[
  wiki: 三角函数]

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

== 复数域内指数函数


#set text(size: 18pt)

$ e^(i x)  =  cos x + i sin x $


