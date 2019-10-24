# User uid Login System

Tuya Smart provides the uid login system. If clients have their own user system, the user system can access our sdk via the uid login system. 

## (1)User uid Registration

**[Description]**

User uid registration

**[Method Invocation]**

```java

/** User uid registration
* @param countryCode Country code
* @param uid         User uid
* @param password    Password
* @param callback    uid uid registration callback interface. 
**/
TuyaHomeSdk.getUserInstance().registerAccountWithUid(String countryCode, String uid, String password, IRegisterCallback callback);
```
**[Example Codes]**
```java
// uid registration.
TuyaHomeSdk.getUserInstance().registerAccountWithUid("86", "1234","123456", new IRegisterCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "The UID registration succeeds.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }

});
```

## (2) User uid Login

**[Description]**

User uid login

**[Method Invocation]**
```java
/** uid login
* @param countryCode Country code
* @param uid         User uid
* @param passwd      Password
* @param callback    Login callback interface. 
*/
TuyaHomeSdk.getUserInstance().loginWithUid(String countryCode, String uid, String passwd, ILoginCallback callback);
```
**[Example Codes]**
```java

// uid login

TuyaHomeSdk.getUserInstance().loginWithUid("86", "1234", "123456", new ILoginCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "Login succeeds, username:" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```
## (3) Reset User uid Password

**[Description]**

The user uid password resetting requires Cloud-Cloud connection. Please refer to the Cloud API documents for details.[https://docs.tuya.com/en/openapi/api/post_apps.schema.user_1.0.html](<https://docs.tuya.com/en/openapi/api/post_apps.schema.user_1.0.html>)

## (4) User uid Login Interface and Registration Interface

**[Description]**

User uid login interface and registration interface will be integrated. 

**[Method Invocation]**
```java
/** uid login
* @param countryCode Country code
* @param uid         User uid
* @param passwd      Password
* @param callback    Login callback interface. 
*/
TuyaHomeSdk.getUserInstance().loginOrRegisterWithUid(String countryCode, String uid, String passwd, ILoginCallback callback);
```
**[Example Codes]**
```java
// uid login
TuyaHomeSdk.getUserInstance().loginOrRegisterWithUid("86", "1234", "123456", new ILoginCallback() {
    @Override
    public void onSuccess(User user) {
        Toast.makeText(mContext, "Login succeeds, username:" , Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String code, String error) {
       Toast.makeText(mContext, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
    }
});
```