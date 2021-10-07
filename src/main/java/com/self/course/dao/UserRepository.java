package com.self.course.dao;

import com.self.course.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from t_user where username = :username and password = :password", nativeQuery = true)
    User queryUserNameAndPassword(@Param("username") String username, @Param("password") String password);

}
