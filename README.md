# AppInject

开发库的基本框架，动态实现库的特征

## Use

```gradle
implementation("io.github.clistery:appinject:1.5.0")
```

## For Library

- 获取Context
  - 让你的库实例实现 InjectHelper 接口
  - 通过你自己的库实例调用 ctx() 即可获取 Context

## For APP

- 实现
  - 让你的 Application 实例实现 IBaseAppInject 接口

- 注册库
  - 使用库实例调用 register() 即可
