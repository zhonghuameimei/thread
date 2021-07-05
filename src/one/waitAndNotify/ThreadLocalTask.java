package one.waitAndNotify;

public class ThreadLocalTask {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            threadLocal.set(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": "+ threadLocal.get());
        });

        Thread threadB = new Thread(() -> {
            threadLocal.set(2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": "+ threadLocal.get());
        });

        threadA.start();
        threadB.start();

        Thread.sleep(3000);
        System.out.println(threadLocal.get());
    }
}
