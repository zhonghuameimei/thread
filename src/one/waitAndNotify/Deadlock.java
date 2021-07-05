package one.waitAndNotify;

public class Deadlock {

    private static Object objectA = new Object();
    private static Object objectB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (objectA){
                System.out.println("threadA get objectA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadA wait objectB release...");
                synchronized (objectB){
                    System.out.println("threadA get objectB");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (objectB){
                System.out.println("threadB get objectB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB wait objectA release...");
                synchronized (objectA){
                    System.out.println("threadB get objectA");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
