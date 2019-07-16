package cz.spring.springboot.controller;

import com.alibaba.fastjson.JSON;
import cz.spring.springboot.dto.AccessTokenDTO;
import cz.spring.springboot.dto.GithubUser;
import cz.spring.springboot.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:AuthorizeController
 *
 * @Description: TODO
 * @Author:chenzhen Date:2019/7/16 22:02
 * Version 1.0
 * @Controller: 提供路由功能
 **/
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name ="state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:4527/callback");
        accessTokenDTO.setClient_id("effb165aab42757ae743");
        accessTokenDTO.setClient_secret("2bde840358b651965227ae06d01520d72ceacda9");
        GithubUser user = githubProvider.getUser(githubProvider.getAccessToken(accessTokenDTO));

        System.out.println(user.toString());
        return "index";
    }
}
