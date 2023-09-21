package jgdabc.handler;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jgdabc.entity.SysUser;
import jgdabc.util.Constants;
import jgdabc.util.JsonUtils;
import jgdabc.util.JwtUtils;
import jgdabc.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        PrintWriter writer =  null;//字符流
     try {

//        返回json格式
         response.setContentType("application/json;character=utf-8");
         response.setCharacterEncoding("UTF-8");
         UsernamePasswordAuthenticationToken authenticationToken  = (UsernamePasswordAuthenticationToken) authentication;
         Object credentials = authenticationToken.getCredentials();
         Object principal = authenticationToken.getPrincipal();
         //UsernamePasswordAuthenticationToken对象getPrincipal()返回的是UserDetailService的loadUserByUsername方法的结果对象
         SysUser sysUser = (SysUser) authenticationToken.getPrincipal();
         //创建token
         String userJson = JsonUtils.toJson(sysUser);
         String jwt = JwtUtils.createJwt(userJson);
//         将生成的token存入redis
         redisTemplate.opsForValue().set(Constants.REDIS_JWT_KEY+sysUser.getId(),jwt,30, TimeUnit.MINUTES);





         ResultResponse resultResponse = ResultResponse.success();
//        将结果转换为json写出去
         String resultJson = JsonUtils.toJson(resultResponse);
         writer =  response.getWriter();
//        将json对象写出去
         writer.write(resultJson);

         writer.flush();


     }finally {
         if (!ObjectUtils.isEmpty(writer))
         {
             writer.close();
         }

     }





    }
}
