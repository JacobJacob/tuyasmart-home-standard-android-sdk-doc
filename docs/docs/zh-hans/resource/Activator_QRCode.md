### ZigBee子设备配网

#### 描述

该功能只适用于设备自入网，且设备上电，重置配网状态后，已连上涂鸦云


```sequence

Title: 扫设备二维码配网

participant Device
participant httpdns
participant mqtt
participant APP
participant cloud

Note over Device: 设备上电联网
Note over Device: 重置设备为待配网状态

Device -> httpdns: 选择httpdns，获取mqttsUrl域名
httpdns -> Device: 返回mqttsUrl域名
Device -> mqtt: 使用uuid连接mqtt

APP -> Device: 扫设备二维码
APP -> cloud: APP获取设备uuid，向云端创建token
APP -> cloud: 根据token轮询入网设备

cloud -> mqtt: 推送token和region
mqtt -> Device: 透传给设备

Device -> cloud: 去激活设备
cloud -> Device: 激活成功

cloud-->APP: 激活成功，返回成功设备列表

```

#### 配网方法调用

```java
/**
* @param uuid 设备UUID
* @param homeId 家庭ID
* @param CONFIG_TIME_OUT 超时时间，建议设置为120s（单位是秒）
*/
TuyaQRCodeActivatorBuilder builder = new TuyaQRCodeActivatorBuilder()
.setUuid(uuid)
.setHomeId(homeId)
.setContext(mActivity)
.setTimeOut(CONFIG_TIME_OUT)
.setListener(new ITuyaSmartActivatorListener() {
    @Override
    public void onError(String errorCode, String errorMsg) {
    }

    @Override
    public void onActiveSuccess(DeviceBean devResp) {
    }

    @Override
    public void onStep(String step, Object data) {
    }
}));

ITuyaActivator mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newQRCodeDevActivator(builder);
//开始配网
mTuyaActivator.start();
//停止配网
mTuyaActivator.stop();
//销毁
mTuyaActivator.onDestory();
```
