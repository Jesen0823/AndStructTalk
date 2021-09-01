# AndStructTalk
android 架构 总结



![MVP架构](./images/README-1630488526253.png)

![mvvm](./images/README-1630504690746.png)

apt预编译与Activity没有关系，只跟布局文件里的databinding布局规范有关
关系：MVVM是一种架构；dataBinding是一种辅助工具，可以用到MVP里面MVC里面。

![问题解析](./images/README-1630510452231.png)


DataBinding会扫描项目中含<Data></Data>标签的布局文件，生成如下文件：
路径：build/intermediates/data_binding_layout_info_type_merge/debug/out/activity_login-layout.xml
![activity_login-layout.xml](./images/README-1630512745198.png)

根据他生成的文件路径：
build/generated/ap_generated_sources/debug/out/com/jesen/mvvm/databinding/ActivityLoginBindingImpl.java

![生成文件](./images/README-1630513231124.png)

查看纯净的layout布局文件：
build/intermediates/incremental/mergeDebugResources/stripped.dir/layout/activity_login.xml

![布局](./images/README-1630513861517.png)


