//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.multiThreading;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class ProducerConsumerManual {
    private static final int CAPACITY = 5;
    private final Queue<Integer> buffer = new LinkedList();

    public static void main(String[] args) {
        ProducerConsumerManual pc = new ProducerConsumerManual();
        Objects.requireNonNull(pc);
        new Thread(pc::produce);
        Objects.requireNonNull(pc);
        new Thread(pc::consume);
    }

    public void produce() {
        int value = 0;

        while(true) {
            synchronized(this.buffer) {
                while(this.buffer.size() == 5) {
                    try {
                        System.out.println("Buffer full, producer waiting...");
                        this.buffer.wait();
                    } catch (InterruptedException var5) {
                        Thread.currentThread().interrupt();
                    }
                }

                this.buffer.add(value);
                System.out.println("Produced: " + value);
                ++value;
                this.buffer.notify();
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException var6) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void consume() {
        while(true) {
            synchronized(this.buffer) {
                while(this.buffer.isEmpty()) {
                    try {
                        System.out.println("Buffer empty, consumer waiting...");
                        this.buffer.wait();
                    } catch (InterruptedException var5) {
                        Thread.currentThread().interrupt();
                    }
                }

                int value = (Integer)this.buffer.remove();
                System.out.println("Consumed: " + value);
                this.buffer.notify();
            }

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var4) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
