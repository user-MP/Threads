package org.example;
import java.util.concurrent.locks.ReentrantLock;


class MyThread extends Thread{


    private ReentrantLock lock;;
    public MyThread(String name){
        super(name);

        lock=new ReentrantLock();

    }
    @Override
    public void run() {

         String nameThread=Thread.currentThread().getName();

        try {
            Thread.sleep(3000);// STATE WAITING
            for(int i=0;i<10;i++){
                System.out.println(nameThread+" = "+i);
            }

            lock.lock(); // BLOCKED THREAD
            System.out.println("Thread "+nameThread+" BLOCKED");


            lock.unlock();
            System.out.println("Thread "+nameThread+" unlocked");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}





public class Main {
    public static void main(String[] args) throws InterruptedException {



        System.out.println("Main thread started...");
        System.out.println("Main thread state "+Thread.currentThread().getState());
        MyThread thread1= new MyThread("MyThread1");
        System.out.println("Thread 1 state "+ thread1.getState());
        MyThread thread2= new MyThread("MyThread2");
        thread1.start();
        Thread.sleep(1000);
        System.out.println("STATE thread 1 after sleep main thread(1000) "+thread1.getState());

        Thread.sleep(5000);
        System.out.println("Thread 1 has a state after sleep main thread(5000) "+thread1.getState()); // TERMINATED for this time thread 1 will be executed
        thread2.start();

        System.out.println("Main thread finished...");


    }
}