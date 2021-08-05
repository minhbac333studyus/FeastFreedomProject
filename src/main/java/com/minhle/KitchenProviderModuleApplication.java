package com.minhle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
//@EnableWebMvc
@SpringBootApplication

public class KitchenProviderModuleApplication extends SpringBootServletInitializer {

	@Autowired
    private DispatcherServlet servlet;
	public static void main(String[] args) {
		SpringApplication.run(KitchenProviderModuleApplication.class, args);
	}

//	@Bean
//    public CommandLineRunner getCommandLineRunner(ApplicationContext context) {
//        servlet.setThrowExceptionIfNoHandlerFound(true);
//        return args -> {};
//    }

}

//@RestControllerAdvice
// class AppException {
//
//    @ExceptionHandler(value={NoHandlerFoundException.class})
//    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
//    public ApiError badRequest(Exception e, HttpServletRequest request, HttpServletResponse response) {
//        e.printStackTrace();
//        return new ApiError(400, HttpStatus.BAD_REQUEST.getReasonPhrase());
//    }
//}   
// class ApiError {
//    private int code;
//    private String message;
//    public ApiError(int code, String message) {
//        this.code = code;
//        this.message = message;
//    }
//    public ApiError() {
//    }   
//    //getter & setter methods...
//}