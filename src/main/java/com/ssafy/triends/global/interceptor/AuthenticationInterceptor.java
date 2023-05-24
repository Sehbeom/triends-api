package com.ssafy.triends.global.interceptor;

import com.ssafy.triends.global.error.exception.ExceptionMessage;
import com.ssafy.triends.global.error.exception.UserInfoNotFoundException;
import com.ssafy.triends.global.util.jwt.JwtOperator;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private JwtOperator jwtOperator;

    public AuthenticationInterceptor(JwtOperator jwtOperator) {
        this.jwtOperator = jwtOperator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
        if (Objects.isNull(loginRequired)) {
            return true;
        }

        String accessToken = request.getHeader("access-token");
        if (accessToken == null || "".equals(accessToken)) {
            throw new UserInfoNotFoundException(ExceptionMessage.USERINFO_NOT_FOUND.getMessage());
        }

        if (jwtOperator.checkToken(accessToken)) {
            return true;
        } else {
            throw new UserInfoNotFoundException(ExceptionMessage.TOKEN_EXPIRED.getMessage());
        }
    }
}
