## 配网与入网

### 扫描附近待配网子设备
##### 【描述】
扫描前需要检查蓝牙和位置权限

##### 【方法调用】
```
//开启扫描
mMeshSearch.startSearch();
//停止扫描
mMeshSearch.stopSearch();

```
##### 【代码范例】
```
ITuyaBlueMeshSearchListener iTuyaBlueMeshSearchListener=new ITuyaBlueMeshSearchListener() {
    @Override
    public void onSearched(SearchDeviceBean deviceBean) {

    }

    @Override
    public void onSearchFinish() {

    }
};
// 待配网的SigMesh设备UUID是固定的
UUID[] MESH_PROVISIONING_UUID = {UUID.fromString("00001827-0000-1000-8000-00805f9b34fb")};
SearchBuilder searchBuilder = new SearchBuilder()
								.setServiceUUIDs(MESH_PROVISIONING_UUID)	//SigMesh的UUID是固定值
                .setTimeOut(100)        //扫描时长 单位秒
                .setTuyaBlueMeshSearchListener(iTuyaBlueMeshSearchListener).build();

ITuyaBlueMeshSearch mMeshSearch = TuyaHomeSdk.getTuyaBlueMeshConfig().newTuyaBlueMeshSearch(searchBuilder);

//开启扫描
mMeshSearch.startSearch();

//停止扫描
mMeshSearch.stopSearch();
```

### 子设备入网

#### 通过手机蓝牙通道入网

##### 【初始化参数配置】
```
//普通设备入网参数配置
TuyaSigMeshActivatorBuilder tuyaSigMeshActivatorBuilder = new TuyaSigMeshActivatorBuilder()
            .setSearchDeviceBeans(mSearchDeviceBeanList)
            .setSigMeshBean(sigMeshBean)
            //超时时间
            .setTimeOut(CONFIG_TIME_OUT)
            .setTuyaBlueMeshActivatorListener();
```

##### 【参数说明】
###### 【入参】
```
* @param mSearchDeviceBeans     待配网的设备集合,通过 startSearch 扫描得到的
* @param timeout    配网的超时时间设置，默认是100s（当一次性配网设备过多时，需要增加时间）
* @param sigMeshBean  SigMeshBean   
```

###### 【出参】
ITuyaBlueMeshActivatorListener listener 配网回调接口

```
//单设备配网失败回调
void onError(String mac, String errorCode, String errorMsg);

@param errorCode:
21002       invite 失败
21004       provision 失败
21006       send public key 失败
21008       conform 失败
210010      random conform 失败
210014      send data 失败
210016      composition data 失败
210018      add appkey 失败
210020      bind model 失败
210022      publication model 失败
210024      network transmit 失败
210026      云端注册失败
210027      设备地址分配已满
210034      notify 失败
20021       配网超时

//单设备配网成功回调
void onSuccess(String mac, DeviceBean deviceBean);

//整个配网结束回调
void onFinish();

```

##### 【方法调用】

```
//开启配网
iTuyaBlueMeshActivator.startActivator();

//停止配网
iTuyaBlueMeshActivator.stopActivator();
```

##### 【代码范例】
```
//普通设备入网
TuyaSigMeshActivatorBuilder tuyaSigMeshActivatorBuilder = new TuyaSigMeshActivatorBuilder()
            .setSearchDeviceBeans(mSearchDeviceBeanList)
            .setSigMeshBean(sigMeshBean)
            //超时时间
            .setTimeOut(CONFIG_TIME_OUT)
            .setTuyaBlueMeshActivatorListener(new ITuyaBlueMeshActivatorListener() {
                    @Override
                    public void onSuccess(String mac, DeviceBean deviceBean) {
                        L.d(TAG, "subDevBean onSuccess: " + deviceBean.getName());
                    }

                    @Override
                    public void onError(String mac, String errorCode, String errorMsg) {
                        L.d(TAG, "config mesh error" + errorCode + " " + errorMsg);
                    }

                    @Override
                    public void onFinish() {
                        L.d(TAG, "config mesh onFinish： ");
                    }
                });
ITuyaBlueMeshActivator iTuyaBlueMeshActivator = TuyaHomeSdk.getTuyaBlueMeshConfig().newActivator(tuyaBlueMeshActivatorBuilder);

iTuyaBlueMeshActivator.startActivator();

```

#### 通过网关通道入网
同 [ZigBee子设备配网](./Activator_ZigbeeSub.md)

### 网关入网
同 [Wifi EZ 配网模式](./Activator_wifi.md#EZ模式配网)
