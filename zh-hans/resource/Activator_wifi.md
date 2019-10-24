### WiFi设备配网

#### 描述

WiFi设备配网主要有EZ模式和AP模式两种

#### 获取配网Token

开始配网之前，SDK需要在联网状态下从涂鸦云获取配网Token，然后才可以开始EZ/AP模式配网。
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

#### 初始化配网参数

```java
protected ITuyaActivator mTuyaActivator; //集成配网具体实现接口
```

```java
/**
* @param token: 配网所需要的激活key。
* @param ssid: 配网之后，设备工作WiFi的名称。（家庭网络）
* @param password: 配网之后，设备工作WiFi的密码。（家庭网络）
* @param activatorModel: 现在给设备配网有以下两种方式:
ActivatorModelEnum.TY_EZ: 传入该参数则进行EZ配网
ActivatorModelEnum.TY_AP: 传入该参数则进行AP配网
* @param timeout: 配网的超时时间设置，默认是100s.
* @param context: 需要传入activity的context.
*/
```

#### EZ模式配网

```sequence
Title: EZ 配网

participant APP
participant Device
participant Server

Note over APP: 连上路由器
Note over Device: Wifi灯快闪

APP-->Server: 获取token
Server-->APP: 返回token

Note over APP: 开始配网，通过广播、组播循环发ssid/pwd/token
Device->Device: 捕捉到ssid/password/token

APP-->Server: 根据token 2秒钟轮询一次入网激活设备列表(总时长默认100s)

Device-->Server: 去激活设备
Server-->Device: 激活成功

Server-->APP: 激活成功，返回成功设备列表

```

```java
mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newMultiActivator(new ActivatorBuilder()
.setSsid(ssid)
.setContext(context)
.setPassword(password)
.setActivatorModel(ActivatorModelEnum.TY_EZ)
.setTimeOut(CONFIG_TIME_OUT)
.setToken(token)
.setListener(this));
```

#### AP模式配网

```sequence
Title: AP 配网

participant APP
participant Device
participant Server

Note over APP: 连上路由器
Note over Device: Wifi灯慢闪
APP-->Server: 获取token
Server-->APP: 返回token

Note over APP: 连上设备的热点
APP-->Device: 开始配网，发送配置信息ssid/pwd/token

Note over Device: 自动关闭热点
Note over Device: 连上路由器WiFi

Note over APP: 自动重新连上路由器
APP-->Server: 根据token 2秒钟轮询一次入网激活设备列表(总时长默认100s)

Device-->Server: 去激活设备
Server-->Device: 激活成功

Server-->APP: 激活成功，返回成功设备列表
```

```java
mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newActivator(new ActivatorBuilder()
.setSsid(ssid)
.setContext(context)
.setPassword(password)
.setActivatorModel(ActivatorModelEnum.TY_AP)
.setTimeOut(CONFIG_TIME_OUT)
.setToken(token)
.setListener(this));
```

#### 配网方法调用

```java
mTuyaActivator.start(); //开始配网

mTuyaActivator.stop(); //停止配网

mTuyaActivator.onDestroy(); //退出页面销毁一些缓存和监听
```

#### 配网结果回调

```
ITuyaSmartActivatorListener listener 配网回调接口，配网业务类需要实现该接口
```

```java
* @method onError(String errorCode,String errorMsg);
@param errorCode:
1001        网络错误
1002        配网设备激活接口调用失败，接口调用不成功
1003        配网设备激活失败，设备找不到。
1004        token 获取失败
1005        设备没有上线
1006        配网超时

@param errorMsg:
暂时没有数据，请参考errorCode。

* @method onActiveSuccess(DeviceBean deviceBean);
设备配网成功,且设备上线（手机可以直接控制），可以通过

* @method onStep(String step, Object o);
|@param step         |@param o
|device_find         |devId (String)
|device_bind_success |dev (DeviceBean)
【备注】
device_find 发现设备
device_bind_success 设备绑定成功，但还未上线，此时设备处于离线状态，无法控制设备。
```

#### 配网问题汇总

配网超时，此时设备一直处于连不上网络的状态。有以下几种原因。

- 获取WiFi Ssid 错误，导致配网失败
安卓系统API里面获取到ssid，通常前后会有“”。
建议使用Tuya Sdk里面自带的WiFiUtil.getCurrentSSID()去获取
- WiFi密码包含空格
用户在输入密码的时候，由于输入法联想的功能很容易在密码中输入空格。建议密码输入的时候直接显示出来，另外在判断密码含有空格的时候，弹窗提醒用户。
- 用户不输入WiFi密码
用户在首次使用智能设备产品的过程中，很容易不输入密码就进行后续操作
建议判断密码输入为空且WiFi加密类型不为NONE时，弹窗提醒用户。
- 用户在AP配网时选择了设备的热点名称，用户首次使用智能产品的过程中，很容易出现此问题。
建议在判别AP配网时用户选择了设备的热点名称，弹窗提醒给用户。
- 获取WiFi的Ssid为"0x","\<unknown ssid\>"
目前发现在一些国产手机会出现此问题。并不是用户选择的WiFi名称。这是由于定位权限没开启导致的，建议用户可以手动输入WiFi的Ssid，或者给出弹窗提醒，让用户开启相应权限。

配网超时，此时设备已经激活成功。可能原因有：

- APP没有连接到正常的网络，导致无法获取设备的状态。
