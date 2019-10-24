## 意见反馈

用于在用户和企业或开发者之间提供一种沟通通道。

ITuyaFeedbackManager是反馈的管理类，提供了新增反馈、获取反馈列表等接口，调用方式：

```java

ITuyaFeedbackManager = TuyaHomeSdk.getTuyaFeekback().getFeedbackManager()

```

ITuyaFeedbackMag是针对某一会话的反馈管理类，也提供了新增反馈、获取当前会话的消息列表，调用方式：

```java

ITuyaFeedbackMag = TuyaHomeSdk.getTuyaFeekback().getFeedbackMsg(String hdId, int hdType)

```
### 获取反馈列表

#### 【描述】

获取该用户所有的反馈。

##### 【方法原型】

```java
/**
 * 获取反馈列表
 *
 * @param callback 回调
 */
void getFeedbackList(final ITuyaDataCallback<List<FeedbackBean>> callback);
```

其中， `FeedbackBean` 类提供以下接口:

```java
/**
 * 获取日期和时间 格式: 2018-06-08 17:12:45
 * 
 * @return 日期和时间
 */
public String getDateTime() {
    return dateTime;
}

/**
 *  获取最新一条反馈内容, 用于显示在列表中
 *
 * @return 反馈内容
 */ 
public String getContent() {
    return content;
}

/**
 *  获取反馈类目id
 *
 * @return 反馈类目id
 */
public String getHdId() {
    return hdId;
}

/**
 * 获取反馈类型
 * 2: 设备故障
 * 7: 其他
 *
 * @return 反馈类型
 */
public int getHdType() {
    return hdType;
}

/**
 *  获取反馈类目标题(如果为设备故障反馈即设备名称)
 *
 * @return 类目标题
 */
public String getTitle() {
    return title;
}
```

`FeedbackTypeBean`类提供以下接口:

```java
/**
 *  获取反馈类型id
 *
 * @return 反馈类型id
 */
public String getHdId() {
    return hdId;
}

/**
 * 获取反馈类型
 * 2: 设备故障
 * 7: 其他
 *
 * @return 反馈类型
 */
public int getHdType() {
    return hdType;
}

/**
 *  获取反馈类型标题(如果为设备故障反馈即设备名称)
 *
 * @return 类型标题
 */
public String getTitle() {
    return title;
}
```

##### 【代码范例】

```java
TuyaHomeSdk.getTuyaFeekback().getFeedbackManager().getFeedbackList(new ITuyaDataCallback<List<FeedbackBean>>() {
    @Override
    public void onSuccess(List<FeedbackBean> feedbackTalkBeans) {
    }

    @Override
    public void onError(String errorCode, String errorMessage) {
    }
}); 
```

### 获取反馈类型列表

##### 【描述】

获取可选择的反馈类型列表，用于创建反馈之前选择。

##### 【方法原型】

```java
/**
 * 获取反馈类型列表
 *
 * @param callback 回调
 */
void getFeedbackType(final ITuyaDataCallback<List<FeedbackTypeRespBean>> callback);
```

其中, `FeedbackTypeRespBean`类提供以下接口:

```java
/**
 * 获取反馈类型列表(设备列表和其他列表)
 *
 * @return 类型列表
 */
public ArrayList<FeedbackTypeBean> getList() {
    return list;
}

/**
 *  获取列表类别(目前仅有设备和其他)
 * 
 * @return 列表类别
 */
public String getType() {
    return type;
}

```



##### 【代码范例】

```java
TuyaHomeSdk.getTuyaFeekback().getFeedbackManager().getFeedbackType(new ITuyaDataCallback<List<FeedbackTypeRespBean>>() {
    @Override
    public void onSuccess(List<FeedbackTypeRespBean> feedbackTypeRespBeans) {
    }

    @Override
    public void onError(String errorCode, String errorMsg) {
    }
});
```

### 新增反馈

##### 【描述】

新增一条反馈信息。

##### 【方法原型】

```java
/**
 * 添加反馈
 *
 * @param message  反馈内容
 * @param contact  联系方式（电话或邮箱）
 * @param hdId     反馈类目id
 * @param hdType   反馈类型
 * @param callback 回调
 */
void addFeedback(final String message,String contact, String hdId, int hdType, final ITuyaDataCallback<FeedbackMsgBean> callback);
```

注 `hdId`, `hdType`变量可以从[获取反馈类型列表]()接口返回的`FeedbackTypeBean`类中获取。

##### 【代码范例】

```java
TuyaHomeSdk.getTuyaFeekback().getFeedbackManager().addFeedback(
    "设备存在故障", //反馈信息
    "abc@qq.com",
    feebackTypeBean.getHdId(), 
    feebackTypeBean.getHdType(), 
    new ITuyaDataCallback<FeedbackMsgBean>() {
        @Override
        public void onSuccess(FeedbackMsgBean feedbackMsgBean) {
        }

        @Override
        public void onError(String errorCode, String errorMsg) {
        }
});
```

### 反馈消息管理

由[获取反馈列表](###)接口返回的反馈列表中，每个反馈对象都对应者一组消息(对话)。请用该接口返回的`FeedbackBean`对象的参数来调用`TuyaHomeSdk.getTuyaFeekback().getFeedbackMsg(String hdId, int hdType)` 方法来初始化消息管理类。

例:

```java
ITuyaFeedbackMag mFeedbackMag = TuyaHomeSdk.getTuyaFeekback().getFeedbackMsg(
    feedbackBean.getHdId(),
    feedbackBean.getHdType()
);
```

 `FeedbackMsgBean`提供以下接口:

```java
/**
 *  获取消息内容
 * 
 * @return 消息内容
 */
public String getContent() {
    return content;
}

/**
 *  获取消息时间
 *
 * @return 消息时间
 */
public int getCtime() {
    return ctime;
}
/**
 * 区分用户和后台发送的消息
 * 0代表用户
 */
public int getType() {
    return type;
}

/**
 *  获取反馈类目id
 *
 * @return 反馈类目id
 */
public String getHdId() {
    return hdId;
}

/**
 * 获取反馈类型
 * 2: 设备故障
 * 7: 其他
 *
 * @return 反馈类型
 */
public int getHdType() {
    return hdType;
}

```

#### 获取反馈消息列表

##### 【描述】

用于获取当前反馈话题（会话场景）的消息列表。

##### 【方法原型】

```java
/**
 * 获取反馈消息列表
 *
 *  @param callback 回调
 */
void getMsgList(ITuyaDataCallback<List<FeedbackMsgBean>> callback);
```

##### 【代码范例】

```java
mFeedbackMsg.getMsgList(new ITuyaDataCallback<List<FeedbackMsgBean>>() {
    @Override
    public void onSuccess(List<FeedbackMsgBean> result) {
    }

    @Override
    public void onError(String errorCode, String errorMessage) {
    }
});
```

#### 添加新反馈

##### 【描述】

用于在当前对话中添加新的反馈消息。

##### 【方法原型】

```java
/**
 * 添加新反馈
 *
 * @param msg      反馈内容
 * @param contact  联系方式
 * @param callback 回调
 */
void addMsg(String msg, String contact, ITuyaDataCallback<FeedbackMsgBean> callback);
```

##### 【代码范例】

```java
mFeedbackMsg.addMsg(
    "再次反馈问题","abc@qq.com", 
    new ITuyaDataCallback<FeedbackMsgBean>() {
        @Override
        public void onSuccess(FeedbackMsgBean result) {
        }

        @Override
        public void onError(String errorCode, String errorMessage) {
        }
});

```