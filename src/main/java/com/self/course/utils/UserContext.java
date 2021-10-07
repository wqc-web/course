package com.self.course.utils;

import com.self.course.entity.User;

public class UserContext {

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static User getUser() {
        return threadLocal.get();
    }

    public static void setUser(User user) {
        threadLocal.set(user);
    }

}
