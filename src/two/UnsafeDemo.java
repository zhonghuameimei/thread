package two;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    static Unsafe unsafe;

    static long stateOffset;

    private volatile long state = 0;

    //获取 unsafe
    static {
        try {
            //使用反射获取 unsafe 的成员变量 theUnsafe
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            //设置为可存取
            theUnsafe.setAccessible(true);
            //获取该变量的值
            unsafe = (Unsafe)theUnsafe.get(null);
            //获取 state 在 UnsafeDemo 中的偏移量
            stateOffset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boolean b = unsafe.compareAndSwapInt(new UnsafeDemo(), stateOffset, 0, 1);
        System.out.println(b);
    }
}
