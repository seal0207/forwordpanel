# 闲(的)蛋(疼)中转面板
基于Java编写的中转管理平台(中转面板), 用来加速国内服务
使用的技术:
- springboot
- element-ui
- h2

> 注意, 系统不必每个nat上都部署, 也不需要一定要部署在nat, 只要部署的机器能ssh上nat即可, 部署一套系统管理多个nat

## 使用方式
### 1. JAVA方式
1. 本地安装jdk环境, maven
2. 下载源码, 修改resources文件夹下的application.properties的spring.datasource.password为自己想要的密码
2. 使用maven打包 mvn package
3. 将target中打包好的jar(或者从release中下载) forwordpanel-0.0.1-SNAPSHOT.jar拷贝到要部署的服务器
4. 服务器安装open-jdk
5. 执行nohup java -jar forwordpanel-0.0.1-SNAPSHOT.jar&
6. 初始化数据库, 访问ip:8080/h2 进入数据库管理, 用户名sa, 密码为上面修改的密码 将源码resources下的data.sql拷贝下来, 执行
7. 访问ip:8080 登录吧, 默认登录账号为 admin XIAOLIzz123
### 2. DOCKER方式
#### 2.1 安装启动
1. docker run -d -p 10203:8080 leeroydocker/forwordpanel:latest
2. 访问ip:10203 登录吧, 默认登录账号为 admin XIAOLIzz123
3. 交流群, 见issue

#### 2.2镜像升级
1. docker pull leeroydocker/forwordpanel:latest
2. 命令行输入docker ps 回车, 获取当前forwordpanel的实例id, 比如是aaa
3. 从实例中拷贝出数据库文件 docker cp aaa:/forward_db.mv.db .
4. 停止实例  docker stop aaa
5. 使用新镜像启动实例  docker run -d -p 10203:8080 leeroydocker/forwordpanel:latest
6. 命令行输入docker ps 回车, 获取当前forwordpanel的实例id, 比如是bbb
7. 将之前的数据库拷贝进去  docker cp forward_db.mv.db bbb:/forward_db.mv.db
8. 重启  docker restart bbb

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
