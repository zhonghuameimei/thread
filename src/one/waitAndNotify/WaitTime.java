package one.waitAndNotify;

public class WaitTime implements Runnable{

    private Object object = new Object();

    @Override
    public void run() {
        synchronized (object){
            while (true){
                try {
                    System.out.println("wait time one second!");
                    object.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new WaitTime()).start();
    }
}
