---
title: 西门子PLC从入门到精通
toc: true
date: 2023-08-10 10:10:29
# cover: /gallery/covers/cover.jpg
# thumbnail: /gallery/thumbnails/thumbnail.jpg
# excerpt: 可以在这里写摘要，也可以用
# password: hello
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

一小时掌握西门子PLC

<!-- more -->

## Quick Start

PLC型号：
- PLC1200



## Tips


**大端模式**


西门子PLC是大端。

目前常见的CPU PowerPC、IBM是大端模式。

x86的PC是小端模式。

ARM既可以工作在大端模式，也可以工作在小端模式，一般ARM都默认是小端模式

一般通讯协议都采用的是大端模式。

![大小端](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/byte_order.png)

参考：

- [Siemens data format](https://snap7.sourceforge.net/siemens_dataformat.html)


**WORD/DWORD和INT/UINT/DINT**

WORD/DWORD 和 INT/UINT/DINT 在数据类型上属于不同分类。

![数据类型](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/data-type.PNG)
![WORD](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/WORD.PNG)
![DWORD](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/DWORD.PNG)
![INT](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/INT.PNG)
![UINT](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/UINT.PNG)
![DINT](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/DINT.PNG)
![word_compare](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/word_compare.PNG)


INT是一种数据类型，是16位整型数据，-32768-32767
WORD表示一个字，可以存放16位的数据，可以表示有符号也可以无符号，对于数据的类型没有具体的要求。

虽然在很多情况下，plc不区分这2种，word也能参与运算，但是一般建议是：通常情况下WORD用于逻辑运算（内存映射、数据转换等），INT用于数学运算。

所以建议在使用中，如果已经知道了参数的数据范围，还是用明确的INT/UINT/DINT等代替WORD/DWORD

另外在TIA软件种，WORD类型的变量可以切换到不同的模式进行显示(10#,16#,8#,2#)。


参考：

- [PLC数据类型INT 和WORD的区别点](https://www.ad.siemens.com.cn/service/answer/solved_141681_1029.html)
- [S7_300PLC里面编程WORD比较](https://www.ad.siemens.com.cn/service/answer/solved_67925_1029.html)




## 参考资料