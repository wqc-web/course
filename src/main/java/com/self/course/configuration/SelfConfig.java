package com.self.course.configuration;

import com.self.course.utils.SelfBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class SelfConfig {

    @Bean
    SelfBean createSelfBean() {
        return new SelfBean("SelfBean" + UUID.randomUUID().toString());
    }

}
