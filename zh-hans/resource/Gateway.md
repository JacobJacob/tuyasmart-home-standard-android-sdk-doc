## 网关

网关类封装了ZigBee网关的相关操作，包括控制，查询子设备，监听子设备状态等。
可以通过TuyaHomeSdk.newGatewayInstance()去初始化网关。
子设备控制可以通过TuyaDevice来实现。

### 接口描述
```java
public interface ITuyaGateway {
    /**
     * 给子设备发送控制命令 
     * @param nodeId 对应 DeviceBean 的nodeId 
     * @param dps 
     * @param callback
     */
    void publishDps(String nodeId, String dps, IResultCallback callback);

    /**
     * 给网关下的子设备发送广播，进行设备控制
     *
     * @param dps
     * @param callback
     */
    void broadcastDps(String dps, IResultCallback callback);

    /**
     * 给网关下的子设备发送组播，进行设备控制
     *
     * @param localId
     * @param dps
     * @param callback
     */
    void multicastDps(String localId, String dps, IResultCallback callback);

    /**
     * 通过云端获取网关子设备列表
     *
     * @param callback
     */
    void getSubDevList(ITuyaDataCallback<List<DeviceBean>> callback);

    /**
     * 监听子设备信息变更
     *
     * @param listener
     */
    void registerSubDevListener(ISubDevListener listener);

    /**
     * 注销子设备信息变更
     */
    void unRegisterSubDevListener();
}

```

### 子设备信息变更监听

#### 接口描述

```java

public interface ISubDevListener {

    /**
     * 设备dp点状态变更
     *
     * @param nodeId
     * @param dpStr
     */
    void onSubDevDpUpdate(String nodeId, String dpStr);

    /**
     * 设备移除
     */
    void onSubDevRemoved(String devId);

    /**
     * 新增设备
     *
     * @param devId
     */
    void onSubDevAdded(String devId);

    /**
     * Device information updates such as name
     */
    void onSubDevInfoUpdate(String devId);
    
    /**
     * Subdevices  online and offline status
     */
     
    void onSubDevStatusChanged(List<String> onlineNodeIds, List<String> offlineNodeIds);
}


```

------

