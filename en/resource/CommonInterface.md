##  Common interface

The server api calls the common function interface in the interface` ITuyaSmartRequest`

There are the following interfaces available for calling

```java
  /**
 * Applicable to api interface request with session
 * @param apiName: api name
 * @param version: api version
 * @param postData: Post sent data
 * @param object: Data object returned by the server
 * @param callback: callback
 * @param <T>
 */
<T> void requestWithApiName(String apiName, String version, Map<String, Object> postData, Class<T> object, final ITuyaDataCallback<T> callback);

/**
 * Applies to api interface requests without session
 * @param apiName: api name
 * @param version: api version
 * @param postData: Post sent data
 * @param object: Data object returned by the server
 * @param callback: callback
 * @param <T>
 */
<T> void requestWithApiNameWithoutSession(String apiName, String version, Map<String, Object> postData, Class<T> object, final ITuyaDataCallback<T> callback);

```

The calling method is as follows:`TuyaHomeSdk.getRequestInstance()`

## Example

Take the following interface as an example:

| Interface Description | apiName             | version | postData |
| ------| ------------------- | ------- | -------- |
|Get a list of countries| tuya.m.country.list | 1.0 | - |

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

