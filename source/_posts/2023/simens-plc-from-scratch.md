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




## 常规设置

![启动方式](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC%E6%9A%96%E5%90%AF%E5%8A%A8.png)

![IP地址](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/%E8%AE%BE%E7%BD%AEPLCIP%E5%9C%B0%E5%9D%80.png)

![默认是150ms，一般可以设置为100ms，当然也可以考虑启动最短循环时间](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E5%BE%AA%E7%8E%AF%E5%91%A8%E6%9C%9F.png)

![系统时钟](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E7%B3%BB%E7%BB%9F%E6%97%B6%E9%92%9F.png)



![查看固件](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E5%9B%BA%E4%BB%B6.png)

![资源查看](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E8%B5%84%E6%BA%90%E6%9F%A5%E7%9C%8B.png)

![PLC常规](https://ghproxy.com/?q=https%3A%2F%2Fgithub.com%2Fwumin199%2Fwm-blog-image%2Fblob%2Fmain%2Fimages%2F2023%2Fplc%2Fmodbus%2FPLC%25E5%25B8%25B8%25E8%25A7%2584.png)

![PLC诊断](https://ghproxy.com/?q=https%3A%2F%2Fgithub.com%2Fwumin199%2Fwm-blog-image%2Fblob%2Fmain%2Fimages%2F2023%2Fplc%2Fmodbus%2FPLC%25E8%25AF%258A%25E6%2596%25AD.png)

![PLC复位](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/plc_reset.jpg)

## ModbusTcp设置

![访问级别](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E8%AE%BF%E9%97%AE%E7%BA%A7%E5%88%AB.png)

![连接机制:通过网口通讯的都要勾上](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E8%BF%9E%E6%8E%A5%E6%9C%BA%E5%88%B6.png)

![端口号和Slave_ID](https://ghproxy.com/?q=https%3A%2F%2Fgithub.com%2Fwumin199%2Fwm-blog-image%2Fblob%2Fmain%2Fimages%2F2023%2Fplc%2Fmodbus%2FMB_SERVER%25E8%25AE%25BE%25E7%25BD%25AE.png)

![硬件标志符，一般是64](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/MB_InterfaceID.png)


## S7设置


![访问级别](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E8%AE%BF%E9%97%AE%E7%BA%A7%E5%88%AB.png)

![连接机制:通过网口通讯的都要勾上](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/modbus/PLC_%E8%BF%9E%E6%8E%A5%E6%9C%BA%E5%88%B6.png)

![rack和slot](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/S7/S7_rack_slot.png)

![共享全局数据块的设置](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/S7/S7_%E5%85%A8%E9%83%A8db%E5%9D%97%E8%AE%BE%E7%BD%AE.png)

![不优化的全局db块](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/S7/S7_DB%E5%9D%97%E6%B2%A1%E6%9C%89%E4%BC%98%E5%8C%96.png)

![全局db块](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/S7/S7_%E5%85%A8%E5%B1%80db%E5%9D%97.png)

## Tips

### 常见经验

1. 一般先设置本机是DHCP，然后扫描PLC地址，扫描到以后，再将PLC的地址设置到组态网络的网口中并下载进去。
2. V17和V16
   V17导出的.scl可以在V16中导入，但是如果是V17中导出的梯形图则不支持在V16中导入
3. CPU循环周期
   
   使用PLC默认的150ms循环周期

   肖强经验：一般不改

   房姿凯经验：100ms。一般会要求1S内10次接收，所以换算成时间的话差不多就是100ms。

4. 指示灯报警
   
   ![有时候PLC指示灯会报警闪，报LED(SF)故障](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/plc_reset.jpg)

5. 一般Real类型用的比LReal多


### In/Out/InOut/Static/Temp

- In：程序输入参数
  在程序内部使用时，该参数只能读，不能写（不能给它赋值）
- Out：程序输出参数
  一个参数是Out类型，则在程序内部，只能用作输出，不能做输入（不能读），否则会有警告。虽然也可以运行，但是有一定的风险。
- Static: 我们一般理解的功能块作用域的局部变量。这个变量在程序里面可以读写。
- Temp：临时变量，在运行时分配和释放内存，超出最小作用域后即释放内存。
- InOut：输出输出变量，类似于指针或者引用做参数。
  这种变量在程序内部可以读也可以写


![](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/plc-variable-type.png)

![](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/plc_out_1.PNG)

![iCmd这里是Out参数，编译器会有警告。建议换成Static类型，这样可读可写](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/siemens/plc_out_2.PNG)



### 大端模式


西门子PLC是大端。

目前常见的CPU PowerPC、IBM是大端模式。

x86的PC是小端模式。

ARM既可以工作在大端模式，也可以工作在小端模式，一般ARM都默认是小端模式

一般通讯协议都采用的是大端模式。

![大小端](https://ghproxy.com/https://raw.githubusercontent.com/wumin199/wm-blog-image/main/images/2023/plc/byte_order.png)

参考：

- [Siemens data format](https://snap7.sourceforge.net/siemens_dataformat.html)


### WORD/DWORD和INT/UINT/DINT

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