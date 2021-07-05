package one.waitAndNotify;

public class DaemoThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (;;);
        });

        thread.setDaemon(true);
        thread.start();
        System.out.println("end...");
    }
}
