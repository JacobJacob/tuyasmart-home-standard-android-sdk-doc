## Mesh 设备

### Mesh设备判断方法
##### 【代码范例】

```java
DeviceBean deviceBean=TuyaHomeSdk.getDataInstance().getDeviceBean(mDevId);
if(deviceBean.isBleMesh()){
    L.d(TAG, "This device is mesh device");
 }else{
 
}
```
###  子设备重命名
##### 【方法调用】
```java
* @param devId    	 设备Id
* @param name		  重命名名称
* @param callback	  回调
public void renameMeshSubDev(String devId, String name, IResultCallback callback);

```

##### 【代码范例】
```java
 mTuyaBlueMesh.renameMeshSubDev(devBean.getDevId(),"设备名称", new IResultCallback() {
            @Override
            public void onError(String code, String errorMsg) {
            		Toast.makeText(mContext, "重命名失败"+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "重命名成功", Toast.LENGTH_LONG).show();
            }
        });
```

###  子设备移除
#####  【方法调用】
```java
* @param devId    	 设备Id
* @param pcc  		 设备大小类
* @param callback	  回调
public void removeMeshSubDev(String devId, IResultCallback callback) ;

```
#####  【代码范例】
```java
mTuyaBlueMesh.removeMeshSubDev(devBean.getDevId(), new IResultCallback() {
            @Override
            public void onError(String code, String errorMsg) {
            		Toast.makeText(mContext, "子设备移除失败 "+ errorMsg, Toast.LENGTH_LONG).show();
    
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "子设备移除成功", Toast.LENGTH_LONG).show();
            }
        });
```

### 单个子设备信息查询
##### 【说明】
云端获取到的dp点数据可能不是当前设备实时的数据，可以通过该命令去查询设备的当前数据值，结果通过IMeshDevListener的onDpUpdate方法返回

#####  【方法调用】
```java
* @param pcc  		 设备大小类
* @param nodeId    	 设备nodeId
* @param callback	  回调
public void querySubDevStatusByLocal(String pcc, final String nodeId, final IResultCallback callback);

```

#####  【代码范例】
```java
 mTuyaBlueMeshDevice.querySubDevStatusByLocal(devBean.getCategory(), devBean.getNodeId(), new IResultCallback() {
            @Override
            public void onError(String code, String errorMsg) {
            		Toast.makeText(mContext, "查询失败 "+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "查询成功 ", Toast.LENGTH_LONG).show();
            }
        });
```