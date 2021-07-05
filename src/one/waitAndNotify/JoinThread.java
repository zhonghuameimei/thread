package one.waitAndNotify;

public class JoinThread {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("threadA start");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("ThreadB start");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        System.out.println("main Thread start");

        threadA.join();
        threadB.join();
        System.out.println("main Thread end");
    }
}
