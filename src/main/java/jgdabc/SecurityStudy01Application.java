package jgdabc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(prePostEnabled = true) //开启方法注解式的权限检查
@SpringBootApplication
public class SecurityStudy01Application  {

    public static void main(String[] args) {
        SpringApplication.run(SecurityStudy01Application.class, args);
    }

}
