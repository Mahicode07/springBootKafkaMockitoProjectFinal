//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.multiThreading;

public class OddEvenUsingThread {
    private static final Object lock = new Object();
    private static int counter = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while(counter <= 10) {
                synchronized(lock) {
                    if (counter % 2 == 0) {
                        System.out.println("even " + counter);
                        ++counter;
                        lock.notify();

                        try {
                            lock.wait();
                        } catch (InterruptedException var3) {
                            return;
                        }
                    }
                }
            }

        });
        Thread t2 = new Thread(() -> {
            while(counter <= 10) {
                synchronized(lock) {
                    if (counter % 2 == 1) {
                        System.out.println("Odd " + counter);
                        ++counter;
                        lock.notify();

                        try {
                            lock.wait();
                        } catch (InterruptedException var3) {
                            return;
                        }
                    }
                }
            }

        });
        t1.start();
        t2.start();
    }
}
