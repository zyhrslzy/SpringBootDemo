package springdemo.controllers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springdemo.entities.Users;
import springdemo.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired // 依赖注入
    private UserServiceImpl userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("test...");
        return "test";
    }

    @RequestMapping("/regist_page")
    public String regist_page() {
        return "regist";
    }

    @RequestMapping("/login_page")
    public String login_page() {
        return "login";
    }

    @RequestMapping("/regist")
    public String regist(Users user, Map<String, Object> map) {
        LOG.info(user);
        // 调用业务逻辑
        Integer num = userService.regist(user);
        if (num > 0) {
            return "login";
        }
        map.put("registDefeat", "注册失败，用户已存在");
        return "regist";
    }

    @RequestMapping("/login")
    public String login(Users user, Map<String, Object> map, HttpSession session) {
        // 调用业务逻辑
        user = userService.login(user);
        if (user != null && user.getUserid() > 0) {
            session.setAttribute("user", user);
            PageHelper.startPage(1, 5);
            List<Users> userList = userService.getAllUsers(1, 5);
            PageInfo<Users> pageInfo = new PageInfo<>(userList);
            map.put("pageInfo", pageInfo);
            map.put("userList", userList);
            return "showAllUsers";
        }
        map.put("loginDefeat", "登录失败，用户名称或密码不正确");
        return "login";
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Integer modify(Users user, Map<String, Object> map, HttpSession session) {
        // LOG.info(user);
        Integer num = userService.modifyUserinfoById(user);
        return num;
    }

    @RequestMapping("getAllUsers")
    public String getAllUsers(Integer pageNum, Integer maxPage, Map<String, Object> map, HttpSession session) {

        List<Users> userList = userService.getAllUsers(pageNum, maxPage);
        PageInfo<Users> pageInfo = new PageInfo<>(userList);
        map.put("pageInfo", pageInfo);
        map.put("userList", userList);
        return "showAllUsers";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @RequestMapping("/delect")
    @ResponseBody
    public Integer delect(Users user, Map<String, Object> map, HttpSession session) {
        // LOG.info(user);
        Integer num = userService.delectUserinfoById(user);
        return num;
    }

    @RequestMapping("/aaa")
    public String aaa(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
