package com.chandalala;

import static com.chandalala.ThreadColor.ANSI_BLUE;

/**
 * Created by chandalala on 25/05/2016.
 */
public class AnotherThread extends Thread {

    @Override
    public void run() {

        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());

        try {
            Thread.sleep(5000);//pauses the thread for 5 seconds
            // TimeUnit.SECONDS.sleep(3);

        }
        catch(InterruptedException e) {
            System.out.println(ANSI_BLUE +  "Another thread woke me up");// 5 seconds have not passed
            return;//this returns control to another thread instance and immediately terminate the AnotherThread instance
        }

        //thread resumes after 5 seconds if there is no an exception
        System.out.println(ANSI_BLUE + "Five seconds have passed and I'm awake");//this will not run if the thread has been interrupted

    }

}
