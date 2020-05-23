# personal_blogs(请忽略项目名, 项目名跟项目内容无关系)

### 说明: 
  - 此项目案例和整合均是在2018-2019年编写. 此是第一版也是最终版, 以后停止更新. 
  - 此项目案例比较庞大, 说有上百个案例毫不为过, 在这里我没有写文档说明, 是因为每个包下基本上都有说明.md来说明,并且每个代码文件中也非常详细的说明了用途和使用.
#### 如何启动???
  1. 下载项目, 导入idea
  2. 修改application.properties中将local改为prod
  3. 配置application.prod中的环境信息
  4. 导入mysql的blog.sql文件(在sql文件夹中). 
  4. run ...
  5. 然后就可以做你需要的测试案例了..

#### 此项目你会学到什么?
  ### 环境整合
    - 整合多数据源, druid监控
    - 整合mysql, postgresql, oracle, monggodb, redis
    - 整合es
    - 整合rabbitmq
    - 整合kafka
    - 整合springcloud
    - 整合springboot的相关插件(如分页插件, 文件上传下载)
    - 整合swagger
    - 等等等等... 拉取下项目可以详细看
  ### 案例
    - 并发编程(多线程, 并发安全, 线程池, 手写连接池, 队列, 锁等等..)
      - calable, cas, aqs, countdownlatch, exchange, forkjoin, threadlocal colatiles等等..
    - 设计模式(策略模式, 代理模式, 单例模式, 工厂模式, 适配器模式, 外观模式, 装饰器模式等)
    - 加密算法: base64 md5 rsa sha 雪花算法
    - jdk8相关特性: lambda, stream等
    - 强软弱虚引用等, 反射, 泛型
    - 自定义注解
    - aop切面, 事务等
    - netty, websocket, socket
    - java util包下等方法什么场合使用, IO流, 枚举
    - shell脚本
    - 等等..
    
  ### 工具
    - java poi
    - httpclint连接池
    - java调用python
    - token + jwt
    - 上传下载
