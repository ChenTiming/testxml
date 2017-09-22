package ThreadTest;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chentm on 2017/8/21.
 */
public class CyclicBarrierPlayer implements Runnable{

    private CyclicBarrier cyclicBarrier;
    private String playerName;
    private long execTime;

    public CyclicBarrierPlayer(CyclicBarrier cyclicBarrier,String name,long time){
        this.cyclicBarrier = cyclicBarrier;
        this.playerName = name;
        this.execTime = time;
    }

    public void run() {
        try {
            System.out.println("Player: " + this.playerName + " 开始准备");
            //Thread.sleep(execTime*1000);
            System.out.println("Player: " + this.playerName + " 准备就绪");
            try {
                cyclicBarrier.await();
                System.out.println("终于大家都好了");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println("通知各位都好了");
            }
        });
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 3; i++){
            CyclicBarrierPlayer cyclicBarrierPlayer = new CyclicBarrierPlayer(cyclicBarrier,"palyer"+i,i+1);
            exec.execute(cyclicBarrierPlayer);
        }
        exec.shutdown();
    }
}
