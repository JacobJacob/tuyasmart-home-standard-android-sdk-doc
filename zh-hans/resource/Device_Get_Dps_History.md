#### 获取数据点的历史统计数据

获取dp点的历史统计数据，例如电量统计等信息。

##### 获取dp点所有的历史统计数据

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

##### 获取dp点某个月的历史统计数据

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