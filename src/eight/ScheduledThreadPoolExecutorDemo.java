package eight;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch  = new CountDownLatch(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                System.out.println("A");
            }finally {
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println("B");
            }finally {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        System.out.println("C");
    }
}
