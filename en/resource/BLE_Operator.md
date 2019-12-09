# Device operation
1、Operating device includes , status query, connection, and sending command, modifying name and unbinding , etc.
2、After successful activator, deviceBean will be obtained, and devId can be obtained. Subsequent operations are mainly based on devId.

### 1.Judging device status
* Query in a common way Consistent with wifi
```java
TuyaHomeSdk.getDataInstance().getDeviceBean(devId).getIsOnline();
```
* Query whether the device's local Bluetooth is connected
```java
TuyaHomeSdk.getBleManager().isBleLocalOnline(devId);
```

### 2.Connect the device
If the device is offline, you can call the connection method to connect the device.
```java
 List<String> devIdList = new ArrayList<>();
 devIdList.add(devId1); //device1 devId
 devIdList.add(devId2); //device2 devId

 String ids = JSONObject.toJSONString(devIdList);
     
 TuyaHomeSdk.getBleManager().addScanLinkTaskIds(ids);
```

### 3. Device Control
Consistent with wifi device operation
  * [Device Control](./resource/Device.md)
  * [Function Points of Device](./resource/Device.md#function-points-of-device)
  * [Device Control](./resource/Device.md#device-control)
  * [Device Renaming](./resource/Device.md#device-renaming)
  * [Remove Device](./resource/Device.md#remove-device)