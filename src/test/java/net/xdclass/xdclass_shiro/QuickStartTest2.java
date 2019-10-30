package net.xdclass.xdclass_shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

/*
* 单元测试执行顺序
* @Before-->@Test-->@After-->@AfterClass
*/
public class QuickStartTest2 {
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    SimpleAccountRealm accountRealm = new SimpleAccountRealm();
    @Before
    public void init(){
        //初始化数据源
        accountRealm.addAccount("ityan","1234","root","admin");
        accountRealm.addAccount("222","1231114","root");
        //构建环境
        securityManager.setRealm(accountRealm);
    }
    @Test
    public void test(){
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //获取账号密码
        UsernamePasswordToken usernamePasswordToken = new
                UsernamePasswordToken("ityan","1234");
        subject.login(usernamePasswordToken);
        //判断是否登录成功
        System.out.println("认证结果:"+subject.isAuthenticated());
        System.out.println("是否有root角色"+subject.hasRole("root"));
        System.out.println("是否有admin角色"+subject.hasRole("admin"));
        System.out.println("拥有的所有角色："+subject.hasAllRoles(Arrays.asList("admin","root")));
        System.out.println("当前主体名称："+subject.getPrincipal());
        subject.checkRole("root");

        subject.logout();
        System.out.println("退出后认证结果"+subject.isAuthenticated());


    }
}
