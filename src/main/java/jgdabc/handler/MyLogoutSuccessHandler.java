package jgdabc.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jgdabc.util.JsonUtils;
import jgdabc.util.ResultResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.PrintWriter;
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrintWriter  writer  =  null;
        try {
            response.setContentType("application/json;character=utf-8");
            response.setCharacterEncoding("UTF-8");
            ResultResponse resultResponse = ResultResponse.success("退出成功");
            writer =  response.getWriter();
            String resultJson = JsonUtils.toJson(resultResponse);
            writer.write(resultJson);
            writer.flush();
        } finally {
            if (!ObjectUtils.isEmpty(writer))
            {
                writer.close();
            }
        }

    }
}
