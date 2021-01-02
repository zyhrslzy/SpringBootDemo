import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import springdemo.Start;
import springdemo.entities.Users;
import springdemo.service.impl.UserServiceImpl;


@RunWith(SpringRunner.class) // 运行Spring环境
@SpringBootTest(classes = Start.class) // 标记当前类为一个springboot的单元测试类，依赖spring-boot-starter-test
@WebAppConfiguration // 单元测试对web的支持（1.当前类不能是final修饰的类
// 2.当前类不能是匿名内部类）
public class UserServiceImplTest {// 类名+Test 表示此类针对哪个类进行测试
    Logger log = Logger.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserServiceImpl userService;

    // 在真正执行的方法之前之前--初始化参数--有几个单元测试调用几次
    @Before
    public void init() {
        log.info("初始化。。。");
    }

    @Test
    public void test() {
        // 绿条代表通过，断言正确，红色条反之

        log.info("真正执行的方法");
    }

    @Test
    public void test2() {
        log.info("真正执行的方法2");
    }

    // Test+方法名---要对哪个方法进行测试
    // 场景1
    @Test
    public void testLogin_input_correct_username_null_pass_expect_notPass() {
        log.info("aaa");
        Users user = new Users();
        user.setUsername("admin");
        user.setPassword("");
        Users user2 = userService.login(user);
        System.out.println(user2 + "...");
        // 绿条代表预期的值和实际值相等，红色代表不相等
        Assert.assertEquals(null, user2);
        // Assert.assertNotNull(user2);
        // Assert.assertNotEquals(null, user2);
    }

    // 场景2
    @Test
    public void testLogin_input_null_username_correct_pass_expect_notPass() {
        log.info("bbb");
        Users user = new Users();
        user.setUsername("");
        user.setPassword("superman");
        Users user2 = userService.login(user);
        System.out.println(user2 + "...");
        // 绿条代表预期的值和实际值相等，红色代表不相等
        Assert.assertEquals(null, user2);
    }

    // 场景3
    @Test
    public void testLogin_input_correct_username_error_pass_expect_notPass() {
        log.info("ccc");
        Users user = new Users();
        user.setUsername("admin");
        user.setPassword("admin");
        Users user2 = userService.login(user);
        System.out.println(user2 + "...");
        // 绿条代表预期的值和实际值相等，红色代表不相等
        Assert.assertEquals(null, user2);
    }

    // 场景4
    @Test
    public void testLogin_input_error_username_correct_pass_expect_notPass() {
        log.info("ddd");
        Users user = new Users();
        user.setUsername("aaa");
        user.setPassword("superman");
        Users user2 = userService.login(user);
        System.out.println(user2 + "...");
        // 绿条代表预期的值和实际值相等，红色代表不相等
        Assert.assertEquals(null, user2);
    }

    // 场景5
    @Test
    public void testLogin_input_correct_username_correct_pass_expect_notPass() {
        log.info("eee");
        Users user = new Users();
        user.setUsername("admin");
        user.setPassword("superman");
        Users user2 = userService.login(user);
        System.out.println(user2 + "...");
        Assert.assertNotNull(user2);
    }

    // 如果方法的返回值为引用数据类型，测试场景一般包含：1、空2、正确3、不正确
    // 如果方法的返回值为基本数据类型：测试场景一般包含：1、边界值2、正确的范围值3、不正确的范围值
    // 在真正执行的方法之前之后--资源释放--有几个单元测试调用几次
    @After
    public void finish() {
        log.info("完成...");
    }
}

