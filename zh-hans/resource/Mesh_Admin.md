# Mesh 管理
### 创建Mesh

##### 【方法调用】
```java
* @param meshName   mesh的名称（不超过16字节）
* @param callback   回调
void createBlueMesh(String meshName, ITuyaResultCallback<BlueMeshBean> callback);
```

##### 【代码范例】
``` java
TuyaHomeSdk.newHomeInstance("homeId").createBlueMesh("meshName", new ITuyaResultCallback<BlueMeshBean>() {
    @Override
    public void onError(String errorCode, String errorMsg) {
        Toast.makeText(mContext, "创建mesh失败  "+ errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override	
    public void onSuccess(BlueMeshBean blueMeshBean) {
        Toast.makeText(mContext, "创建mesh成功", Toast.LENGTH_LONG).show();
    }
});
```

### 删除Mesh

##### 【代码范例】
```java
TuyaHomeSdk.newBlueMeshDeviceInstance(meshId).removeMesh(new IResultCallback() {
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
```java
TuyaHomeSdk.newHomeInstance("homeId").getHomeBean().getMeshList()
```
##### 【代码范例】
```java
ITuyaHome mTuyaHome = TuyaHomeSdk.newHomeInstance("homeId");
if (mTuyaHome.getHomeBean() != null){
	List<BlueMeshBean> meshList = mTuyaHome.getHomeBean().getMeshList();
	BlueMeshBean meshBean= meshList.get(0);
}            
```

### 从缓存中获取Mesh下的子设备数据
##### 【代码范例】
```java
List<DeviceBean> meshSubDevList = TuyaHomeSdk.newBlueMeshDeviceInstance("meshId").getMeshSubDevList();
    
```


### Mesh初始化和销毁
建议在家庭切换的时候 销毁当前mesh  然后重新初始化家庭中的mesh

##### 【方法调用】
```java
//初始化mesh
TuyaHomeSdk.getTuyaBlueMeshClient().initMesh(String meshId);       

//销毁当前mesh
TuyaHomeSdk.getTuyaBlueMeshClient().destroyMesh();       
```


### Mesh子设备连接和断开
##### 【描述】
ITuyaBlueMeshClient 提供 开始连接、断开连接、开启扫描、停止扫描

##### 【方法调用】
```java
// 开启连接
TuyaHomeSdk.getTuyaBlueMeshClient().startClient(mBlueMeshBean);

//断开连接
TuyaHomeSdk.getTuyaBlueMeshClient().stopClient();

//开启扫描
TuyaHomeSdk.getTuyaBlueMeshClient().startSearch()

//停止扫描
TuyaHomeSdk.getTuyaBlueMeshClient().stopSearch();

```

##### 【注意事项】 
###### 开启连接后，会在后台不断的去扫描周围可连接设备，直到连接成功为止。
###### 后台一直扫描会消耗资源，可以通过通过开启扫描和停止扫描来控制后台的扫描
###### 当未startClient()时候，调用startSearch()和stopSearch()是没有效果的
###### 当已经连接到mesh网的时候，调用startSearch和stopSearch是没有效果的