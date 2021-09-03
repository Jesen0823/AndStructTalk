##  源码分析

### Binder

  进程间通讯：
1. 管道，系统内核空间先从用户空间1复制数据，再从内核空间复制数据给用户空间2，数据进行了两次拷贝， 耗费性能，半双工(两个进程发送信息是独立的)，全双工（两个进程同时互相发送数据）
    ![管道通信](./images/README_源码-1630566264267.png)

2. 共享内存 多个进程同时访问同一块内存，管理混乱
3. Socket  适用于网络通信，效率低
4. Binder Android进程通信，C/S架构：
   1. 比Socket更安全，身份验证
   2. 性能高，只需要一次数据拷贝

  ![binder过程](./images/README_源码-1630567406833.png)

    内存映射,mmap()在binder驱动启动的时候调用
    ![mmap](./images/README_源码-1630568479452.png)

![binder](./images/README_源码-1630568797623.png)


### PMS
  PackageManagerService管理应用程序的管家。 负责应用安装，卸载，应用信息的查询。

  ![PMS调用示例 PMS-project](./images/README_源码-1630578972986.png)

### apk安装

   * 有界面安装正常安装
     拉起的安装界面是系统应用：
     ![系统安装应用](./images/README_源码-1630580015353.png)

   * 静默安装
   ![静默](./images/README_源码-1630583211614.png)

### AMS
   ![startService过程](./images/README_源码-1630583923063.png)

![Activity启动](./images/README_源码-1630595354599.png)

### app启动分析
  launcher是由SystemService启动
  ![launcher的启动](./images/README_源码-1630597016361.png)

![app启动流程](./images/README_源码-1630599569951.png)

![app框架](./images/README_源码-1630599708742.png)

### 换肤框架

![原理](./images/README_源码-1630601127723.png)

![关键点](./images/README_源码-1630602365627.png)

![思路](./images/README_源码-1630603131442.png)

![缺点及兼容性](./images/README_源码-1630603168911.png)



