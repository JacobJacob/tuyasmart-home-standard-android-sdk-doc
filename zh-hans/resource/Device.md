### 设备信息获取

##### 【描述】

涂鸦智能提供了丰富的接口供开发者实现设备信息的获取和管理能力(移除等)。设备相关的返回数据都采用异步消息的方式通知接受者。

##### 【注意事项】

- 设备控制必须先初始化数据，即先调用TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(ITuyaHomeResultCallback callback)
- 设备控制如果需要使用经纬度，需要再配网前调用TuyaSdk.setLatAndLong，其中lon、lat用来标示经纬度信息。
------

### 设备操作控制

ITuyaDevice类提供了设备状态通知能力，通过注册回调函数，开发者可以方便的获取设备数据接受、设备移除、设备上下线、手机网络变化的通知。同时也提供了控制指令下发，设备固件升级的接口。

```java
//根据设备id初始化设备控制类
ITuyaDevice mDevice = TuyaHomeSdk.newDeviceInstance(deviceBean.getDevId());
```

#### 设备功能点

DeviceBean 类 dps 属性定义了设备的状态，称作数据点（DP点）或功能点。`dps`字典里的每个`key`对应一个功能点的`dpId`，`dpValue`为该功能点的值。各自产品功能点定义参见[涂鸦开发者平台](https://developer.tuya.com/)的产品功能。
功能点具体参见[功能点相关概念](https://docs.tuya.com/cn/product/function.html)

##### 【指令格式】

发送控制指令按照以下格式：
{"(dpId)":"(dpValue)"}

##### 【功能点示例】

开发平台可以看到一个产品这样的界面
![功能点](./images/ios_dp_sample.jpeg)

根据后台该产品的功能点定义，示例代码如下:

```java
//设置dpId为101的布尔型功能点示例 作用:开关打开 
dps = {"101": true};

//设置dpId为102的字符串型功能点示例 作用:设置RGB颜色为ff5500
dps = {"102": "ff5500"};

//设置dpId为103的枚举型功能点示例 作用:设置档位为2档
dps = {"103": "2"};

//设置dpId为104的数值型功能点示例 用:设置温度为20°
dps = {"104": 20};

//设置dpId为105的透传型(byte数组)功能点示例 作用:透传红外数据为1122
dps = {"105": "1122"};

//多个功能合并发送
dps = {"101": true, "102": "ff5500"};

mDevice.publishDps(dps, new IControlCallback() {
        @Override
        public void onError(String code, String error) {
        //错误码11001
        //有下面几种情况：
        //1、类型不对导致，例如，string类型格式，发成boolean类型数据。
        //2、只读类型dp数据不能下发，参考SchemaBean getMode "ro"是只读类型。
        //3、raw格式发送数据格式不是16进制字符串。
        }
        @Override
        public void onSuccess() {
        }
    });
```

##### 【注意事项】

- 控制命令的发送需要特别注意数据类型.
	比如功能点的数据类型是数值型（value），那控制命令发送的应该是 `{"104": 25}`  而不是  `{"104": "25"}`
- 透传类型传输的byte数组是16进制字符串格式并且必须是偶数位。
	比如正确的格式是: `{"105": "0110"}` 而不是 `{"105": "110"}`



####  设备控制

设备控制支持三种通道控制，局域网控制，云端控制，和自动方式（如果局域网在线，先走局域网控制，局域网不在线，走云端控制）

局域网方式

```java
mDevice.publishDps(commandStr, TYDevicePublishModeEnum.TYDevicePublishModeLocal, callback);
```
云端控制
```java
mDevice.publishDps(commandStr, TYDevicePublishModeEnum.TYDevicePublishModeInternet, callback);
```
自动控制
```java
mDevice.publishDps(commandStr, TYDevicePublishModeEnum.TYDevicePublishModeAuto, callback);
```

#### 初始化数据监听

##### 【描述】

TuyaHomeDevice提供设备相关信息（dp数据、设备名称、设备在线状态和设备移除）的监听，会实时同步到这里。

##### 【实现回调】

```java
mDevice.registerDevListener(new IDevListener() {
    @Override
    public void onDpUpdate(String devId, String dpStr) {
    //dp数据更新:devId 和相应dp数据
    }
    @Override
    public void onRemoved(String devId) {
    //设备被移除
    }
    @Override
    public void onStatusChanged(String devId, boolean online) {
    //设备在线状态，online
    }
    @Override
    public void onNetworkStatusChanged(String devId, boolean status) {
    //网络状态监听
    }
    @Override
    public void onDevInfoUpdate(String devId) {
    //设备信息变更，目前只有设备名称变化，会调用该接口
    }
});
```

#### 数据下发

##### 【代码范例】

以灯类产品作为示例

1、定义灯开关dp点

```java
public static final String STHEME_LAMP_DPID_101 = "101"; //灯开关 
```

2、关于灯开关的数据结构：

```java
public class LampBean {
	private boolean open;
	
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
```

3、对设备进行初始化

```java
/**
 * 设备对象。该设备的所有dp变化都会通过callback返回。
 *
 * 初始化设备之前，请确保已经初始化连接服务端，否则无法获取到服务端返回信息。
 */
mDevice = new TuyaHomeSdk.newDeviceInstance(mDevId);

mDevice.registerDevListener(new IDevListener() {
    @Override
    public void onDpUpdate(String devId, String dpStr) {
        //dp数据更新:devId 和相应dp数据
    }
    @Override
    public void onRemoved(String devId) {
        //设备被移除
    }
    @Override
    public void onStatusChanged(String devId, boolean online) {
        //设备在线状态，online
        //这个statusChange是指硬件设备和云端通信是否正常。
    }
    @Override
    public void onNetworkStatusChanged(String devId, boolean status) {
        //网络状态监听
        //这个onNetworkStatusChanged是指手机和云端通信是否正常。
    }
    @Override
    public void onDevInfoUpdate(String devId) {
        //设备信息变更，目前只有设备名称变化，会调用该接口
    }
});
```

4、开灯的代码片段

```java
 public void openLamp() {
    LampBean bean = new LampBean();
    bean.setOpen(true);
    HashMap<String, Object> hashMap = new HashMap<>();
    hashMap.put(STHEME_LAMP_DPID_101, bean.isOpen());
    mDevice.publishDps(JSONObject.toJSONString(hashMap), new IControlCallback() {
        @Override
        public void onError(String code, String error) {
            Toast.makeText(mContext, "开灯失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess() {
            Toast.makeText(mContext, "开灯成功", Toast.LENGTH_SHORT).show();
        }
    });
}
```

5、注销设备监听事件

```java
mDevice.unRegisterDevListener();
```

6、设备资源销毁

```java
mDevice.onDestroy();
```

##### 【注意事项】

- 指令下发成功并不是指设备真正操作成功，只是意味着指令成功发送出去。操作成功会有dp数据信息上报上来 ，且通过`IDevListener onDpUpdate`接口返回。
- command 命令字符串 是以`Map<String dpId,Object dpValue>` 数据格式转成JsonString。
- command 命令可以一次发送多个dp数据。



#### 设备信息查询

##### 【描述】

查询单个dp数据 从设备上查询dp最新数据 会经过 IDevicePanelCallback onDpUpdate 接口回调。

##### 【方法调用】

```java
mDevice.getDp(String dpId, IResultCallback callback);
```

##### 【代码范例】

```java
1、通过调用mTuyaDevice.getDp方法。

2、数据会通过dp数据更新监听上报上来。
IDevListener.onDpUpdate(String devId,String dpStr)
```

##### 【注意事项】 

- 该接口主要是针对那些数据不主动去上报的dp点。 常规查询dp数据值可以通过 DeviceBean里面 getDps()去获取。

#### 设备重命名

##### 【描述】

设备重命名，支持多设备同步。

##### 【方法调用】

```java
//重命名
mDevice.renameDevice(String name,IResultCallback callback);
```

##### 【代码范例】

```java
mDevice.renameDevice("设备名称", new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        //重命名失败
    }
    @Override
    public void onSuccess() {
        //重命名成功
    }
});
```

成功之后 ，`IDevListener.onDevInfoUpdate()` 会收到通知。

调用以下方法获取最新数据，然后刷新设备信息即可。

```java
TuyaHomeSdk.getDataInstance().getDeviceBean(String devId);
```

#### 移除设备

##### 【描述】

用于从用户设备列表中移除设备

##### 【方法调用】

```java
/**
 * 移除设备
 *
 * @param callback
 */
void removeDevice(IResultCallback callback);
```

##### 【代码范例】

```java
mDevice.removeDevice(new IResultCallback() {
    @Override
    public void onError(String errorCode, String errorMsg) {
    }

    @Override
    public void onSuccess() {
    }
});
```

#### 查询WiFi信号强度

##### 【描述】

查询当前设备WiFi的信号强度

##### 【方法调用】

```java

void requestWifiSignal(WifiSignalListener listener);
```

##### 【代码范例】

```java
mDevice.requestWifiSignal(new WifiSignalListener() {
     
     @Override
     public void onSignalValueFind(String signal) {
      
     }
     
     @Override
     public void onError(String errorCode, String errorMsg) {

     }
 });;
```

#### DeviceBean 数据模型


| 字段|类型|描述|
| :--:| :--:| :--:|
| iconUrl |String|图标地址|
| isOnline |Boolean|设备是否在线（局域网或者云端在线）|
| name |String|设备名称|
| schema |String|设备控制数据点的类型信息|
| productId |String|产品ID，同一个产品ID，Schema信息一致|
| supportGroup |Boolean|设备是否支持群组，如果不支持请到开放平台开启此项功能|
| time | Long |设备激活时间|
| pv | String |网关协议版本|
| bv | String |网关通用固件版本|
| schemaMap | Map |Schema缓存数据|
| dps | Map |          设备当前数据信息。key 是 dpId ，value 是值          |
| isShare | boolean |是否是分享设备|
| virtual|boolean |是否是虚拟设备|
| lon、lat |String| 用来标示经纬度信息，需要用户使用sdk前，调用TuyaSdk.setLatAndLong 设置经纬度信息 |
| isLocalOnline|boolean|设备局域网在线状态|
| nodeId |String|用于网关和子设备类型的设备，属于子设备的一个属性，标识其短地址ID，一个网关下面的nodeId都唯一的|
| timezoneId |String|设备时区|
| category | String |设备类型|
| meshId |String|用于网关和子设备类型的设备，属于子设备的一个属性，标识其网关ID|
| devId |String|设备唯一标示id|
| isZigBeeWifi |boolean|是否是ZigBee网关设备|
| hasZigBee |boolean|hasZigBee|

