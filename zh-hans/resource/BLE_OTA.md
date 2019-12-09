# 设备OTA
蓝牙设备OTA 升级

### OTA升级
```java

    /**
     *
     * @param uuid 设备的uuid
     * @param type 升级类型 0 为BLE固件升级 ，1为 MCU升级
     * @param version 需要升级的版本
     * @param binPackagePath 固件文件本地地址
     * @param listener //升级回调
     */
   TuyaHomeSdk.getBleManager().startBleOta(uuid, type, version, binPackagePath, new  OnBleUpgradeListener() {
        @Override
        public void onUpgrade(int percent) {
            // 升级进度 百分比
        }

        @Override
        public void onSuccess() {
             //升级成功
        }

        @Override
        public void onFail(String errorCode, String errorMsg) {
             //升级失败
        }
    });

```