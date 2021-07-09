package six;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 不可重入锁
 */
public class NonReentrantLock implements Lock, java.io.Serializable {

    private static class Sync extends AbstractQueuedSynchronizer{
        //判断锁是否被持有
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }

        //如果没有持有锁，尝试获取
        public boolean tryAcquire(int acquires){
            assert acquires == 1;
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //尝试释放锁
        public boolean tryRelease(int releases){
            assert releases == 1;
            if (getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //提供条件变量接口
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    private static Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

class NonReentrantLockDemo{
    static final NonReentrantLock nonReentrantLock = new NonReentrantLock();
    static final Condition full = nonReentrantLock.newCondition();
    static final Condition empty = nonReentrantLock.newCondition();

    static final Queue<String> queue = new LinkedBlockingQueue<>();

    static final int linkSize = 10;

    public static void producer() throws InterruptedException {
        nonReentrantLock.lock();
        try {
            //当队列长度大于指定长度
            while (queue.size() >= linkSize){
                System.out.println("满了.......");
                full.await();
            }
            System.out.println(Thread.currentThread().getName() + "：创造...");
            queue.add("ele");
            empty.signal();
        }finally {
            nonReentrantLock.unlock();
        }
    }

    public static void consume() throws InterruptedException {
        nonReentrantLock.lock();
        try {
            //当队列长度为空时
            while (queue.size() == 0){
                System.out.println("空了.......");
                empty.await();
            }
            System.out.println(Thread.currentThread().getName() + "：消费...");
            queue.poll();
            full.signal();
        }finally {
            nonReentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        NonReentrantLockDemo nonReentrantLockDemo = new NonReentrantLockDemo();
        new Thread(() -> {
            try {
                while (true){
                    nonReentrantLockDemo.producer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"producer").start();

        new Thread(() -> {
            try {
                while (true){
                    nonReentrantLockDemo.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consume").start();
    }
}
