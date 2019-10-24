## 消息中心

### 消息推送设置

#### 获取消息总开关的当前状态

##### 【描述】

消息推送开关为总开关，关闭状态下无法接收到 设备告警、家庭消息、通知消息 等任何消息

##### 【方法原型】

```java
   /**
     * 获取消息总开关
     * @param callback
     */
    void getPushStatus(ITuyaResultCallback<PushStatusBean> callback);
```

##### 【代码范例】

```java
 TuyaHomeSdk.getPushInstance().getPushStatus(new ITuyaResultCallback<PushStatusBean>() {
            @Override
            public void onSuccess(PushStatusBean result) {
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
            }
        });
```

#### 设置消息总开关的当前状态

##### 【描述】

消息推送开关为总开关，关闭状态下无法接收到 设备告警、家庭消息、通知消息 等任何消息

##### 【方法原型】

```java
    /**
     * 设置消息推送总开关
     * @param isOpen 是否开启
     * @param callback
     */
    void setPushStatus(boolean isOpen, ITuyaDataCallback<Boolean> callback);
```

##### 【代码范例】

```java
TuyaHomeSdk.getPushInstance().setPushStatus(open, new ITuyaDataCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
            }
        });
```


#### 根据消息类型获取消息的开关状态

##### 【描述】

根据消息类型获取当前类型消息的开关状态

##### 【方法原型】

```java
  /**
     * 根据消息类型获取推送开关状态
     *  PushType.PUSH_ALARM 告警
     *  PushType.PUSH_FAMILY 家庭
     *  PushType.PUSH_NOTIFY 通知
     * @param type 消息类型 
     * @param callback
     */
    void getPushStatusByType(PushType type, ITuyaDataCallback<Boolean> callback);
```

##### 【代码范例】

```java
    TuyaHomeSdk.getPushInstance().getPushStatusByType(type, new ITuyaDataCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
            }
        });
```

#### 根据消息类型设置消息的开关状态

##### 【描述】

根据消息类型获取当前类型消息的开关状态

##### 【方法原型】

```java
  /**
     * 根据消息类型获取推送开关状态
     *  PushType.PUSH_ALARM 告警
     *  PushType.PUSH_FAMILY 家庭
     *  PushType.PUSH_NOTIFY 通知
     * @param type 消息类型 
     * @param isOpen 是否开启
     * @param callback
     */
    void setPushStatusByType(PushType type, isOpen, ITuyaDataCallback<Boolean> callback);
```

##### 【代码范例】

```java
        TuyaHomeSdk.getPushInstance().setPushStatusByType(pushType, checked, new ITuyaDataCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
            }
        });
```