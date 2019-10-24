# 集成友盟

国内Push功能是基于友盟推送开发的，请先参考[友盟文档](https://developer.umeng.com/docs/66632/detail/66744)将友盟集成到项目中，涂鸦云支持友盟第三方通道，如果需要小米、华为、魅族通道，去各个平台申请，并且依照友盟文档初始化。

### 配置信息

将友盟申请方式可以参考文档[**友盟推送Key申请流程**](https://docs.tuya.com/docDetail?code=K8uhkjmak2nn8)

将申请到的 app key等信息复制到 “APP工作台”-“APP SDK”-对应应用的配置中，

> ⚠️请勿将信息配置错误：
>
> * 确认友盟后台包名与sdk应用包名一致
> * Umeng Message Secret 和 App Master Secret 不要配置反了
> * 友盟后台 “启用服务器IP地址” 需要手动关闭

<img src="images/push_umeng_config.png" style="zoom: 33%;" />

### 设置用户别名

在确保友盟已经集成到项目中后，通过友盟SDK设置用户id，推送时会按照用户id向用户推送消息

```java
/**
* @param aliasId 可以是你的应用为每个用户自动生成的唯一id
* @param ALIAS_TYPE		 需要填"TUYA_SMART"
*/
mPushAgent.setAlias("可以使用友盟获取的token", "TUYA_SMART", new UTrack.ICallBack() {
    @Override
    public void onMessage(boolean isSuccess, String message) {
    }
});
```

### 注册涂鸦云Push

将aliasId注册到涂鸦云，（必须！）

```java
/**
* @param aliasId   用户别名 将上一步中拿到的别名注册到涂鸦云，涂鸦云将会以该别名向app推送消息
* @param pushProvider   注册push的类别  
* 		                      友盟需填写："umeng"
*/		
TuyaHomeSdk.getPushInstance().registerDevice(String aliasId, String pushProvider, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
    }

    @Override
    public void onSuccess() {

    }
});
```

### 第三方通道设置

如果使用了友盟第三方通道，弹窗的activity必须命名为SpecialPushActivity.

以小米为例，SpecialPushActivity继承自UmengNotifyClickActivity，并且完整的包名路径为`com.activity.SpecialPushActivity`.

### Push消息接收

继承 UmengMessageService, 实现自己的Service来完全控制达到消息的处理，参考友盟文档

```java
public class MyPushIntentService extends UmengMessageService {
    @Override
    public void onMessage(Context context, Intent intent) {
        try {
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            UMessage msg = new UMessage(new JSONObject(message);
            Log.d(TAG, "message=" + message);      //消息体
            Log.d(TAG, "custom=" + msg.custom);    //自定义消息的内容
            Log.d(TAG, "title=" + msg.title);      //通知标题
            Log.d(TAG, "text=" + msg.text);        //通知内容
            
            PushBean pushBean = PushBean.formatMessage(msg.custom)

        } catch (Exception e) {
            L.e(TAG, e.toString());
        }
}
```

>说明
> - msg.custom中的内容就是收到的推送信息
> - msg.custom的具体协议格式：
>	custom=tuya://message?a=view&ct="title"&cc="content"&p=>{}&link=tuyaSmart%3A%2F%2Fbrowser%3Furl%3Dhttp%253A%252F%252Fwww.baidu.com;
>- 通过PushBean.formatMessage()来对数据进行解析得到PushBean

### 用户解绑

**接口说明**

在用户退出登录等需要解除应用和用户关系的操作下调用友盟的移除别名的方法

```java
/**
* @param aliasId Alias		用户自动生成的唯一id
* @param ALIAS_TYPE		需要填"TUYA_SMART"
*/ 
mPushAgent.deleteAlias(aliasId, "TUYA_SMART", new UTrack.ICallBack() {
    @Override
    public void onMessage(boolean isSuccess, String message) {
    }
});
```

### 发送Push

#### 新增运营Push

涂鸦开发者平台 - 用户运营 - 消息中心 - 新增消息

![](images/android-push-setting-operation.png)

#### 新增告警Push

涂鸦开发者平台 - 对应产品 - 扩展功能 - 告警设置 - 新增告警规则(应用推送方式)

![](images/android-push-setting-warning.png)