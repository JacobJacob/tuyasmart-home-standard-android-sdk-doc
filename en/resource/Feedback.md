## Feedback

It is used to provide a communication channel between users and businesses/developers.

ITuyaFeedbackManager is classified into a feedback management class, and provides interfaces like adding feedback and obtaining feedback lists. Invoked method: 

```java

ITuyaFeedbackManager = TuyaHomeSdk.getTuyaFeekback().getFeedbackManager()
```

ITuyaFeedbackMag is classified as a feedback management class for some conversation, and also provides new feedback and obtain a list of messages from the current conversation. Invoked method:

```java

ITuyaFeedbackMag = TuyaHomeSdk.getTuyaFeekback().getFeedbackMsg(String hdId, int hdType)
```

## Obtain feedback list

**[Description]**

Obtain all feedback from the user.

**[Method Prototype]**
```java
/**
 * Obtain feedback list
 *
 * @param callback
 */
void getFeedbackList(final ITuyaDataCallback<List<FeedbackBean>> callback);

```

Among, FeedbackBean class provides the following interfaces:

```java

/**
 * Obtain date and time  Format: 2018-06-08 17:12:45
 * 
 * @return date and time
 */
public String getDateTime() {
     return dateTime;
}

/**
 * Obtain the latest feedback to display in the list
 *
 * @return Feedback content 
 */ 
public String getContent() {
     return content;
}

/**
 *  Obtain feedback category id
 *
 * @return Feedback category id
 */
public String getHdId() {
     return hdId;
}

/**
 * Obtain feedback category
 * 2: Device fault
 * 7: Miscellaneous
 * @return Feedback class
 */
public int getHdType() {
    return hdType;
}

/**
 * Obtain feedback category titles (if device fault occurs, the device name will be fed back.)
 *
 * @return category title
 */

public String getTitle() {
     return title;

}
```

FeedbackTypeBean class provides the following interfaces:

```java

/**
 * Obtain feedback category id
 *
 * @return Feedback class id
 */
public String getHdId() {
     return hdId;
}

/**
 * Obtain feedback category
 * 2: Device fault
 * 7: Miscellaneous
 *
 * @return Feedback class
 */
public int getHdType() {
     return hdType;
}

/**
 * Obtain feedback class titles (if device fault occurs, the device name will be fed back.)
 *
 * @return class title
 */
public String getTitle() {
     return title;
}
```

**[Example Codes]**

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
## Obtain feedback type list

**[Description]**

Obtain a list of selectable feedback type to create choices  before feedback.

**[Method Prototype]**
```java
/**
 * Obtain feedback type list
 *
 * @param callback
 */
void getFeedbackType(final ITuyaDataCallback<List<FeedbackTypeRespBean>> callback);
```

Among, FeedbackTypeRespBean class provides the following interfaces:

```java
/**
 * Obtain a list of feedback type (device list and other lists)
 * @return class list
 */
public ArrayList<FeedbackTypeBean> getList() {
     return list;
}
/**
 * Obtain list category (Currently, only device and other lists are available)
 * @return list category
 */
public String getType() {
     return type;
}
```

**[Example Codes]**
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
**Add feedback**

**[Description]**

Add a new feedback.

**[Method Prototype]**
```java
/**
 * Add feedback
 *
 * @param message feedback content
 * @param contact  contact (phone or e-mail)
 * @param hdId     Feedback category id
 * @param hdType   Feedback class 
 * @param callback
 */

void addFeedback(final String message,String contact, String hdId, int hdType, final ITuyaDataCallback<FeedbackMsgBean> callback);
```
Note hdId, hdTypevariable can be obtained from the FeedbackTypeBean class fed back by[ Obtain a List of Feedback Type](https://github.com/TuyaInc/tuyasmart_home_android_sdk/blob/master/TuyaSmartHomeSdkDemo/doc) interface.

**[Example Codes]**

```java

TuyaHomeSdk.getTuyaFeekback().getFeedbackManager().addFeedback(
     “Thedevice fails ", //feedback
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

## Message management feedback

From the feedback list fed by[Obtain Feedback List](https://github.com/TuyaInc/tuyasmart_home_android_sdk/blob/master/TuyaSmartHomeSdkDemo/doc/tuyahome.md#%23%23)interface, Each feedback object corresponds to a group of messages (conversations). Please use parameters of FeedbackBean object fed back by the interface to invoke TuyaHomeSdk.getTuyaFeekback().getFeedbackMsg(String hdId, int hdType) for initializing the message management class.

For example:

```java
ITuyaFeedbackMag mFeedbackMag = TuyaHomeSdk.getTuyaFeekback().getFeedbackMsg(
     feedbackBean.getHdId(),
     feedbackBean.getHdType()
);
```

Among, FeedbackTalkBean provides the following interfaces:

```java
/**
 *  Obtain message content
 * @return Message content 
 */
public String getContent() {
     return content;
}

/**
 *  Obtain message time
 * @return message time
 */
public int getCtime() {
     return ctime;
}
/**
 * Distinguish messages sent between users and backstage supporter.
 * 0 represents user
 */
public int getType() {
     return type;
}

/**
 *  Obtain feedback category id
 *
 * @return Feedback category id
 */
public String getHdId() {
     return hdId;
}

/**
 * Obtain feedback category
 * 2: Device fault
 * 7: Miscellaneous
 * @return Feedback class
 */
public int getHdType() {
    return hdType;
}

```

## Obtain a list of feedback messages

**[Description]**

It is used to obtain a list of messages for the current feedback topic (conversation scene).

**[Method Prototype]**

```java
/**
 * Obtain a list of feedback messages
 *
 *  @param callback callback
 */

void getMsgList(ITuyaDataCallback<List<FeedbackMsgBean>> callback);
```

**[Example Codes]**

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
## Add new feedback

**[Description]**

It is used to add a new feedback message to the current conversation.

**[Method Prototype]**

```java
/**
 * Add new feedback
 *
 * @param msg      Feedback content 
 * @param contact  contact 
 * @param callback
 */
void addMsg(String msg, String  contact,ITuyaDataCallback<FeedbackMsgBean> callback);
```

**[Example Codes]**

```java
mFeedbackMsg.addMsg(
    “Question feedback again","abc@qq.com", 
    new ITuyaDataCallback<FeedbackMsgBean>() {
        @Override
        public void onSuccess(FeedbackMsgBean result) {
        }

        @Override
        public void onError(String errorCode, String errorMessage) {
        }
});

```