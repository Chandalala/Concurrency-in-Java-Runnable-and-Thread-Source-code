package com.chandalala.thread_variables_counter_threads;

public class CountdownThread extends Thread {

    private Countdown threadCountdown;

    public CountdownThread(Countdown threadCountdown){
        this.threadCountdown=threadCountdown;
    }

    @Override
    public void run() {
        threadCountdown.doCountdown();
    }
}
