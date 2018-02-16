package com.test.concurrent;

import java.util.concurrent.*;

/**
 * 测试java Future类的使用
 *
 * @author songxibo
 * @date 2018/2/16下午11:36
 */
public class FutureTest {

    public static class Task1 implements Callable<String> {
        @Override
        public String call() throws Exception {
            String tid = String.valueOf(Thread.currentThread().getId());
            System.out.printf("Thread#%s : in call\n", tid);
            Thread.sleep(111);
            return tid;
        }
    }

    public static class Task2 implements Callable<String> {
        @Override
        public String call() throws Exception {
            String tid = String.valueOf(Thread.currentThread().getId());
            System.out.printf("Thread#%s : in call\n", tid);
            Thread.sleep(1111);
            return tid;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<String> restult1 = es.submit(new Task1());
        Future<String> restult2 = es.submit(new Task2());

        System.out.println(restult2.get());
        System.out.println(restult1.get());
    }

}
