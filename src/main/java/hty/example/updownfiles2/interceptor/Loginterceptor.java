package hty.example.updownfiles2.interceptor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configurable
public class Loginterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //request对应前端的请求
        return true;
    }
}
