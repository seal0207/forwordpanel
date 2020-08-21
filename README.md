# 闲(的)蛋(疼)中转面板
基于Java编写的中转管理平台, 用来加速国内服务
使用的技术:
- springboot
- layui
- h2

## 使用方式
> 后面会制作docker镜像, 不会用的可以先star
1. 本地安装jdk环境, maven
2. 下载源码, 修改resources文件夹下的application.properties的spring.datasource.password为自己想要的密码
2. 使用maven打包 mvn package
3. 将target中打包好的jar forwordpanel-0.0.1-SNAPSHOT.jar拷贝到要部署的服务器
4. 服务器安装open-jdk
5. 执行nohup java -jar forwordpanel-0.0.1-SNAPSHOT.jar&
6. 初始化数据库, 访问ip:8080/h2 进入数据库管理, 用户名sa, 密码为上面修改的密码 将源码resources下的data.sql拷贝下来, 执行
7. 访问ip:8080 登录吧, 默认登录账号为 admin XIAOLIzz123
8. 交流群, 见issue

## 主要功能
### 1. 服务器管理
维护中转服务器, 也就是这个面板可以同时管理多台, 主要有配置上用户名密码即可

### 2. 端口管理
维护服务器的端口

### 3. 账号管理
为你的租户创建账号, 分配端口, 到期时间(到期自动停机), 流量限制(还没做)

### 中转管理
租户使用该功能自己设置转发

### 4.配置管理
可以托管一些配置, 这个自己灵活运用

![dJMS8s.png](https://s1.ax1x.com/2020/08/20/dJMS8s.png)
![dJMtGd.png](https://s1.ax1x.com/2020/08/20/dJMtGd.png)
![dJMgRs.png](https://s1.ax1x.com/2020/08/20/dJMgRs.png)
![dJM5ZT.png](https://s1.ax1x.com/2020/08/20/dJM5ZT.png)
![dJMjL6.png](https://s1.ax1x.com/2020/08/20/dJMjL6.png)
