package com.self.course.aop;

import com.self.course.entity.User;
import com.self.course.exception.HttpException;
import com.self.course.utils.UserContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AdminAopManager {

    @Around("@annotation(com.self.course.annotation.Admin)")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        User user = UserContext.getUser();
        if (user != null) {
            if (user.getRoleList().stream().anyMatch(role -> "管理员".equals(role.getName()))) {
                return joinPoint.proceed();
            } else {
                throw new HttpException(403, "not admin permission");
            }
        } else {
            throw new HttpException(403, "not login");
        }

    }

}
