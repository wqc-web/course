package com.self.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class LoginController {

    Map<String, String> userPassword = new ConcurrentHashMap<>();
    Map<String, String> cookieToUser = new ConcurrentHashMap<>();

    {
        userPassword.put("xiaohong", "123");
    }

    @GetMapping("/login")
    public String login(String username, String password, HttpServletResponse response) {
        if (password.equals(userPassword.get(username))) {
            String cookieName = "courseSessionId";
            String sessionId = UUID.randomUUID().toString();
            response.addCookie(new Cookie(cookieName, sessionId));
            cookieToUser.put(sessionId, username);
            return "login success";
        } else {
            return "login error";
        }
    }

}
