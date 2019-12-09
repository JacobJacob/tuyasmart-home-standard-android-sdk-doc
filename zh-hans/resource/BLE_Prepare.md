## 权限要求

1、需要蓝牙所具备的权限

```java
	  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    
```
2、使用准备
  *  上述权限申请后，APP在使用蓝牙连接或者扫描操作前 需要检查上述权限是否被用户所允许。
  *  检查蓝牙状态和开启蓝牙。

3、HomeSdk中 有蓝牙状态使用的基本功能。以下可以方便检查蓝牙操作的工具类。

* 判断手机是否支持蓝牙操作
```java
TuyaHomeSdk.getBleOperator().isBleSupported();
```
* 判断蓝牙是否开启
```java
TuyaHomeSdk.getBleOperator().isBluetoothOpened();
```
* 开启蓝牙
```
TuyaHomeSdk.getBleOperator().openBluetooth();
```
* 关闭蓝牙
```
TuyaHomeSdk.getBleOperator().closeBluetooth();
```

> 权限判断sdk中不包含 可自行处理

