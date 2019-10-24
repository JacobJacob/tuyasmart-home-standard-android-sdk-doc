### 修改备注名

#### (1) 分享者修改被分享者的备注名

##### 【描述】

分享者修改被分享者的备注名， 你分享给其他人设备，你可以修改这个人的备注名。

##### 【代码调用】

```java
* @param memberId     用户成员Id 从SharedUserInfoBean中获取
* @param name   			  要修改的备注名
void renameShareNickname(long memberId, String name, IResultCallback callback);
```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().renameShareNickname(memberId, name, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess() {

    }
})
```

#### (2) 被分享者修改分享者的备注名

##### 【描述】

被分享者修改分享人的备注名。你收到了其他人的分享，你可修改这个人的备注名。

##### 【方法调用】

```java
* @param memberId     用户成员Id 从SharedUserInfoBean中获取
* @param name    		 要修改的备注名 
void renameReceivedShareNickname(long memberId, String name, IResultCallback callback);
```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().renameReceivedShareNickname(memberId, name, new IResultCallback() {
    @OverrideTuyaHomeSdk.getDeviceShareInstance
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess() {

    }
})
```
