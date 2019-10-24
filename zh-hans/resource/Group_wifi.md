### Wifi群组

#### 群组列表获取

```java
/**
* 获取可创建群组设备列表
* @param homeId 家庭id
* @param groupId 群组未创建，入参groupId传-1；已有群组，请传实际群组ID
* @param productId 选择创建群组的设备的pid
*/
TuyaHomeSdk.newHomeInstance(homeId).queryDeviceListToAddGroup(groupId, productId, new IGetDevsFromGroupByPidCallback() {
    @Override
    public void onSuccess(List<GroupDeviceBean> bizResult) {

    }

    @Override
    public void onError(String errorCode, String errorMsg) {

    }
});
```

#### 创建群组

```java
/**
* 创建一个空群组
* @param homeId 家庭id
* @param productId 选择创建群组的设备的pid
* @param name 选择创建群组的名称
* @param selectedDeviceIds 选择的设备的deviceId列表
*/
TuyaHomeSdk.newHomeInstance(homeId).createNewGroup(productId, name, selectedDeviceIds, new ICreateGroupCallback() {
    @Override
    public void onSuccess(long groupId) {
			//返回groupId
    }

    @Override
    public void onError(String errorCode, String errorMsg) {

    }
});
```

#### 更新保存群组

```java
/**
* 更新保存群组到云端
* @param groupId 群组id
* @param deviceIds 新增或者删除成功的设备id列表
*/
TuyaHomeSdk.newGroupInstance(groupId).updateDeviceList(deviceIds, new IResultCallback() {
                @Override
                public void onError(String s, String s1) {

                }

                @Override
                public void onSuccess() {

                }
            });
```
