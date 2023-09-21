package jgdabc.config;

import jgdabc.filter.JwtVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
//使用构造器注入
    private  final  AuthenticationSuccessHandler successHandler;
    @Autowired
    private  final AuthenticationFailureHandler failureHandler;

    private final AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private final JwtVerifyFilter jwtVerifyFilter;

    public SecurityConfig(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AccessDeniedHandler accessDeniedHandler, JwtVerifyFilter jwtVerifyFilter) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.jwtVerifyFilter = jwtVerifyFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //前后端分离可以把sessionManagement关闭，这样不是携带session测试，不用jwttoken前端传再保存到上下文认证的话可以打开注释
//        打开session意味着这里测试的时候请求一会后下次请求将会携带上自己的cookie里面的jsessionid去访问
//       关闭意味着每次请求再请求将不会携带session访问。
       return  httpSecurity.sessionManagement(sessionManagement->{sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);})
         .formLogin(formLogin->formLogin.loginProcessingUrl("/userLogin")
                        .usernameParameter("uName").passwordParameter("uPwd").successHandler(successHandler).failureHandler(failureHandler)).exceptionHandling(ex->ex.accessDeniedHandler(accessDeniedHandler)).authorizeHttpRequests(au->au.requestMatchers("/index").permitAll().anyRequest().authenticated()).addFilterAfter(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class).csrf(csrf->csrf.disable()).build();

    }

}
