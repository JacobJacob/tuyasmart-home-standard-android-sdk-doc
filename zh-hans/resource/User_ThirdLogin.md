#  第三方登陆

## 一、Twitter登陆

**接口说明**

```java
/**
* @param countryCode 国家区号
* @param token       twitter授权登录获取的token
* @param secret      twitter授权登录获取的secret
*/

TuyaHomeSdk.getUserInstance().loginByTwitter(String countryCode, String token, String secret, ILoginCallback callback);
```


## 二、QQ登陆

**接口说明**

```java
/**
* @param countryCode 国家区号
* @param userId         QQ授权登录获取的userId
* @param accessToken    QQ授权登录获取的accessToken
*/

TuyaHomeSdk.getUserInstance().loginByQQ(String countryCode, String userId, String accessToken, ILoginCallback callback);
```

## 三、微信登陆

**接口说明**

```java
/**
 *  @param countryCode 国家区号
 *  @param code 微信授权登录获取的code
 */
TuyaHomeSdk.getUserInstance().loginByWechat(String countryCode, String code, ILoginCallback callback);

```

## 四、Facebook 登陆
**接口说明**


```java
/**
 *  @param countryCode 国家区号
 *  @param token facebook授权登录获取的token
 */
TuyaHomeSdk.getUserInstance().loginByFacebook(String countryCode, String token, ILoginCallback callback);
```