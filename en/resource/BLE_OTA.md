# Device OTA
Bluetooth device OTA upgrade

### OTA upgrade
```java

    /**
     *
     * @param uuid: device uuid
     * @param type:  0: firmware upgrade ，1:mcu upgrade
     * @param version:  Version to be upgraded
     * @param binPackagePath: Firmware file local path
     * @param listener //callback 
     */
   TuyaHomeSdk.getBleManager().startBleOta(uuid, type, version, binPackagePath, new  OnBleUpgradeListener() {
        @Override
        public void onUpgrade(int percent) {
            // Upgrade progress percent
        }

        @Override
        public void onSuccess() {
             //Upgrade Success
        }

        @Override
        public void onFail(String errorCode, String errorMsg) {
            //Upgrade fail
        }
    });

```