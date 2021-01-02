package springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Start {

    public static void main(String[] args) {
        System.out.println("开始...");
        SpringApplication.run(Start.class, args);
        System.out.println("结束...");

    }


    @RequestMapping(value = "/init")
    @ResponseBody 
    public String init() {
        System.out.println("init...");
        return "Hello,Springboot！";
    }

}
