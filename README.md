## AndroidKit
# AndroidUtils
[![](https://jitpack.io/v/yingzikeji/AndroidUtils.svg)](https://jitpack.io/#yingzikeji/AndroidUtils)
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
<a href='https://bintray.com/yingzi/maven/androud-utils?source=watch' alt='Get automatic notifications about new "androud-utils" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

Android 安卓工具库
Android 开发工具库 API文档 说明

------------

library 使用方式
```
在项目配置文件中引用：compile 'vip.devkit:android-kit:1.0.0'
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
  <p><a href="https://bintray.com/yingzi/maven/android-kit?source=watch" target="_blank"><img src="./library/bintray_badge_color.png" alt="Bintray"></a></p>
