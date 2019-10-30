package net.xdclass.xdclass_shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class QuickStartTest4 {
    CustomRealm customRealm = new CustomRealm();
    DefaultSecurityManager securityManager = new DefaultSecurityManager();

    @Before
    public void init(){
        //构建环境
      securityManager.setRealm(customRealm);
      SecurityUtils.setSecurityManager(securityManager);

    }
    @Test
    public void testAuthentication(){
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("aaa", "aaa");
        subject.login(usernamePasswordToken);
        System.out.println("认证结果："+subject.isAuthenticated());
        System.out.println("是否有admin角色"+subject.hasRole("admin"));
        System.out.println("是否有delete权限"+subject.isPermitted("delete"));
        System.out.println(""+subject);
        System.out.println("当前角色名称："+subject.getPrincipal());


    }

}
