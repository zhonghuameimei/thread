package seven;

import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {

        System.out.println(1 + Math.pow(10,2) );

        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        queue.add(new Task(1,"1"));
        queue.add(new Task(5,"5"));
        queue.add(new Task(3,"3"));
        queue.add(new Task(2,"2"));

        queue.stream().forEach(task -> System.out.println(task.getTaskName()));
    }

}

class Task implements Comparable<Task>{

    private int priority;

    private String taskName;


    public Task(){}

    public Task(int priority, String taskName){
        this.priority = priority;
        this.taskName = taskName;
    }

    @Override
    public int compareTo(Task task) {
        if (this.priority >= task.priority){
            return 1;
        }
        return -1;
    }

    public int getPriority(){
        return priority;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
}
