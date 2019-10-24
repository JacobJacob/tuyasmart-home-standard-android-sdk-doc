### 家庭管理类
ITuyaHomeManager 提供了创建家庭、获取家庭列表以及监听家庭相关的变更,
接口入口`TuyaHomeSdk.getHomeManagerInstance()`。

#### HomeBean字段信息
| 字段 | 类型 | 描述 |
| --- | --- | --- |
| name | String  | 家庭名称|
| lon | double   | 经度 |
| lat | double | 纬度|
| geoName | String |家庭地理位置名称 |
| homeId | long | 家庭Id |
| admin | boolean  | 管理员身份 |
| rooms | List&lt;RoomBean&gt;  | 所有房间列表 |
| deviceList | List&lt;DeviceBean&gt;  | 所有设备列表 |
| groupList | List&lt;GroupBean&gt;  |所有群组 |
| meshList | List&lt;BlueMeshBean&gt; |网关设备 |
| sharedDeviceList | List&lt;DeviceBean&gt; |收到的共享设备 |
| sharedGroupList | List&lt;GroupBean&gt; |收到的共享群组|
| homeStatus | int | 家庭状态（1:等待接受 2:接受 3:拒绝）|

#### 创建家庭

```java
    /**
     *
     * @param name     家庭名称
     * @param lon      经度
     * @param lat      纬度
     * @param geoName  家庭地理位置名称
     * @param rooms    房间列表
     * @param callback
     */
    void createHome(String name, double lon, double lat, String geoName, List<String> rooms, ITuyaHomeResultCallback callback);


```


#### 获取家庭列表

```java

   /**
    *
    * @param callback
    */
    void queryHomeList(ITuyaGetHomeListCallback callback);
```

#### 家庭信息的变更
1.注册与注销监听
```java

    /**
     * 注册家庭信息的变更
     * 有：家庭的增加、删除、信息变更、分享列表的变更和服务器连接成功的监听
     *
     * @param listener
     */
    void registerTuyaHomeChangeListener(ITuyaHomeChangeListener listener);
    
    
    /**
     * 注销家庭信息的变更
     *
     * @param listener
     */
    void unRegisterTuyaHomeChangeListener(ITuyaHomeChangeListener listener);
    
```
    
2.事件清单
```java
    
public interface ITuyaHomeChangeListener {
    /**
     * 家庭添加成功
     * 用于多设备数据同步
     *
     * @param homeId
     */
    void onHomeAdded(long homeId);

    /**
     * 家庭邀请
     * @param homeId    家庭ID
     * @param homeName  家庭名称
     */
    void onHomeInvite(long homeId,String homeName);
    
    /**
     * 家庭删除成功
     * 用于多设备数据同步
     *
     * @param homeId
     */
    void onHomeRemoved(long homeId);

    /**
     * 家庭信息变更
     * 用于多设备数据同步
     *
     * @param homeId
     */
    void onHomeInfoChanged(long homeId);

    /**
     * 分享设备列表变更
     * 用于多设备数据同步
     *
     * @param sharedDeviceList
     */
    void onSharedDeviceList(List<DeviceBean> sharedDeviceList);

    /**
     *
     * 手机连接涂鸦云服务器成功，接收到此通知，
     * 本地数据与服务端数据可能会不一致或者无法控制设备，
     * 可以调用Home下getHomeDetail接口重新初始化数据。
     */
    void onServerConnectSuccess();
}
```



### 对家庭的缓存数据操作
**注意**: 获取此数据前，应该调用家庭的初始化接口 TuyaHomeSdk.newHomeInstance("homeId").getHomeDetail()或者TuyaHomeSdk.newHomeInstance("homeId").getHomeLocalCache()之后下面的接口才会有缓存数据,调用入口:`TuyaHomeSdk.getDataInstance()`

```java

public interface ITuyaHomeDataManager {

    /**
     * 家庭下面的设备、群组、房间列表
     *
     * @param homeId 家庭ID
     * @return List<RoomBean>
     */
    List<RoomBean> getHomeRoomList(long homeId);

    /**
     * 获取家庭下面的设备列表
     *
     * @param homeId 家庭ID
     * @return List<DeviceBean>
     */
    List<DeviceBean> getHomeDeviceList(long homeId);

    /**
     * 获取家庭下面的群组列表
     *
     * @param homeId 家庭ID
     * @return List<DeviceBean>
     */
    List<GroupBean> getHomeGroupList(long homeId);

    /**
     * 获取群组
     *
     * @param groupId 群组ID
     * @return GroupBean
     */
    GroupBean getGroupBean(long groupId);

    /**
     * 获取设备
     *
     * @param devId 设备ID
     * @return DeviceBean
     */
    DeviceBean getDeviceBean(String devId);

    /**
     * 根据群组ID获取房间
     *
     * @param groupId 群组ID
     * @return RoomBean
     */
    RoomBean getGroupRoomBean(long groupId);

    /**
     * 获取房间
     *
     * @param roomId 房间ID
     * @return RoomBean
     */
    RoomBean getRoomBean(long roomId);

    /**
     * 根据设备获取房间信息
     *
     * @param devId 设备ID
     * @return RoomBean
     */
    RoomBean getDeviceRoomBean(String devId);

    /**
     * 获取群组下面的设备列表
     *
     * @param groupId 群组ID
     * @return List<DeviceBean>
     */
    List<DeviceBean> getGroupDeviceList(long groupId);

    /**
     * 获取mesh下面的群组列表
     *
     * @param meshId meshId
     * @return List<GroupBean>
     */
    List<GroupBean> getMeshGroupList(String meshId);

    /**
     * 获取mesh设备列表
     * @param meshId 
     * @return List<DeviceBean>
     */
    List<DeviceBean> getMeshDeviceList(String meshId);

    /**
     * 根据房间ID获取房间下面的设备列表
     *
     * @param roomId 房间ID
     * @return List<DeviceBean>
     */
    List<DeviceBean> getRoomDeviceList(long roomId);

    /**
     * 根据房间ID获取房间下面的群组列表
     *
     * @param roomId 房间ID
     * @return List<GroupBean>
     */
    List<GroupBean> getRoomGroupList(long roomId);

    /**
     * 获取Home数据
     *
     * @param homeId homeId
     * @return HomeBean
     */
    HomeBean getHomeBean(long homeId);
    
}

```

