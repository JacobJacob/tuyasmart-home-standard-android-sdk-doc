# 手机账号体系

涂鸦智能提供手机验证码登陆体系。
## 一、手机验证码登陆

手机验证码登录功能，需要先调用验证码发送接口，发送验证码。再调用手机验证码验证接口。将收到的验证码填入对应的参数中。

**接口描述**

```java
/**
* 获取手机验证码
* @param countryCode   国家区号
* @param phoneNumber   手机号码
*/
TuyaHomeSdk.getUserInstance().getValidateCode(String countryCode, String phoneNumber, final IValidateCallback callback);
/** 
* 手机验证码登陆
* @param countryCode 国家区号
* @param phone       电话
* @param code        验证码
* @param callback    登陆回调接口
*/
TuyaHomeSdk.getUserInstance().loginWithPhone(String countryCode, String phone, String code, final ILoginCallback callback)
```

**代码范例**

```java
//获取手机验证码
TuyaHomeSdk.getUserInstance().getValidateCode("86","13666666666", new IValidateCallback(){
    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "获取验证码成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
//手机验证码登陆
TuyaHomeSdk.getUserInstance().loginWithPhone("86", "13355555555", "123456", new ILoginCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "登录成功，用户名：" +TuyaHomeSdk.getUserInstance().getUser().getUsername(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
});
```
## 二、用户手机密码登陆

涂鸦智能提供手机密码登陆体系。
### 1. 手机密码注册
手机密码注册。

**接口描述：**

```java
/**
* 获取手机验证码
* @param countryCode   国家区号
* @param phoneNumber   手机号码
*/
TuyaHomeSdk.getUserInstance().getValidateCode(String countryCode, String phoneNumber, final IValidateCallback callback);

/**
* 手机密码注册
* @param countryCode 国家区号
* @param phone       电话
* @param passwd      密码
* @param code        验证码
* @param callback
*/
TuyaHomeSdk.getUserInstance().registerAccountWithPhone(final String countryCode, final String phone, final String passwd, final String code, final IRegisterCallback callback);
```
**代码范例**

```java
//获取手机验证码
TuyaHomeSdk.getUserInstance().getValidateCode("86","13666666666", new IValidateCallback(){
    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "获取验证码成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
 });
//注册手机密码账户
TuyaHomeSdk.getUserInstance().registerAccountWithPhone("86","13666666666","123456","124332", new IRegisterCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```
### 2. 手机密码登陆
**接口描述**

使用手机号码和密码登陆。
```java
/**
* 手机密码登陆
* @param countryCode 国家区号
* @param phone       手机密码
* @param passwd      登陆密码
* @param callback    登陆回调接口
*/
TuyaHomeSdk.getUserInstance().loginWithPhonePassword(String countryCode, String phone, String passwd, final ILoginCallback callback);
```
**代码范例**

```java
//手机密码登陆
TuyaHomeSdk.getUserInstance().loginWithPhonePassword("86", "13666666666", "123456", new ILoginCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "登录成功，用户名：" +TuyaHomeSdk.getUserInstance().getUser().getUsername(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```
### 3. 手机重置密码
**接口描述**

手机账号重置密码。
```java
/**
* 获取手机验证码
* @param countryCode   国家区号
* @param phoneNumber   手机号码
*/
TuyaHomeSdk.getUserInstance().getValidateCode(String countryCode, String phoneNumber, final IValidateCallback callback);

/**
* 重置密码
* @param countryCode 国家区号
* @param phone       手机号码
* @param code        手机验证码
* @param newPasswd   新密码
* @param callback    重置手机密码回调接口
*/
TuyaHomeSdk.getUserInstance().resetPhonePassword(final String countryCode, final String phone, final String code, final String newPasswd, final IResetPasswordCallback callback);

```
**代码范例**

```java
//手机获取验证码
TuyaHomeSdk.getUserInstance().getValidateCode("86", "13555555555", new IValidateCallback() {
    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "获取验证码成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
//重置手机密码
TuyaHomeSdk.getUserInstance().resetPhonePassword("86", "13555555555", "123456", "123123", new IResetPasswordCallback(){
    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "找回密码成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```