### Zigbee群组

#### 群组列表获取

```java
/**
* 获取可创建群组设备列表
* @param homeId 家庭id
* @param groupId 群组未创建，入参groupId传-1；已有群组，请传实际群组ID
* @param productId 选择创建群组的设备的pid
*/
TuyaHomeSdk.newHomeInstance(homeId).queryZigbeeDeviceListToAddGroup(groupId, productId, 
        new ITuyaResultCallback<List<GroupDeviceBean>>() {
            @Override
            public void onSuccess(List<GroupDeviceBean> arrayList) {
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
* @param meshId 选择创建群组的设备的网关id（可使用deviceBean.getMeshId()获取）
* @param name 选择创建群组的名称
*/
TuyaHomeSdk.newHomeInstance(homeId).createZigbeeGroup(productId, meshId, name, new ITuyaResultCallback<CloudZigbeeGroupCreateBean>() {
    @Override
    public void onSuccess(CloudZigbeeGroupCreateBean cloudZigbeeGroupCreateBean) {
        //输出结果
        long mGroupId = cloudZigbeeGroupCreateBean.getGroupId();
        String mGId = cloudZigbeeGroupCreateBean.getLocalId();
    }

    @Override
    public void onError(String errorCode, String errorMsg) {
    }
});
```

#### 新增设备到群组

```java
/**
* 新增设备到群组
* @param meshId 选择创建群组的设备的网关id（可使用deviceBean.getMeshId()获取）
* @param selectedDeviceIds 选择新增设备的deviceId列表
* @param gid 群组的localId（可以通过创建空群组时获得，若已有群组可通过groupBean.getLocalId()获取）
*/
mITuyaZigbeeGroup.addDeviceToGroup(meshId, selectedDeviceIds, gid, new ITuyaResultCallback<ZigbeeGroupCreateResultBean>() {
            @Override
            public void onSuccess(ZigbeeGroupCreateResultBean zigbeeGroupCreateResultBean) {
                if (zigbeeGroupCreateResultBean != null) {
                    //新增设备成功的列表
                    if (zigbeeGroupCreateResultBean.getSuccess() != null && zigbeeGroupCreateResultBean.getSuccess().size() > 0) {
                        List<String> mAddSuccessDeviceIds = new ArrayList<>();
                        mAddSuccessDeviceIds.addAll(zigbeeGroupCreateResultBean.getSuccess());
                    }
                    //新增设备失败的列表
                    if (zigbeeGroupCreateResultBean.getFailure() != null && zigbeeGroupCreateResultBean.getFailure().size() > 0) {
                        List<String>mAddFailDeviceIds = new ArrayList<>();
                        mAddFailDeviceIds.addAll(zigbeeGroupCreateResultBean.getFailure());
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
            }
        });
```

#### 删除群组已有设备

```java
/**
* 删除群组已有设备
* @param meshId 选择创建群组的设备的网关id（可使用deviceBean.getMeshId()获取）
* @param selectedDeviceIds 选择删除设备的deviceId列表
* @param gid 群组的localId（可以通过创建空群组时获得，若已有群组可通过groupBean.getLocalId()获取）
*/
mITuyaZigbeeGroup.delDeviceToGroup(meshId, selectedDeviceIds, gid, new ITuyaResultCallback<ZigbeeGroupCreateResultBean>() {
            @Override
            public void onSuccess(ZigbeeGroupCreateResultBean zigbeeGroupCreateResultBean) {
                if (zigbeeGroupCreateResultBean != null) {
                    //删除设备成功的列表
                    if (zigbeeGroupCreateResultBean.getSuccess() != null && zigbeeGroupCreateResultBean.getSuccess().size() > 0) {
                        List<String> mDelSuccessDeviceIds = new ArrayList<>();
                        mDelSuccessDeviceIds.addAll(zigbeeGroupCreateResultBean.getSuccess());
                    }
                    //删除设备失败的列表
                    if (zigbeeGroupCreateResultBean.getFailure() != null && zigbeeGroupCreateResultBean.getFailure().size() > 0) {
                        List<String> mDelFailDeviceIds = new ArrayList<>();
                        mDelFailDeviceIds.addAll(zigbeeGroupCreateResultBean.getFailure());
                    }
                }
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
* @param homeId 家庭id
* @param selectedDeviceIds 新增或者删除成功的设备id列表
*/
TuyaHomeSdk.newZigbeeGroupInstance(groupId).updateGroupDeviceList(homeId, selectedDeviceIds, new IResultCallback() {
        @Override
        public void onError(String s, String s1) {
        }

        @Override
        public void onSuccess() {
        }
    });
```
