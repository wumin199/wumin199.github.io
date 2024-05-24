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

## key_words

overview/review
summary
examples/questions
introduction/preparation
definition/application/question/assignment/homework/course
characteristics/characteriaztion
cheatsheets


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
  
  逆动力学的概念,就是根据toque和q计算出动力学方程的M和C矩阵的过程. 这里还提到用CRBA的方法计算逆动力学.使用该方法的库包括Pinocchio, RBDL等。

  这里描述的有点问题：

  pinocchio中是这样说的：

    实现的主要算法：

   正向运动学（forward kinematics）：给定机器人构型，计算每个关节的空间位置并将其存储为坐标变换。如果给定速度或加速度，将计算每个关节的空间速度（以局部坐标系表示）。

   运动学雅可比矩阵（kinematic jacobian）：在机械臂运动学中用来计算机械臂末端执行器的速度与各个关节运动速度之间的关系。

   逆动力学（inverse dynamics）：采用Recursive Newton-Euler Algorithm (RNEA) 计算逆动力学。即给定所需的机器人构型(各个轴角度)、速度(各个速度)、加速度(各个轴加速度)，计算并存储执行该运动所需的扭矩。

   关节空间惯性矩阵（Joint space inertia matrix）：采用Compostie rigid body algortihm (CRBA)算法，计算关节空间惯性矩阵。(M和C矩阵)

   前向动力学（forward dynamics）：采用Articulated Body Algorithm（ABA）计算无约束的前向动力学。即给定机器人构型、速度、扭矩和外力的情况下，可以计算出由此产生的关节加速度。

   其他算法：其他算法包括约束正运动学，脉冲动力学，逆关节空间惯性矩阵，向心动力学。

- [4. 前向Forward dynamics](https://scaron.info/robotics/forward-dynamics.html)
  
  根据关节q, 关节v, 外力f,计算出关节加速度的过程.

  需要先用到逆动力学求出M和C,然后再计算出关节a.方法有RNEA(pinocchio)或者ABA(bullet/Dart)

- [5. 常见振动系统和公式](https://ccrma.stanford.edu/CCRMA/Courses/152/vibrating_systems.html)

- [6. 常见的关节动图](https://gepettoweb.laas.fr/doc/stack-of-tasks/pinocchio/master/doxygen-html/md_doc_c-maths_b-joints.html#autotoc_md64)

- path和trajectory
  
  path是几何路径，trajectory是包含vel, acc和t

  path一般给的是关键点，trajcectory会走到这些关键点。所以为了更光滑，需要对path进行插值，如toppra中的spline插值

  path -> add_constraint(vel, acc) -> toppra -> trajectory

  ![](https://github.com/wumin199/wm-blog-image/raw/main/images/material_collection/imgs/path-notation.png)

- toppra中的插值
  
  All toppra interpolators try to match all given waypoints, and hence it can lead to large fluctuation if the waypoints change rapidly. In this case, it is recommended to smooth the waypoints prior to using toppra using for example scipy.interpolation.

  see: [Minimum requirement on path smoothness](https://hungpham2511.github.io/toppra/notes.html#minimum-requirement-on-path-smoothness)

- 摩擦力
  
  常见的摩擦力包含静摩擦和动摩擦立：[friction](https://physics.info/friction/summary.shtml)、[Determining the Coefficient of Friction](https://nfsi.org/wp-content/uploads/2013/10/Determining.pdf)

## 数学相关


- [1. 数值积分 integration_basics](https://gafferongames.com/post/integration_basics/)
  
  The RK4 is a fourth order integrator, which means its accumulated error is on the order of the fourth derivative. This makes it very accurate. Much more accurate than explicit and implicit euler which are only first order.

  有个龙格库塔法的具体案例, 案例用到了
  
  [5. 常见振动系统和公式](https://ccrma.stanford.edu/CCRMA/Courses/152/vibrating_systems.html)
  
  [龙格库塔法介绍](https://en.wikipedia.org/wiki/Runge%E2%80%93Kutta_methods)

  重点是,里面的
  ```c++
    struct State
    {
        float x;      // position
        float v;      // velocity
    };

    struct Derivative
    {
        float dx;      // 速度, 应该用dx/dt更直观
        float dv;      // 加速度, 应该用dv/dt表示更直观
    };
   Derivative evaluate( const State & initial, 
                         double t, 
                         float dt, 
                         const Derivative & d )
    {
        State state;
        state.x = initial.x + d.dx*dt; // x = x0+v*dt
        state.v = initial.v + d.dv*dt; // v = v0 + a*vt

        Derivative output;
        output.dx = state.v; // 注意这里是v
        output.dv = acceleration( state, t+dt ); // 这里是a
        return output;
    }

    float acceleration( const State & state, double t )
    {
        // see: https://ccrma.stanford.edu/CCRMA/Courses/152/vibrating_systems.html
        // ma + bv+kx=0,假设m=1 --> a = -kx-bv
        const float k = 15.0f;
        const float b = 0.1f;
        return -k * state.x - b * state.v; // 返回a
    }

    // y = State
    // dy/dt = Derivative == State的导数
    // dy/dt = evaluate(t, dy/dt, initial_y)
  ```

  作者同时说明了如何选择积分器

  这里也给出了从加速度积分到速度,再积分到位置的案例: [Physics in 3D](https://gafferongames.com/post/physics_in_3d/)


- [2. 差分公式](https://www.dam.brown.edu/people/alcyew/handouts/numdiff.pdf)
  
  APMA 0160 (A. Yew) Spring 2011, Numerical differentiation: finite differences

https://scaron.info/blog/conversion-from-least-squares-to-quadratic-programming.html

https://scaron.info/blog/quadratic-programming-in-python.html



## 编程相关