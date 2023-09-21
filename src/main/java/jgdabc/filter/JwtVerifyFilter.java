package jgdabc.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jgdabc.entity.SysUser;
import jgdabc.util.JsonUtils;
import jgdabc.util.JwtUtils;
import jgdabc.util.ResultResponse;
import jgdabc.util.WirterOutUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtVerifyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        获取请求地址 /userLogin, /api/add
        String requestUrI  =  request.getRequestURI();
//        如果是登录请求，那么直接放行，让后面的过滤器直接去处理
        if (requestUrI.equals("/userLogin"))
        {
//            如果是用户登录请求那么就直接放行
            filterChain.doFilter(request,response);
            return;
        }
//        如果不是登录请求
//        从请求头部获取前端传来的token
        String authorization = request.getHeader("Authorization");
        if (null==authorization)
        {
            ResultResponse tokenFail = ResultResponse.fail("token为null");
            WirterOutUtil.write(response,tokenFail);
            return;

        }
//        去掉authorization 里面的Bearer
        String token  =  authorization.replace("Bearer","");
//        然后验证token
        Boolean verifyToken = JwtUtils.veryJwt(token);
        if (!verifyToken)
        {
            ResultResponse tokenFail = ResultResponse.fail("token校验失败");
            WirterOutUtil.write(response,tokenFail);
            return;
        }

//        从token中获取到UserID userName auth 权限的信息
        String  userJson = JwtUtils.parseJwt(token);
        SysUser sysUser= JsonUtils.toObject(userJson, SysUser.class);
//        回填数据到springContextHolder.context
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser,null,sysUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);




    }
}
