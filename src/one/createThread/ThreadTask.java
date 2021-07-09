package one.createThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTask extends Thread{

    @Override
    public void run() {
        System.out.println("I am a child thread");
    }

    public static void main(String[] args) {
        new ThreadTask().start();
    }
}
