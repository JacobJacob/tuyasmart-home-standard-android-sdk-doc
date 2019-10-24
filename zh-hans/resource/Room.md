###  房间管理类
ITuyaRoom 提供房间的管理类，负责房间的新增、删除设备或群组
调用入口: `TuyaHomeSdk.newRoomInstance("roomId")`。

#### RoomBean字段信息

| 字段 | 类型 | 描述 |
| --- | --- | --- |
| roomId | long  | 房间id|
| name | String   | 房间名字|
| deviceList | List &lt;DeviceBean&gt;   | 房间下面的设备|
| groupList | List &lt;GroupBean&gt;  | 房间下面的群组|
| displayOrder | int | 房间顺序|


#### 接口清单:
```java

    /**
     * 更新房间名称
     *
     * @param name     新房间名称
     * @param callback
     */
    void updateRoom(String name, IResultCallback callback);

    /**
     * 添加设备
     *
     * @param devId
     * @param callback
     */
    void addDevice(String devId, IResultCallback callback);

    /**
     * 添加群组
     *
     * @param groupId
     * @param callback
     */
    void addGroup(long groupId, IResultCallback callback);

    /**
     * 删除设备
     *
     * @param devId
     * @param callback
     */
    void removeDevice(String devId, IResultCallback callback);

    /**
     * 删除群组
     *
     * @param groupId
     * @param resultCallback
     */
    void removeGroup(Long groupId, IResultCallback resultCallback);

    /**
     * 把群组或者设备移除房间
     *
     * @param list
     * @param callback
     */
    void moveDevGroupListFromRoom(List<DeviceAndGroupInRoomBean> list, IResultCallback callback);

    /**
     * 对房间里的群组或者设备进行排序
     * @param list
     * @param callback
     */
    void sortDevInRoom(List<DeviceAndGroupInRoomBean> list, IResultCallback callback);

```
