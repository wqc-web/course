package com.self.course.controller;

import com.self.course.dao.UserRepository;
import com.self.course.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@EnableJpaAuditing
public class LoginController {

    @Autowired
    UserRepository userRepository;

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

    @GetMapping("/findUser")
    public Iterable<User>  findUser() {
        Iterable<User> all = userRepository.findAll();
        return all;
    }

    @GetMapping("/addUser")
    public User addUser() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("123456");
        userRepository.save(user);
        return user;
    }

}
