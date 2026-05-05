---
title: 高效笔记规范
date: 2026-05-03 19:00:00
toc: true
password: wumin199
comment: false
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

高效,直觉,可视化,快速定位,简洁而非冗长,归档

<!-- more -->

## 临时笔记(fleeting notes)


如:
- 会议纪要
- 平时的点子/想法/备忘录
- 周报月报

核心诉求:
- 快速记录,归纳,存档

方案:

- 参考zyx的bi-weekly meeting，一个季度都写在一个page里面，不需要那么多子page，然后一个季度复盘清理一次
- 在notion中专门开个workspace,分类记录下
- 对于点子/想法/备忘录, 可以按照季度进行回顾和整理
- 定期存档

## 文献/专著/公开课笔记(literature notes)



## 专题学习类

### 编程类

- 高性能优化(无锁,零拷贝)
- 行为树和有限状态机
- 数据结构与算法
- cuda
- valgrind,perf
- 实时性
- 优秀开源库学习

核心: 

- 测试脚本

推荐方法:

- 总分总结构,先了解全貌
- 让AI或者NotebookLM来阅读代码仓库或者说明书
- 每个都有脚本来测试,脚本不要互相以来,有自解释


### 数学类

- 线性代数
- The Matrix Cookbook
- 数值分析
- 凸优化
- 深度学习中的数学

核心:
- 建立直觉

推荐方法:
- 参考AI方面的论述

### 专业类

- 现代机器人学
- 自动化设备和机器人的轨迹规划
- 正逆解
- python robotics

学习这些需要结合AI,具体在AI中有论述.

这里只列出一些核心笔记需要关注的核心痛点:

- 没有建立直觉,无法随机应变
- 回顾笔记耗费大量时间, 缺少可视化的东西
  - 如mermaid
  - 可视化的引擎
- whole map
  - [The-Art-of-Linear-Algebra](https://github.com/kenjihiranabe/The-Art-of-Linear-Algebra)
- cheat sheat
  - [matrix cookbook](https://www.math.uwaterloo.ca/~hwolkowi/matrixcookbook.pdf)
  - [ Edexcel Further Maths A-level - CP1](https://pmt.physicsandmathstutor.com/download/Maths/A-level/Further/Core-Pure/Edexcel/CP1/Cheat-Sheets/Ch.6%20Matrices.pdf)
  - [linear-algebra_ep4_PythonCheatSheet](https://allthemath.org/wp-content/uploads/2023/08/linear-algebra_ep4_PythonCheatSheet.pdf)
  - [Matrix-Cheat-Sheet](https://www.scribd.com/document/909447203/Matrix-Cheat-Sheet)
- [Visualize-ML](https://github.com/Visualize-ML)
- [manim](https://www.manim.community/)
- [3b1b/manim](https://github.com/3b1b/manim)
- python
  - streamlit or gradio
  - matplotlib/plotly/Altair
  - 数值计算:scipy/numpy/pandas
  - 符号计算:SymPy

## 建立直觉

- 形式逻辑: 形式逻辑主要遵循的是演绎推理和归纳推理，典型是亚里士多德的三段论：大前提A包含了小前提B，通过A和B同时推出结论C。
    - 教学/学习理解: 直观 <—>  凝练/升华: 数学抽象

- 数学高度抽象(形式逻辑),对一般人本身很难理解, 可以通过几个方面去拓展抽象数学的直觉意义:
  - 几何意义   —> 直观
  - 代数意义
  - 应用中的意义  —> 明确
      - 物理意义: 机器人领域, 力学领域, 数字图像处理领域
      - 统计学意义: 经济学, 社会学, 人口学, 消费学, 心理学等
      - …(欢迎补充)
  - 最佳实践
- whole map -> sections -> chapters

区分cheat sheet和学习,学习的话重点介绍自己不懂的或者核心概念.刚开始不需要大而且,慢慢补充代码或者可视化

## 共性问题

- 快速定位: 可以快速定位笔记
- 简洁: 笔记不宜非常庞大且复杂,可以通过附录来链接
  - 有必要保持笔记的简介
- 高效检索: 可以快速复盘和逻辑整理
- 建立直觉: 多关注直觉性的东西,多用mermaid/案例/代码测试/几何含义等来辅助理解
  - 案例代码解耦: 如每个测试单元尽量最小可执行单元, 自解释, 不追求所有细节, 少依赖其他笔记的内容
- 高效学习: 不求大而全,核心概念和主要应用还是遵守二八法则,学习也一样
- 整体面貌,到具体章节,到细节
  - 严格定义,数据集(应用案例),编程实现,可视化,几何含义
- 利用ai和专业工具:
  - notebook lm
  - 优秀开源库

二次创作

找对教材 + cheat sheat + 直觉 + 案例 + 应用 + 编程

可学习和发布的课程!

- [streamlit](https://streamlit.io/)
- [katex](https://katex.org/docs/node)

## 参考

- [Path2AGI](https://github.com/datawhalechina/Path2AGI/tree/main)
  - [02-linear-algebra](https://github.com/datawhalechina/Path2AGI/blob/main/02-linear-algebra.md)
  - [微积分与优化理论](https://github.com/datawhalechina/Path2AGI/blob/main/03-calculus-and-optimization.md)
- 可视化工具
  - mermaid
  - xml的draw.io
- 公式推导
  - 符号推导: scipy等
  - [wolframalpha](https://www.wolframalpha.com/)
- [arvix](https://arxiv.org/)
  - summary, intuition, 


## Skills

- pdf
- mermaid
- draw.io