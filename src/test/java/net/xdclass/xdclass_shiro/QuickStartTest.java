package net.xdclass.xdclass_shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/*
* 单元测试执行顺序
* @Before-->@Test-->@After-->@AfterClass
*/
public class QuickStartTest {
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    SimpleAccountRealm accountRealm = new SimpleAccountRealm();
    @Before
    public void init(){
        //初始化数据源
        accountRealm.addAccount("ityan","1234");
        accountRealm.addAccount("222","1231114");
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

    }
}
