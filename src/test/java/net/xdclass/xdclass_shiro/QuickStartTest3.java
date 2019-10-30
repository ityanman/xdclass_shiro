package net.xdclass.xdclass_shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class QuickStartTest3 {
    @Test
    public void testAuthentication(){
        //创建SecurityManager工厂，通过ini配置文件创建
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //拿到工厂
        SecurityManager securityManager = factory.getInstance();
        //将securityManager设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //获取当前主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("ityan", "1234");
        subject.login(usernamePasswordToken);
        System.out.println("认证结果："+subject.isAuthenticated());
        System.out.println("是否有admin角色"+subject.hasRole("admin"));
        System.out.println("是否有delete权限"+subject.isPermitted("delete"));
        System.out.println("当前角色名称："+subject.getPrincipal());


    }

}
