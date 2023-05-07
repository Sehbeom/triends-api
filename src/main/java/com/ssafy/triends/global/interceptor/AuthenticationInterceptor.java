package com.ssafy.triends.global.interceptor;

import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.error.exception.ExceptionMessage;
import com.ssafy.triends.global.error.exception.UserInfoNotFoundException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

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

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        if (userDto == null) {
            throw new UserInfoNotFoundException(ExceptionMessage.USERINFO_NOT_FOUND.getMessage());
        } else {
            return true;
        }
    }
}
