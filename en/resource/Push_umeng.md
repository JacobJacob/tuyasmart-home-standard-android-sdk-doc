## Integrated Umeng

The domestic Push function is developed based on Umeng's push. Please refer to the [Umeng Document](https://developer.umeng.com/docs/66632/detail/66744)to integrate Umeng into the project. Tuya Cloud supports Umeng third-party channel. If Xiaomi, Huawei and Meizu channels are required, a user can apply to each platform for initialization according to Umeng Document.

### Configuration app information

Copy the information such as “app key” to the “APP Workbench” - “APP SDK” - the configuration of the corresponding application.

>  ⚠️ Do not configure the information incorrectly:
- Confirm that the Friend League background package name is the same as the sdk application package name
- Umeng Message Secret and App Master Secret don't be reversed
- You Union background "Enable server IP address" needs to be manually closed

<img src="images/push_umeng_iot_en.png" style="zoom: 33%;" />

### Set up a user alias

After Umeng has been ensured to integrate into the project, the user id is set through the Umeng SDK, and the message is pushed to the user according to the user id.

```java
/**
@param aliasId Alias		
1.It can be the unique id that your app automatically generates for each user.

* 2. It can also be the user id obtained from the third party platform when the user logs in with a third-party platform. Refer to the Umeng Document for details.

* 3.You can also use the methods in the tuya SDK to get a unique id. PhoneUtil.getDeviceID(context),PhoneUtilis located in com.tuya.smart.android.common.utils.

* @param ALIAS_TYPE		fill in"TUYA_SMART"

*/
mPushAgent.setAlias("zhangsan@sina.com", "TUYA_SMART", new UTrack.ICallBack() {
     @Override
     public void onMessage(boolean isSuccess, String message) {

     }
});
```
### Push Tuya Cloud Registration

Register aliasId to the Tuya Cloud
```java
/**
* @param aliasId   user alias, the alias as the user alias used in registering Umeng in Step 2
* @param pushProvider   registered push category   It is filled in"umeng”

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

### Third-party channel setting

If users use the Umeng third-party channel, the pop-up activity must be named SpecialPushActivity.   Take Xiaomi as an example, SpecialPushActivity inherits from UmengNotifyClickActivity, and the complete package name path is com.activity.SpecialPushActivity.

**Push message reception**

Inherit UmengMessageService, and implement your own Service to fully control the processing of the Msg.custom contains the received push information message. Refer to the Umeng document.
```java
public class MyPushIntentService extends UmengMessageService {
     @Override
     public void onMessage(Context context, Intent intent) {
         try {
             String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
             UMessage msg = new UMessage(new JSONObject(message);
             UmLog.d(TAG, "message=" + message);      //message body
             UmLog.d(TAG, "custom=" + msg.custom);    //content of custom message 
             UmLog.d(TAG, "title=" + msg.title);      // notice title
             UmLog.d(TAG, "text=" + msg.text);        //notice content
	         PushBean pushBean=PushBean.formatMessage(msg.custom)
         } catch (Exception e) {
             L.e(TAG, e.toString());
         }
}
```
**Description**

- msg.custom contains the received push information.
- The specific protocol format of msg.custom: custom=tuya://message?a=view&ct=${title}&cc=${content}&p={}&link=tuyaSmart%3A%2F%2Fbrowser%3Furl%3Dhttp%253A%252F%252Fwww.baidu.com;
- The data is parsed with PushBean.formatMessage() to obtain the PushBean.

### User unbind

**Description**

When the user needs to unburden the application and user relationship, such as logging out, user can invoke the removal alias method by Umeng.
```java
/**
* @param aliasId Alias		user-generated unique id
* @param ALIAS_TYPE		fill in"TUYA_SMART"
*/
mPushAgent.deleteAlias(aliasId, "TUYA_SMART", new UTrack.ICallBack() {
    @Override
    public void onMessage(boolean isSuccess, String message) {
    }

});
```
### Send push

### Add operation push

Tuya Developer Platform -> User operation -> Message center ->Add message

![](images/android-push-setting-operation.png)

### Add alarm push

![](images/android-push-setting-warning.png)

Tuya Developer Platform -> Product -> Extended function ->Set alarm -> Add rules for alarms (apply the push mode)

