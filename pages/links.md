---
layout: page
title: Links
description: 
keywords: 友情链接
comments: true
menu: 链接
permalink: /links/
---

> 个人相关

{% for link in site.data.links %}
  {% if link.src == 'myself' %}
* [{{ link.name }}]({{ link.url }})
  {% endif %}
{% endfor %}

> 友情链接

{% for link in site.data.links %}
  {% if link.src == 'others' %}
* [{{ link.name }}]({{ link.url }})
  {% endif %}
{% endfor %}
