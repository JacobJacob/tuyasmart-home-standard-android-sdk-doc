## 控制
ITuyaBlueMeshDevice类提供了对Mesh设备的操作

###  控制指令下发
#####  【指令格式】
发送控制指令按照以下格式： {"(dpId)":"(dpValue)"}  

##### 【方法调用】
```java
//控制设备
* @param nodeId   子设备本地编号
* @param pcc		  设备产品大小类
* @param dps		  控制指令
* @param callback  回调
void publishDps(String nodeId, String pcc, String dps, IResultCallback callback);


//控制群组
* @param localId   群组本地编号
* @param pcc		  产品大小类
* @param dps		  控制指令
* @param callback  回调
void multicastDps(String localId, String pcc, String dps, IResultCallback callback)
```

##### 【代码范例】
```java
//设备控制
String dps = {"1":false};
ITuyaBlueMeshDevice mTuyaSigMeshDevice=TuyaHomeSdk.newSigMeshDeviceInstance("meshId");
mTuyaSigMeshDevice.publishDps(devBean.getNodeId(), devBean.getCategory(), dps, new IResultCallback() {
            @Override
            public void onError(String s, String s1) {
            		Toast.makeText(mContext, "发送失败"+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "发送成功", Toast.LENGTH_LONG).show();
            }
        });
        
        
//群组控制        
String dps = {"1":false};
ITuyaBlueMeshDevice mTuyaSigMeshDevice= TuyaHomeSdk.newSigMeshDeviceInstance("meshId");
mTuyaSigMeshDevice.multicastDps(groupBean.getLocalId(), devBean.getCategory(), dps, new IResultCallback() {
            @Override
            public void onError(String errorCode, String errorMsg) {
            		Toast.makeText(mContext, "发送失败"+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "发送成功", Toast.LENGTH_LONG).show();
            }
        });

```
###  数据监听
##### 【描述】
mesh网内相关信息（dp数据、状态变更、设备名称、设备移除）会实时同步到 IMeshDevListener 

##### 【实现回调】
```java
mTuyaSigMeshDevice.registerMeshDevListener(new IMeshDevListener() {

		/**
         * 数据更新
         * @param nodeId    更新设备的nodeId
         * @param dps       dp数据
         * @param isFromLocal   数据来源 true表示从本地蓝牙  false表示从云端
         */
            @Override
            public void onDpUpdate(String nodeId, String dps,boolean isFromLocal) {
                //可以通过node来找到相对应的DeviceBean
                DeviceBean deviceBean = mTuyaBlueMeshDevice.getMeshSubDevBeanByNodeId(nodeId);
            }

		 /**
         * 设备状态的上报
         * @param online    在线设备列表
         * @param offline   离线设备列表
         * @param gwId      状态的来源 gwId不为空表示来自云端（gwId是上报数据的网关Id）   为空则表示来自本地蓝牙
         */
            @Override
            public void onStatusChanged(List<String> online, List<String> offline,String gwId) {

            }
            
        /**
         * 网络状态变化
         * @param devId
         * @param status
         */
            @Override
            public void onNetworkStatusChanged(String devId, boolean status) {

            }
            

        /**
         * raw类型数据上报
         * @param bytes
         */
            @Override
            public void onRawDataUpdate(byte[] bytes) {

            }
            

        /**
         * 设备信息变更（名称等）
         * @param bytes
         */            
            @Override
            public void onDevInfoUpdate(String devId) {

            }
            
        /**
         * 设备移除
         * @param devId
         */
            @Override
            public void onRemoved(String devId) {

            }
        });
```



