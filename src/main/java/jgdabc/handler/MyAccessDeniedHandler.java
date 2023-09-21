package jgdabc.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jgdabc.util.JsonUtils;
import jgdabc.util.ResultResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.PrintWriter;
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        PrintWriter writer  =  null;
        try {
            response.setContentType("application/json;character=utf-8");
            response.setCharacterEncoding("UTF-8");
            ResultResponse fail = ResultResponse.fail("权限不足");
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
