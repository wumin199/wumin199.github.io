---
layout: page
title: About
description: 生命如此短暂，掌握技艺却要如此长久
keywords: Simon Wu, About Me
comments: true
menu: 关于
permalink: /about/
---

> 生活 
> 
> 除了眼前的苟且
>
> 还有
>
> 诗和远方

Hey, 我是武敏(Simon Wu), 工业机器人工程师

热爱读书，迷恋中国传统文化

乒乓球、篮球爱好者

喜欢独处，性格安静，理想主义

自由之心，身不能至，心向往之

## 联系我

* 微信: Simon_wumin199
* E-mail: wumin199@126.com
* 知乎: [@云梦泽](https://www.zhihu.com/people/min-min-66-90)
* GitHub: [@wumin199](https://github.com/wumin199)
* 坐标: 上海

## Skill Keywords

{% for category in site.data.skills %}
### {{ category.name }}
<div class="btn-inline">
{% for keyword in category.keywords %}
<button class="btn btn-outline" type="button">{{ keyword }}</button>
{% endfor %}
</div>
{% endfor %}
