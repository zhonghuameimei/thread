package one.createThread;

public class ThreadTask extends Thread{

    @Override
    public void run() {
        System.out.println("I am a child thread");
    }

    public static void main(String[] args) {
        new ThreadTask().start();
    }
}
