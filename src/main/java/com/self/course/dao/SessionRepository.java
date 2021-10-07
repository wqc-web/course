package com.self.course.dao;

import com.self.course.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    @Query(value = "select * from t_session where cookie = :cookie", nativeQuery = true)
    Session querySession(@Param("cookie") String cookie);
}
