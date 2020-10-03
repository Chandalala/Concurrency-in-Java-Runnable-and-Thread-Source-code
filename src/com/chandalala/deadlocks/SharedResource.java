package com.chandalala.deadlocks;

public class SharedResource {

    private Worker owner;

    public SharedResource(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }

    //synchronised normaly used when changing a value so on get methods is not useful
    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }

}
