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

## 痛点

- 笔记混乱,查找困难
- 笔记内容太多,充满各种细节,没有归档
- 笔记无法快速回顾,缺少整体图
- 学习时长无法控制
- 代码案例没有最小可执行单元,充满各种注释和背景知识

## 临时笔记(fleeting notes)


如:
- 会议纪要
- 平时的点子/想法/备忘录
- 周报月报

核心诉求:
- 快速记录,归纳,存档

方案:

- 参考xxx的bi-weekly meeting，一个季度都写在一个page里面，不需要那么多子page，然后一个季度复盘清理一次
- 在notion中专门开个workspace,分类记录下
- 对于点子/想法/备忘录, 可以按照季度进行回顾和整理
- 定期存档

## 文献/专著/公开课笔记(literature notes)

文献/专著/公开课笔记(literature notes) → 不单独成章 → 可能是某个专题笔记的一部分

- 产品手册、调研报告、竞品分析等
- 开一个page, 附上原文pdf, 不直接在pdf上高亮和备注. 所有的笔记, 都是基于这个pdf
- 简单说就是简洁, 只记重点或者理解有难度的地方, 不需要大段原文抄袭, 那样的笔记没有意义
- 包含:
    p181: keywords1, kyewords2
    - detail1
    - detail1
    - detail2
- [MM相机专利分析.pdf](https://github.com/wumin199/wm-blog-image/raw/main/images/2026/note/MM%E7%9B%B8%E6%9C%BA%E4%B8%93%E5%88%A9%E5%88%86%E6%9E%90202201%E5%89%8D.pdf)
- [MM手册阅读笔记.pdf](https://github.com/wumin199/wm-blog-image/raw/main/images/2026/note/MM%E6%89%8B%E5%86%8C%E9%98%85%E8%AF%BB%E7%AC%94%E8%AE%B0.pdf)
- [MM碰撞检测设置调研.pdf](https://github.com/wumin199/wm-blog-image/raw/main/images/2026/note/%E7%A2%B0%E6%92%9E%E6%A3%80%E6%B5%8B%E8%AE%BE%E7%BD%AE%E8%B0%83%E7%A0%94.pdf)
- [MM软件调研.pdf](https://github.com/wumin199/wm-blog-image/raw/main/images/2026/note/MM%E8%B0%83%E7%A0%94.pdf)
- [Zone功能开发调研](https://github.com/wumin199/wm-blog-image/raw/main/images/2026/note/M-Zone%E5%8A%9F%E8%83%BD%E5%BC%80%E5%8F%91%E8%B0%83%E7%A0%94.pdf)


## 专题学习类

学习这些需要结合AI,具体在[AI工具使用指南](https://wumin199.github.io/post/20260404160908.html)中有论述.

### 编程类

- 高性能优化(无锁,零拷贝)
- 行为树和有限状态机
- 数据结构与算法
- cuda
- valgrind,perf
- 实时性
- 优秀开源库学习

核心: 

- 测试脚本混乱,没有最小可执行单元
- 测试脚本没有

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


## 代码笔记

如:

- 优秀开源库


痛点:

- 有些repo会一直更新, 需要持续学习
- 需要将写的代码笔记, 汇总到一个markdown, 方便快速检索笔记
    - 不会因为一个repo开了很多个写注释代码的分支, 而导致注释代码部分分散在不同分支, 这样无法快速检索和查找.
    - 起码一个repo下的所有的持续更新的笔记, 需要归档到一个markdown下
- 后续如有可能, 再把所有的repo的笔记, 汇总到一个地方  —> 优先级下

解决思路:

- 新建一个branch, 这个branch基于想学习的稳定分支, 而且里面只新增笔记的markdown
- 其他的代码学习, 也需要新开分支, 规则如下:
    - 如果架构很复杂, 可以使用vscode的[plantUML](https://plantuml.com/zh/) 绘制类图
    - 个人评论学习,要写在分支下面, 分支名称命名规则如下:
        - `cmt/summary` → 汇总各个分支的笔记 → [repo笔记汇总(`cmt/summary`)](https://www.notion.so/repo-cmt-summary-56cc2bd2c2024ece8975c83a5be4319c?pvs=21)
        - `cmt/1st_20220423`  → 第一次阅读源码所做的注释
        - `cmt/功能_20220801` → 后续新增的功能
    - 每个`cmt`分支下(除了`cmt/summary`),需要有:
        - `summary_1st_20220423.md`
            
            ```markdown
            
            # 概要
            
            <table><tr><td bgcolor=#F4A460><font size="4" color="#000000">ℹ️ status: NG</font></td></tr></table>  --> 本项目是否所有代码都学习了
            
            功能包基本介绍, 整个程序运行逻辑, 阅读方法/顺序,  设计模式, 流程图etc
            
            # /文件夹1
            
            <table><tr><td bgcolor=#F4A460><font size="4" color="#000000">ℹ️ status: NG</font></td></tr></table>  --> 本文件夹是否代码都学习了
            
            文件夹基本介绍
            
            | 文件 | 状态 | 简介 |
            | :-----| :-----|:-----|
            | protocol.py | √ | 该脚本主要是对socket接受和发送的数据的pack和unpack |
            | request_handler.py |  |  |
            
            # /文件夹2
            
            同文件夹1
            ```
            
        - `note_1st_20220423.md` —> 汇总本分支的所有笔记
            
            ```markdown
            update: 2022-06-15 18:42:26
            ------
            
            ### bitbucket-pipelines.yml
            total:8
            ```yml
            @Study: C++中的CI，export参数，make并进行单元测试和代码风格检查
            @Study: make cpplint, make check-format(clang-format检查) -> 通过CPMAddPackage(NAME xyz-cmake-scripts)引入的target -> 在其中的static-check.cmake，都是add_custom_target
            @Study: 启用检查方法1：CMakeLists.txt中enable_clang_tidy()/enable_cpplint()； 方法2： CI中make clang-tidy make cpplint
            @Study: make -j2进行编译, 2个并行jobs进行检查
            @Study: ctest 等价于make test
            @Study: CD的时候，指定了我们的安装路径DCMAKE_INSTALL_PREFIX
            @Study: make install执行cmake中的install命令
            @Study: 在CD中进行git tag
            ```
            
            ### CMakeLists.txt
            total:8
            ```yml
            @Study: CPMAddPackage下载xyz-cmake-scripts 并使用里面的cmake宏函数
            @Study: 这些宏函数就来自于xyz-cmake-scripts -> cxx_17() enable_clang_tidy() enable_all_warnings()
            @Study: 启动clang-tidy检查方法1：enable_clang_tidy()； 方法2： CI中make clang-tidy。2选1即可
            @Study: set缓存变量, 类型是PATH(也可以是STRING，PATH，BOOL,LEPATH等)
            @Study: CMAKE_INSTALL_PREFIX的标准写法
            @Study: protobuf默认是用bazel编译，通过导入XYZMsgGen，可以用Make来编译 -> add_service_files()和generate_services()
            @Study: 使用XYZMsgGen.cmake提供的宏编译生成protobuf
            @Study: header only的代码，可以直接安装，没有target
            ```
            
            ### include/xyz_msgs/converter/basic_converter.h
            total:5
            ```yml
            @Study: header-only的头文件, 不需要CMakeLists.txt，直接install就行 -> 确保xyz_msgs这个包被编译并安装
            @Study: 模板返回值是T的用法 auto cloud_msgs = xyz_msgs::converter::convert(pcloud.cloud);
            @Study: protobuf设置对象Vector3d的值 -> 基础数据类型
            @Study: protobuf设置Pose下面的orientation的值 -> 不是基础数据类型
            @Study: probobuf添加repeated的值
            ```
            
            ### proto/xyz_msgs/visualization_msgs/Marker.proto
            total:1
            ```yml
            @Study: protobuf中定义enum
            ```
            
            ### proto/xyz_msgs/visualization_msgs/MenuEntry.proto
            total:1
            ```yml
            @Study: 用protocolbuf定义树形结构
            ```
            
            ### test/CMakeLists.txt
            total:2
            ```yml
            @Study: 添加Eigen3/Boost的include到target
            @Study: add_test:指定测试名称，可以随意指定； 指定测试命令行 -> 执行utest_converter这个target
            ```
            
            ### tools/code_style_check.sh
            total:1
            ```yml
            @Study: code_style_check进行cpplint检查，前提是1: 有CPPLINT.cfg; 2:安装了cpplint。 手动自己检查
            ```
            ```
            
        - `/my_test`
            - 这个文件夹,专门用来放自己看代码过程中进行的一些测试
        - `/my_pic`
            - 这个文件夹,用来放一些截图和pdf等资料
        - 针对某个代码: `request_handler.py`
            
            ```python
            ## 该文件基本功能和介绍
            
            ## 笔记/note:
            ## 需要的时候在import,而不是马上import
            ## return self,完成链式调用
            
            def get_target_pose():
            
            		## @Study: return self,完成链式调用
            		## 参考链接: xxx
            		## 这样就可以xxx.get_target_pose().get_target_pose()
            		return self
            ```
            
    - repo笔记汇总(`cmt/summary`)
        - `notes`文件夹:
            - `notes.md`   —> 介绍各个笔记的基本情况
            - `note_1st_20220423.md`  —> 直接从分支合并过来
            - `note_功能_20220801.md` —> 直接从各个合并过来
        - `CHANGELOG_NOTES.md` —> 记录每次把各个分支提交过来的笔记的记录
- 自动提取注释
    - 提取含义@Study的行,并对应到某个特定文件夹下
    - shell脚本或者用python处理
        
        ```markdown
        前提：
        1. 只能在linux下运行
        2. 需要装git
        
        3. 将`doc_generate.sh`拷贝到项目的根目录下
        4. `chmod +x doc_generate.sh`
        5. `./doc_generate.sh mynote.md`
        ```
        
        ```bash
        #! /bin/bash
        
        RED='\033[0;31m'
        GREEN='\033[0;32m'
        YELLOW='\033[0;33m'
        NC='\033[0m' # No Color
        
        # exit when error
        set -e
        
        get_branch_name(){
          ## 如果git已经安装好了，则默认用当前分支名，否则用默认名称
          if git --version > /dev/null ; then
              branch_name=$(git rev-parse --abbrev-ref HEAD)
          else
              branch_name="notes.md"
          fi
          ## 防止有些起名是cmt/1st_20220610，不符合命名规则
          echo -e $(basename $branch_name).md
        }
        
        if [ $# -ne 1 ]; then
          echo -e "${YELLOW}usage: doc_generate file_name${NC}"
          comment_file_name=$(get_branch_name)
          echo -e "${YELLOW}use [$comment_file_name] as default${NC}"
        else
          comment_file_name=$1
        fi
        
        # 初始化
        thisFile=$_ 
        
        # 获取相对路径下的脚本名称
        if [ $BASH ]  #/bin/bash
        then
          thisFile=${BASH_SOURCE[0]}  # 脚本名称，相对路径
        fi
        
        # 获取脚本绝对路径
        # > /dev/null 的目的是，将realpath $thisFile的输出，不显示处理, 否则会在终端打印出来
        # >/dev/null 就是将标准输出和标准出错的信息屏蔽不显示
        if realpath $thisFile > /dev/null ; then  # realpath 用于获取指定目录或文件的绝对路径。
          thisFile=$(realpath $thisFile)
        else
          thisFile=$0
        fi
        
        # 获取脚本所在文件夹路径
        projectDir=$(cd $(dirname $thisFile) && pwd)
        
        make_sure_folder_does_not_exist() {
          if [[ -d $1 ]]; then
            error "Error: $1 does exist!"
            exit 1
          fi
        }
        
        # 创建文件夹并初始化
        create_file(){
            if [ -f $1 ]; then 
              rm -rf $1
            fi
            touch $1
            echo -e "update: $(date +'%Y-%m-%d %H:%M:%S')" > $1
            echo -e "------" >> $1
        }
        
        # 读取文件夹下的所有文件
        # 通过echo来返回 -> 返回是字符串
        # 递归函数要使用局部变量，不要使用全局变量
        read_dir() {
          # 获取传入的目录路径
          local dir=$1
          # 循环指定目录下的所有文件
          local files
          files=$(ls "$dir")
          for file in $files; do
            local path="$dir/$file" #指的是当前遍历文件的完整路径
            # 判断是否是目录，如果是目录则递归遍历，如果是文件则打印该文件的完整路径
            if [ -d "$path" ]; then
              read_dir "$path"
            else
              echo -e "$path" # 
            fi
          done
        }
        
        generate_doc() {
        
          local note
          # 如果传递进来的参数<1
          if [ $# -ne 1 ]; then
            echo -e "${RED}Usage: generate_doc file${NC}"
            return
          fi
        
          file=$1
        
          # grep没有找到匹配的，由于set -e的存在，程序会直接退出来， 所以使用 { || }
          match_data=$({ grep -o "@[Ss][Tt][Uu][Dd][Yy].*$" $file || echo "" ; })
        
          # 没匹配上，返回空
          if [ -z "$match_data" ]; then
            # echo -e "exit"
            # echo -e "${RED}no match data${NC}"
            return
          fi
        
          relative_file=${file#*$projectDir/}  # 截取字符串 http://c.biancheng.net/view/1120.html
          record_num=$(grep -i @study $1 | wc -l)
        
          note=$(grep -o '@[Ss][Tt][Uu][Dd][Yy].*$' $file | \
          awk -v file=$relative_file -v record_num=$record_num 'BEGIN{printf "\n### %s\ntotal:%d\n%s\n", file, record_num, "```yml"}{print $0}END{print "```"}')
          echo -e "$note" # ""会原样输出，包含回车，换行
        
          # 其他方法2
          # grep -i @study $file | \
          # awk -v file=$relative_file -v record_num=$record_num 'BEGIN{printf "\n### %s\ntotal:%d\n%s\n", file, record_num, "---<<<"}{print $0}END{print "--->>>"}'
        
          # 其他方法3
          # awk -v file=$relative_file -v record_num=$record_num 'BEGIN{printf "\n### %s\ntotal:%d\n%s\n", file, record_num, "---<<<"} /@[Ss][Tt][Uu][Dd][Yy]/ {print $0} END{print "--->>>"}' $file
        }
        
        comment_file=$projectDir/$comment_file_name
        create_file $comment_file
        
        ret_str=$(read_dir ${projectDir})
        file_array=($ret_str)
        # echo -e ${#file_array[*]} 
        
        for i in ${!file_array[@]}
        do
          if [ ${file_array[i]} != $thisFile ]; then
            if [ ${file_array[i]} != $comment_file ]; then
              # echo -e ${file_array[i]}
              note=$(generate_doc ${file_array[i]})
              if [ -n "$note" ]; then
                echo -e "$note" >> $comment_file
              fi
            fi
          fi
        done
        
        echo -e "${YELLO}generating...${NC}"
        echo -e "${NC}generate $comment_file done ${NC}"
        ```

## 建立直觉




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

- utest + google benchmark

找对教材 + cheat sheat + 直觉 + 案例 + 应用 + 编程

不要复制粘贴那些容易获取的东西,关注你不知道的东西

可学习和发布的课程!

不可拖太长,有个时间

痛点:学习时长要规定,一般按照全职学习的花,3~5天算是比较长的一门课程来.参考深蓝学院里面的课程量和难度来评估,以及参考

- [streamlit](https://streamlit.io/)
- [katex](https://katex.org/docs/node)
- [typst](https://typst.app/)

## 参考笔记

- [Path2AGI](https://github.com/datawhalechina/Path2AGI/tree/main)
  - [02-linear-algebra](https://github.com/datawhalechina/Path2AGI/blob/main/02-linear-algebra.md)
  - [微积分与优化理论](https://github.com/datawhalechina/Path2AGI/blob/main/03-calculus-and-optimization.md)
- 可视化工具
  - mermaid
  - xml的drawio
  - excalidraw
- 公式推导
  - 符号推导: scipy等
  - [wolframalpha](https://www.wolframalpha.com/)
- [arvix](https://arxiv.org/)
  - summary, intuition, 


## Skills

- pdf
- mermaid
- draw.io