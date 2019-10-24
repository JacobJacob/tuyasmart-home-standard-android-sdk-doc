### 添加分享

#### (1)添加多个设备共享（追加）

##### 【描述】

分享多个设备给指定用户，会将要分享的设备追加到指定用户的所有分享中

##### 【方法调用】

```java
@param homeId		分享者家庭id
@param countryCode   手机区号码,例如中国是“86”
@param phoneNumber   手机号码
@param devIds 		 分享的设备id列表

TuyaHomeSdk.getDeviceShareInstance().addShareWithHomeId(long homeId,String countryCode, String userAccount, List<String> devIds, ITuyaResultCallback<SharedUserInfoBean> callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().addShareWithHomeId(homeId, countryCode, userAccount, devIds, new ITuyaResultCallback<SharedUserInfoBean>() {
    @Override
    public void onSuccess(SharedUserInfoBean bean) {
    }

    @Override
    public void onError(String errorMsg, String errorCode) {
    }
        });
```

#### (2)批量添加设备共享

##### 【描述】

批量添加设备共享

```java
/**
 * 批量添加设备共享
 * @param memberId 分享目标用户id
 * @param devIds 设备id列表
 * @param callback
 */
void addShareWithMemberId(long memberId,List<String> devIds,IResultCallback callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().addShareWithMemberId (memberId, devIds, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess() {

    }
});
```

#### (3)单个设备取消共享

##### 【描述】

通过用户关系id取消单个设备分享

##### 【方法调用】

```java
/**
 * 用户下的设备分享关闭
 *
 * @param devId
 * @param memberId
 * @param callback
 */
void disableDevShare(String devId, long memberId, IResultCallback callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().disableDevShare (devId, memberId, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess() {

    }
});
```

#### (4)单个设备开启分享

##### 【描述】

通过用户关系id开启单个设备分享

##### 【方法调用】

```java
/**
 * 用户下的设备分享开启
 *
 * @param devId
 * @param memberId
 * @param callback
 */
void enableDevShare(String devId, long memberId, IResultCallback callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().enableDevShare (devId, memberId, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess() {

    }
});
```

### 查询分享

#### (1)查询主动分享的关系列表

##### 【描述】

分享者获取主动分享的关系列表(分享给其他用户的用户信息列表)

##### 【方法调用】

```java
void queryUserShareList(long homeId, ITuyaResultCallback<List<SharedUserInfoBean>> callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().queryUserShareList(homeId,new ITuyaResultCallback<List<SharedUserInfoBean>>() {
            @Override
            public void onError(String errorMsg, String errorCode) {
            }

            @Override
            public void onSuccess(List<SharedUserInfoBean> list) {
                }
            }
        });
```

#### (2)查询收到分享关系列表

##### 【描述】

被分享者获取收到的分享关系列表

##### 【方法调用】

```java
void queryShareReceivedUserList(ITuyaResultCallback<List<SharedUserInfoBean>> callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().queryShareReceivedUserList(new ITuyaResultCallback<List<SharedUserInfoBean>>() {
            @Override
            public void onError(String errorMsg, String errorCode) {
       
            }

            @Override
            public void onSuccess(List<SharedUserInfoBean> list) {
         
                }
            }
        });

```

#### (3)查询指定设备的分享用户列表

##### 【描述】

分享者获取某个设备的共享用户列表

##### 【方法调用】

```java
@param devId 设备Id
void queryDevShareUserList(String devId, ITuyaResultCallback<List<SharedUserInfoBean>> callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().queryDevShareUserList(devId, new ITuyaResultCallback<List<SharedUserInfoBean>>() {
            @Override
            public void onError(String errorMsg, String errorCode) {
   
            }

            @Override
            public void onSuccess(List<SharedUserInfoBean> list) {
          
                }
            }
        });
```

#### (4)查询指定设备是谁共享的

##### 【描述】

被分享者查找指定设备是谁共享过来的

##### 【方法调用】

```java
@param devId 设备Id
void queryShareDevFromInfo(String devId, ITuyaResultCallback<SharedUserInfoBean> callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().queryShareDevFromInfo(devId, new ITuyaResultCallback<SharedUserInfoBean>() {
            @Override
            public void onSuccess(SharedUserInfoBean result) {
                
            }

            @Override
            public void onError(String errorCode, String errorMessage) {

            }
        });
```

#### (5)查询分享到指定用户的共享关系

##### 【描述】

分享者 通过memberId 获取分享给这个关系用户的所有共享设备信息

##### 【方法调用】

```

@param memberId 用户成员Id 从SharedUserInfoBean中获取
void getUserShareInfo(long memberId, ITuyaResultCallback<ShareSentUserDetailBean> callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().getUserShareInfo(memberId, new ITuyaResultCallback<ShareSentUserDetailBean>() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess(ShareSentUserDetailBean result) {

    }
})
```

#### (6)查询收到指定用户共享的信息

##### 【描述】

被分享者 通过memberId 获取收到这个关系用户的所有共享设备信息

##### 【方法调用】

```java
@param memberId 用户成员Id 从SharedUserInfoBean中获取
void getReceivedShareInfo(long memberId, ITuyaResultCallback<ShareReceivedUserDetailBean> callback);

```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().getReceivedShareInfo(memberId, new ITuyaResultCallback<ShareReceivedUserDetailBean>() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess(ShareReceivedUserDetailBean result) {

    }
});
```