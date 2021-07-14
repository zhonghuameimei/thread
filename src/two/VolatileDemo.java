package two;

public class VolatileDemo {

    private static boolean isFlag = false;
    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            isFlag = true;
            num = 2;
        }).start();


        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (isFlag) {
                    System.out.println(num + num);
                }
                System.out.println("read thread....");
            }
        });

        thread.start();

        Thread.sleep(1);
        thread.interrupt();
        System.out.println("over");
    }
}
