## 群组
ITuyaGroup类提供了对Mesh群组的操作
### Mesh 群组判断方法

可以通过群组中是否具备MeshId来区分Mesh群组和普通wifi群组
#####  【代码范例】

```java
GroupBean groupBean=TuyaHomeSdk.getDataInstance().getGroupBean("groupId");
if(!TextUtils.isEmpty(groupBean.getMeshId())){    
	L.d(TAG, "This group is mesh group");
}else{

}

```

### 创建群组

##### 【指令格式】

一个mesh网内支持创建 16128 个群组  返回时id范围  C000 - FEFF （16进制）  由本地进行维护

##### 【方法调用】

```java
* @param name			群组名称
* @param pcc			群组中设备的大小类  (支持跨小类创建)
* @param localId		群组的localId  (范围 C000 - FEFF 16进制字符串)
* @param callback		回调
public void addGroup(String name, String pcc, String localId,IAddGroupCallback callback);
```

##### 【代码范例】

```java
ITuyaBlueMeshDevice mTuyaSigMeshDevice= TuyaHomeSdk.newSigMeshDeviceInstance("meshId");

mTuyaSigMeshDevice.addGroup("群组名称","大小类", "8001", new IAddGroupCallback() {
			@Override
            public void onError(String errorCode, String errorMsg) {
            		Toast.makeText(mContext, "创建群组失败"+ errorMsg, Toast.LENGTH_LONG).show();
            }
            
            	
            @Override
            public void onSuccess(long groupId) {
            		Toast.makeText(mContext, "创建群组成功", Toast.LENGTH_LONG).show();
            }
        
        });
```



### 添加子设备到群组

##### 【方法调用】
```java
* @param devId		设备Id
* @param callback	回调
public void addDevice(String devId,IResultCallback callback);
```
##### 【代码范例】

```java
ITuyaGroup mGroup = TuyaHomeSdk.newSigMeshGroupInstance(groupId);
mGroup.addDevice("devId", new IResultCallback() {
            @Override
            public void onError(String code, String errorMsg) {
            		Toast.makeText(mContext, "添加设备到群组失败 "+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "添加设备到群组成功 ", Toast.LENGTH_LONG).show();
            }
        });
```


### 从群组中移除子设备
##### 【方法调用】
```java
* @param devId		设备Id
* @param callback	回调
public void removeDevice(String devId,IResultCallback callback);

```

##### 【代码范例】
```java
ITuyaGroup mGroup = TuyaHomeSdk.newSigMeshGroupInstance(groupId);
mGroup.removeDevice("devId", new IResultCallback() {
            @Override
            public void onError(String code, String errorMsg) {
            		Toast.makeText(mContext, "从群组中移除设备失败 "+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "从群组中移除设备成功 ", Toast.LENGTH_LONG).show();
            }
        });

```

### 解散群组
##### 【方法调用】
```java
* @param callback	回调
public void dismissGroup(IResultCallback callback);
```
##### 【代码范例】
```java
ITuyaGroup mGroup = TuyaHomeSdk.newSigMeshGroupInstance(groupId);
mGroup.dismissGroup(new IResultCallback() {
            @Override
            public void onError(String code, String errorMsg) {
            		Toast.makeText(mContext, "解散群组失败 "+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "解散群组成功 ", Toast.LENGTH_LONG).show();
            }
        });

```


### 重命名群组
##### 【方法调用】
```java
* @param groupName	重命名名称
* @param callback	回调
public void renameGroup(String groupName,IResultCallback callback);
```
##### 【代码范例】
```java
ITuyaGroup mGroup = TuyaHomeSdk.newSigMeshGroupInstance(groupId);
mGroup.renameGroup("群组名称",new IResultCallback() {
            @Override
            public void onError(String code, String errorMsg) {
            		Toast.makeText(mContext, "重命名群组失败 "+ errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
            		Toast.makeText(mContext, "重命名群组成功 ", Toast.LENGTH_LONG).show();
            }
        });

```