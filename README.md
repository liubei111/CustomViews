# CustomViews

Custom Views(自定义View)
CustomButton：自定义按钮 支持 圆角、边框、背景色(narmal、press、false)、文本色(narmal、press、false)




Add it in your root build.gradle at the end of repositories:
(在根目录下的build添加内容，如下所示)
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

Step 2. Add the dependency
(在app下的build中添加内容，如下所示)
dependencies {
	implementation 'com.github.liubei111:CustomViews:1.0.4'
}

