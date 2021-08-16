# AppInject

开发库的基本框架，让你无需纠结 Context 的获取和基本日志的打印功能

## Use

```gradle
implementation("io.github.clistery:appinject:1.4.3")
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
  - com/yh/appinject/logger

## 一些组件

- SafeJobIntentService
  - 该组件重写了 JobIntentService 并捕捉了以下异常

    ```log
    java.lang.SecurityException: Caller no longer running, last stopped +2s5ms because: timed out while starting
    ```

- ExtKotlinReflect.kt
  - 提供了一些基于反射的扩展用法(所有方法都是安全的，不会抛出异常，最坏的情况就是操作不成功或返回null)

    ```kotlin
    val userClazz = Class.forName("xxx.User")
    // 通过匹配的构造器创建 User 对象
    val user = userClazz.safeCreator(String::class.java, "clistery")
    // 获取 User 对象的 name 字段的值
    userClazz.safeFieldGet("name", user)
    // 设置 User 对象的 email 字段的值
    userClazz.safeFieldSet("email", user, "cai1083088795@gmail.com")
    // 更多方法请参阅 API
    ......
    ```

- ViewBindingActivity
  - 该类是一个抽象的 ViewBinding's Activity，使用时需要传入你的 ViewBinding 泛型
    - beforeOnCreate - 在 super.onCreate() 被调用前的函数，可以重写实现你自己的业务代码
    - binderCreator - 创建 ViewBinding 的抽象方法，由子类实现
    - preInit - 在 VB.onInit() 被调用前的函数，可以重写实现你自己的业务代码
    - VB.onInit - 初始化函数，在这里你可以对视图进行一些初始化操作，比如设置视图大小、颜色、状态等
    - changeBinder - 操作视图改变的函数，可以在子线程直接调用该函数

## 其他的扩展

- ExtAny.kt
  - Any?.memoryId - 提供该对象的(伪)内存地址，只能用作参考
- ExtContext.kt
  - Context.isMainProcess - 检查当前是否处于主进程
- ExtLooper.kt
  - Looper.isCurrentLooper - 检查调用线程是否在当前线程
