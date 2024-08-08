# mall-swarm-arch

## 介绍

《mall-swarm微服务学习教程》架构篇示例代码，循序渐进带你搭建一个mall-swarm项目在用的微服务项目脚手架，包含了项目脚手架搭建过程中的代码。

## 教程文档

文档地址：[https://cloud.macrozheng.com](https://cloud.macrozheng.com)

## 项目结构

``` lua
mall-swarm-arch
├── mall-swarm-tiny -- 一个基于Spring Cloud的微服务项目脚手架
├── mall-swarm-tiny-01 -- mall-tiny项目拆分为多模块
├── mall-swarm-tiny-02 -- mall-swarm-tiny项目集成注册中心和网关
├── mall-swarm-tiny-03 -- mall-swarm-tiny项目整合Knife4j实现网关聚合文档
├── mall-swarm-tiny-04 -- mall-swarm-tiny整合Sa-Token实现认证授权功能
├── mall-swarm-tiny-05 -- mall-swarm-tiny整合Admin实现微服务监控
├── mall-tiny -- 一个基于Spring Boot的脚手架项目，精简掉了项目中的电商业务，保留了基本的项目骨架
└── mall-tiny-template -- 一个基于Spring Boot的模版项目，创建子模块时可以直接拷贝
```