package one.waitAndNotify;

public class YieldDemo {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            run();
        });

        Thread thread1 = new Thread(() -> {
            run();
        });

        thread.interrupt();
        thread.start();
        thread1.start();
    }

    public static void run(){
        for (int i = 0; i < 10; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread() + ": " + i);
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + " is over");
    }
}
