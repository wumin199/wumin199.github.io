---
title: 概念合集
date: 2024-05-23 09:01:51
toc: true
password: 123
comment: false
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

plain, understandable and intuitive materials

<!-- more -->

## 机器人相关

### 核心资料

- [Github-KDL](https://github.com/orocos/orocos_kinematics_dynamics)(Kinematics and Dynamics C++ library)(参考了kdl，里面有数值解，可以求动力学，雅可比，甚至是轨迹插值等)
  - [The Orocos Project](https://www.orocos.org/index.html)
  - [Orocos Project documentation](https://docs.orocos.org/)
- [Github-toppra](https://github.com/hungpham2511/toppra)(考虑运动学和动力学约束的时间最优轨迹规划,基于NI的方法，该方法最大的缺点是不是jerk bound)
  - [toppra-documentation](https://hungpham2511.github.io/toppra/index.html)
- [Github-dart](https://github.com/dartsim/dart)(DART: Dynamic Animation and Robotics Toolkit)
  - [dart documentation](https://dartsim.github.io/)
- [Github-pinocchio](https://github.com/stack-of-tasks/pinocchio)(动力学计算的库)
- [example-robot-data](https://github.com/Gepetto/example-robot-data)(机器人urdf库)
- [Mordern-robotics](https://github.com/NxRLab/ModernRobotics)(教材对应的代码，有python/c++版本，可以加深概念，有POE相关代码)
- [robot toolbox](https://petercorke.com/toolboxes/robotics-toolbox/)(可以用来验证FK、IK、雅可比矩阵)
  - [robotics-toolbox-python](https://github.com/petercorke/robotics-toolbox-python)
  - [Ducumentation python](https://petercorke.github.io/robotics-toolbox-python/index.html)
- [sophus](https://github.com/strasdat/Sophus)(李群李代数c++库)
- [drake](https://drake.mit.edu/)(Model-Based Design and Verification for Robotics)(里面有一部分插值的东西)

### 其他

- [1. Equations of motion](https://scaron.info/robotics/equations-of-motion.html)
  
  动力学方程的理解,动力学方程中的雅可比矩阵, 只考虑重力补偿的动力学非常.

  动力学有2个cases: Case of manipulators 和 Case of legged robots

  文章中的 inertial frame 应该就是质心的frame
  
  解释了为什么人形机器人是欠驱动系统(因为Base有6个自由度,可以动).对于机械手来说，与环境的接触约束是隐式执行的，因为关节角度矢量与机器人的世界位置无关。而对于有腿机器人来说，这种情况已不复存在，接触需要通过运动学接触约束来主动执行。

  这里有腿部的动力学方程, 和机械臂的动力学方程类似,只是它增加了接触力(可能和环境有几个接触点)导致的扭矩以及考虑到接触对关节影响的修正扭矩

  [Some comments on the structure of the dynamics of articulated motion](https://inria.hal.science/inria-00390428/document),这篇文章作者强烈推荐,因为据说通俗易懂介绍了link inertias and Jacobian matrcies. 不过里面是基于腿部机器人

  最终任何形式的动力学可以归结到: [Constrained equations of motion](https://scaron.info/robotics/constrained-equations-of-motion.html)

- [2. 惯性矩阵或惯量矩阵, Inertia maxtrix](https://en.wikipedia.org/wiki/Moment_of_inertia#Motion_in_space_of_a_rigid_body,_and_the_inertia_matrix)
  
  惯量矩阵是通过[角动量](https://en.wikipedia.org/wiki/Angular_momentum)推到而来的

  根据[角动量](https://en.wikipedia.org/wiki/Angular_momentum),角动量又叫旋转动量,对一个封闭系统,角动量守恒.

  角动量公式: L = r.cross(p) = r x (m*v) == Icw,其中的Ic就是基于质心(centor of mass)的惯量矩阵

  记住:惯性矩阵是基于centor of mass (C)的!!

- [3. 逆动力学 Computing the inertia matrix in OpenRAVE](https://scaron.info/robotics/computing-the-inertia-matrix.html)
  
  逆动力学的概念,就是根据toque和q计算出动力学方程的M和C矩阵的过程. 这里还提到用CRBA的方法计算逆动力学.使用该方法的库包括Pinocchio, RBDL等

- [4. 前向Forward dynamics](https://scaron.info/robotics/forward-dynamics.html)
  
  根据关节q, 关节v, 外力f,计算出关节加速度的过程.

  需要先用到逆动力学求出M和C,然后再计算出关节a.方法有RNEA(pinocchio)或者ABA(bullet/Dart)


## 数学相关


## 编程相关