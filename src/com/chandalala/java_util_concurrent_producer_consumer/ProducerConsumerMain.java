package com.chandalala.java_util_concurrent_producer_consumer;

import com.chandalala.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Executor services interface, we use implementations of this interface to manage threads for us so that we do not
 * have to explicitly start and create threads manually. It makes use of thread pools
 * A thread pool is a managed set of threads, it reduces the overhead of thread creation especially in applications that
 * use a large number of threads
 *
 */

public class ProducerConsumerMain {

    public static void main(String[] args) {

        List<String> buffer=new ArrayList<>();

        //In place of synchronised
        ReentrantLock bufferLock=new ReentrantLock();

        ExecutorService executorService= Executors.newFixedThreadPool(3);

        MyProducer producer=new MyProducer(buffer,ThreadColor.ANSI_GREEN,bufferLock);
        MyConsumer consumer=new MyConsumer(buffer,ThreadColor.ANSI_PURPLE,bufferLock);

        //Instead of
        //new Thread(producer).start();
        //We do
        executorService.execute(producer);
        //new Thread(consumer).start();
        executorService.execute(consumer);
        //new Thread(new MyConsumer(buffer,ThreadColor.ANSI_BLUE,bufferLock)).start();
        executorService.execute(new MyConsumer(buffer,ThreadColor.ANSI_BLUE,bufferLock));
/*
         Future<String> future= executorService.submit(new Callable<String>() {
             @Override
             public String call() throws Exception {
                 return null;
             }
         });*/

        //Executor service should be shutdown when we nolonger need it
        executorService.shutdown();

        //Executor service is very useful for an application with a large number of threads


    }

}
