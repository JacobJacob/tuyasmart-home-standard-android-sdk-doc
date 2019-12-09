# Activator Device
Two steps are required to activate the device. The first step is to scan the device. The second step is to activate the device.

### 1.Scan the device
> Prior to scanning, you need to check the permissions of the scan first. For details, see  [BLE Prepare](./resource/BLE_Prepare.md) 
```java
/**
  *SCAN_TIME_OUT :Scan timeout time, 60s is recommended.
  *ScanType.SINGLE:scan type
  *response:callback
  */
  TuyaHomeSdk.getBleOperator().startLeScan(
  SCAN_TIME_OUT, ScanType.SINGLE, new TyBleScanResponse() {
            @Override
            public void onResult(ScanDeviceBean bean) {
                
            }
        });

```

### 1.1 Callback Description

|Attributes|Description|
|:---|---|
|id|Scan ID usually consists of uuid, which can uniquely distinguish the device|
|name|device ble Name|
|providerName|SingleBleProvider|
|data|raw data|
|configType|config_type_single：normal ble device ;config_type_wifi:Dual-mode device|
|productId|productId|
|uuid|Device unique code|
|mac|Device mac|
|isbind|true:bound false:not bind|
|flag|bit0:Whether to support 5G WiFi|

### 1.2 Stop Scan
```java
 TuyaHomeSdk.getBleOperator().stopLeScan();
 When the device is start to activator, it is recommended to stop scanning.
```

### 2.Normal BLE device Activator
```java
  /**
         * homeId:current Home's Id
         * uuid:Device unique code from scan
         * params:not need for normal ble device
         * configListener:callback
         */
 TuyaHomeSdk.getBleManager().startBleConfig(FamilyManager.getInstance().getCurrentHomeId(), uuid, null, new ITuyaBleConfigListener() {
            @Override
            public void onSuccess(DeviceBean bean) {
            
            }

            @Override
            public void onFail(String code, String msg, Object handle) {

            }
        });
```
### 2.1 cancel activator
```java
/**
 *  uuid: Device unique code from scan
 */
TuyaHomeSdk.getBleManager().stopBleConfig(uuid);
```

### 3.BLE+WIFI Dual-mode Device Activator
```java
    /**
     * homeId:current Home's Id
     * uuid:Device unique code from scan
     * param:
     *          Map<String, Object> param = new HashMap<>();
     *          param.put("ssid", ssid); //wifi ssid
     *          param.put("password", pwd); //wifi pwd
     *          param.put("token", token); // use token，The method of obtaining the token is the same as that of the wifi device activator.
     * configListener:callback
     */
 TuyaHomeSdk.getBleManager().startBleConfig(FamilyManager.getInstance().getCurrentHomeId(), uuid, param, new ITuyaBleConfigListener() {
            @Override
            public void onSuccess(DeviceBean bean) {
              
            }

            @Override
            public void onFail(String code, String msg, Object handle) {

            }
        });
```
### 3.1 Cancel BLE+WIFI Dual-mode Device Activator
```java
/**
 *   uuid:Device unique code from scan
 */
TuyaHomeSdk.getBleManager().stopBleConfig(uuid);
```