package net.xdclass.xdclass_shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*自定义realm*/
public class CustomRealm extends AuthorizingRealm {
    //先模拟一个数据库
    private final Map<String,String> userInfoMap = new HashMap<String,String>();
    {
        userInfoMap.put("ityan","1234");
        userInfoMap.put("aaa","aaa");
    }
    //声明权限集合
    private final Map<String, Set<String>> permissionMap = new HashMap<String, Set<String>>();
    {
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        set1.add("select");
        set1.add("delete");

        set2.add("add");
        set2.add("update");
        permissionMap.put("ityan",set1);
        permissionMap.put("aaa",set2);
    }
    //声明角色集合Role
    private final Map<String, Set<String>> RoleMap = new HashMap<String, Set<String>>();
    {
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        set1.add("admin");
        set1.add("root");

        set2.add("root");

        RoleMap.put("ityan",set1);
        RoleMap.put("aaa",set2);
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        System.out.println("来到了认证");
        String name = (String) Token.getPrincipal();
        String password = getPwdByUsernameFromDB(name);
        if (password==null || "".equals(password)){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,password,this.getName());

        return simpleAuthenticationInfo;
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("来到了授权");
        //获取主体
        String name = (String) principalCollection.getPrimaryPrincipal();
        //获取权限
        Set<String> permissions = getPermissionByNameFromDB(name);
        //获取角色
        Set<String> roles = getRoleByNameFromDB(name);
        //给授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    private String getPwdByUsernameFromDB(String name){
        return userInfoMap.get(name);
    }
    //通过name获取权限
    private Set<String> getPermissionByNameFromDB(String name){
        return permissionMap.get(name);
    }
    //通过name获取角色
    private Set<String> getRoleByNameFromDB(String name){
        return RoleMap.get(name);
    }


}
