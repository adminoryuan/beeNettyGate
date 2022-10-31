# NettyGate 
- 基于spring+netty实现的高性能采集网关,用来收集beeclient上报的信息.并实时转发数据到订阅的websocket客户端
# 实现功能
- [x] tcp 上报鉴权
- [x] websocket token鉴权
- [x] 链接心跳实现
- [x] redis实现单点登录
- [x] websocket 实时转发
- [x] 异步消息投递到rabbitmq中
- [x] 批量提交
# 如何使用
- 下载代码
- # 修改mq链接信息
- ```com.bee.mq.impl.MqFactory```
- # 修改redis链接信息
- ``` com.bee.untils.redisTempleUntils```
- 向redis添加token
- 向redis 添加可以上报的apikeys 
- 打开beeclient 配置apikey 即可上报信息 [beeclient](https://github.com/adminoryuan/beeClient)
- # 测试
- 打开postman
- 在headers中添加 token 
- 创建websocket链接
- 即可可以看到上报的服务器信息了
