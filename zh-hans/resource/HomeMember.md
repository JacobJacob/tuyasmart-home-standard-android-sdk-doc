### 家庭成员管理类
ITuyaHomeMember提供了家庭成员管理接口，包括添加、删除成员，更新成员的控制权限、获取家庭成员列表等。调用方式:`TuyaHomeSdk.getMemberInstance()` 。


#### MemberBean字段信息

| 字段 | 类型 | 描述 |
| --- | --- | --- |
| homeId | long  | 家庭id|
| nickName | String | 备注名 |
| admin | boolean | 是否是管理员 |
| memberId | long | 成员id |
| headPic | String | 头像url地址 |
| account | String  | 成员账户名称 |
| uid | String | 成员唯一标识id |
| memberStatus | int| 成员状态 （1:等待接受 2:接受 3:拒绝）|

#### Home下面添加成员

```java
    /**
     * 给这个Home下面添加成员
     *
     * @param countryCode 国家码
     * @param userAccount 用户名
     * @param name        昵称
     * @param admin       是否拥有管理员权限
     * @param callback
     */
    void addMember(long homeId ,int countryCode, String userAccount, String name, boolean admin, ITuyaMemberResultCallback callback);

```

#### 移除Home下面的成员

```java
   /**
     * 移除Home下面的成员
     *
     * @param id
     * @param callback
     */
    void removeMember(long memberId, IResultCallback callback);
```

#### 更新成员备注名和权限
```java
/**
 * 更新成员备注名和权限
 * @param memberId 成员Id
 * @param name 备注 
 * @param admin  是否是管理员
 * @param callback
 */
void updateMember(long memberId,String name, boolean admin, IResultCallback callback);
```

#### 查询Home下面的成员列表

```java
/**
 * 查询Home下面的成员列表
 * @param callback
*/
void queryMemberList(long homeId,ITuyaGetMemberListCallback callback);
```

#### 接受或者拒绝家庭邀请

```java
 /**
 * 接受或拒绝加⼊入家庭
 * @param homeId    家庭Id
 * @param action    接受/拒绝
 * @param callBack
*/
void processInvitation(long homeId, boolean action, final IResultCallback callBack);
```