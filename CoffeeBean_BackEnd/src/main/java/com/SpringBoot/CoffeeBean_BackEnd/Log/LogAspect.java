package com.SpringBoot.CoffeeBean_BackEnd.Log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.extractEmail(..))")
    public void logExtractEmail () {
        log.info(" ExtractEmail SuccessFully ....! ");
    }

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.key(..))")
    public void logKey () {
        log.info(" Key Generate SuccessFully ....! ");
    }

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.extractClaims(..))")
    public void logExtractClaims () {
        log.info(" ExtractClaims SuccessFully ....! ");
    }

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.extractRole(..))")
    public void logExtractRole () {
        log.info(" ExtractRole SuccessFully ....! ");
    }

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.extractExpiration(..))")
    public void logExtractExpiration () {
        log.info(" ExtractExpiration SuccessFully ....! ");
    }

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.validateExpiration(..))")
    public void logValidateExpiration () {
        log.info(" ValidateExpiration SuccessFully ....! ");
    }

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.tokenValidation(..))")
    public void logTokenValidation () {
        log.info(" TokenValidation SuccessFully ....! ");
    }

    @Around("execution(*com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils.*(..))")
    public void logJetUtils () {
        log.info(" Enter the JwtUtils Class  ....! ");
    }

    @Before("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.doFilterInternal(..))")
    public void logDoFilterInternal () {
        log.info(" Enter the Security Filter ....! ");
    }

    @Before("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.securityFilterChain(..))")
    public void logInSecurityFilterChain () {
        log.info(" Entry The SecurityConfiguration .......! ");
    }

    @After("execution(* com.SpringBoot.CoffeeBean_BackEnd.Security.securityFilterChain(..))")
    public void logOutSecurityFilterChain () {
        log.info(" Exit The SecurityConfiguration .......! ");
    }

}

