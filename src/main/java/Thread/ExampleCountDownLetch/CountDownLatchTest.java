package Thread.ExampleCountDownLetch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by chentm on 2017/2/14.
 */
public class CountDownLatchTest implements Runnable {

    public long timeTasks(int threeds, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threeds);

        for (int i = 0; i < threeds; i++) {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        System.out.println("end");
        long end = System.nanoTime();


        return end - start;
    }

    public void run() {
        try {
            System.out.println("--lalalal--");
            Thread.sleep(new Random().nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        CountDownLatchTest task = new CountDownLatchTest();
        long time = task.timeTasks(3,task);
        System.out.print(time);
    }
}
