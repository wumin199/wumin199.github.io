---
title: AI工具使用指南
date: 2026-04-04 16:09:08
tags: 编程
toc: true
comment: false
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

AI工具使用指南

<!-- more -->

## 模型能力

- [llm stats](https://llm-stats.com/)
- [llm-leaderboard](https://onyx.app/llm-leaderboard)
- [livebench](https://livebench.ai/)
- [artificial analysis](https://artificialanalysis.ai/)

## Gemini

- [Google AI Studio](https://aistudio.google.com/prompts/new_chat)
- [NotebookLM](https://notebooklm.google.com/)
- [Gemini](https://gemini.google.com/app)
- [Gemini Code Assist](https://codeassist.google/)(包含CLI和IDE插件两种模式)

没问题，这就为你转换成清晰的 Markdown 表格格式：

| 维度 | NotebookLM (笔记本模式) | Gemini Gems (定制助手) |
| :--- | :--- | :--- |
| **知识来源** | **完全基于你上传的资料**（PDF、文档、网页链接、音频等）。 | **基于大模型的通用知识** + 你给出的特定指令。 |
| **首要目标** | **理解与总结**：帮你理清复杂资料之间的逻辑。 | **执行与创作**：按照特定风格或流程帮你干活。 |
| **幻觉控制** | **极低**：它只在你的资料范围内回答，并提供原文引用。 | **中等**：虽然有指令约束，但仍可能产生通用模型的幻觉。 |
| **交互特色** | 自动生成播客（Audio Overview）、学习指南、时间线。 | 像是一个有特定性格或专业技能的聊天队友。 |
| **适用人群** | 学生、研究人员、律师、需要处理大量文档的专业人士。 | 创意工作者、程序员、需要特定写作风格的博主。 |

总结建议
- 如果你手里有一大堆资料（比如你要研究某个汽车零部件的 RFQ 技术要求），想快速找出重点，请用 NotebookLM。
- 如果你想定义一个长期的工作伙伴（比如一个专门帮你检查 C++ 代码规范的助手，或者一个帮你策划 Vlog 脚本的创意导演），请用 Gemini Gems。


| 维度 | Gemini Code Assist (IDE/CLI) | Gemini Gem (网页端定制助手) |
| :--- | :--- | :--- |
| **存在形式** | VS Code / JetBrains 插件、终端命令行。 | 在 `gemini.google.com` 中自定义的机器人。 |
| **上下文感知** | **强**：它能“看”到你整个工程的代码、文件结构。 | **弱**：它只知道你对话中传给它的内容，不知道你的本地代码。 |
| **主要功能** | 代码补全、单元测试生成、实时 Debug、重构。 | 制定技术方案、角色扮演、代码风格规范制定。 |
| **交互方式** | 侧边栏聊天 + 编辑器内直接生成（Inline）。 | 纯聊天对话框。 |
| **适用阶段** | **编码阶段**：手在键盘上不停敲代码、改 Bug 的时候。 | **设计/复盘阶段**：动手前理思路，或结束后写文档、做方案。 |



### 案例: 学习行为树(基础版)

背景：根据几个特定的pdf、教材、网页，来学习行为树，同时需要编程来实践

推荐使用方法：

- 以NotebookLM为学习底座
  - 它会完全按照教材的章节逻辑、术语定义来回答你的问题，而不会被互联网上其他杂乱的定义干扰
  - 梳理梳理逻辑难点，你可以问它：“根据教材第五章，描述一下 Sequence 节点在遇到 Running 状态时的具体行为。”
  - 自动生成学习指南： 它可以基于教材内容自动为你整理出一份“行为树核心概念对照表”或“复习问卷”
- 以Gemini Gem作为代码教练
  - NotebookLM 的弱点在于它的“创造力”被严格限制在教材内，如果教材里的代码示例不够多，它很难凭空为你写出高质量、可运行的各种测试 Demo

最佳实践流程

- 阅读材料
- NotebookLM：阅读教材关于某个节点（如 Fallback 节点）的理论。如果看不懂，让 NotebookLM 用通俗的语言解释教材里的这段话
- NotebookLM：询问：“教材中提到的关于该节点的伪代码或示例逻辑是什么？”（擅长处理上传的教材/多教材中框架下的的复杂逻辑推理）
- Gemini Gem：将教材中的逻辑描述发给你的 Gem 助手，说：“教材里说 Fallback 节点用于容错。请帮我写一个 C++ Demo：模拟一个机器人导航任务，如果‘激光雷达定位’失败，就尝试‘IMU里程计定位’。”
- Gemini Gem：让 Gem 为你生成多组边缘测试用例（例如：如果所有子节点都返回 Failure，父节点会发生什么？），这能极大地弥补教材在实战案例上的不足。

总结建议：

NotebookLM 负责**“写对”（符合教材），Gemini 负责“写多”**（丰富 Demo）。对于你这种有 12 年行业经验、追求严谨的人来说，NotebookLM 是确保代码逻辑不走偏的最佳工具


- NotebookLM 用来确保你**“学得准”**（不偏离教材权威理论）
  - NotebookLM也能写代码，但会比较严格按照上传的教材的逻辑来写
- Gemini Gem 用来确保你**“练得多”**（提供源源不断的测试代码和实战 Demo）
  - 全网的开源代码库来给你凑出一个完整的工程


### Tutorial 

- [code assist](https://codeassist.google/)
  - [docs](https://developers.google.com/gemini-code-assist/docs/overview?hl=zh-cn)
- [vscode extension](https://marketplace.visualstudio.com/items?itemName=Google.geminicodeassist)
  - [代码功能概览](https://developers.google.com/gemini-code-assist/docs/code-overview?hl=zh-cn)
  - [对话功能概览](https://developers.google.com/gemini-code-assist/docs/chat-overview?hl=zh-cn)
  - [Agent模式概览](https://developers.google.com/gemini-code-assist/docs/use-agentic-chat-pair-programmer?hl=zh-cn)(MCP Server, GEMINI.md)
  - [在 GitHub 上设置 Gemini Code Assist](https://developers.google.com/gemini-code-assist/docs/set-up-code-assist-github?hl=zh-cn)(可以在Github的PR界面用@bot进行review)
- [gemini cli](https://geminicli.com/)
  - [gemini cli docs](https://geminicli.com/docs/)(quick start, cheat sheet, best practices)
  - [github gemini cli](https://github.com/google-gemini/gemini-cli)



1. Tools (Internal AI Capabilities):

What they are: These are the specific actions I (the AI) can perform on your behalf. Examples include read_file, grep_search, run_shell_command, replace, and ask_user.
How they are used: You do not use slash syntax (like /read_file) for these. You simply ask me to do something in plain English (e.g., "Find all the CSS files," "Read the README," "Run the tests"), and I will automatically select and use the correct internal tools to fulfill your request.
2. Commands (CLI User Controls):

What they are: These are special instructions you type directly into the prompt to control the CLI application itself, bypassing normal AI conversation. They always start with a slash /.
How they are used: You type them exactly as written. Examples include:
/help: Shows you the general help menu.
/tools: Shows you the list of tools I have available.
/bug: Lets you report an issue with the CLI.
/clear: Clears the chat history.


## CLI or/and IDE extenson

Query and edit large codebases, generate apps from images or PDFs, and automate complex workflows—all from your terminal with Gemini 3



## 学习方法论

**以Gemini**系列工具使用为例

### 行为树学习工作流（进阶版）

你的总结非常精辟，这已经是一套非常成熟的**“AI 辅助深度学习工作流”**了。

为了让这套流程在处理你那份 **200 页行为树 PDF** 时更具操作性，我最后帮你微调一下每个环节的“发力点”，确保你不会在信息大海里迷失：

| 步骤 | 动作 | 关键目标 | 避坑指南 |
| :--- | :--- | :--- | :--- |
| **1. 建立地图** | **NotebookLM** 生成全书大纲 + 术语对照表。 | 掌握 10 章的逻辑骨架，知道哪几章是核心。 | 不要纠结细节，先看这书是怎么架构的。 |
| **2. 场景映射** | 让 **Gemini Gem** 针对当前章节设计一个“轮式臂机器人”或“汽车生产线”案例。 | 将抽象的 BT 节点（如 *Parallel*）具象化为实际业务。 | 如果 Gem 给的场景太复杂，让它“简化到只有一个核心逻辑”。 |
| **3. 精准回溯** | 带着场景问 **NotebookLM**：“教材里哪一部分讲了处理这种冲突的逻辑？” | 锁定 PDF 中最关键的那 3-5 页。 | 善用 NotebookLM 的**原文引用**功能，直接点开看原文。 |
| **4. 深度研读** | 只读那几页原文，并划重点。 | 攻克最难的理论堡垒。 | 读的时候，脑子里要想着 Gem 给你的那个机器人案例。 |
| **5. 动手实验** | 在 IDE 里让 **Code Assist** 快速生成这个场景的 C++ Demo。 | 形成肌肉记忆，验证理论是否能跑通。 | **必须亲自编译运行**。AI 写出的代码可能会有环境问题，解决过程就是学习。 |
| **6. 闭环内化** | 让 **NotebookLM** 针对本章出 3 道案例分析题。 | 确认自己是否真的懂了，而不是“看懂了”。 | 如果答错了，立刻让 NotebookLM 指出教材里的对应解释。 |

额外小建议

* **关于 C++ 代码 (Code Assist)：**
    因为你使用的是 `BehaviorTree.CPP`（目前主流的 C++ 开源库），在用 Code Assist 时，记得提醒它：*“请使用 BehaviorTree.CPP v4 的语法”*（因为 v3 和 v4 区别很大）。

* **关于行业关联：**
    由于你负责大众（VW）业务，你甚至可以把一些大众公开的软件架构文档（如果是 PDF 的话）也丢进这个 NotebookLM 笔记本。这样当你学行为树时，你可以问：*“这种行为树架构，在大众的自动驾驶或热管理系统逻辑中是否有相似的应用？”*

这套方法不仅能帮你啃下这 200 页，以后你处理复杂的汽车行业 RFQ（报价请求）或技术标准时，同样可以复制这套 **“全局扫描 -> 场景带入 -> 精准定位 -> 实践验证”** 的逻辑。

### matrix cookbook




- [Matrix Cookbook](https://www.math.uwaterloo.ca/~hwolkowi/matrixcookbook.pdf)

特点
- 比行为树的教程更加硬核
- 《Matrix Cookbook》这类高度浓缩、公式密集的“硬核”参考书
- 偏数学类的，包括：数学性质和推导证明等
- 它不负责解释为什么，只负责告诉你“是什么”。因此，高效学习的关键在于**“从查阅者转变为推导者”**

我感觉核心痛点有：

1. 公式符号看不懂，定义不清楚
2. 不了解公式或性质的意义（如物理含义或使用场景等）
3. 不知道公式或定理怎么推导（需要推导或证明过程）
4. 知道以后，不知道怎么使用（包括案例和编程实现）


> Gemini 基于大语言模型，其推导逻辑是“概率性的语义连接”，而非“确定性的逻辑运算”。
> 强项（思路与翻译）：
> 步骤拆解： 它能很好地解释公式 (156) Woodbury 恒等式的直观物理意义 。
> 模式识别： 它擅长处理“迹的导数”这类具有固定模式的变换 。
> 符号翻译： 它可以帮你把 Cookbook 中干瘪的符号（如 $A^H$ 或 $vec(A)$）翻译成你更容易理解的语言 。
> 弱点（幻觉与细节）：
> 正负号与下标： 在处理长达 10 步以上的复杂推导时（如多变量正态分布的矩推导 ），它可能会在某一步漏掉一个负号或转置符号。
> 维数盲区： 它有时会忽略矩阵乘法的维数兼容性（$m \times n$ 与 $n \times p$） 

在处理复杂的矩阵恒等式（如公式 156 或 259）时，AI 容易在**长距离代数变换**中犯错（漏掉负号、转置变逆等） [cite: 166, 813]。Wolfram 的加入提供了以下价值：

* **绝对严谨性**：Wolfram 不会产生“幻觉”，它基于严格的数学内核运行，确保推导的每一步都符合代数法则。
* **符号化简**：当你面对像 $A+iB$ 这种复杂的复数矩阵求逆时（公式 259），手动展开极其痛苦，Wolfram 可以瞬间给出最简的解析形式。
* **维数自动校验**：如果你输入的矩阵乘法维数不匹配，Wolfram 会直接报错，这能帮你快速发现对定义理解的偏差。


| 工具 | 解决的问题 | 核心动作 |
| :--- | :--- | :--- |
| **NotebookLM** | **定义 (What)** | 查阅手册的 **Basics (1.1-1.3)** 和 **Notation**，理清变量维数。 |
| **Gemini Gem** | **逻辑 (Why)** | 询问定理的**背景、策略和思路**（例如：为什么要用分块矩阵初等变换来推导 Woodbury？）。 |
| **Wolfram** | **推导 (How)** | **执行代数演算**。将 Gem 建议的中间步骤丢给 Wolfram，确保正负号、逆矩阵顺序 100% 正确。 |
| **Code Assist** | **实战 (Use)** | 编写 Python/NumPy 脚本进行**数值对撞**，验证公式在实际计算中的加速效果。 |

假设你在啃手册第 27 页关于 $A+iB$ 的逆矩阵：

1.  **NotebookLM**：告诉你 $A, B$ 是实矩阵，结果是一个复矩阵。
2.  **Gemini Gem**：你问：“推导这个公式的逻辑是什么？”它会告诉你：关键是构造辅助矩阵 $E=A+tB$ 和 $F=B-tA$，将复数求逆转化为实数矩阵求逆。
3.  **Wolfram**：你尝试按照 Gem 的逻辑自己写一步，但不确定 $(E+iF)^{-1}$ 展开对不对。
    * **动作**：在 Wolfram 输入：`Inverse[E + I*F]`。
    * **结果**：它会给出解析解，你可以直接对比手册中的公式 (256)-(259)，看哪一步符号记错了。
4.  **Code Assist**：让它写个代码，生成随机对称正定矩阵 $A, B$，比较直接用复数运算 `np.linalg.inv(A + 1j*B)` 和用公式 (259) 分解计算的结果差异。


**Gem + Wolfram** 的组合本质上是 **“思路引导 + 算力校验”**。

* 用 **Gem** 帮你把 200 页的硬核推导拆解成你能听懂的“人话”和“逻辑小块”。
* 用 **Wolfram** 确保这些“逻辑小块”在代数变换上不出任何纰漏。


### 数值分析

“数值分析”（Numerical Analysis）与《Matrix Cookbook》或“行为树”又有所不同。它是**数学理论与计算机算力之间的桥梁**，核心在于**“误差控制”**与**“算法收敛性”**。

如果《Matrix Cookbook》是字典，那么数值分析就是“如何用最少的计算量、最稳的精度在电脑上算出字典里的公式”。

针对这一硬核课程，我为你设计了一套**“算法解构 + 误差实验”**的学习路径：


| 工具 | 扮演角色 | 针对数值分析的核心任务 |
| :--- | :--- | :--- |
| **NotebookLM** | **定义与约束专家** | 厘清算法的**适用条件**（如：矩阵是否必须正定？）和**收敛阶**（线性还是平方收敛？）。 |
| **Gemini Gem** | **算法直觉导师** | 解释算法的**几何意义**（如：牛顿迭代法是在找切线交点）和**稳定性逻辑**。 |
| **WolframAlpha** | **解析解与推导器** | 获取算法在理想状态下的**精确解**，作为后续误差分析的标杆。 |
| **Code Assist** | **误差实验室 (核心)** | 观察**舍入误差**（Rounding Error）和**截断误差**（Truncation Error）的动态演变。 |

假设你正在学习 **“线性方程组的迭代解法（如 Gauss-Seidel 迭代）”**：

第一步：建立边界 (NotebookLM)

数值算法不是万能的。
* **动作：** 上传教材，询问：“根据教材，Gauss-Seidel 迭代法对系数矩阵 $A$ 有什么严格要求？如果矩阵不是严格对角占优（Strictly Diagonally Dominant）会发生什么？” [cite: 851, 1029]
* **目的：** 确保你在写代码前，知道算法的“死穴”在哪里。

第二步：视觉化逻辑推理 (Gemini Gem)

理解算法为什么能“慢慢靠近”正确答案。
* **动作：** 问 Gem：“请用几何语言描述迭代法是如何一步步逼近 $Ax=b$ 的真实解的？它和直接求逆相比，为什么更适合超大规模矩阵？” [cite: 852, 854]
* **目的：** 建立直觉，理解“时间复杂度”与“空间复杂度”的权衡。


第三步：获取标杆解析解 (WolframAlpha)

在做数值实验前，你需要知道“正确答案”长什么样。
* **动作：** 在 WolframAlpha 输入具体的矩阵方程，获取它的精确解析解。
* **目的：** 这样你才能计算出你的数值算法产生的**绝对误差**和**相对误差**。

第四步：误差与稳定性实验 (Code Assist) —— **最关键的一步**

数值分析不看代码运行结果，看**“误差曲线”**。
* **动作：** 让 Code Assist 写一段 Python 脚本：
    1. 实现 Gauss-Seidel 迭代。
    2. 改变矩阵的“条件数”（Condition Number） [cite: 476, 484]。
    3. 绘制“迭代次数 vs 误差”的半对数坐标图。
* **实验推理：** 当你手动调大条件数，看到误差曲线开始剧烈震荡甚至不收敛时，你才会真正理解教材里说的“病态矩阵”（Ill-conditioned matrix）是什么意思。 [cite: 481, 482]


**数值分析学习的最佳实践建议**

A. 关注“稳定性”而非“正确性”
在 C++ 开发中（如你之前的项目），代码跑通只是开始。在数值分析里，你要问：“如果输入数据有 $10^{-5}$ 的扰动，输出结果会崩吗？”
* **工具组合：** 用 **Gem** 讨论稳定性理论，用 **Code Assist** 加入随机噪声进行压力测试。

B. 算法对比实验
数值分析教材通常会对比多种算法（如插值法里的 Lagrange vs Newton）。
* **策略：** 不要只学一种。让 **Code Assist** 同时实现两种，用同一组测试数据对比它们的**震荡现象**（如龙格现象 Runge's phenomenon）。


C. 从解析推导过渡到矩阵化实现
很多算法在教材里是 $x_1, x_2...$ 的标量形式，但在工程中要写成矩阵形式。
* **关联：** 利用 **NotebookLM** 提取算法的标量公式，让 **Gem** 帮你重构成矩阵形式，最后用 **Code Assist** 编写高效的 NumPy 向量化代码。

总结

学习数值分析，**代码不是为了实现功能，而是为了“观测误差”**。

1. **NotebookLM** 告诉你算法的**地基**（数学前提）。
2. **Gem + Wolfram** 给你算法的**灵魂**（逻辑与标杆）。
3. **Code Assist** 是你的**显微镜**（观察误差如何产生和传播）。


#### 符号计算

简单来说，**符号计算（Symbolic Computation）**，也称为计算机代数（Computer Algebra），是指利用计算机对**数学表达式**进行精确的逻辑运算，而不是简单的数值近似。

如果你习惯了用计算器算 $\sqrt{2} \approx 1.414$，那么符号计算给出的结果就是保留 $\sqrt{2}$。

1. 符号计算 vs 数值计算

为了让你更直观地理解，我们可以对比一下你在“数值分析”和“矩阵手册学习”中会遇到的两种模式：

| 特性 | 数值计算 (Numerical) | 符号计算 (Symbolic) |
| :--- | :--- | :--- |
| **处理对象** | 具体数字（如 `3.14159`） | 数学符号（如 $\pi, x, y$） |
| **结果形式** | 近似值（有舍入误差） | **精确解析式**（无误差） |
| **核心逻辑** | 算术运算（$+ - \times \div$） | **代数推理**（因式分解、展开、化简） |
| **典型工具** | NumPy, MATLAB, C++, Excel | **WolframAlpha**, SymPy, Mathematica |


2. 符号计算能帮你做什么？

在你学习《Matrix Cookbook》或复杂的数学定理时，符号计算是你的“逻辑自动机”：

* **代数化简**：自动将 $(x+y)^2$ 展开为 $x^2 + 2xy + y^2$，或者反过来进行因式分解。
* **精确求导与积分**：它不是通过斜率近似导数，而是根据微积分法则（如链式法则）推导出导数的**解析表达式**。例如，输入 `diff(sin(x^2), x)`，它会逻辑推导出 $2x \cos(x^2)$。
* **矩阵恒等式推导**：正如我们之前讨论的，它可以处理未赋值的矩阵变量 $A, B$，帮你验证 $(AB)^{-1} = B^{-1}A^{-1}$ 是否成立 [cite: 32, 35, 492]。
* **解方程组**：它能给出方程的解析解（如求出 $x = \frac{-b \pm \sqrt{b^2-4ac}}{2a}$），而不是只给你一个具体的数值坐标。


1. 为什么符号计算对你“硬核学习”至关重要？

结合你提到的学习痛点，符号计算的作用在于：

**消除推导恐惧**：当你看不懂教材中复杂的跳步时，符号计算工具（如 Wolfram）可以展示中间的**代数变换步骤**，帮你补全逻辑。

**建立理论标杆**：在“数值分析”中，符号计算提供的是**“真值（Ground Truth）”。你先用符号计算算出精确的解析解，再用 C++ 或 Python 算数值解，两者一减，就是你这门课最关心的**“误差”**。

**公式验证**：《Matrix Cookbook》中有很多复杂的性质，比如关于迹（Trace）的导数 [cite: 14, 287, 293]。手动推导极易出错，而符号计算可以 100% 严谨地帮你核对每一个转置符号和正负号。


常用工具推荐

* **WolframAlpha / Mathematica**：符号计算的行业标准，内置了几乎人类已知的所有数学公式和推理规则。
* **SymPy (Python)**：非常适合程序员。如果你在写代码时需要一段复杂的数学公式，可以用 SymPy 推导出来，然后直接生成 Python 代码。
* **Maple**：在数学证明和教学领域非常强大，逻辑展示极其清晰。

**总结一句话：**
**数值计算**是把数学问题变成**数字**，而**符号计算**是把数学问题保持为**逻辑**。

既然你打算开始学习“数值分析”，你是否想尝试用 **SymPy**（符号计算）来推导一个简单的插值公式，然后再用代码去实现它？我们可以演示这个“从符号到数值”的完整推理过程。

#### AI4Math

**AI4Math** 是指利用人工智能（特别是大语言模型和形式化验证技术）来辅助**数学发现、推理、证明和计算**。在你的学习场景下，它主要体现为：

* **自然语言到数学语言的翻译**：你用口语描述一个复杂的物理场景（比如机器人末端抖动），AI 帮你转化成数学模型（如 $C^k$ 连续性问题）。
* **代码化的数学验证**：利用 SymPy（符号计算）、NumPy（数值计算）或 SciPy（优化计算）来验证推导结果 。
* **交互式推理**：不像 WolframAlpha 只能给出一个死板的结果，AI4Math 能根据你的反馈，调整推导的深度和解释的角度。


| 动作阶段 | 传统方法 | AI4Math 模式 | 涉及工具 |
| :--- | :--- | :--- | :--- |
| **理解性质** | 死记硬背公式或性质。 | 询问性质背后的物理意义和逻辑关联。 | **Gemini Gems** |
| **补全推导** | 在纸上手算，极易出错。 | 让 AI 拆解步骤，并写出符号推导脚本。 | **SymPy / Wolfram** |
| **数值验证** | 盲目相信教材结论。 | 生成随机矩阵或物理参数进行“数值对撞”。 | **NumPy / Code Assist** |
| **误差分析** | 很难直观感受到误差传播。 | 编写循环，绘制误差随精度变化的曲线。 | **Matplotlib / Python** |


主力机：Gemini + SymPy + Wolfram（解决 99% 的工程数学问题）。

这种“逻辑引导 + 符号演算 + 数值实证”的闭环，才是攻克硬核课程的终极捷径

这个闭环之所以被称为“终极捷径”，是因为它模仿了顶级科学家研究问题的全过程：**先有直觉逻辑（猜想），再进行严密演算（推导），最后用实验数据验证（实证）**。

对于你正在学习的《Matrix Cookbook》、机器人学或数值分析，这个闭环能解决“书本知识无法转化为个人能力”的难题。

**逻辑引导 (Logic Guidance) —— 解决“为什么”**

这是学习的**战略阶段**，主要依靠 **Gemini Gems** 或 **NotebookLM**。
* **核心任务**：建立直觉，理解概念背后的物理意义或数学动机。
* **动作**：不看公式，而是问：“为什么要发明这个概念？它在解决什么痛点？”
* **例子**：在学习轨迹规划时，不要去背五次多项式公式，而是理解：“为了让机械臂在起点和终点速度、加速度都为 0（不抖动），我至少需要 6 个自由度来满足这些边界条件，所以才需要五次多项式。”


**符号演算 (Symbolic Computation) —— 解决“怎么写”**

这是学习的**战术阶段**，主要依靠 **WolframAlpha** 或 **SymPy**。
* **核心任务**：处理严密的数学表达，确保代数变换过程 100% 正确。
* **动作**：将逻辑转化为精确的符号公式。由于人类手算极易在正负号、转置、逆矩阵顺序上出错，利用符号计算工具来做“体力活”。
* **例子**：利用 SymPy 自动求出五次多项式的 6 个未知系数的解析表达式。这比你在纸上解 6x6 矩阵方程快且准。

**数值实证 (Numerical Verification) —— 解决“对不对”**

这是学习的**战斗阶段**，主要依靠 **Code Assist + Python/NumPy**。
* **核心任务**：将抽象公式带入真实数字，通过“观察结果”来反哺对理论的理解。
* **动作**：编写脚本，生成随机测试数据，验证等式左右两边是否相等，或者绘制性能曲线。
* **例子**：在 Python 中生成一段轨迹，如果发现加速度曲线超标了，你会立刻意识到：“哦！原来多项式阶次太高会引起龙格现象（Runge's phenomenon），导致中间段剧烈震荡。”这种“被坑过”的记忆比看书深刻 100 倍。

| 阶段 | 状态 | 产出 | 工具联动 |
| :--- | :--- | :--- | :--- |
| **逻辑引导** | 模糊但有方向 | **解题策略** | **Gemini** 告诉你：“用分块矩阵求逆的思路去做。” |
| **符号演算** | 精确但抽象 | **解析公式** | **Wolfram/SymPy** 帮你算出：“公式的右边应该是 $A^{-1}-A^{-1}U...$。” |
| **数值实证** | 具体且可感 | **运行结果/误差图** | **Code Assist** 帮你写出验证代码，发现误差在 $10^{-15}$，证明公式无误。 |


这个闭环的精髓在于：**不盲从教材，不深陷计算，不脱离实践。**

* **NotebookLM** 确保你没读错符号。
* **Gem** 确保你没想错逻辑。
* **Wolfram/SymPy** 确保你没算错步骤。
* **Code Assist** 确保你没用错场景。

你现在想针对《现代机器人学》或《Matrix Cookbook》中哪一个让你头疼的“硬骨头”尝试跑一遍这个闭环？我可以作为你的“逻辑引导员”开始第一步。

### 现代机器人学

NotebookLM 搞定符号。

Gem 搞定几何直觉。

Wolfram 搞定符号运算。

Code Assist + 仿真器 搞定工程实现。

### 自动化设备和机器人的轨迹规划

学习《Trajectory planning for automatic machines and robotics》（自动机械与机器人轨迹规划）是一门典型的**“工程应用数学”**课程。它不像《现代机器人学》那样充满了深奥的李代数，也不像《Matrix Cookbook》那样只有静态公式。

这门课的核心在于**“平滑性”与“约束”：如何在满足电机转矩限制、速度限制的情况下，规划出一条让机器人动作最丝滑（Jerk-limited）的路径。针对这种课程，你的学习重心应放在“轮廓设计（Profiling）”与“时间最优（Time-optimal）”**上

具体实践上可以参考 现代机器人学


### expert C++

TODO

凭感觉优化是大忌


- [Expert-C PDF](https://github.com/Rishabh-creator601/Books/blob/master/Lang-other/Expert%20C%2B%2B.pdf)
- [Expert-C 豆瓣](https://book.douban.com/subject/35363065/)
- [Expert-C-2nd-edition](https://github.com/PacktPublishing/Expert-C-2nd-edition)
- [Expert-CPP](https://github.com/PacktPublishing/Expert-CPP)
- [Expert C++的个人翻译](https://github.com/xiaoweiChen/Expert-Cpp)



- [David Spule 系列作品](https://www.aussieai.com/book/free-pdf)
- [C++ low talency: multithreading and hotpath Optimizations](https://www.aussieai.com/pdf/CPP-Low%20Latency-Spuler-2025.pdf)
- [C++ Ultra-Low Latency](https://www.aussieai.com/pdf/CPP-Ultra-Low-Latency-Spuler-2025.pdf)
- [C++ AVX Optimization CPU SIMD Vectorization](https://www.aussieai.com/pdf/CPP-AVX-Optimizations-Spuler-2025.pdf)





## Vibe Coding，程序员还需要学习么

TODO

可以用于重构

## AI出现后，如何开发大型项目

- 可以用于重构
- 单元测试

TODO

遇到的一些问题：

1. 不是最佳实践
2. 能跑就行，没人看得懂代码，无法人为修改，ai修改可能变动很大
3. 协作出问题
4. 复杂度太高
5. 模块化/可测试/复杂度/可理解？


## Terminology

### MCP

- [gemini cli mcp setup](https://geminicli.com/docs/cli/tutorials/mcp-setup/)
- [gemini code assist MCP setup](https://developers.google.com/gemini-code-assist/docs/use-agentic-chat-pair-programmer?hl=zh-cn)

MCP (Model Context Protocol, 模型上下文协议) 是一种开源标准，它的核心作用是作为 AI 助手（如 Claude Desktop、Cursor 等）与你的本地数据/工具之间的“安全桥梁”。

在没有 MCP 之前，如果你想让 AI 帮你分析本地的 C++ 代码或查阅 PDF，你需要手动把代码或文字复制粘贴给 AI。有了 MCP Server 后，你可以授权 AI 直接“看”到你指定的本地文件夹、数据库或调用特定的本地脚本。

常见的 MCP Server 类型包括：

- Filesystem (文件系统)：允许 AI 读取/搜索你授权的本地文件夹。
- Git/GitHub：允许 AI 直接读取你的 Git 仓库状态、提交记录等。
- Database (数据库)：允许 AI 查询本地SQLite/PostgreSQL数据。


### Agent



### Harness


### Skill

- [Get started with Agent Skills](https://geminicli.com/docs/cli/tutorials/skills-getting-started/)

skill库

### plan

Standard LLMs have a limited context window and can “forget” the original goal after 10 turns of code generation. Task planning provides:

- Visibility: You see exactly what the agent plans to do before it starts.
- Focus: The agent knows exactly which step it’s working on right now.
- Resilience: If the agent gets stuck, the plan helps it get back on track.

- [Plan tasks with todos](https://geminicli.com/docs/cli/tutorials/task-planning/)


Architecting a complex solution requires precision. By combining Plan Mode’s structured environment with model steering’s real-time feedback, you can guide Gemini CLI through the research and design phases to ensure the final implementation plan is exactly what you need.


Plan Mode lets you collaborate with Gemini CLI to design a solution before Gemini CLI takes action.

Plan Mode is a read-only environment for architecting robust solutions before implementation. With Plan Mode, you can:

- Research: Explore the project in a read-only state to prevent accidental changes.
- Design: Understand problems, evaluate trade-offs, and choose a solution.
- Plan: Align on an execution strategy before any code is modified.


参考：


- [Use Plan Mode with model steering for complex tasks](https://geminicli.com/docs/cli/tutorials/plan-mode-steering/)
- [plan mode](https://geminicli.com/docs/cli/plan-mode/)

### OpenRouter



- MCP
- Agent
- Skills


https://cli.github.com/

https://docs.github.com/en/codespaces


Claude Code，方案思路一般，编程最强，但容易封号。VScode 插件没有Gpt(Codex)或Gemini做得好用
Gpt或者Gemini：思路方案框架较强，Codex编程略差Claude,Genimi再差一点，这两不封号

antigravity

## Codex

codex的key：sk-dnRT6j3Y7E8FgyOJmHlDxyMuWmE5TSrEksmWxMSL1nZwTn2L

- [Codex](https://chatgpt.com/codex)
- [PP Codex Cli教程](https://ncn2tixwfspn.feishu.cn/wiki/FqCPwuUrpiwJPWkD2P2cXSKInDb)
- [Codex 技术客服&&配置教程](https://ucn9uf8devd7.feishu.cn/wiki/XUrvw5RbCihuh4kEPrdcvFHNnhd)

配额：2400配额（400次请求）
使用教程：PP Codex Cli教程
记录查询：https://code.ppchat.vip/
带图评论~免费加200次请求
体验卡有400次请求
升级VIP月卡每日可用高达500次，长期更划算～


```sh
# 如果是vscode 远程到另一台主机的docker，需要先确保remote中可以访问外网
# ref： https://www.notion.so/wumin199/12d514e8cada807c96a2c4fc60322e42
# 如果是vscode 远程到本地的docker，则不需要特别设置（除了开clash）

# 先安装codex
curl -fsSL http://47.115.148.185/codex/codex-install.sh | bash
# 再安装vscode的codex插件
# vscode：reload window
```







## 最佳实践

1. 一般问题用deepseek或者豆包问，节省token
2. 可以用gemini等的mcp server来节省和github的交互，加强review等
3. [Manage context and memory](https://geminicli.com/docs/cli/tutorials/memory-management/#best-practices)
   
   - Keep it focused: Avoid adding excessive content to GEMINI.md. Keep instructions actionable and relevant to code generation.
   - Use negative constraints: Explicitly telling the agent what not to do (for example, “Do not use class components”) is often more effective than vague positive instructions.
   - Review often: Periodically check your GEMINI.md files to remove outdated rules.