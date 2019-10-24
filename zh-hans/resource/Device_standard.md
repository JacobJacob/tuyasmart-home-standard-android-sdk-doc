### 设备信息获取

##### 【描述】

涂鸦智能提供了丰富的接口供开发者实现设备信息的获取和管理能力(移除等)。设备相关的返回数据都采用异步消息的方式通知接受者。

##### 【注意事项】

- 设备控制必须先初始化数据，即先调用TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(ITuyaHomeResultCallback callback)
- 设备控制如果需要使用经纬度，需要再配网前调用TuyaSdk.setLatAndLong，其中lon、lat用来标示经纬度信息。

### 标准设备功能集

具体品类的设备标准dpCode 功能集可以参照对应的文档。


### 设备操作控制

ITuyaDevice类提供了设备状态通知能力，通过注册回调函数，开发者可以方便的获取设备数据接受、设备移除、设备上下线、手机网络变化的通知。同时也提供了控制指令下发，设备固件升级的接口。

```java
//根据设备id初始化设备控制类
ITuyaDevice mDevice = TuyaHomeSdk.newDeviceInstance(deviceBean.getDevId());
```

##### 【指令格式】

发送控制指令按照以下格式：

```java
Map<String, Object> dps

// dps.put("dpCode","dpValue");
dpCode: 标准指令Code
dpValue: dpValue
```



示例代码如下:

```java
//作用:开关打开 
dps.put("switch_led",true);

//字符串型功能点示例 作用:设置彩光颜色
dps.put("colour_data": "009003e803e8"};

//枚举型功能点示例 作用:设置模式为"white"白光模式
dps.put("work_mode","white");

//设置数值型功能点示例 用:设置亮度为100
dps.put("bright_value",100);


//多个功能合并发送
dps.put("work_mode","colour");
dps.put("colour_data","009003e803e8");

mDevice.publishCommands(dps, new IResultCallback() {
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
	比如功能点的数据类型是数值型（value），那控制命令发送的应该是 `{"bright_value": 25}`  而不是  `{"bright_value": "25"}`
- 透传类型传输的byte数组是16进制字符串格式并且必须是偶数位。
	比如正确的格式是: `{"*code": "0110"}` 而不是 `{"*code": "110"}`

#### 初始化数据监听

##### 【描述】

TuyaHomeDevice提供设备相关信息（dp数据、设备名称、设备在线状态和设备移除）的监听，会实时同步到这里。

##### 【实现回调】

```java
mDevice.registerDeviceListener(new IDevListener() {
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

##### 代码范例】

以灯类产品作为示例

1、定义灯开关dp点

```java
public static final String SWITCH_LED = "switch_led"; //灯开关 
```

2、对设备进行初始化

```java
/**
 * 设备对象。该设备的所有dp变化都会通过callback返回。
 *
 * 初始化设备之前，请确保已经初始化连接服务端，否则无法获取到服务端返回信息。
 */
mDevice = new TuyaHomeSdk.newDeviceInstance(mDevId);

mDevice.registerDeviceListener(new IDevListener() {
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
    HashMap<String, Object> hashMap = new HashMap<>();
    hashMap.put(SWITCH_LED, true);
    mDevice.publishCommands(hashMap, new IControlCallback() {
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
- command 是以`Map<String dpCode,Object dpValue>` 数据格式。
- command 命令可以一次发送多个dp数据。

#### 设备信息查询

##### 1. 常规查询dp数据值可以通过 DeviceBean里面 getDpCodes()去获取。

##### 2. 查询单个dp数据 从设备上查询dp最新数据 会经过 IDevicePanelCallback onDpUpdate 接口回调。

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

- 该接口主要是针对那些数据不主动去上报的dp点。
