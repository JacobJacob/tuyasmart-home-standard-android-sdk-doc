#### 设备重命名

##### 【描述】

设备重命名，支持多设备同步。

##### 【方法调用】

```java
//重命名
mDevice.renameDevice(String name,IResultCallback callback);
```

##### 【代码范例】

```java
mDevice.renameDevice("设备名称", new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        //重命名失败
    }
    @Override
    public void onSuccess() {
        //重命名成功
    }
});
```

成功之后 ，`IDevListener.onDevInfoUpdate()` 会收到通知。

调用以下方法获取最新数据，然后刷新设备信息即可。

```java
TuyaHomeSdk.getDataInstance().getDeviceBean(String devId);
```

#### 移除设备

##### 【描述】

用于从用户设备列表中移除设备

##### 【方法调用】

```java
/**
 * 移除设备
 *
 * @param callback
 */
void removeDevice(IResultCallback callback);
```

##### 【代码范例】

```java
mDevice.removeDevice(new IResultCallback() {
    @Override
    public void onError(String errorCode, String errorMsg) {
    }

    @Override
    public void onSuccess() {
    }
});
```

#### 查询WiFi信号强度

##### 【描述】

查询当前设备WiFi的信号强度

##### 【方法调用】

```java

void requestWifiSignal(WifiSignalListener listener);
```

##### 【代码范例】

```java
mDevice.requestWifiSignal(new WifiSignalListener() {
     
     @Override
     public void onSignalValueFind(String signal) {
      
     }
     
     @Override
     public void onError(String errorCode, String errorMsg) {

     }
 });;
```

#### DeviceBean数据模型


| 字段|类型|描述|
| :--:| :--:| :--:|
| iconUrl |String|图标地址|
| isOnline |Boolean|设备是否在线（局域网或者云端在线）|
| name |String|设备名称|
| schema |String|设备控制数据点的类型信息|
| productId |String|产品ID，同一个产品ID，Schema信息一致|
| supportGroup |Boolean|设备是否支持群组，如果不支持请到开放平台开启此项功能|
| time | Long |设备激活时间|
| pv | String |网关协议版本|
| bv | String |网关通用固件版本|
| schemaMap | Map |Schema缓存数据|
| dps | Map |          设备当前数据信息。key 是 dpId ，value 是值          |
| isShare | boolean |是否是分享设备|
| virtual|boolean |是否是虚拟设备|
| lon、lat |String| 用来标示经纬度信息，需要用户使用sdk前，调用TuyaSdk.setLatAndLong 设置经纬度信息 |
| isLocalOnline|boolean|设备局域网在线状态|
| nodeId |String|用于网关和子设备类型的设备，属于子设备的一个属性，标识其短地址ID，一个网关下面的nodeId都唯一的|
| timezoneId |String|设备时区|
| category | String |设备类型|
| meshId |String|用于网关和子设备类型的设备，属于子设备的一个属性，标识其网关ID|
| devId |String|设备唯一标示id|
| isZigBeeWifi |boolean|是否是ZigBee网关设备|
| hasZigBee |boolean|hasZigBee|

