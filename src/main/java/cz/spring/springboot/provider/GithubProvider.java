package cz.spring.springboot.provider;

import com.alibaba.fastjson.JSON;
import cz.spring.springboot.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;
import cz.spring.springboot.dto.GithubUser;
import java.io.IOException;

/**
 * ClassName:GithubProvider
 *github提供打的支持
 * @Description: TODO
 * @Author:chenzhen Date:2019/7/16 22:12
 * Version 1.0
 * @Component:将当前类初始化的spring的容器中,不需要实例化
 *
 **/
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String result = response.body().string();

                String accessToken=result.split("&")[0].split("=")[1];

                System.out.println(accessToken);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;

    }
    public GithubUser getUser(String accessToken){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();

            String result = response.body().string();
            GithubUser githubUser = JSON.parseObject(result, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
