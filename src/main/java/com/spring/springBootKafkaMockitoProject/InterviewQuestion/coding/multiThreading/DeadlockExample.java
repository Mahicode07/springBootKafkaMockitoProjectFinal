
package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.multiThreading;

public class DeadlockExample {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();

    public static void main(String[] args) {
        (new Thread(() -> {
            synchronized(lockA) {
                System.out.println("acquired lock A by t1");

                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("waiting for lock B");
                synchronized(lockB) {
                    System.out.println("acquired lock B by t1");
                }

            }
        })).start();
        (new Thread(() -> {
            synchronized(lockB) {
                System.out.println("acquired lock B by t2");

                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("waiting for lock A");
                synchronized(lockA) {
                    System.out.println("acquired lock A by t2");
                }

            }
        })).start();
    }
}
