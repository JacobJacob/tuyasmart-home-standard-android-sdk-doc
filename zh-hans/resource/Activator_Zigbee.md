### ZigBee网关配网

#### 描述

这里的ZigBee网关配网是指有线配网,不用输入路由器的热点名称和密码。
ZigBee网关配网前，请确保ZigBee网关设备连接上外网联通的路由器，并使ZigBee网关设备处于配网状态

```sequence
Title: Zigbee 网关配网

participant APP
participant Device
participant Server

Note over Device: 将Zigbee网关连上路由器，并置于配网状态
APP --> APP: APP连上和网关相同的路由器热点
Device-->APP: 发送广播包，待配网设备
APP-->APP: 收到设备信息及active状态

APP-->Server: 获取token
Server-->APP: 返回token

APP-->Device: 发送激活命令给网关设备
APP-->Server: 根据token 2秒钟轮询一次入网激活设备列表(总时长默认100s)

Note over Device: 设备收到APP激活广播信息

Device->Server: 去云端进行激活
Server-->Device: 激活成功

Server-->APP: 激活成功，返回成功设备列表

```

#### 获取配网Token

开始配网之前，SDK需要在联网状态下从涂鸦云获取配网Token，然后才可以开始配网。
Token的有效期为5分钟，且配置成功后就会失效（再次配网需要重新获取）。

```java
/**
* @param homeId(参考家庭管理章节)
* @param callback
*/
TuyaHomeSdk.getActivatorInstance().getActivatorToken(homeId, new ITuyaActivatorGetToken() {
@Override
public void onSuccess(String token) {

}

@Override
public void onFailure(String s, String s1) {

}
});
```

#### 配网方法调用

```java
//初始化监听
ITuyaSmartActivatorListener  listener =new ITuyaSmartActivatorListener() {
@Override
public void onError(String errorCode, String errorMsg) {

}

@Override
public void onActiveSuccess(DeviceBean deviceBean) {

}

@Override
public void onStep(String step, Object data) {

}
})
ITuyaActivator mITuyaActivator = TuyaHomeSdk.getActivatorInstance().newGwActivator(new TuyaGwActivatorBuilder()
.setToken(token)
.setTimeOut(100)
.setContext(this)
.setListener(listener);

//开始配网
mITuyaActivator.start()
//停止配网
mITuyaActivator.stop()
//退出页面清理
mITuyaActivator.onDestroy()
```


