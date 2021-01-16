package ${groupId}.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut(value = "execution(* ${groupId}..*.*(..))")
    public void executeService() {
    }

    @Before(value = "executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();

        Logger logger = LoggerFactory.getLogger(signature.getDeclaringTypeName());

        logger.info("Before execute method: {}, args: {}", signature.getName(), joinPoint.getArgs());
    }

    @After(value = "executeService()")
    public void doAfterAdvice(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();

        Logger logger = LoggerFactory.getLogger(signature.getDeclaringTypeName());

        logger.info("After execute method: {}, args: {}", signature.getName(), joinPoint.getArgs());
    }


    @AfterReturning(value = "executeService()", returning = "result")
    public void doAfterReturningAdvice(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();

        Logger logger = LoggerFactory.getLogger(signature.getDeclaringTypeName());

        logger.info("After execute method: {}, result: {}", signature.getName(), result);
    }

    @AfterThrowing(value = "executeService()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {

        Signature signature = joinPoint.getSignature();

        Logger logger = LoggerFactory.getLogger(signature.getDeclaringTypeName());

        logger.error("After execute method: {}, exception: {}, message: {}", signature.getName(), exception,  exception.getMessage());
    }
}
