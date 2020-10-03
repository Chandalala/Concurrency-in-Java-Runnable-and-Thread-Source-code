package com.chandalala.deadlocks;

public class MoreDeadlocks {

    public static void main(String[] args) {

        final PolitePerson person=new PolitePerson("Tinashe");
        final PolitePerson person1=new PolitePerson("Chanda");

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.sayHello(person1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person1.sayHello(person);
            }
        }).start();


    }

    //1. Thread1 acquires the lock on the Jane object and enters the sayHello() method. It prints to the console and the suspends
    //2. Thread2 acquires the lock on the John object and enters the sayHello() method. It prints to the console then suspends
    //3. Thread1 runs again and wants to say hello back to the John object. It tries to call the sayHelloBack() method using the
    //   john object that was passed into the sayHello() method but Thread2 is holding the john lock so Thread1 suspends
    //4. Thread2 runs again and wants to say he llo back to the Jane object. It tries to call the sayHelloBack() method using the
    //   jane object that was passed into the sayHello() method but Thread1 us holding the jane lock so Thread2 suspends

    static class PolitePerson{

        private final String name;

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person){

            System.out.format("%s: %s"+" has said hello to me!%n",this.name,person.getName());
            person.sayHelloBack(this);//this refers to Tinashe
        }

        public synchronized void sayHelloBack(PolitePerson person){

            System.out.format("%s: %s"+" has said hello back to me!%n",this.name,person.getName());
        }
    }
}
