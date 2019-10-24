#### Get historical statistics of DP

##### Get historical statistics of DP such as battery statistics and other information.

#### Get all historical statistics of DP

```java
Map<String, Object> map = new HashMap<>();
map.put("devId", "your_devId");
map.put("dpId", "your_device_dpId");

TuyaHomeSdk.getRequestInstance().requestWithApiName("m.smart.dp.his.stat.get.all", "1.0", map, JSONObject.class, new ITuyaDataCallback<JSONObject>() {
    @Override
    public void onSuccess(JSONObject jsonObject) {

    }

    @Override
    public void onError(String s, String s1) {

    }
});
```

#### Get historical statistics of DP for one month

```java
Map<String, Object> map = new HashMap<>();
map.put("devId", "your_devId");
map.put("month", "201809");
map.put("dpId", "your_device_dpId");

TuyaHomeSdk.getRequestInstance().requestWithApiName("m.smart.dp.his.stat.get.month", "1.0", map, JSONObject.class, new ITuyaDataCallback<JSONObject>() {
    @Override
    public void onSuccess(JSONObject jsonObject) {
        
    }

    @Override
    public void onError(String s, String s1) {

    }
});
```