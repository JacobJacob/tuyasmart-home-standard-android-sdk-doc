# 操作设备
1、操作设备包含设备状态查询 ，设备连接，和设备发送指令，修改设备和解绑设备等操作。
2、配网成功后，将会得到deviceBean,可以取到devId，后续操作主要根据devId进行操作。
  获取已经用户的设备列表等功能 请参家庭管理。

### 1.判断设备状态
* 通过通用方式查询 与wifi一致（此状态是综合状态，若蓝牙设备添加到网关下面 则蓝牙设备可云端在线）
```java
TuyaHomeSdk.getDataInstance().getDeviceBean(devId).getIsOnline();
```
* 查询设备本地蓝牙是否连接
```java
TuyaHomeSdk.getBleManager().isBleLocalOnline(devId);
```

### 2.连接设备
若设备处于离线状态 可以调用连接方法 进行设备连接。
```java
 List<String> devIdList = new ArrayList<>();
 devIdList.add(devId1); //设备1 devId
 devIdList.add(devId2); //设备2 devId

 String ids = JSONObject.toJSONString(devIdList);
     
 TuyaHomeSdk.getBleManager().addScanLinkTaskIds(ids);
```

### 3. 设备控制
与wifi设备操作一致，
  * [设备控制](./resource/Device.md)
  * [设备功能点](./resource/Device.md#设备功能点)
  * [设备控制](./resource/Device.md#设备控制)
  * [修改设备名称](./resource/Device.md#设备重命名)
  * [移除设备](./resource/Device.md#移除设备)