# AppInject

开发库的基本框架，动态实现库的特征

## Use

```gradle
implementation("io.github.clistery:appinject:1.6.0")
```

## For Library

- 特征
  1. 特征继承自 IBaseAppInject 接口

  ```kotlin
  interface Lib1Inject : IBaseAppInject {
    val lib1Number: Number
  }
  ```

  - 库实例实现 InjectHelper 抽象类

  ```kotlin
  object Lib1 : InjectHelper<Lib1Inject>()
  ```

  - 库中使用特征

  ```kotlin
  class A {
    init {
        logW("A init: ${Lib1.inject.lib1Number}", loggable = Lib1)
    }
  }
  ```

## For APP

- 实现
  - 实现库定义好的特征接口

  ```kotlin
  class InjectImpl : IBaseAppInject, Lib1Inject {
    override val lib1Number: Number get() = Math.random()

    override fun showTipMsg(msg: String) {
      Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show()
    }

    override fun getNotificationIcon(): Int = R.mipmap.ic_launcher
  }
  ```

  - 并通过库实例的 register 函数注册特征实现
  
  ```kotlin
  Lib1.register(InjectImpl())
  ```
