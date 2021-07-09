package five;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始 remove");
            copyOnWriteArrayList.remove(1);
        });

        Iterator iterator = copyOnWriteArrayList.iterator();

        thread.start();

        thread.join();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(copyOnWriteArrayList.size());
    }
}
