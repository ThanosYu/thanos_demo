package com.yu.test.ThreadTest;

/**
 * @author Thanos_Yu
 * @date 2018/3/20.
 */
public class FirstThreadTest extends Thread {

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + " run"  + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" :"+i);
            if (i==5){
                new FirstThreadTest().start();
                new FirstThreadTest().start();
            }
        }
    }
}
