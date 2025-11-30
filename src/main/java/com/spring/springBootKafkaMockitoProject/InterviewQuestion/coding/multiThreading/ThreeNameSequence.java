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
    private static int retryCount = 3;
    private int state = 0;
    private final Lock lock = new ReentrantLock();
    Condition AlicCondition;
    Condition BobCondition;
    Condition CharlieCondition;

    public ThreeNameSequence() {
        this.AlicCondition = this.lock.newCondition();
        this.BobCondition = this.lock.newCondition();
        this.CharlieCondition = this.lock.newCondition();
    }

    private void printA() {
        for(int i = 0; i <= 3; ++i) {
            this.lock.lock();

            try {
                while(this.state % retryCount != 0) {
                    this.AlicCondition.await();
                }

                System.out.println("Alice");
                ++this.state;
                this.BobCondition.signal();
                continue;
            } catch (InterruptedException var6) {
            } finally {
                this.lock.unlock();
            }

            return;
        }

    }

    private void printB() {
        for(int i = 0; i <= 3; ++i) {
            this.lock.lock();

            try {
                while(this.state % retryCount != 1) {
                    this.BobCondition.await();
                }

                System.out.println("BOB");
                ++this.state;
                this.CharlieCondition.signal();
                continue;
            } catch (InterruptedException var6) {
            } finally {
                this.lock.unlock();
            }

            return;
        }

    }

    private void printC() {
        for(int i = 0; i <= 3; ++i) {
            this.lock.lock();

            try {
                while(this.state % retryCount != 2) {
                    this.CharlieCondition.await();
                }

                System.out.println("charlie");
                ++this.state;
                this.AlicCondition.signal();
                continue;
            } catch (InterruptedException var6) {
            } finally {
                this.lock.unlock();
            }

            return;
        }

    }

    public static void main(String[] args) {
        ThreeNameSequence threeNameSequence = new ThreeNameSequence();
        Objects.requireNonNull(threeNameSequence);
        Thread t1 = new Thread(threeNameSequence::printA);
        Objects.requireNonNull(threeNameSequence);
        Thread t2 = new Thread(threeNameSequence::printB);
        Objects.requireNonNull(threeNameSequence);
        Thread t3 = new Thread(threeNameSequence::printC);
        t1.start();
        t2.start();
        t3.start();
    }
}
