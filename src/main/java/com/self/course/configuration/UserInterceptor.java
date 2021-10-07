package com.self.course.configuration;

import com.self.course.dao.SessionRepository;
import com.self.course.entity.Session;
import com.self.course.utils.Constant;
import com.self.course.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor preHandle!!");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (Constant.COOKIE_NAME.equals(cookie.getName())) {
                Session session = sessionRepository.querySession(cookie.getValue());
                if (session != null) {
                    //设置当前线程用户
                    UserContext.setUser(session.getUser());
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空当前线程用户
        UserContext.setUser(null);
    }

}
