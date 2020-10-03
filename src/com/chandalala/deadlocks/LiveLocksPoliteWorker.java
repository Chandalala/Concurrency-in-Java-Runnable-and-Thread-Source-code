package com.chandalala.deadlocks;

public class LiveLocksPoliteWorker {
    /**
     * Another potential that might occur when working with threads is live locks
     * live locks are similar to deadlocks but instead of the threads being blocked they are continuously active and usually waiting for all other
     * tracks to complete all the other tasks
     * Since all the threads are waiting for other threads to complete, non can actually progress
     */

    public static void main(String[] args) {

        final Worker worker=new Worker("Tinashe",true);
        final Worker worker2=new Worker("Chanda",true);

        final SharedResource sharedResource=new SharedResource(worker);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker.work(sharedResource,worker2);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource,worker);

            }
        }).start();
    }
}
