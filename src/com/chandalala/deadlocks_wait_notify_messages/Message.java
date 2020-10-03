package com.chandalala.deadlocks_wait_notify_messages;

public class Message {

    private String message;
    private boolean empty =true;

    //this will be used by the consumer to read a message
    public synchronized String read(){
        while (empty){
        //to prevent a deadlock
            try {
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        empty =true;
        notifyAll();
        return message;
    }
    //THE CASE IS WE DONT WANT A THREAD TO READ A MESSAGE WHILST ANOTHER ONE IS STILL WRITING THE MESSAGE AND VICE VERSA

    //this will be used by the producer to write a message
    public synchronized void write(String message){
        while (!empty){
            //to prevent a deadlock
            try {
                wait();//tells the current thread to wait until notified
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        empty =false;
        this.message=message;
        notifyAll();
    }

}
