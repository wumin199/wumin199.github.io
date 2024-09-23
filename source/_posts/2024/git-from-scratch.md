---
title: git速查表
date: 2024-01-29 17:51:08
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

hello git

<!-- more -->

### rebase和merge区别

当前开发者在 feature 分支，现在想和master同步，让自己的分支包含master所有最新的内容

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/test_case_1.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>

以下是feature分支下的 `rebase` 的方案：

```bash
# 确保master最新
git checkout master
git pull

# 把master rebase到feature 
git checkout feature
git rebase -i master
```

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/git_rebase_result.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>

`rebase` 特点：

1. feature的3个commit会放在master后面，最后branch的commit历史相对master的commit历史是线性的
   1. 所以这对后面提PR非常有用
2. feature的3个commit会同时被添加进master的内容！
   1. 所以如果3个commit都和master有冲突，需要解决3次
   2. 解决方法是：先把这3个分支自己用rebase压缩成一个(见下文)，然后再把master rebase进来，这样只需要解决一次冲突即可

以下是feature分支下的 `merge` 的方案：

```bash
# 确保master最新
git checkout master
git pull

# 把master rebase到feature 
git checkout feature
git merge master
```

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/git_merge_result.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>

`merge` 特点：

1. feature后面会生成一个新的commit
   1. 最后branch的commit历史相对master的commit历史不是线性的
   2. 提PR的时候，别人看你的分支会比较混乱，因为不是线性的
2. 只有这个新生成的commit包含了master的最新提交历史
   1. 优点：冲突只解决最新的这次

结论：一般都用 `rebase`


### 版本策略

包含仓库设置

TODO


1. wm-studio是交付软件，对应wm-studio这个repo
2. wm-studio有很多依赖，比如wm-control
3. wm-studio的大版本规定了，各个依赖的大版本也规定了
4. wm-studio升级了大版本，则

```
wm-studio 10.0(release/10.0)
- wm-control release/1.0
- wm-video release/0.5
- wm-test release/0.3
```

现在假设 wm-studio 10.0(release/10.0) -> wm-studio 11.0(release/11.0)

设置策略：
1. release/1.3，对于管理员可以push，但是不能push -f
   1. 可以push的目的是添加新的版本号，不可以push -f的目的是，不允许强制覆盖很多提交历史，这非常危险
   2. 管理员设置成可以push，但是不能push —f，否则可能一下子改变一大堆提交

### 使用rebase合并

**案例1：将所有的commit合并成1个**

已知历史commit如下：

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/git_history_1.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>

其中 `test_rebase` 分支是从 `feature/..` 切换出来的，现在要求将前4个commit合并成一个.

```bash
# 先切换到这个分支
git checkout test_rebase

# 如果不确定操作，可以先备份这个分支，再切换回来
git checkout -b "backup"
git checkout test_rebase

# git rebase 到 commit(refactor)的前一个commit(feature/...)
git rebase -i 0baff3f5
```

之后会跳出：

```vim
pick c7a171ef refactor     # 这是最老的
pick 6479da02 changelog
pick 09fd705d update xxx yaml
pick 62359e03 update xxx info

# Rebase 0baff3f5..62359e03 onto 0baff3f5 (4 commands)
#
# Commands:
# p, pick <commit> = use commit
# r, reword <commit> = use commit, but edit the commit message
# e, edit <commit> = use commit, but stop for amending
# s, squash <commit> = use commit, but meld into previous commit
# f, fixup [-C | -c] <commit> = like "squash" but keep only the previous
#                    commit's log message, unless -C is used, in which case
#                    keep only this commit's message; -c is same as -C but
#                    opens the editor
# x, exec <command> = run command (the rest of the line) using shell
# b, break = stop here (continue rebase later with 'git rebase --continue')
# d, drop <commit> = remove commit
# l, label <label> = label current HEAD with a name
# t, reset <label> = reset HEAD to a label
```

修改如下：

```vim
pick c7a171ef refactor
f 6479da02 changelog
f 09fd705d update xxx yaml
f 62359e03 update xxx info
```

解释：

```vim
pick c7a171ef refactor              # 最老的历史
f 6479da02 changelog                ↑
f 09fd705d update xxx yaml          ↑
f 62359e03 update xxx info          ↑
```


然后保存退出，之后的历史如下：

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/git_rebase_1.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>

最后执行：

```bash
git push -f  # f是 --force-with-lease，因为之前的操作改变了Git的线性历史

```

技巧1：以上的git rebse 可以借助vscode的 ``Git Graph`` 完成
技巧2：看commit或者git rebase中的commit哪个最新哪个最老，可以借助 ``Git Graph`` 的commit提交时间

**案例2：将中间的2个commit合并到一个**

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/git_history_2.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>

只将图中的2个合并在一起

```bash
# ...

# git rebase 到 changelog的前一个commit(refactor)
git rebase -i c7a171ef
```
会看到：

``` 
pick 6479da02 changelog           # 这是最老，可以借助GitGraph的commit提交时间
pick 09fd705d update xxx yaml
pick 62359e03 update xxx info   # 这是最新
```

修改为：

```
pick 6479da02 changelog           
f 09fd705d update xxx yaml
pick 62359e03 update xxx info  
```

解释如下
```
pick 6479da02 changelog             这是最老的历史      
f 09fd705d update xxx yaml              ↑
pick 62359e03 update xxx info          不变
```

现在历史如下：

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/git_rebase_2.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>

之后

```bash
git push -f
```

**知识点：squash和fixup**

`squash`会将两个commit合并，并且允许你编辑新的commit信息。`fixup`也会将两个commit合并，但是它会丢弃第二个commit的信息。即如果你选择了`squash`，那么你将有机会编辑新的commit信息，如果对历史commit信息不那么在乎，则可以用 `fixup`。我一般用 `fixup`就行。


### rebase my_branch 到master

my_branch 有很多commit，可能好多个commit都和master有冲突，这时候直接


```bash
# 确保 master最新
git checkout master
git pull

# 将my_branch的所有commit 合并到一个(就是my_branch的第一个commit)
git checkout my_branch
git rebase -i the_commit_before_first_commit_in_my_branch # 一般是my_branch从这个分支切出来的
# 好处是如果后续和master有冲突，只要处理这个commit的冲突就行了
# 如果十分确认 my_branch 没有和master任何冲突，则可以省掉 上一步

# 将master的信息rebase进来
git rebase -i master
# 解决冲突，之后 按照提示操作即可
# 此时my_branch 就是和master没有冲突，且和master是最新的线性历史

# 
git push -f
```

### git reset 和 git revert

git revert 命令用于撤销一个已提交的更改。与 git reset 不同的是，git revert 会创建一个新的提交来撤销指定的更改，而不是直接删除提交记录。

```bash
git revert <commit-hash>
# 如果有冲突, 解决冲突后添加文件
git add <file>
# 完成提交
git commit
```

```bash
# 此模式下，HEAD 会被重置到指定的提交，但索引区和工作目录的内容保持不变。更改会被保留在索引区中，准备好进行新的提交。
git reset --soft <commit-hash>

# 此模式下，HEAD、索引区和工作目录都会被重置到指定的提交。所有未提交的更改都会被丢弃。
git reset --hard <commit-hash>
```

案例：

`commit1 -> commit2 -> commit3 -> commit4 (HEAD)`

```bash
git reset --hard commit2
```

结果：`commit1 -> commit2 (HEAD)`

git reset 是一个强大的命令，可以根据需要选择不同的模式来重置 HEAD、索引区和工作目录。使用时请谨慎，特别是 --hard 模式，因为它会丢弃所有未提交的更改。

**案例1** get pull前需要git reset

本地的branch历史可能和远端的不一样，直接git pull会冲突，推荐做法：

```bash
# 某branch在一个地方进行了
get rebase release/1.3
# 在另一个地方需要pull的话，不可以直接pull，否则会conflict，具体需要：
get reset —hard HEAD~100
git pull
# 或删掉分支后再checkout
```

### cherry-pick

**案例1：PR到1.5.1和1.6.1**

假设：先提交到1.5.1，再提交的1.6.1

```bash
# 保持 1.5.1 和 1.6.1 最新 
git checkout 1.5.1
git pull
git checkout 1.6.1
git pull

############
# PR到1.5.1
############

# 将 my_branch 压缩为一个commit，好处是有冲突只要处理一个commit就行了
git checkout my_branch
git rebase -i the_commit_before_first_commit_in_my_branch # 一般是my_branch从这个分支切出来的

# 将master的信息rebase进来
git rebase -i master
# 解决冲突，之后 按照提示操作即可
# 此时my_branch 就是和master没有冲突，且和master是最新的线性历史

# PR到1.5.1
# Github上提交PR

############
# PR到1.6.1
############

# 在上述的基础上，创建新的提交分支
git checkout 1.6.1
git checkout -b "my_branch_1.6.1"

# 
git cherry-pick commit-id # 来源于上面那个唯一的commit_id
# 这样my_branch_1.6.1就有一个提交commit，就是上面那个commit

# 继续commit和1.6.1有关的内容
# 如：1.6.1的changelog

############
# PR到1.6.1
############
```

**案例2：将release/0.11 rebase到master**

release/0.11一般有很多个PR(很多个PR的commit)，将其合并到master的时候，需要选择``Fast Forward``，这样master中的commit才是原本的commit。如果选择``Squash``，则release/0.11中所有的commit都将合并成一条。

如果是单次PR，则选择Squash合并策略即可，否则会有很多commit信息被合并到分支中。

<div style="display: flex; justify-content: center; align-items: center;">
  <img src="https://github.com/wumin199/wm-blog-image/raw/main/images/2024/git/git_rebase_master.png" alt="" style="width:50%;">
  <div style="width: 50%;"></div>
</div>