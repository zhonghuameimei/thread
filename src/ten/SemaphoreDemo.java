package ten;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            System.out.println(Thread.currentThread()+" over");
            semaphore.release();
        });

        executorService.submit(() -> {
            System.out.println(Thread.currentThread()+" over");
            semaphore.release();
        });

        semaphore.acquire(2);
        System.out.println("all child thread over");
        executorService.shutdown();
    }
}

