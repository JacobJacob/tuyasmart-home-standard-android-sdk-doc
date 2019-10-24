# Add for Sharing


## (1) Add multiple devices for sharing (supplementing)

**[Description]**

Sharing multiple devices with a specified user will result in that the device to be shared will be added to the list of all his shared devices.

**[Method Invocation]**
```java
/**
@param homeId		home id of the sharer
@param countryCode	area code of mobile phone number (for example, the area code in China is “86”)
@param phoneNumber   mobile phone number
@param devIds 	list of device ids of the sharer
**/
TuyaHomeSdk.getDeviceShareInstance().addShareWithHomeId(long homeId,String countryCode, String userAccount, List<String> devIds, ITuyaResultCallback<SharedUserInfoBean> callback);
```

**[Example Codes]**

```java
TuyaHomeSdk.getDeviceShareInstance().addShareWithHomeId(homeId, countryCode, userAccount, devIds, new ITuyaResultCallback<SharedUserInfoBean>() {
    @Override
    public void onSuccess(SharedUserInfoBean bean) {
    }
    @Override
    public void onError(String errorMsg, String errorCode) {
    }
 });

/**
 * Adding devices in batches for sharing
 * @param memberId	sharing target user id
 * @param devIds	list of device ids
 * @param callback
 */
void addShareWithMemberId(long memberId,List<String> devIds,IResultCallback callback);
```

## (2) Batch add device sharing(supplementing)

**[Description]**

Batch add device sharing by MemberId

```java
	/**
     * Batch add device sharing by MemberId(supplementing)
     *
     * @param memberId Share the target user id
     * @param devIds
     * @param callback
     */
    void addShareWithMemberId(long memberId, List<String> devIds, IResultCallback callback);

```

**[Example Codes]**

```java
TuyaHomeSdk.getDeviceShareInstance().addShareWithMemberId (memberId, devIds, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
    }

    @Override
    public void onSuccess() {
    }
});
```

## (3) Unsharing a single device

**[Description]**

Unshare a single device via the user relationship id.

**[Method Invocation]**

```java
/**
 * Close device sharing under user
 *
 * @param devId
 * @param memberId
 * @param callback
 */
void disableDevShare(String devId, long memberId, IResultCallback callback);

```

### 【Example Codes】

```java
TuyaHomeSdk.getDeviceShareInstance().disableDevShare (devId, memberId, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess() {

    }
});
```

## (4)Sharing a single device

### 【描述】

Share a single device via the user relationship id.

**[Method Invocation]**

```java
/**
 * Open device sharing under user
 *
 * @param devId
 * @param memberId
 * @param callback
 */
void enableDevShare(String devId, long memberId, IResultCallback callback);

```

### 【Example Codes】

```java
TuyaHomeSdk.getDeviceShareInstance().enableDevShare (devId, memberId, new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        
    }

    @Override
    public void onSuccess() {

    }
});
```



# Query Sharing

## (1) Querying the list of actively shared relationships

**[Description]**

The sharer obtains a list of actively shared relationships (a list of user information shared with other users).

**[Method Invocation]**

```java
void queryUserShareList(long homeId, ITuyaResultCallback<List<SharedUserInfoBean>> callback);
```
**[Example Codes]**
```java
TuyaHomeSdk.getDeviceShareInstance().queryUserShareList(homeId,new ITuyaResultCallback<List<SharedUserInfoBean>>() {
            @Override
            public void onError(String errorMsg, String errorCode) {
            }
            @Override
            public void onSuccess(List<SharedUserInfoBean> list) {
                }
            }
        });
```
## (2) Querying the list of received relationships for sharing

**[Description]**

The sharer obtains the list of received relationships for sharing.

**[Method Invocation]**
```java
void queryShareReceivedUserList(ITuyaResultCallback<List<SharedUserInfoBean>> callback);
```
**[Example Codes]**
```java
TuyaHomeSdk.getDeviceShareInstance().queryShareReceivedUserList(new ITuyaResultCallback<List<SharedUserInfoBean>>() {
            @Override
            public void onError(String errorMsg, String errorCode) {       
            }
            @Override
            public void onSuccess(List<SharedUserInfoBean> list) {

         
				}
            }
        });
```
## (3) Querying the list of shared users of the specified device

**[Description]**

The sharer obtains the list of shared users of a certain device.

**[Method Invocation]**
```java
/**
* @param devId	device Id
**/
void queryDevShareUserList(String devId, ITuyaResultCallback<List<SharedUserInfoBean>> callback);
```
**[Example Codes]**
```java
TuyaHomeSdk.getDeviceShareInstance().queryDevShareUserList(devId, new ITuyaResultCallback<List<SharedUserInfoBean>>() {
            @Override
            public void onError(String errorMsg, String errorCode) {

              }
            @Override
            public void onSuccess(List<SharedUserInfoBean> list) {
          
                }
            }
        });
```
## (4) Querying the sharer of a specified device

**[Description]**

The receiver queries the sharer of a specified device.

**[Method Invocation]**
```java
/**
* @param devId	device Id
*/
void queryShareDevFromInfo(String devId, ITuyaResultCallback<SharedUserInfoBean> callback);
```
**[Example Codes]**
```java
TuyaHomeSdk.getDeviceShareInstance().queryShareDevFromInfo(devId, new ITuyaResultCallback<SharedUserInfoBean>() {
            @Override
            public void onSuccess(SharedUserInfoBean result) {
                
            }

            @Override
            public void onError(String errorCode, String errorMessage) {


            }
        });
```
## (5) Querying the relationship shared with a specified user

**[Description]**

The sharer obtains all shared device information shared to this relationship user via memberId.

**[Method Invocation]**
```java
/**
* @param memberId	user member Id (obtained from SharedUserInfoBean)
*/
void getUserShareInfo(long memberId, ITuyaResultCallback<ShareSentUserDetailBean> callback);
```


**[Example Codes]**
```java
TuyaHomeSdk.getDeviceShareInstance().getUserShareInfo(memberId, new ITuyaResultCallback<ShareSentUserDetailBean>() {
    @Override
    public void onError(String code, String error) {
        
    }


    @Override
    public void onSuccess(ShareSentUserDetailBean result) {


    }

})
```
## (6) Querying receipt of information shared by a specified user

**[Description]**

The receiver obtains information about receipt of all shared devices of this relationship user via memberId.

**[Method Invocation]**
```java
/**
* @param memberId 	user member Id (obtained from SharedUserInfoBean)
*/
void getReceivedShareInfo(long memberId, ITuyaResultCallback<ShareReceivedUserDetailBean> callback);
```
**[Example Codes]**
```java
TuyaHomeSdk.getDeviceShareInstance().getReceivedShareInfo(memberId, new ITuyaResultCallback<ShareReceivedUserDetailBean>() {
    @Override
    public void onError(String code, String error) {
        
    }
    @Override
    public void onSuccess(ShareReceivedUserDetailBean result) {
    }
});
```