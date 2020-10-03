package com.chandalala.deadlocks_wait_notify_messages;

public class Main {

    /**
     * One class is going to produce deadlocks_wait_notify_messages and the other one is going to consume these deadlocks_wait_notify_messages hence the consumer producer example
     *
     *
    */
    public static void main(String[] args) {

        Message message=new Message();
//        Writer writer=new Writer(message);
//        Reader reader=new Reader(message);
//
//        Thread thread=new Thread(writer);
//        Thread thread1=new Thread(reader);
//
//        thread.start();
//        thread1.start();
        new Thread(new Writer(message)).start();
        new Thread(new Reader(message)).start();
    }
}
