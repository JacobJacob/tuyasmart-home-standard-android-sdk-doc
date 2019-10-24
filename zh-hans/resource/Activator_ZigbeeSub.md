### ZigBee子设备配网

#### 描述

ZigBee子设备配网需要ZigBee网关设备云在线的情况下才能发起,且子设备处于配网状态。

```sequence
Title: Zigbee 子设备激活

participant APP
participant SDK
participant Zigbee网关
participant Service

Note over Zigbee网关: 将Zigbee子设备重置
APP->SDK: 发送子设备激活指令
SDK->Zigbee网关: 发送子设备激活指令

Note over Zigbee网关: 收到子设备激活信息

Zigbee网关->Service: 通知云端子设备激活
Service-->Zigbee网关: 子设备激活成功

Zigbee网关-->SDK: 子设备激活成功
SDK-->APP: 子设备激活成功

```

#### 配网方法调用

```java
TuyaGwSubDevActivatorBuilder builder = new TuyaGwSubDevActivatorBuilder()
//设置网关ID
.setDevId(mDevId)
//设置配网超时时间
.setTimeOut(100)
.setListener(new ITuyaSmartActivatorListener() {
@Override
public void onError(String s, String s1) {
}

@Override
public void onActiveSuccess(DeviceBean deviceBean) {
}

@Override
public void onStep(String s, Object o) {
}
});

ITuyaActivator mTuyaGWActivator = TuyaHomeSdk.getActivatorInstance().newGwSubDevActivator(builder);
//开始配网
mTuyaGWActivator.start();
//停止配网
mTuyaGWActivator.stop();
//销毁
mTuyaGWActivator.onDestory();
```
