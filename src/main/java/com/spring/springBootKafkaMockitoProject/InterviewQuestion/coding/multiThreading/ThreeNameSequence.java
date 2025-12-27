//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.multiThreading;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeNameSequence {
    private final Lock lock = new ReentrantLock();
    private final Condition turnChanged = lock.newCondition();

    private int state = 0;         // shared counter
    private final int NAMES = 3;    // Alice, Bob, Charlie

    private void print(String name, int myTurn, int times) {
        for (int i = 0; i < times; i++) {
            lock.lock();
            try {
                while (state % NAMES != myTurn) {
                    turnChanged.await();
                }
                System.out.println(name);
                state++;
                turnChanged.signalAll(); // wake others, only correct one proceeds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreeNameSequence obj = new ThreeNameSequence();
        int times = 4;

        new Thread(() -> obj.print("Alice",   0, times)).start();
        new Thread(() -> obj.print("Bob",     1, times)).start();
        new Thread(() -> obj.print("Charlie", 2, times)).start();
    }
}
