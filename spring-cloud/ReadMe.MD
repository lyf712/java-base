一、概述
springcloud学习笔记


二、具体步骤

（1） Eureka模块
1. 作用：服务治理，提供注册中心（zookeeper也可以提供），进行生产者和消费者的统一管理。
2. 使用过程：创建Server(注册中心)和client(生产者和消费者，需要在server上面进行注册)
3. 关于注册中心的安全保护，采用SpringSecurity进行账户密码保护(集成，配置即可)
4. 搭建集群（注册中心进行两两相互注册），目的防止中心崩塌
5. 关于eureka的自我保护和instanceID（利用心跳进行监测）


（2） Ribbon模块
1. 作用：做负载均衡（客户端，nginx和F5集中式）
2. 使用过程：单独调用ribbon进行负载均衡（创建一个servser数组，进行遍历访问）
3. Ribbon结合RestTemplate使用（实例找不到？？？）
