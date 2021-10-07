package com.self.course.utils;

import lombok.Data;

@Data
public class SelfBean {

    private String name;

    public SelfBean(String name) {
        this.name = name;
    }
}
