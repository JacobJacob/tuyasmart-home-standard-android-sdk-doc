### 移除分享

#### (1)删除共享关系

##### 【描述】

分享者通过memberId 删除与这个关系用户的所有共享关系（用户维度删除）

##### 【方法调用】

```java
@param memberId 用户成员Id 从SharedUserInfoBean中获取
void removeUserShare(long memberId, IResultCallback callback);
```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().removeUserShare(memberId, new IResultCallback() {
    @Override
    public void onError(String code, String error) {

    }

    @Override
    public void onSuccess() {

    }
})
```

#### (2)删除收到的共享关系

##### 【描述】

被分享者通过memberId 删除与这个关系用户的所有共享关系（用户维度删除）

##### 【方法调用】

```java
@param memberId 用户成员Id 从SharedUserInfoBean中获取
void removeReceivedUserShare(long memberId, IResultCallback callback);
```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().removeReceivedUserShare(memberId, new IResultCallback() {
    @Override
    public void onError(String code, String error) {

    }

    @Override
    public void onSuccess() {

    }
})
```

#### (3)删除某个设备共享

##### 【描述】

分享者删除指定关系用户下的某个共享的设备

##### 【方法调用】

```java
@param devId 设备Id
@param memberId 用户成员Id 从SharedUserInfoBean中获取
void disableDevShare(String devId, long memberId, IResultCallback callback);
```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().disableDevShare(devId, memberId, new IResultCallback() {
    @Override
    public void onError(String code, String error) {

    }

    @Override
    public void onSuccess() {

    }
})
```

#### (4)移除收到的分享设备

##### 【描述】

被分享者删除某个共享的设备

##### 【方法调用】

```java
@param devId 设备Id
void removeReceivedDevShare(String devId, IResultCallback callback);
```

##### 【代码范例】

```java
TuyaHomeSdk.getDeviceShareInstance().removeReceivedDevShare(devId,new IResultCallback() {
    @Override
    public void onError(String code, String error) {

    }

    @Override
    public void onSuccess() {

    }
})
```