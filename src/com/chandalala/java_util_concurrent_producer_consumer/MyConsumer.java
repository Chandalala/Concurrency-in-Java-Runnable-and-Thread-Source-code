package com.chandalala.java_util_concurrent_producer_consumer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyConsumer implements Runnable {

    public static final String EOF="EOF";

    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock=bufferLock;
    }

    @Override
    public void run() {

        while (true){
            bufferLock.lock();
            try {
                if (buffer.isEmpty()){
                    continue;
                }
                if (buffer.get(0).equals(EOF)){
                    System.out.println(color+"Exiting");
                    break;
                }
                else {
                    System.out.println(color+"Removed "+buffer.remove(0));
                }
            }
            finally {//This will always run
                bufferLock.unlock();
            }

        }

    }


}
