package ten;

import java.util.concurrent.*;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
            System.out.println("cyclicBarrier reset");
        });

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> {
            try {
                System.out.println("child threadOne start!");
                cyclicBarrier.await();
                System.out.println("child threadOne end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println("child threadTwo start!");
                cyclicBarrier.await();
                System.out.println("child threadTwo end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        System.out.println(cyclicBarrier.getParties());
        executorService.shutdown();
    }
}
