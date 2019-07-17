package cz.spring.springboot.controller;

import com.alibaba.fastjson.JSON;
import cz.spring.springboot.dto.AccessTokenDTO;
import cz.spring.springboot.dto.GithubUser;
import cz.spring.springboot.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name ="state") String state,
                            HttpServletRequest request){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        System.out.println(accessTokenDTO);
        GithubUser user = githubProvider.getUser(githubProvider.getAccessToken(accessTokenDTO));

       if(user!=null){
           //登录成功，写cookie和Session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
       }else{
           //登录失败，重新登录
           return "redirect:/";
       }

    }
}
