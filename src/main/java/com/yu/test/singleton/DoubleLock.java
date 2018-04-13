package com.yu.test.singleton;

/**
 * @author Thanos Yu
 * @date 2017/12/18
 */

/**
 * 在同步代码块外多了一层instance为空的判断。由于单例对象只需要创建一次，如果后面再次调用getInstance()只需要直接返回单例
 * 对象。因此，大部分情况下，调用getInstance()都不会执行到同步代码块，从而提高了程序性能。不过还需要考虑一种情况，假如两
 * 个线程A、B，A执行了if (instance == null)语句，它会认为单例对象没有创建，此时线程切到B也执行了同样的语句，B也认为单例
 * 对象没有创建，然后两个线程依次执行同步代码块，并分别创建了一个单例对象。为了解决这个问题，还需要在同步代码块中增加
 * if (instance == null)语句，也就是下面看到的代码2。volatile的一个语义是禁止指令重排序优化，也就保证了instance变量被赋
 * 值的时候对象已经是初始化过的
 */
public class DoubleLock {

    private static volatile DoubleLock instance = null;

    private DoubleLock() {
    }

    public static DoubleLock getInstance() {
        if (null == instance) {
            synchronized (DoubleLock.class) {
                //2重
                if (null == instance) {
                    instance = new DoubleLock();
                }
            }
        }
        return instance;
    }
}
