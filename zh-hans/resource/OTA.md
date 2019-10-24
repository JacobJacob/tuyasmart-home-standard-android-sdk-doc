#### 固件升级

##### 【描述】

固件升级主要用于修复设备bug和增加设备新功能。固件升级主要分两种，第一种是设备升级，第二种是MCU升级。升级的接口位于ITuyaOta中。
从SDK 2.9.1版本起支持zigbe 子设备升级。

#### 查询固件升级信息

##### 【方法调用】



```java
/**
 *	wifi、zigbee网关、摄像头等设备初始化接口
 * @param devId 设备id
 * @return
 */
ITuyaOta newOTAInstance(String devId)

/**
 * zigbee 子设备初始化接口
 * @param meshId zigbee网关id
 * @param devId zigbee子设备id
 * @param nodeId zigbee子设备mac地址（从子设备的DeviceBean获取）
 * @return
 */
ITuyaOta newOTAInstance(String meshId, String devId, String nodeId);

```

```java
//获取固件升级信息
TuyaHomeSdk.newOTAInstance(mDevId).getOtaInfo(new IGetOtaInfoCallback({
	@Override
	void onSuccess(List<UpgradeInfoBean> list){
	
	}
	@Override
	void onFailure(String code, String error);
	
});

TuyaHomeSdk.newOTAInstance("xxxmeshId","xxxdevId","xxxmac address").getOtaInfo(new IGetOtaInfoCallback({
	@Override
	void onSuccess(List<UpgradeInfoBean> list){
	
	}
	@Override
	void onFailure(String code, String error);
	
});

```


`UpgradeInfoBean`返回固件升级的信息，提供以下信息

```java
	private int upgradeStatus;//升级状态，0:无新版本 1:有新版本 2:在升级中
    private String version;//最新版本
    private String currentVersion;//当前版本
    private int timeout;//超时时间，单位：秒
    private int upgradeType;//0:app提醒升级 2-app强制升级 3-检测升级
    private int type;//0:wifi设备 1:蓝牙设备 2:GPRS设备 3:zigbee设备 9:MCU
    private String typeDesc;//模块描述
    private long lastUpgradeTime;//上次升级时间，单位：毫秒
```

##### 【代码范例】

```java
iTuyaOta.getOtaInfo(new IGetOtaInfoCallback() {
    @Override
    public void onSuccess(List<UpgradeInfoBean> list) {
        
        }
    }

    @Override
    public void onFailure(String code, String error) {
        L.e(TAG, "check error " + code + "----error=" + error);
    }
        });
```

#### 设置升级状态回调

##### 【描述】

ota之前需要注册监听，以实时获取升级状态

##### 【方法调用】

```java
//otaType 升级的设备类型，同`UpgradeInfoBean`的type字段
iTuyaOta.setOtaListener(new IOtaListener() {
    @Override
    public void onSuccess(int otaType) {
        
    }

    @Override
    public void onFailure(int otaType, String code, String error) {

    }

    @Override
    public void onProgress(int otaType, int progress) {

    }
});
```

#### 开始升级

##### 【描述】

 调用该方法开始升级,调用后注册的ota监听会把升级状态返回回来，以便开发者构建UI

##### 【方法调用】

```java
iTuyaOta.startOta();
```

#### 销毁

##### 【描述】

 离开升级页面后要销毁，回收内存。

##### 【方法调用】

```java
iTuyaOta.onDestroy();
```

#### 