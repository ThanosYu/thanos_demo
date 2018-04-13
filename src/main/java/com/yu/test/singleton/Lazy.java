package com.yu.test.singleton;

/**
 * @author Thanos Yu
 * @date 2017/12/18
 */

/**
 * 懒汉模式中单例是在需要的时候才去创建的，如果单例已经创建，再次调用获取接口将不会重新创建新的对象，而是直接返回
 * 之前创建的对象。如果某个单例使用的次数少，并且创建单例消耗的资源较多，那么就需要实现单例的按需创建，这个时候使
 * 用懒汉模式就是一个不错的选择。但是这里的懒汉模式并没有考虑线程安全问题，在多个线程可能会并发调用它的getInstance()
 * 方法，导致创建多个实例，因此需要加锁解决线程同步问题，实现如下。
 */
public class Lazy {

    private static Lazy instance = null;

    private Lazy() {
    }

    public static synchronized Lazy getInstance() {
        if (null == instance) {
            instance = new Lazy();
        }
        return instance;
    }
}
