//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.multiThreading;

public class PingPongThread extends Thread {
    public static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized(lock) {
                for(int i = 0; i < 5; ++i) {
                    System.out.println("ping");
                    lock.notify();

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        Thread t2 = new Thread(() -> {
            synchronized(lock) {
                for(int i = 0; i < 5; ++i) {
                    System.out.println("pong");
                    lock.notify();

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        t1.start();
        t2.start();
    }
}
