package com.chandalala.deadlocks;

public class Worker {

    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(SharedResource sharedResource, Worker otherWorker){

        while (active){
            //Thread is gona check if owns the resource
            if (sharedResource.getOwner() != this){
                try {
                    wait(10);//if it doesnt it will wait for 10 sec and check again
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                continue;
            }
            //if it has acquired the resource it will check if other worker is active
            if (otherWorker.isActive()){
                System.out.println(getName()+" : give the resource to the worker "+otherWorker.getName());
                sharedResource.setOwner(otherWorker);
                continue;
            }

            System.out.println(getName() +": working on the common resource");
            active=false;
            sharedResource.setOwner(otherWorker);
        }
    }
}
