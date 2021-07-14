package six;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {

    public static void main(String[] args) {
        System.out.println("park start");

        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();

        System.out.println("park end");
    }
}
