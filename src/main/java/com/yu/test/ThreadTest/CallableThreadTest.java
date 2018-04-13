package com.yu.test.ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Thanos_Yu
 * @date 2018/3/20.
 */
public class CallableThreadTest implements Callable<Integer> {

    private int i = 0;

    public Integer call() throws Exception {
        for (; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " run " + i);
        }
        return i;
    }

    public static void main(String[] args) {
        CallableThreadTest threadTest = new CallableThreadTest();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(threadTest);
        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" 的循环变量i:"+i);
            if (i==5){
                new Thread(futureTask,"有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值："+futureTask.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
