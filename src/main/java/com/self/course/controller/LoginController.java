package com.self.course.controller;

import com.self.course.dao.SessionRepository;
import com.self.course.dao.UserRepository;
import com.self.course.entity.Session;
import com.self.course.entity.User;
import com.self.course.exception.HttpException;
import com.self.course.utils.Constant;
import com.self.course.utils.SelfBean;
import com.self.course.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@EnableJpaAuditing
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SelfBean selfBean;


    @GetMapping("/login")
    public String login(String username, String password, HttpServletResponse response) {
        User user = userRepository.queryUserNameAndPassword(username, password);
        if (user != null) {
            String cookieValue = UUID.randomUUID().toString();
            response.addCookie(new Cookie(Constant.COOKIE_NAME, cookieValue));
            //
            Session session = new Session();
            session.setCookie(cookieValue);
            session.setUserId(user.getId());
            sessionRepository.save(session);
            //
            return "login success";
        } else {
            return "login error";
        }
    }

    @GetMapping("/auth")
    public User auth() {
        User user = UserContext.getUser();
        if (user == null) {
            throw new HttpException(500, "not login!");
        } else {
            return user;
        }
    }

    @GetMapping("/findUser")
    public Iterable<User> findUser() {
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

    @GetMapping("/selfBean")
    public SelfBean selfBean() {
        return selfBean;
    }

}
