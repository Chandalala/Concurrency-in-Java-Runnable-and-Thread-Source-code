package com.chandalala.deadlocks_wait_notify_messages;

import java.util.Random;

public class Writer implements Runnable {

    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String[] messages={
                "Humpty dumpty sat on a wall",
                "Humpty dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn;t put Humpty together again",
        };

        Random random= new Random();

        for (int i=0;i<=messages.length;i++){
            message.write(messages[i]);
            try {
                //Thread can sleep for any number of random of milliseconds
                // ranging from 0-2000 after writing a message to give the consumer a chance to read the message
                Thread.sleep(random.nextInt(2000));
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        message.write("Finished!");
    }
}
