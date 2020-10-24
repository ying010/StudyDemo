package com.wzy.example.demo;

import com.wzy.example.demo.aop.TestMethod;
import com.wzy.example.demo.aop.TestMethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        MyCallableDemo demo = new MyCallableDemo();
//        FutureTask<String> future = new FutureTask<>(demo);
//        new Thread(future).start();
//
//        System.err.println(future.get());

        // todo 切面编程
        // 准备通知
        TestMethodInterceptor interceptor = new TestMethodInterceptor();

        // 准备切点
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern("com.wzy.example.demo.aop.TestMethod.*");

        // 切面 = 切点 + 通知
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, interceptor);

        TestMethod test = new TestMethod();
        // 代理
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(test);
        TestMethod proxy = (TestMethod) factory.getProxy();
        proxy.test();
    }
}

class MyCallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.err.println("my callable is start...");
        Thread.sleep(2000);
        return "Hello Callable!";
    }
}
