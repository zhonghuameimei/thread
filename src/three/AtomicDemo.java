package three;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicDemo {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(1);
        System.out.println(atomicLong.getAndIncrement());
        System.out.println(atomicLong.get());
    }
}
