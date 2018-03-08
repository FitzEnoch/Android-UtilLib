## AndroidKit
# AndroidUtils
[ ![Download](https://api.bintray.com/packages/yingzi/maven/android-util/images/download.svg) ](https://bintray.com/yingzi/maven/android-util/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)


Android 安卓工具库
Android 开发工具库 API文档 说明

------------

library 使用方式
```
在项目配置文件中引用：compile 'vip.devkit:android-util:1.0.5'

```



library 说明
  Library 是常用工具类集成
  每一个Android开发者在日常开发中都会积累一些自己的代码片段
  目的：
  1.将常用功能模块做成工具类
  2.封装Android系统api,简化api的使用
  3.收集一些高效的正确的代码片段避免下次踩坑
  4.尽量少依赖第三方
  5.便捷开发


  library 中的方法调用

  调用方法如此简单：
  调用结果 = 类名.方法名( 参数 )
  AppUtils.getAppName(MainActivity.this,vip.devkit.library);

  library Bintray
  <p><a href="https://bintray.com/yingzi/maven/android-util?source=watch" target="_blank"><img src="./library/bintray_badge_color.png" alt="Bintray"></a></p>
