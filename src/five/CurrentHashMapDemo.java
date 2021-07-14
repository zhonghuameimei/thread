package five;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class CurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1",1);

        new Thread(() -> {
            concurrentHashMap.put("2",2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        concurrentHashMap.values().forEach(System.out::println);
    }
}
