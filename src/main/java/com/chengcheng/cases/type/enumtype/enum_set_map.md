#### 专门用于枚举的集合类
> 我们平常一般习惯于使用诸如：HashMap 和 HashSet等集合来盛放元素，而对于枚举，有它专门的集合类：EnumSet和EnumMap

##### EnumSet
* EnumSet 是专门为盛放枚举类型所设计的 Set 类型。
```
// 还是举例来说，就以文中开头定义的角色枚举为例：
publicenum UserRole {
    ROLE_ROOT_ADMIN,  // 系统管理员
    ROLE_ORDER_ADMIN, // 订单管理员
    ROLE_NORMAL       // 普通用户
}

// 比如系统里来了一批人，我们需要查看他是不是某个角色中的一个：
// 定义一个管理员角色的专属集合
EnumSet<UserRole> userRolesForAdmin
    = EnumSet.of(
        UserRole.ROLE_ROOT_ADMIN,
        UserRole.ROLE_ORDER_ADMIN
    );

// 判断某个进来的用户是不是管理员
Boolean isAdmin( User user ) {
    
    if( userRoles.contains( user.getUserRole() ) )
        returntrue;
    
    returnfalse;
}
```


##### EnumMap
* 同样，EnumMap 则是用来专门盛放枚举类型为key的 Map 类型。
```
// 比如，系统里来了一批人，我们需要统计不同的角色到底有多少人这种的话：
Map<UserRole,Integer> userStatisticMap = new EnumMap<>(UserRole.class);

for ( User user : userList ) {
    Integer num = userStatisticMap.get( user.getUserRole() );
    if( null != num ) {
        userStatisticMap.put( user.getUserRole(), num+1 );
    } else {
        userStatisticMap.put( user.getUserRole(), 1 );
    }
}
```