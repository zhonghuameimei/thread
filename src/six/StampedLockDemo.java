package six;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    public static void main(String[] args) {
        Point point = new Point();
        point.move(1,2);
        System.out.println(point.distanceFromOrigin());
    }
}

class Point {

    private double x, y;

    StampedLock stampedLock = new StampedLock();

    void move(double deltaX, double deltaY){
        long stamp = stampedLock.writeLock();
        try {
            x = deltaX;
            y = deltaY;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin(){
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = this.x;
        double currentY = this.y;
        if (!stampedLock.validate(stamp)){
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    void moveIfAtOrigin(double newX, double newY){
        //获取读锁
        long stamp = stampedLock.readLock();
        try {
            while (x == 0.0 && y == 0.0){
                //升级写锁
                long stampW = stampedLock.tryConvertToWriteLock(stamp);
                if (stampW != 0L){
                    stamp = stampW;
                    x = newX;
                    y = newY;
                    break;
                }else {
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        }finally {
            stampedLock.unlock(stamp);
        }
    }

}
