package com.herong.demo.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截所有controller模块的所有接口
     */
    @Pointcut("execution(* com.herong.demo.controller.*.*(..))")
    public void accessLogInGatewayModule(){};

    @Around(value = "accessLogInGatewayModule()")
    public Object accessLogInGatewayCalls(ProceedingJoinPoint joinPoint){
        Object result = null;

        try{
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            if(args != null && args.length > 0){
                logger.info(new StringBuilder(targetName).append(".").append(methodName).append("(req):").append(JSON.toJSONString(joinPoint.getArgs())).toString());
            }
            result = joinPoint.proceed();

            if(result != null){
                logger.info(new StringBuilder(targetName).append(".").append(methodName).append("(resp):").append(JSON.toJSONString(result)).toString());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
