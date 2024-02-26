---
title: 工程优化模型
date: 2024-02-25 10:06:48
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

UCB - Optimization Models in Engineering | EECS 127/227A

<!-- more -->

## mathematics in drake

1. initial guess
   
   Some optimization problems, such as nonlinear optimization, require an initial guess. Other types of problems, such as quadratic programming, mixed-integer optimization, etc,  can be solved faster if a good initial guess is provided. The user could provide an initial guess as an input argument in Solve function. If no initial guess is provided, Drake will use a zero-valued vector as the initial guess.

   Solving an NLP requires an initial guess on all the decision variables
  
- QP
  
  AddQuadraticCost
  AddQuadraticErrorCost
  Add2NormSquaredCost


### 参考资料

- [DRAke](https://drake.mit.edu/)
- [Drake: 设计哲学](https://medium.com/toyotaresearch/drake-model-based-design-in-the-age-of-robotics-and-machine-learning-59938c985515)
- [mathematical_program术语](https://github.com/RobotLocomotion/drake/blob/master/tutorials/mathematical_program.ipynb)(decision variables, objectives, and constraints)
  - [Formulating and Solving Optimization Problems](https://drake.mit.edu/doxygen_cxx/group__solvers.html)
  - [deepnote: drake mathematical problem](https://deepnote.com/workspace/minwu-6eea3adc-5e07-45fe-ae5d-f99aafc25557/project/Tutorials-Duplicate-95ab0b99-5264-4bb2-a97d-7b12c3a3ac17/notebook/efcc0a0d7a814450acd96b86e0a555f4)



## EECS 127/227A



### 参考资料

- [Home Page](https://eecs127.github.io/)
- [Bili](https://www.bilibili.com/video/BV1pM411c7Jc/?spm_id_from=333.337.search-card.all.click&vd_source=991bc0898d44d84ddbbb0469ce816e70)


## 关键概念

1. 凸/非凸
2. 