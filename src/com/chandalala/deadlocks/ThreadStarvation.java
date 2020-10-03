package com.chandalala.deadlocks;

import com.chandalala.ThreadColor;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadStarvation {

    /**
     * Another potential that might occur when working with threads is live locks
     * live locks are similar to deadlocks but instead of the threads being blocked they are continuously active and usually waiting for all other
     * tracks to complete all the other tasks
     * Since all the threads are waiting for other threads to complete, non can actually progress
     */

    //Another alternative to synchronise blocks is to use a fair lock, it tries to be first come first serve
    private static ReentrantLock lock=new ReentrantLock(true);//true sets the lock to fair(First come first serve)

    public static void main(String[] args) {

        Thread t1=new Thread(new Worker(ThreadColor.ANSI_RED),"Priority 10");
        Thread t2=new Thread(new Worker(ThreadColor.ANSI_BLUE),"Priority 8");
        Thread t3=new Thread(new Worker(ThreadColor.ANSI_GREEN),"Priority 6");
        Thread t4=new Thread(new Worker(ThreadColor.ANSI_CYAN),"Priority 4");
        Thread t5=new Thread(new Worker(ThreadColor.ANSI_PURPLE),"Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);
        //Setting priority makes starvation more likely to happen we can use fair locks
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


    }

    private static class Worker implements Runnable{

        private int runCount=1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i=0; i<100; i++){
                lock.lock();try {
                    System.out.format(threadColor+"%s: runCount=%d\n", Thread.currentThread().getName(),runCount++);
                    //execute critical section of the code
                }
                finally {
                    lock.unlock();
                }

            }
        }
    }
}
