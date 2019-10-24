## 管理

### 创建SigMesh

由云端分配MeshId，一个家庭下只能创建一个Mesh

##### 【方法调用】
```
* @param callback   回调
void createSigMesh(ITuyaResultCallback<SigMeshBean> callback);
```

##### 【代码范例】
``` java
TuyaHomeSdk.newHomeInstance("homeId").createSigMesh(new ITuyaResultCallback<SigMeshBean>() {
    @Override
    public void onError(String errorCode, String errorMsg) {
        Toast.makeText(mContext, "创建Simesh失败  "+ errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(SigMeshBean sigMeshBean) {
        Toast.makeText(mContext, "创建mesh成功", Toast.LENGTH_LONG).show();
    }
});
```

### 删除SigMesh

##### 【代码范例】
```
TuyaHomeSdk.newSigMeshDeviceInstance(meshId).removeMesh(new IResultCallback() {
    @Override
    public void onError(String errorCode, String errorMsg) {
	    Toast.makeText(mContext, "删除mesh失败  "+ errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
	    Toast.makeText(mContext, "删除mesh成功", Toast.LENGTH_LONG).show();
    }
});

```

### 从缓存中获取Mesh数据
##### 【方法调用】
```
TuyaHomeSdk.getSigMeshInstance().getSigMeshList()
```
##### 【代码范例】
```
ITuyaHome mTuyaHome = TuyaHomeSdk.newHomeInstance("homeId");
if (mTuyaHome.getHomeBean() != null){
	List<SigMeshBean> meshList = TuyaHomeSdk.getSigMeshInstance().getSigMeshList()
}
```

### 从缓存中获取Mesh下的子设备数据
##### 【代码范例】
```
List<DeviceBean> meshSubDevList = TuyaHomeSdk.newSigMeshDeviceInstance("meshId").getMeshSubDevList();

```


### Mesh初始化和销毁
建议在家庭切换的时候 销毁当前Mesh  然后重新初始化家庭中的Mesh

##### 【方法调用】
```
//初始化mesh
TuyaHomeSdk.getTuyaSigMeshClient().initMesh(String meshId);

//销毁当前mesh
TuyaHomeSdk.getTuyaSigMeshClient().destroyMesh();
```


### Mesh子设备连接和断开
##### 【描述】
ITuyaBlueMeshClient 提供 开始连接、断开连接、开启扫描、停止扫描

##### 【方法调用】
```
// 开启连接
TuyaHomeSdk.getTuyaSigMeshClient().startClient(mSigMeshBean);
// 开启连接 指定扫描的时间 
TuyaHomeSdk.getTuyaSigMeshClient().startClient(mSigMeshBean,searchTime);

//断开连接
TuyaHomeSdk.getTuyaSigMeshClient().stopClient();

//开启扫描
TuyaHomeSdk.getTuyaSigMeshClient().startSearch()

//停止扫描
TuyaHomeSdk.getTuyaSigMeshClient().stopSearch();

```

##### 【注意事项】
###### startClient(mSigMeshBean)开启连接后，会在后台不断的去扫描周围可连接设备，直到连接成功为止。
###### startClient(mSigMeshBean,searchTime)开启连接后, searchTime 时间内没有扫描到设备就停止扫描
###### 后台一直扫描会消耗资源，可以通过通过开启扫描和停止扫描来控制后台的扫描
###### 当未startClient()时候，调用startSearch()和stopSearch()是没有效果的
###### 当已经连接到mesh网的时候，调用startSearch和stopSearch是没有效果的