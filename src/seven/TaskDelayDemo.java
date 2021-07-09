package seven;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TaskDelayDemo{

    public static void main(String[] args) {
        DelayQueue<TaskDelay> delayQueue = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            TaskDelay delay = new TaskDelay(random.nextInt(500),"task："+i);
            delayQueue.offer(delay);
        }

        TaskDelay taskDelay = null;
        try {
            for (;;){
                while (true){

                        if (!((taskDelay = delayQueue.take()) != null)) break;
                    System.out.println(taskDelay.toString());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TaskDelay implements Delayed {

    private long delayTime;

    private long expire;

    private String taskName;

    public TaskDelay(long delayTime, String taskName) {
        this.delayTime = delayTime;
        this.expire = System.currentTimeMillis() + delayTime;
        this.taskName = taskName;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "TaskDelay{" +
                "delayTime=" + delayTime +
                ", expire=" + expire +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
