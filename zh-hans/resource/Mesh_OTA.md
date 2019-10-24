## 固件升级
子设备升级分为2种，一种是普通设备升级，一种是mesh网关升级

### 子设备升级信息获取
```java
TuyaHomeSdk.getMeshInstance().requestUpgradeInfo(mDevID, new IRequestUpgradeInfoCallback() {
    @Override
    public void onSuccess(ArrayList<BLEUpgradeBean> bleUpgradeBeans) {
    	for (BLEUpgradeBean bean : bleUpgradeBeans) {
             if (bean.getUpgradeStatus()==1) {
				if (bean.getType() == 0) {
					//wifi模块需要升级
				}else if(bean.getType == 1){
					//蓝牙模块需要升级
					//需要手动下载ota固件
					url=bean.getUrl()
				}
             }else{
                 //无需更新
             }
		 }
    }

    @Override
    public void onError(String errorCode, String errorMsg) {
    }
});
```

### 普通子设备升级
##### 【初始化参数配置】
```java
TuyaBlueMeshOtaBuilder build = new TuyaBlueMeshOtaBuilder()
            .setData(byte[] data)
            .setMeshId(String meshId)
            .setProductKey(String productKey)
            .setNodeId(String nodeId)      
            .setDevId(String devId)
            .setVersion(String version)
            .setTuyaBlueMeshActivatorListener(MeshUpgradeListener mListener)
            .bulid();

```
##### 【参数说明】
###### 【入参】
```java
* @param data     				待升级固件的字节流
* @param meshId   				设备MeshId
* @param productKey    		   设备产品Id
* @param mNodeId  				设备NodeId
* @param devId 					设备Id 
* @param version     			待升级固件的版本号
```

###### 【出参】
MeshUpgradeListener listener 升级回调接口

##### 【代码范例】
```java
private MeshUpgradeListener mListener = new MeshUpgradeListener() {
        @Override
        public void onUpgrade(int percent) {
        	//升级进度
        }

        @Override
        public void onSendSuccess() {
        	//固件数据发送成功
        }

        @Override
        public void onUpgradeSuccess() {
        	//升级成功
        	 mMeshOta.onDestroy();
        }

        @Override
        public void onFail(String errorCode, String errorMsg) {
        	//升级失败
        	 mMeshOta.onDestroy();
        }
    };
//获取指定文件的字节流
byte data[] = getFromFile(path);

TuyaBlueMeshOtaBuilder build = new TuyaBlueMeshOtaBuilder()
        .setData(data)
        .setMeshId(mDevBean.getMeshId())
        .setProductKey(mDevBean.getProductId())
        .setNodeId(mDevBean.getNodeId())
        .setDevId(mDevID)
        .setVersion("version")
        .setTuyaBlueMeshActivatorListener(mListener)
        .bulid();
ITuyaBlueMeshOta  = TuyaHomeSdk.newMeshOtaManagerInstance(build);

//开始升级
mMeshOta.startOta();
```

### 网关设备升级
网关设备升级分为2步 1、升级蓝牙模块（操作同 普通子设备升级） 2、升级wifi模块

##### 【代码范例】
```java
private IOtaListener iOtaListener = new IOtaListener() {
        @Override
        public void onSuccess(int otaType) {
            //升级成功
  		
  		}

        @Override
        public void onFailure(int otaType, String code, String error) {
         	//升级失败

        }

        @Override
        public void onProgress(int otaType, final int progress) {
           //升级进度
                      
        }
    };

ITuyaOta iTuyaOta = TuyaHomeSdk.newOTAInstance(mDevID);
iTuyaOta.setOtaListener(mOtaListener);
//开始升级
iTuyaOta.startOta();

//销毁升级
iTuyaOta.onDestroy();

```