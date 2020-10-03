package com.chandalala.thread_variables_counter_threads;

public class CounterMain {

    public static void main(String[] args) throws InterruptedException {

        //Starting main thread
        System.out.println("Starting main thread");

        Countdown countdown=new Countdown();
       // Countdown countdown2=new Countdown();//to solve race condition solution however does not work in real world applications


        //create threads
        CountdownThread t1=new CountdownThread(countdown);
        CountdownThread t2=new CountdownThread(countdown);
        Thread.currentThread().setName("Thread 3");//current thread which is the main thread

        //assign names to threads
        t1.setName("Thread 1");
        t2.setName("Thread 2");


        //start the threads
        t1.start();
        t2.start();

        Thread.sleep(1000);//Main thread sleeps for 1 second before resuming
        System.out.println("After pausing main thread for five seconds");
        countdown.doCountdown();//current thread which is the main thread
    }
}
