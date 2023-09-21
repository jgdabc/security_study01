package jgdabc.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jgdabc.util.JsonUtils;
import jgdabc.util.ResultResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        PrintWriter writer  =  null;
        try {
            response.setContentType("application/json;character=utf-8");
            response.setCharacterEncoding("UTF-8");
            ResultResponse fail = ResultResponse.fail();
            String failResult = JsonUtils.toJson(fail);
            writer  =  response.getWriter();
            writer.write(failResult);
            writer.flush();
        } finally {
            if (!ObjectUtils.isEmpty(writer))
                writer.close();
        }


    }
}
