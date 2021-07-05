package one.waitAndNotify;

public class lnheritableThreadLocalTask {

    static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(5);
        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+": "+ threadLocal.get());
            threadLocal.set(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": "+ threadLocal.get());
        });

        Thread threadB = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+": "+ threadLocal.get());
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
