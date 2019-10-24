# 用户uid登陆体系
涂鸦智能提供uid登陆体系。如果客户自有用户体系，那么可以通过uid登陆体系，接入我们的sdk。
## 一、用户uid注册

**接口描述**

用户uid注册
```java
/**
* 用户uid注册
* @param countryCode 国家号码
* @param uid         用户uid
* @param password    用户密码
* @param callback    uid 注册回调接口
*/
TuyaHomeSdk.getUserInstance().registerAccountWithUid(String countryCode, String uid, String password, IRegisterCallback callback);
```
**代码范例**

```java
//uid注册
TuyaHomeSdk.getUserInstance().registerAccountWithUid("86", "1234","123456", new IRegisterCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "UID注册成功", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```
## 二、用户uid登陆
**接口描述**

用户uid登陆

```java
/**
* uid 登陆
* @param countryCode 国家号码
* @param uid         用户uid
* @param passwd      用户密码
* @param callback    uid 登陆回调接口
*/
TuyaHomeSdk.getUserInstance().loginWithUid(String countryCode, String uid, String passwd, ILoginCallback callback);
```
**代码范例**

```java
//uid登陆
TuyaHomeSdk.getUserInstance().loginWithUid("86", "1234", "123456", new ILoginCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "登录成功，用户名：" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```
## 三、用户uid重置密码
用户uid重置密码，需要通过云云对接的方式进行重置密码。详看云端API文档

[https://docs.tuya.com/cn/openapi/api/post_apps.schema.user_1.0.html](https://docs.tuya.com/cn/openapi/api/post_apps.schema.user_1.0.html)

## 四、用户uid登陆注册接口
**接口描述**

用户UID 登陆注册合成一个接口。

```java
/**
* uid 登陆
* @param countryCode 国家号码
* @param uid         用户uid
* @param passwd      用户密码
* @param callback    uid 登陆回调接口
*/
TuyaHomeSdk.getUserInstance().loginOrRegisterWithUid(String countryCode, String uid, String passwd, ILoginCallback callback);
```
**代码范例**

```java
//uid登陆
TuyaHomeSdk.getUserInstance().loginOrRegisterWithUid("86", "1234", "123456", new ILoginCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "登录成功，用户名：" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```