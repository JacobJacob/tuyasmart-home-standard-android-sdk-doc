# 通用接口

服务端api调用功能通用接口在类`ITuyaSmartRequest`中

有如下接口可供调用

```java
  /**
 * 适用于带session的接口请求
 * @param apiName
 * @param version
 * @param postData
 * @param object
 * @param callback
 * @param <T>
 */
<T> void requestWithApiName(String apiName, String version, Map<String, Object> postData, Class<T> object, final ITuyaDataCallback<T> callback);

/**
 * 适用于不带session的接口请求
 * @param apiName api名
 * @param version api版本号
 * @param postData post发送的数据
 * @param object 服务端返回的数据对象
 * @param callback 回调
 * @param <T>
 */
<T> void requestWithApiNameWithoutSession(String apiName, String version, Map<String, Object> postData, Class<T> object, final ITuyaDataCallback<T> callback);

```

调用方式`TuyaHomeSdk.getRequestInstance()`

## 示例

以如下接口作为示例：

| 接口说明| apiName             | version | postData |
| ------| ------------------- | ------- | -------- |
|获取国家列表| tuya.m.country.list | 1.0 | 无 |

```java
Map<String, Object> postData = null;

TuyaHomeSdk.getRequestInstance().requestWithApiNameWithoutSession("tuya.m.country.list", "1.0", postData, String.class, new ITuyaDataCallback<String>() {
    @Override
    public void onSuccess(String result) {
        Log.i("TAG" , result);
    }

    @Override
    public void onError(String errorCode, String errorMessage) {
        Log.i("TAG" , errorCode);
    }
});
```

