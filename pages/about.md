---
layout: page
title: About
description: 生命如此短暂，掌握技艺却要如此长久
keywords: Simon Wu, About Me
comments: true
menu: 关于
permalink: /about/
---

生活
不只有眼前的苟且
还有
诗和远方

## 联系

{% for website in site.data.social %}
* {{ website.sitename }}：[@{{ website.name }}]({{ website.url }})
{% endfor %}

## Skill Keywords

{% for category in site.data.skills %}
### {{ category.name }}
<div class="btn-inline">
{% for keyword in category.keywords %}
<button class="btn btn-outline" type="button">{{ keyword }}</button>
{% endfor %}
</div>
{% endfor %}
