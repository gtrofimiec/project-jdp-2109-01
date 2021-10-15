package com.kodilla.ecommercee.logger;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActivityLogger {


    private static final Logger logger = LoggerFactory.getLogger("analytics");

    @Before("execution(* com.kodilla.ecommercee.controller..*(..))" +
            "&& target(object) ")
    public void logUserActivity(Object object) {

        logger.trace("User activity:  {}", object.getClass().toString());

    }
}
