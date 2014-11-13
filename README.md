
# 舆情搜索统计服务项目

> 基于Java实现。

## 项目内容

- [项目简介](#项目简介)
- [项目架构](#项目架构)
- [开发人员](#开发人员)

----

## 项目简介

### 项目起因

本项目主要服务于舆情实时数据的搜索统计分析。

### 项目框架

`elasticsearch-parent`: jar和插件依赖工程

`elasticsearch-dao`: 数据接口层

`elasticsearch-redis`: 数据缓存层或消息队列层

`elasticsearch-web`: API接口服务层

`elasticsearch-core`: 核心业务层

> **备注:** 框架持续更新中。

### API文档
[项目wiki](http://192.168.3.23/wiki)

> **备注:** API文档统一放在公司的wiki上。

----

## 项目架构

1. MySQL： 用于存储基本爬虫数据。
2. Redis： 用于数据去重，基本思想是存储每条数据的md5(key)，根据该值进行数据插入更新判断。
3. ElasticSearch： 用于提供分布式实时统计计算服务。

### 常见约束词
Item      | Value
--------- | -----
Dao  | Interface接口层
Domain    | 数据
Constant  | 常量
Util      |  工具

### 示例代码

```java
    ** Redis层调用 **      
        // 对象申明      
	RedisCache redisCache = new RedisCache("hdp321", 6379, "zxsoft");
	String key = "record_key_md5";
	String[] members = { "v1", "v2", "v3", "v4", "v5", "v3" };
	redisCache.sadd(key, members);
	System.out.println(redisCache.scard(key));
	System.out.println(redisCache.sismember(key, "v3"));
	System.out.println(redisCache.sismember(key, "v6"));
	redisCache.sadd(key, "v5", "v7");
	System.out.println(redisCache.scard(key));
	System.out.println(redisCache.smembers(key));
	
    ** 启动脚本 ** 

```

### 开发人员

WeChat: wgybzb

QQ: 1010437118

E-mail: wgybzb@sina.cn

