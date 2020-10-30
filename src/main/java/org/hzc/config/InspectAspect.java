package org.hzc.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hzc.entity.Logger;
import org.hzc.service.LoggerService;
import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 切点表达式（Spring中只能用于方法），表达式还有使用and，or, not连接：
 *    args() : 限制参数为指定类型，如arg(Student, String)
 *    @args(): 限制参数为指定注解类型，
 *    execution：匹配具体的方法
 *    this()：限制指定类型
 *    target：限制目标对象为制定类型
 *    @target()：限制目标对象为指定注解类型
 *   within()： 限制为具体包下
 *    @within()：限制为具体包下指定注解类型
 *    @annotation()：限制带有指定注解
 *    bean(): 限制指定的组件名
 */
// 这个注解表示是一个切面
@Aspect
// 此注解可以替换AspectConfig中的inspectAspect() bean方法
@Component
public class InspectAspect {
    @Autowired
    private LoggerService loggerService;

    @Pointcut("execution(* org.hzc.service.*.*(..))")
    public void pointcut() {}
    // JoinPoint指连接点，包含一些关于被代理对象和方法的信息
    @AfterThrowing(value ="pointcut()" , throwing = "ex")
    public void Save(JoinPoint joinPoint,Throwable ex) {
        String className = joinPoint . getTarget () .getClass () .getName() ;
        System. out.println ("后置异常通知:类名:"+ className) ;
        String methodName = joinPoint .getSignature() . getName () ;
        System. out.println("后置异常通知:方法名:"+ methodName) ;
        String errorMessage = ex. getMessage () ;
        System. out .println("后置异常通知:异常通知:"+ errorMessage) ;
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            System.out.println("后置异常通知:第" + (i+1) + "个参数为:" + args[i].getClass().toString());
            stringBuilder.append(args[i].getClass().toString());
        }
        String arg =  new String(stringBuilder);
        Logger logger = new Logger();
        logger.setArg(arg);
        logger.setClassName(className);
        logger.setErrorMessage(errorMessage);
        logger.setMethodName(methodName);
            loggerService.create(logger);

    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        String className = joinPoint . getTarget () .getClass () .getName() ;
        System. out.println ("后置返回通知:类名:"+ className) ;
        String methodName = joinPoint .getSignature() . getName () ;
        System. out.println("后置返回通知:方法名:"+ methodName) ;
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("后置返回通知:第" + (i+1) + "个参数为:" + args[i].getClass().toString());
        }
        System.out.println("后置返回通知 2018-5-9 何志成");
    }



}
