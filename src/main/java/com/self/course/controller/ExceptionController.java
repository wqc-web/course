package com.self.course.controller;

import com.self.course.exception.HttpException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({HttpException.class})
    public void error(HttpServletResponse response, HttpException httpException) throws IOException {
        response.setStatus(httpException.getCode());
        response.getOutputStream().write(httpException.getMsg().getBytes());
        response.getOutputStream().flush();
    }

}
