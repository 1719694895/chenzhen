package cz.spring.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:HelloController
 *
 * @Description: TODO
 * @Author:chenzhen Date:2019/7/13 9:50
 * Version 1.0
 **/
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

        return "index";
    }
}
