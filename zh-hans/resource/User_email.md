# 用户邮箱账号体系
涂鸦智能提供邮箱密码登陆体系。
## 一、用户邮箱密码注册
**接口描述**

用户邮箱密码注册。支持国内外邮箱注册。
```java
/**
* 邮箱注册获取验证码
* 注册获取邮箱验证码
* @param email
* @param callback
*/
void getRegisterEmailValidateCode(String countryCode, String email, IResultCallback callback);

/**
* 邮箱密码注册
* @param countryCode 国家区号
* @param email       邮箱账户
* @param passwd      登陆密码
* @param code        验证码
* @param callback    邮箱注册回调接口
*/
TuyaHomeSdk.getUserInstance().registerAccountWithEmail(final String countryCode, final String email, final String passwd, final String code, final IRegisterCallback callback);

```
**代码范例**

```java
//注册获取邮箱验证码
TuyaHomeSdk.getUserInstance().getRegisterEmailValidateCode("86","123456@123.com",new IResultCallback() {
    @Override
    public void onError(String code, String error) {
    }

    @Override
    public void onSuccess() {
    }
} );

//邮箱密码注册
TuyaHomeSdk.getUserInstance().registerAccountWithEmail("86", "123456@123.com","123456","5723", new IRegisterCallback() {
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
> 注意事项   账户一旦注册到一个国家，目前数据无法迁移其他国家。

## 二、用户邮箱密码登陆
**接口描述**

用户邮箱密码登陆

```java
/** 
* 邮箱密码登陆
* @param email  邮箱账户
* @param passwd 登陆密码
*/
TuyaHomeSdk.getUserInstance().loginWithEmail(String countryCode, String email, String passwd, final ILoginCallback callback);
```
**代码范例**

```java
//邮箱密码登陆
TuyaHomeSdk.getUserInstance().loginWithEmail("86", "123456@123.com", "123123", new ILoginCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "登录成功，用户名：").show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```
## 三、 用户邮箱重置密码
**接口描述**

用户邮箱重置密码
```java
/**
* 邮箱找回密码，获取验证码
* @param countryCode 国家区号
* @param email       邮箱账户
* @param callback    获取验证码回调接口
*/
TuyaHomeSdk.getUserInstance().getEmailValidateCode(String countryCode, final String email, final IValidateCallback callback);

/**
* 邮箱重置密码
* @countryCode 国家区号
* @param email     用户账户
* @param emailCode 邮箱验证码
* @param passwd    新密码
* @param callback  重置密码回调接口
*/
TuyaHomeSdk.getUserInstance().resetEmailPassword(String countryCode, final String email, final String emailCode, final String passwd, final IResetPasswordCallback callback);
```
**代码范例**

```java
//获取邮箱验证码
TuyaHomeSdk.getUserInstance().getEmailValidateCode("86", "123456@123.com", new IValidateCallback() {
    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "获取验证码成功", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
//重置密码
TuyaHomeSdk.getUserInstance().resetEmailPassword("86", "123456@123.com", "123123"，"a12345", new IResetPasswordCallback() {
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