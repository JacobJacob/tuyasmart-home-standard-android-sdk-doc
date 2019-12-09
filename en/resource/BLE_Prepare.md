## Permission requirements

1、Requires Bluetooth permissions

```java
	  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    
```
2、Preparation before use API
  * The APP developer needs to check whether the above permission is allowed by the user before using Bluetooth connection or scanning operation.
   * Check Bluetooth status and turn on Bluetooth.


