package com.chandalala.thread_variables_counter_threads;

import com.chandalala.ThreadColor;

public class Countdown {

    /**
     *     All threads share a memory location called a heap
     *     Every thread has a thread stack and that's the memory that the thread can only access
     *     this means thread 1 cannot access thread 2's stack and vice versa but they can both access the heap
     *     local variables are stored in a thread's stack that means a thread has its own copy of a local variable
     *     instance variables are however stored in the heap, in that regard, threads share that same object
     *     When one thread changes the value of an instance variable and gets suspended, the other thread will see the
     *     new value of the instance variable this is called THREAD INTERFERENCE or A RACE CONDITION
     *     There is no problem when threads are reading a resource, problems arise when one or more threads is updating the resource
     *     A way to solve in the case below, we can create two separate Countdown objects which we can then pass to our threads classes
     *
     *     i.e  Countdown countdown1 = new Countdown()
     *          Countdown countdown2 = new Countdown()
     *          or use local variable
     *     solution however does not work in real world applications
     *
     *     A good method is to use synchronisation, this determines when a thread can change a value in the heap
     *     We can synchronise methods and statements, and a thread can execute one at a time
     *     When a synchronised method in class is running, all other methods in that class will not run until that methods exits
     *     for example if a class has three synchronised methods, then only one of these methods will ever run at a time
     *     This prevents thread interference within the synchronised methods and not outside those methods
     *     To synchronise a method, just add the synchronised keyword to a method declaration
     *     Constructors cant be synchronised
     *
     *     Another way to prevent race conditions is
     *     We can synchronise a block of statements rather the the whole method
     *     Every java OBJECT has an intrinsic lock, this reference can be seen as a monitor
     *     primitives types do not have this lock such as (i) below
     *     We can synchronise a block of statement that work with an object by forcing a thread to acquire this lock before
     *     executing the statement block and only one thread can hold the lock at a time until it releases it
     *     in our case below, the for loop seems to be the only block of code that we can synchronise
     *     remember not to use local variables to synchronise a block of code
     *     String color below cannot be used to synchronise a block of code because its a local variable
     *     Keep synchronised blocks of code to a minimum
     *
     *     methods that can only be called within synchronised blocks or methods are notify, notifyAll and wait methods
     *     classical example for this is the consumer and producer scenario
     */


    private int i;

    //this method will run before another thread gets access to it
    public /*synchronized*/ void doCountdown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color= ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color=ThreadColor.ANSI_PURPLE;
                break;
            default:
                color=ThreadColor.ANSI_GREEN;
        }
    //CRITICAL SECTION
    //Synchronising a block of code to prevent a race condition
    //    synchronized (color){ does not work because color is a local variable
    //    synchronized (i){ does not work because (i) is not an object
        synchronized (this){//referring the Countdown object, static objects can also be used because they are owned by the class
            for (i=10;i>0;i--){
                System.out.println(color +Thread.currentThread().getName()+": i="+i);
            }
        }

    }
}
