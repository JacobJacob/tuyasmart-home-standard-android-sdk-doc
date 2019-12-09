# 绑定设备
绑定设备 需要先进行扫描 扫描到设备后再进行配网。

### 1.扫描设备
> 扫描前需要对扫描所具备的权限进行优先检查，具体检查内容见  [准备](./resource/BLE_Prepare.md) 
```java
/**
  *SCAN_TIME_OUT :扫描超时时间 单位秒 推荐60s.
  *ScanType.SINGLE:扫描的设备类型 
  *response:扫描回调
  */
  TuyaHomeSdk.getBleOperator().startLeScan(
  SCAN_TIME_OUT, ScanType.SINGLE, new TyBleScanResponse() {
            @Override
            public void onResult(ScanDeviceBean bean) {
                
            }
        });

```

### 1.1 扫描回调说明

|属性|说明|
|:---|---|
|id|扫描id 通常由uuid组成，可以唯一区别设备|
|name|扫描到的蓝牙名称 一般为空|
|providerName|为SingleBleProvider只开发单点设备不需要关注该字段|
|data|原始数据|
|configType|config_type_single：单点设备;config_type_wifi:双模设备|
|productId|产品id|
|uuid|产品uuid,设备唯一码|
|mac|设备mac|
|isbind|是否被绑定|
|flag|bit0:是否支持5G,5G表明双模设备是否支持5GWifi;bit1:是否后绑定|

### 1.2 停止扫描
```java
 TuyaHomeSdk.getBleOperator().stopLeScan();
 在执行设备入网时，建议停止扫描，以防止扫描影响到配网过程。
```

### 2.单点设备入网激活
```java
  /**
         * homeId:当前家庭homeId
         * uuid:前面扫描到的设备uuid，每次进行配网前 需要先扫描，否则不能配网。
         * params:传入null 单点设备需要该参数
         * configListener:配网回调
         */
 TuyaHomeSdk.getBleManager().startBleConfig(FamilyManager.getInstance().getCurrentHomeId(), uuid, null, new ITuyaBleConfigListener() {
            @Override
            public void onSuccess(DeviceBean bean) {
              // 回调的 DeviceBean 数据说明 参见WiFI设备的配网回调。
            }

            @Override
            public void onFail(String code, String msg, Object handle) {

            }
        });
```
### 2.1 取消单点配网
```java
/**
 *  uuid 设备uuid
 */
TuyaHomeSdk.getBleManager().stopBleConfig(devUuid);
```

### 3.双模设备入网激活
```java
    /**
     * homeId:当前家庭homeId
     * uuid:前面扫描到的设备uuid，每次进行配网前 需要先扫描，否则不能配网。
     * param:
     *          Map<String, Object> param = new HashMap<>();
     *          param.put("ssid", ssid); //wifi ssid
     *          param.put("password", pwd); //wifi pwd
     *          param.put("token", token); // 用户token，获取token的方式与wifi设备配网一致，见wifi部分配网获取token
     * configListener:配网回调
     */
 TuyaHomeSdk.getBleManager().startBleConfig(FamilyManager.getInstance().getCurrentHomeId(), uuid, param, new ITuyaBleConfigListener() {
            @Override
            public void onSuccess(DeviceBean bean) {
                // 回调的 DeviceBean 数据说明 参见WiFI设备的配网回调。
            }

            @Override
            public void onFail(String code, String msg, Object handle) {

            }
        });
```
### 3.1 取消双模设备配网
```java
/**
 *  uuid 设备uuid
 */
TuyaHomeSdk.getBleManager().stopBleConfig(devUuid);
```