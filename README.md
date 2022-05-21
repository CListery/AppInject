# AppInject

开发库的基本框架，让你无需纠结 Context 的获取和基本日志的打印功能

## Use

```gradle
implementation("io.github.clistery:appinject:1.4.5")
```

## For Library

- 获取Context
  - 让你的库实例实现 InjectHelper 接口
  - 通过你自己的库实例调用 ctx() 即可获取 Context

- 日志输出
  - 通过你自己的库实例调用 lib[?] 即可输出日志

## For APP

- 实现
  - 让你的 Application 实例实现 IBaseAppInject 接口

- 注册库
  - 使用库实例调用 register() 即可

- 日志输出
  - 可以对特定的库进行 loggerConfig() 设置特定的日志配置
  - 任意函数中调用 log[?] 即可输出日志

- 控制日志输出
  - LogsManager.get().setDefLoggerConfig() 默认的日志配置
    - libConfig - 所有库的日志配置
    - appConfig - APP的日志

## 日志函数

- APP中
  - logD
  - logE
  - logI
  - logV
  - logW
  - logWTF
  - logJSON
  - logXML
  - logCursor
  - logP

- Library
  - libD
  - libE
  - libI
  - libV
  - libW
  - libWTF
  - libJSON
  - libXML
  - libCursor
  - libP

- LibLogs
  - logD
  - logE
  - logI
  - logV
  - logW
  - logWTF
  - logJSON
  - logXML
  - logCursor
  - logP

- 更多关于日志打印功能请参阅
  - com/yh/appbasic/logger
