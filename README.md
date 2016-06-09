Wiggle
===

[![GitHub release](https://img.shields.io/github/release/kubode/Wiggle.svg?maxAge=2592000)]()
[![Build Status](https://travis-ci.org/kubode/Wiggle.svg?branch=master)](https://travis-ci.org/kubode/Wiggle)

![Image](https://github.com/kubode/Wiggle/raw/master/img/image.gif)

Usage
---

Add dependency to `build.gradle`.

```gradle
repositories {
    jcenter()
}
dependencies {
    compile "com.github.kubode.wiggle:framelayout:${latestVersion}"
}
```

You can use `WiggleFrameLayout` in the same way as `FrameLayout`.

```xml
<com.github.kubode.wiggle.framelayout.WiggleFrameLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="20dp"
    android:foreground="#80ffffff" />
```

Note: `WiggleFrameLayout` uses `translationX` and `translationY`.
So you can't use animation of `translationX` and `translationY`.

If you implement wiggle motion yourself using `WiggleHelper`, add dependency `core` rather than `framelayout`.

```gradle
dependencies {
    compile "com.github.kubode.wiggle:core:${latestVersion}"
}
```

License
---

```
Copyright 2016 Masatoshi Kubode

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
