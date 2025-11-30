//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.multiThreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class DeadLockResolver {
    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            try {
                if (lockA.tryLock(1L, TimeUnit.SECONDS)) {
                    System.out.println("aquired lock A by " + Thread.currentThread().getName());
                    if (lockB.tryLock(1L, TimeUnit.SECONDS)) {
                        System.out.println("aquired lock B by " + Thread.currentThread().getName());
                        lockB.unlock();
                    }

                    lockA.unlock();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread t1 = new Thread(task1, "first");
        Thread t2 = new Thread(task1, "second");
        t1.start();
        t2.start();
    }
}
