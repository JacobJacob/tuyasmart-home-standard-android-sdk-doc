### Group Operation

#### Instantiation

```java
/**
* Instantiation of groups
* @param groupId	group Id
*/
ITuyaGroup mITuyaGroup= TuyaHomeSdk.newGroupInstance(groupId);
```

#### Modifying group name

```java
TuyaHomeSdk.newGroupInstance(groupId).renameGroup(titleName, new IResultCallback() {
            @Override
            public void onError(String s, String s1) {

            }

            @Override
            public void onSuccess() {
                           }
        });
```

#### Dismiss Group

```java
TuyaHomeSdk.newGroupInstance(groupId).dismissGroup(new IResultCallback() {
                @Override
                public void onError(String s, String s1) {
	
                }
	
                @Override
                public void onSuccess() {
	
                }
            });
```

#### Group callback event

```java
/**
* Registering group callback event
* @param listener callback
*/
mITuyaGroup.registerGroupListener(new IGroupListener() {
            @Override
            public void onDpUpdate(long l, String s) {

            }

            @Override
            public void onGroupInfoUpdate(long l) {

            }

            @Override
            public void onGroupRemoved(long l) {

            }
        });

* Cancel group callback event
mITuyaGroup.unRegisterGroupListener();
```

#### Sending group control command

```java
/**
* Sending group control command
* @param command	control command
* @param listener callback
*/
mTuyaGroup.publishDps(String command,IControlCallback listener);
```
Example Codes

```java
//Code segment for switching on the light in a group
LampBean bean = new LampBean();
bean.setOpen(true);
HashMap<String, Object> hashMap = new HashMap<>();
hashMap.put(STHEME_LAMP_DPID_1, bean.isOpen());
mTuyaGroup.publishDps(JSONObject.toJSONString(hashMap),callback)；
```
Notes

The returned result of a command sent from a group means that the command is sent to the cloud successfully, and it does not mean that the device has been actually controlled.

#### Group data acquisition

For obtaining the group data locally, the data cannot be obtained before the initialization of Home (invoking getHomeDetail() or getHomeLocalCache).


```java
/**
* Obtaining group data bean locally
* @param groupId    group Id
* @return GroupBean  class of group data
*/
TuyaHomeDataManager.getInstance().getGroupBean(long groupId);
/**
* Obtaining a list of group data locally
* @return  List<GroupBean>  group list
*/
TuyaHomeDataManager.getInstance().getGroupDeviceList(long groupId);
```

#### Group data destruction

```java
//It is recommended to invoke the group data destruction function when exiting the group control page.
mITuyaGroup.onDestroy();
```