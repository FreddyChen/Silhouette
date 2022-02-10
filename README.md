# Silhouette
封装的Android常用控件，比如：SleTextButton、SleImageButton、SleConstraintLayout、SleFrameLayout、SleLinearLayout、SleRelativeLayout等。使控件具备Shape、Selector等功能，省去编写shape或selector文件的繁琐步骤。另外支持N种颜色渐变，弥补原生shape文件只支持三种颜色（startColor/centerColor/endColor）的不足等。  

## 文章链接  
[Silhouette——更方便的Shape/Selector实现方案](https://juejin.cn/post/7063098095969501191/)

## 写在前面
首先祝大家新年快乐，开工大吉。  
最新刚换了工作，大部分精力还是放到新工作上面，所以这次还是先给大家带来一个小而实用的库：**Silhouette**。另外，考虑到**Kotlin**越来越普及，作者在开发过程中也切实感受到**Kotlin**相较于**Java**带来的便利，后续的**IM**系列文章及项目考虑用**Kotlin**重写，而且考虑到由于工作业务需求过多可能出现断更的情况，所以打算一次性写完再放出来，避免大家学习不方便。  
废话不多说，直接开始吧。

## Silhouette是什么？
**Silhouette**意为“剪影”，取名并没有特别的含义，只是单纯地觉得意境较美。例如上一篇文章[Shine——更简单的Android网络请求库封装](https://juejin.cn/post/7054105794840625160)的网络请求库：[Shine](https://github.com/FreddyChen/Shine-Kotlin)即意为“闪耀”，也没有特别的含义，只是作者认为开源库起名较难，特意找一些比较优美的单词。  
**Silhouette**是一系列基于**GradientDrawable**及**StateListDrawable**封装的组件集合，主要用于实现在**Android Layout XML**中直接支持**Shape/Selector**等功能。  
我们都知道在**Android**开发中，不同的**TextView**及**Button**各种样式（形状、背景色、描边、圆角、渐变等）的传统实现方式是在**drawable**文件夹中编写各种**shape/selector**等文件，这种方式至少会存在以下几种弊端：
1. **shape/selector**文件过多，项目体积增大；
2. **shape/selector**文件命名困难，命名规范时往往会存在功能重复的文件；
3. 功能存在局限性：例如**gradient**渐变色。传统**shape**方式只支持三种颜色过渡(**startColor/centerColor/endColor**)，如果设计稿存在四种以上颜色渐变，**shape gradient**无能为力。再比如**TextView**在常态和按下态需要**同时改变背景色及文字颜色**时，传统方式只能在代码中动态设置等。
4. 开发效率低；
5. 难以维护等；

综上所述，我们迫切需要一个库来解决以上问题，**Silhouette**正具备这些能力。接下来，我们来具体看看**Silhouette**能做什么吧。

## Silhouette能做什么？
上面说到**Silhouette**是一系列组件集合，具体包含以下组件：
* **SleTextButton**  
  基于**AppCompatTextView**封装；  
  具备定义各种样式（形状、背景色、描边、圆角、渐变等）的能力 ；  
  具备不同状态（常态、按下态、不可点击态）下文字颜色指定等。

* **SleImageButton**  
  基于**ShapeableImageView**封装；  
  通过指定**sle_ib_type**属性使**ImageView**支持按下态遮罩层、透明度改变、自定义图片，同时支持**CheckBox**功能；  
  通过指定**sle_ib_style**属性使**ImageView**支持**Normal**、圆角、圆形等形状。

* **SleConstraintLayout**  
  基于**ConstraintLayout**封装；  
  具备定义各种样式（形状、背景色、描边、圆角、渐变等）的功能。

* **SleRelativeLayout**  
  基于**RelativeLayout**封装；  
  具备定义各种样式（形状、背景色、描边、圆角、渐变等）的功能。

* **SleLinearLayout**  
  基于**LinearLayout**封装；  
  具备定义各种样式（形状、背景色、描边、圆角、渐变等）的功能。

* **SleFrameLayout**  
  基于**FrameLayout**封装；  
  具备定义各种样式（形状、背景色、描边、圆角、渐变等）的功能。

## 设计、封装思路及原理
* 项目结构  
  **com.freddy.silhouette**
    - **config**（配置相关，存放全局注解及公共常量、默认值等）
    - **ext**（**kotlin**扩展相关，可选择用或不用）
    - **utils**（工具类相关，可选择用或不用）
    - **widget**（控件相关）
        - **button**
        - **layout**

  由此可见，项目结构非常简单，所以**Silhouette**也是一个比较轻量级的库。

* 封装思路及原理  
  由于该库非常简单，实际上就是根据**Shape/Selector**进行自定义属性，从而利用**GradientDrawable**及**StateListDrawable**提供的**API**进行封装，不存在什么难度，在此就不展开讲了。感兴趣的同学可以到官方文档了解**GradientDrawable**及**StateListDrawable**的原理。

## 自定义属性列表
自定义属性分为**通用属性**和**特有属性**。
* **通用属性**
    - 类型  
      | 属性名称 | 类型 | 说明 | 备注 |
      | -- | :--: | :-- | -- |
      | sle_type | enum | 类型<br>mask：遮罩<br>selector：自定义样式<br>none：无 | 默认值：mask<br>默认的mask为90%透明度黑色，可通过sle_maskBackgroundColors属性设置<br>若不指定为selector，则自定义样式无效 |

    - 形状相关  
      | 属性名称 | 类型 | 说明 | 备注 |
      | -- | :--: | :-- | :--: |
      | sle_shape | enum | 形状<br>rectangle：矩形<br>oval：椭圆形<br>line：线性形状<br>ring：环形 | 默认值：rectangle |
      | sle_innerRadius | dimension&#124;reference | 尺寸，内环的半径 | shape="ring"可用 |
      | sle_innerRadiusRatio | float | 以环的宽度比率来表示内环的半径 | shape="ring"可用 |
      | sle_thickness | dimension&#124;reference | 尺寸，环的厚度 | shape="ring"可用 |
      | sle_thicknessRatio | float | 以环的宽度比率来表示环的厚度 | shape="ring"可用 |

    - 背景色相关  
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :--: | :--: |
      | sle_normalBackgroundColor | color&#124;reference | 常态背景颜色 | / |
      | sle_pressedBackgroundColor | color&#124;reference | 按下态背景颜色 | / |
      | sle_disabledBackgroundColor | color&#124;reference | 不可点击态背景颜色 | 默认值：#CCCCCC |
      | sle_selectedBackgroundColor | color&#124;reference | 选中态背景颜色 | / |

    - 描边相关  
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :--: | :--: |
      | sle_normalStrokeColor | color&#124;reference | 常态描边颜色 | / |
      | sle_pressedStrokeColor | color&#124;reference | 按下态描边颜色 | / |
      | sle_disabledStrokeColor | color&#124;reference | 不可点击态描边颜色 | / |
      | sle_selectedStrokeColor | color&#124;reference | 选中态描边颜色 | / |
      | sle_strokeWidth | dimension&#124;reference | 描边宽度 | / |
      | sle_dashWidth | dimension&#124;reference | 虚线宽度 | / |
      | sle_dashGap | dimension&#124;reference | 虚线间隔 | / |

    - 圆角相关  
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :--: | :--: |
      | sle_cornersRadius | dimension&#124;reference | 总圆角半径 | / |
      | sle_cornersTopLeftRadius | dimension&#124;reference | 左上角圆角半径 | / |
      | sle_cornersTopRightRadius | dimension&#124;reference | 右上角圆角半径 | / |
      | sle_cornersBottomLeftRadius | dimension&#124;reference | 左下角圆角半径 | / |
      | sle_cornersBottomRightRadius | dimension&#124;reference | 右下角圆角半径 | / |

    - 渐变相关  
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :-- | :--: |
      | sle_normalGradientColors | reference | 常态渐变背景色 | 支持在res/array下定义数组实现多个颜色渐变 |
      | sle_pressedGradientColors | reference | 按下态渐变背景色 | 支持在res/array下定义数组实现多个颜色渐变 |
      | sle_disabledGradientColors | reference | 不可点击态渐变背景色 | 支持在res/array下定义数组实现多个颜色渐变 |
      | sle_selectedGradientColors | reference | 选中态渐变背景色 | 支持在res/array下定义数组实现多个颜色渐变 |
      | sle_gradientOrientation | enum | 渐变方向<br>TOP_BOTTOM：从上到下<br>TR_BL：从右上到左下<br>RIGHT_LEFT：从右到左<br>BR_TL：从右下到左上<br>BOTTOM_TOP：从下到上<br>BL_TR：从左下到右上<br>LEFT_RIGHT：从左到右<br>TL_BR：从左上到右下 | / |
      | sle_gradientType | enum | 渐变类型<br>linear：线性渐变<br>radial：圆形渐变，起始颜色从gradientCenterX、gradientCenterY点开始<br>sweep：A sweeping line gradient | / |
      | sle_gradientCenterX | float | 渐变中心放射点x坐标 | 注意，这里的坐标是整个背景的百分比的点，并不是确切点，0.2就是20%的点 |
      | sle_gradientCenterY | float | 渐变中心放射点y坐标 | 注意，这里的坐标是整个背景的百分比的点，并不是确切点，0.2就是20%的点 |
      | sle_gradientRadius | dimension&#124;reference | 渐变半径 | 需要配合gradientType=radial使用，如果设置gradientType=radial而没有设置gradientRadius，将会报错 |

    - 其它  
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :--: | :--: |
      | sle_maskBackgroundColor | color&#124;reference | 当sle_type=mask时，按钮按下状态的遮罩颜色 | 默认值：90%透明度黑色(#1A000000) |
      | sle_cancelOffset | dimension&#124;reference | 用于解决手指移出控件区域判断为cancel的偏移量 | 默认值：8dp |

* **特有属性**
    - **SleConstraintLayout/SleRelativeLayout/SleFrameLayout/SleLinearLayout**
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :-- | :--: |
      | sle_interceptType | enum | 事件拦截类型<br>intercept_super：return super<br>intercept_true：return true<br>intercept_false：return false | / |

    - **SleTextButton**
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :--: | :--: |
      | sle_normalTextColor | color&#124;reference | 常态文字颜色 | / |
      | sle_pressedTextColor | color&#124;reference | 按下态文字颜色 | / |
      | sle_disabledTextColor | color&#124;reference | 不可点击态文字颜色 | / |
      | sle_selectedTextColor | color&#124;reference | 选中态文字颜色 | / |

    - **SleImageButton**
      | 属性名称 | 类型 | 说明 | 备注 |
      | :-- | :--: | :-- | :-- |
      | sle_ib_type | enum | 类型<br>mask：图片遮罩<br>alpha：图片透明度改变<br>selector：自定义图片<br>checkBox：CheckBox场景<br>none：无 | 1.指定为mask时，自定义图片资源无效；<br>2.指定为alpha时，sle_pressedAlpha/sle_disabledAlpha生效；<br>3.指定为selector时，sle_normalResId/sle_pressedResId/sle_disabledResId生效；<br>4.指定为checkBox时，sle_checkedResId/sle_uncheckedResId/sle_isChecked生效；<br>5.指定为none时，图片资源均不生效，圆角相关配置有效 |
      | sle_ib_style | enum | ImageView形状<br>normal：普通形状<br>rounded：圆角<br>oval：圆形 | 默认值：normal |
      | sle_normalResId | color&#124;reference | 常态图片资源 | / |
      | sle_pressedResId | color&#124;reference | 按下态图片资源 | / |
      | sle_disabledResId | color&#124;reference | 不可点击态图片资源 | / |
      | sle_checkedResId | color&#124;reference | 选中态checkBox图片资源 | / |
      | sle_uncheckedResId | color&#124;reference | 非选中态checkBox图片资源 | / |
      | sle_isChecked | boolean | CheckBox是否选中 | 默认值：false |
      | sle_pressedAlpha | float | 按下态图片透明度 | 默认值：70% |
      | sle_disabledAlpha | float | 不可点击态图片透明度 | 默认值：30% |

## 使用方式
1. 添加依赖
```
implementation "io.github.freddychen:silhouette:$lastest_version"
```
Note：最新版本可在[maven central silhouette](https://search.maven.org/artifact/io.github.freddychen/silhouette)中找到。

2. 使用  
   由于自定义属性太多，在此就不一一列举了。下面给出几种常见的场景示例，大家可以根据自定义属性表自行编写：
+ 常态
  ![Silhouette Normal](https://raw.githubusercontent.com/FreddyChen/MarkdownPicBed/main/silhouette_normal.png)
+ 按下态
  ![Silhouette Pressed](https://raw.githubusercontent.com/FreddyChen/MarkdownPicBed/main/silhouette_pressed.png)

以上布局代码为：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black"
    android:gravity="center_horizontal"
    android:orientation="vertical">

    <com.freddy.silhouette.widget.button.SleTextButton
        android:id="@+id/stb_1"
        android:layout_width="match_parent"
        android:layout_height="54dp"
        android:layout_marginHorizontal="48dp"
        android:layout_marginTop="14dp"
        android:gravity="center"
        android:text="SleTextButton1"
        android:textSize="20sp"
        app:sle_cornersRadius="28dp"
        app:sle_normalBackgroundColor="#f88789"
        app:sle_normalTextColor="@color/white"
        app:sle_type="mask" />

    <com.freddy.silhouette.widget.button.SleTextButton
        android:id="@+id/stb_2"
        android:layout_width="match_parent"
        android:layout_height="54dp"
        android:layout_marginHorizontal="48dp"
        android:layout_marginTop="14dp"
        android:gravity="center"
        android:text="SleTextButton2"
        android:textSize="20sp"
        app:sle_cornersBottomRightRadius="24dp"
        app:sle_cornersTopLeftRadius="14dp"
        app:sle_normalBackgroundColor="#338899"
        app:sle_normalTextColor="@color/white"
        app:sle_pressedBackgroundColor="#aeeacd"
        app:sle_type="selector" />

    <com.freddy.silhouette.widget.button.SleTextButton
        android:id="@+id/stb_3"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:layout_marginHorizontal="48dp"
        android:layout_marginTop="14dp"
        android:enabled="false"
        android:gravity="center"
        android:text="SleTextButton2"
        android:textSize="14sp"
        app:sle_cornersBottomRightRadius="24dp"
        app:sle_cornersTopLeftRadius="14dp"
        app:sle_normalBackgroundColor="#cc688e"
        app:sle_normalTextColor="@color/white"
        app:sle_pressedBackgroundColor="#34eeac"
        app:sle_shape="oval"
        app:sle_type="selector" />

    <com.freddy.silhouette.widget.button.SleImageButton
        android:id="@+id/sib_1"
        android:layout_width="84dp"
        android:layout_height="84dp"
        android:layout_marginTop="14dp"
        app:sle_ib_type="mask"
        app:sle_normalResId="@drawable/ic_launcher_background" />

    <com.freddy.silhouette.widget.button.SleImageButton
        android:id="@+id/sib_2"
        android:layout_width="128dp"
        android:layout_height="128dp"
        android:layout_marginTop="14dp"
        app:sle_ib_type="alpha"
        app:sle_normalResId="@drawable/ic_launcher_background" />

    <com.freddy.silhouette.widget.button.SleImageButton
        android:id="@+id/sib_3"
        android:layout_width="72dp"
        android:layout_height="72dp"
        android:layout_marginTop="14dp"
        app:sle_ib_type="selector"
        app:sle_normalResId="@mipmap/ic_launcher"
        app:sle_pressedResId="@drawable/ic_launcher_foreground" />

    <com.freddy.silhouette.widget.layout.SleConstraintLayout
        android:id="@+id/scl_1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="48dp"
        android:layout_marginTop="14dp"
        android:paddingHorizontal="14dp"
        android:paddingVertical="8dp"
        app:sle_cornersRadius="10dp"
        app:sle_interceptType="intercept_super"
        app:sle_normalBackgroundColor="@color/white">

        <ImageView
            android:layout_width="72dp"
            android:layout_height="48dp"
            android:scaleType="centerCrop"
            android:src="@mipmap/ic_launcher_round" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="UserName"
            android:textColor="@color/black"
            android:textSize="18sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </com.freddy.silhouette.widget.layout.SleConstraintLayout>

    <com.freddy.silhouette.widget.layout.SleLinearLayout
        android:id="@+id/sll_1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="48dp"
        android:layout_marginTop="14dp"
        android:gravity="center_vertical"
        android:paddingHorizontal="14dp"
        app:sle_type="selector"
        android:paddingVertical="8dp"
        app:sle_cornersTopRightRadius="24dp"
        app:sle_cornersBottomRightRadius="18dp"
        app:sle_interceptType="intercept_true"
        app:sle_pressedBackgroundColor="#fe9e87"
        app:sle_normalBackgroundColor="#aee949">

        <ImageView
            android:layout_width="72dp"
            android:layout_height="48dp"
            android:scaleType="centerCrop"
            android:src="@mipmap/ic_launcher_round" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="14dp"
            android:text="UserName"
            android:textColor="@color/black"
            android:textSize="18sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </com.freddy.silhouette.widget.layout.SleLinearLayout>
</LinearLayout>
```
*Note：需要给组件设置**setOnClickListener**才能看到效果。*  
至于更多的功能，就让大家去试试吧，篇幅有限，就不一一列举了。有任何疑问，欢迎通过**QQ群**或**微信公众号**联系我。

## 版本记录
| 版本号 | 修改时间 | 版本说明 |
| :--: | :--: | :--: |
| 0.0.1 | 2022.02.10 | 首次提交 |

## 写在最后
终于写完了，**Shape/Selector**在每个项目中基本都会用到，而且频率还不算低。**Silhouette**原理虽然简单，但确实能解决很多问题，这些都是平时开发中的积累，希望对大家能有所帮助。欢迎大家**star**和**fork**，让我们为**Android**开发共同贡献一份力量。另外如果有疑问欢迎加入我的QQ群：**1015178804**，同时也欢迎大家关注我的公众号：**FreddyChen**，让我们共同进步和成长。

# License


    Copyright 2022, chenshichao

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
   

