## 配网与入网

### 扫描待配网子设备
##### 【描述】
扫描前需要检查蓝牙和位置权限

##### 【方法调用】
```java
//开启扫描
mMeshSearch.startSearch();
//停止扫描
mMeshSearch.stopSearch();

```
##### 【代码范例】
```java
ITuyaBlueMeshSearchListener iTuyaBlueMeshSearchListener=new ITuyaBlueMeshSearchListener() {
    @Override
    public void onSearched(SearchDeviceBean deviceBean) {

    }

    @Override
    public void onSearchFinish() {

    }
};

SearchBuilder searchBuilder = new SearchBuilder()
				.setMeshName("out_of_mesh")        //要扫描设备的名称（默认会是out_of_mesh，设备处于配网状态下的名称）
                .setTimeOut(100)        //扫描时长 单位秒
                .setTuyaBlueMeshSearchListener(iTuyaBlueMeshSearchListener).build();

ITuyaBlueMeshSearch mMeshSearch = TuyaHomeSdk.getTuyaBlueMeshConfig().newTuyaBlueMeshSearch(searchBuilder);

//开启扫描
mMeshSearch.startSearch();

//停止扫描
mMeshSearch.stopSearch();
```

### 子设备入网
##### 【描述】
子设备入网分为2种，一种是普通设备入网，一种是mesh网关入网

##### 【初始化参数配置】
```java
//普通设备入网参数配置
TuyaBlueMeshActivatorBuilder tuyaBlueMeshActivatorBuilder = new TuyaBlueMeshActivatorBuilder()
            .setSearchDeviceBeans(mSearchDeviceBeanList)
            //默认版本号
            .setVersion("1.0")
            .setBlueMeshBean(mMeshBean)
            //超时时间
            .setTimeOut(CONFIG_TIME_OUT)
            .setTuyaBlueMeshActivatorListener();
            
//mesh网关入网参数配置   
TuyaBlueMeshActivatorBuilder tuyaBlueMeshActivatorBuilder = new TuyaBlueMeshActivatorBuilder()
            .setWifiSsid(mSsid)
            .setWifiPassword(mPassword)     
            .setSearchDeviceBeans(mSearchDeviceBeanList)
            //默认版本号
            .setVersion("2.2")
            .setBlueMeshBean(mMeshBean)
            .setHomeId("homeId")
            //超时时间
        	  .setTimeOut(CONFIG_TIME_OUT)
            .setTuyaBlueMeshActivatorListener();
```

##### 【参数说明】
###### 【入参】
```java
* @param mSearchDeviceBeans     待配网的设备集合
* @param timeout    配网的超时时间设置，默认是100s.
* @param ssid       配网之后，设备工作WiFi的名称。（家庭网络）
* @param password   配网之后，设备工作WiFi的密码。（家庭网络)
* @param mMeshBean  MeshBean 
* @param homeId     设备要加入的Mesh网 所属家庭的HomeId
* @param version    普通设备配网是1.0    网关配网是2.2
```

###### 【出参】
ITuyaBlueMeshActivatorListener listener 配网回调接口

```java
//单设备配网失败回调
void onError(String errorCode, String errorMsg);

@param errorCode:
13007       登录设备失败
13004       重置设备地址失败
13005       设备地址已满
13007       ssid为空
13011       配网超时

//单设备配网成功回调
void onSuccess(DeviceBean deviceBean);

//整个配网结束回调
void onFinish();

```

##### 【方法调用】

```java
//开启配网
iTuyaBlueMeshActivator.startActivator();

//停止配网
iTuyaBlueMeshActivator.stopActivator();
```

##### 【代码范例】
```java
//普通设备入网
TuyaBlueMeshActivatorBuilder tuyaBlueMeshActivatorBuilder = new TuyaBlueMeshActivatorBuilder()
                .setSearchDeviceBeans(foundDevices)
                .setVersion("1.0")
                .setBlueMeshBean(mMeshBean)
                .setTimeOut(timeOut)
                .setTuyaBlueMeshActivatorListener(new ITuyaBlueMeshActivatorListener() {
                    @Override
                    public void onSuccess(DeviceBean deviceBean) {
                        L.d(TAG, "subDevBean onSuccess: " + deviceBean.getName());
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {
                        L.d(TAG, "config mesh error" + errorCode + " " + errorMsg);
                    }

                    @Override
                    public void onFinish() {
                        L.d(TAG, "config mesh onFinish： ");
                    }
                });
ITuyaBlueMeshActivator iTuyaBlueMeshActivator = TuyaHomeSdk.getTuyaBlueMeshConfig().newActivator(tuyaBlueMeshActivatorBuilder);

iTuyaBlueMeshActivator.startActivator();


//网关入网
TuyaBlueMeshActivatorBuilder tuyaBlueMeshActivatorBuilder = new TuyaBlueMeshActivatorBuilder()
                .setWifiSsid(mSsid)
                .setWifiPassword(mPassword)
                .setSearchDeviceBeans(foundDevices)
                .setVersion("2.2")
                .setBlueMeshBean(mMeshBean)
                .setHomeId("homeId")
                .setTuyaBlueMeshActivatorListener(new ITuyaBlueMeshActivatorListener() {

                    @Override
                    public void onSuccess(DeviceBean devBean) {
                    	//单个设备配网成功回调
                        L.d(TAG, "startConfig  success");
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {
                    	//单个设备配网失败回调
                        L.d(TAG, "errorCode: " + errorCode + " errorMsg: " + errorMsg);
                    }

                    @Override
                    public void onFinish() {
                    	//所有设备配网结束回调
                        L.d(TAG, "subDevBean onFinish: ");             
                    }
                });
ITuyaBlueMeshActivator iTuyaBlueMeshActivator = TuyaHomeSdk.getTuyaBlueMeshConfig().newWifiActivator(tuyaBlueMeshActivatorBuilder);
iTuyaBlueMeshActivator.startActivator();

```