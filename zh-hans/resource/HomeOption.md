### 家庭操作类

ITuyaHome 提供了家庭相关的操作类，负责处理家庭的数据和信息的更新。
接口入口：`TuyaHomeSdk.newHomeInstance(homeId)`。

#### 初始化家庭下的所有数据

```java 
    /**
     * 从云端获取家庭的所有信息
     * @param callback
     */
    void getHomeDetail(ITuyaHomeResultCallback callback);

```

#### 获取本地缓存中的数据信息 


```java 
	
	/**
     * 从cache获取家庭的所有信息
     * @param callback
     */
    void getHomeLocalCache(ITuyaHomeResultCallback callback);

```
#### 更新家庭信息

```java

    /**
     * 更新家庭信息
     *
     * @param name     家庭名称
     * @param lon      当前家庭的经度
     * @param lat      当前家庭的纬度
     * @param geoName  地理位置的地址
     * @param callback
     */
    void updateHome(String name, double lon, double lat, String geoName, IResultCallback callback);
```

#### 解散家庭

```java
	/**
     * 解散家庭
     *
     * @param callback
     */
    void dismissHome(IResultCallback callback);

```

#### 排序

```java

    /**
     * 排序
     *
     * @param idList homeId list 
     * @param callback
     */
    void sortHome(List<Long> idList, IResultCallback callback);
```

#### 添加房间

```java
   /**
     * 添加房间
     *
     * @param name
     * @param callback
     */
    void addRoom(String name, ITuyaRoomResultCallback callback);
```

#### 移除家庭下面的房间

```java
   /**
     * 移除房间
     *
     * @param roomId
     * @param callback
     */
    void removeRoom(long roomId, IResultCallback callback);

```

#### 排序家庭下面的房间

```java

 /**
     * 排序房间
     *
     * @param idList   房间id的list
     * @param callback
     */
    void sortRoom(List<Long> idList, IResultCallback callback);

```

#### 查询房间列表

```java
   /**
     * 查询房间列表
     *
     * @param callback
     */
    void queryRoomList(ITuyaGetRoomListCallback callback);

```

#### 创建群组

```java
    /**
     * 创建群组
     *
     * @param productId 产品ID
     * @param name      群组名称
     * @param devIdList 设备ID List
     * @param callback
     */
    void createGroup(String productId, String name, List<String> devIdList, final ITuyaResultCallback<Long> callback);

```

#### 根据设备查询房间信息
```java
    /**
     * 根据设备查询房间信息
     *
     * @param deviceList
     * @return
     */
    List<RoomBean> queryRoomInfoByDevice(List<DeviceBean> deviceList);
```

#### 监听家庭下面信息的变更
```java

    /**
     * 监听家庭下面信息(设备的新增或者删除)变更的监听
     *
     * @param listener
     */
    void registerHomeStatusListener(ITuyaHomeStatusListener listener);
    
    
public interface ITuyaHomeStatusListener {

    /**
     * 设备新增
     * @param devId
     */
    void onDeviceAdded(String devId);

    /**
     * 设备删除
     * @param devId
     */
    void onDeviceRemoved(String devId);

}

```
#### 注销家庭下面信息变更的监听
```java
    /**
     * 注销家庭下面信息变更的监听
     *
     * @param listener
     */
    void unRegisterHomeStatusListener(ITuyaHomeStatusListener listener);
```

#### 查询用户下面相同产品且支持群组的设备列表

```java
    void queryDeviceListToAddGroup(String productId, final ITuyaResultCallback<List<GroupDeviceBean>> callback);
```

#### 销毁
```java
    void onDestroy();
```