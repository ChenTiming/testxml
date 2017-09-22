package Practise;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chentm on 2017/8/30.
 */
public class Practise1 {
    public static volatile int countIndex = 0;
    private static volatile int currentThreadIndex = 0;
    private static volatile int currentThreadCount = 1;

    public static CyclicBarrier c = new CyclicBarrier(4);
    public Lock lock = new ReentrantLock();
    public static void main(String args[]){
        Task[] tasks = new Task[3];
        Practise1 practise1 = new Practise1();
        //BlockingQueue b = new B;
        //ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            Task task = practise1.createTask("task"+(i+1),i+1);
            executorService.submit(task);
        }

        while(true){
            if(countIndex>75){
                executorService.shutdown();
                return;
            }
        }
    }

    public Task createTask(String name,int index){
        return new Task(name, index);
    }


    public class Task implements Runnable{
        public String name;
        public int threadIndex;
        public Task(String name,int threadIndex){
            this.name = name;
            this.threadIndex = threadIndex;
        }

        public void run() {
            while(true) {
                if(currentThreadCount%3 == threadIndex ||
                        (currentThreadCount%threadIndex == 0 && threadIndex == 3)){
                    try {
                        lock.lock();
                        currentThreadIndex = threadIndex;
                        currentThreadCount++;
                        for (int j = 0; j < 5; j++) {
                            if (countIndex > 75) {
                                break;
                            }
                            System.out.println(name + ":" + countIndex);
                            countIndex++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }


}
