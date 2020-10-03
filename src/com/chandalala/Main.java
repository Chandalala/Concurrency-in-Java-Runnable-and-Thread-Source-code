package com.chandalala;

import static com.chandalala.ThreadColor.ANSI_GREEN;
import static com.chandalala.ThreadColor.ANSI_PURPLE;
import static com.chandalala.ThreadColor.ANSI_RED;

public class Main {

    /*
     *   CONCURRENCY/MULTI THREADING
     *
     *   Performing multiple activities at the same time
     *
     *   Types of threads
     *       Daemon threads-->>> background threads for tasks such as gc
     *       Non-Daemon-->>> created within an application
     *   There is always at least one thread in a java program which is the main thread
     *   JVM will not terminate if at least one non daemon thread is running
     *
     *   States of a thread RUNNABLE,RUNNING,BLOCKED
     *
     *
    */

    public static void main(String[] args) {

        System.out.println(ANSI_PURPLE+"Hello from the main thread.");

        //AnotherThread extends the Thread class and not implement the Runnable Interface
        final Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");
        anotherThread.start();

        //Anonymous class thread
        new Thread() {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread");
            }
        }.start();

        //overriding MyRunnable thread which implements the Runnable interface and not extend from the Thread class
        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()");

                //Joining threads
                try {
                    //anotherThread.join();
                    anotherThread.join(2000);//calling a thread that we want to join to the current thread
                    System.out.println(ANSI_RED+"AnotherThread terminated, or timed out, so I'm running again");
                }
                catch (InterruptedException e){
                    //runs if the current thread is interrupted by another thread
                    e.printStackTrace();
                    System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
                }
            }
        });

        myRunnableThread.start();
        //anotherThread.interrupt(); //We interrupt a thread when we want it to stop so that we can do something else

            /*
            * For example
            * When a thread is monitoring a buffer for data that another thread is fetching
            * when the data fetching thread determines that there won't be anymore data to fetch
            * we can then interrupt the monitoring thread so that it will terminate
            *
            * rather than waking up the monitoring thread periodically --->>thread.sleep concept<<--- to check if data has been fetched
            * we can join the two threads (monitoring thread and data fetching thread)
            */

        //Three threads are running, main, myRunnable and anotherThread
        System.out.println(ANSI_PURPLE+"Hello again from the main thread.");

    }
}
