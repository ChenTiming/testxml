package ThreadTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chentm on 2017/6/15.
 */
public class ReentrantLockT {

    public static int i = 0;
    public static ReentrantLock t = new ReentrantLock();

    public static void main(String args[]){

        int x = 0;
        Thread t1 = new Thread(){
            public void run(){
                t.lock();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.unlock();
                System.out.println("got it ");
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                t.lock();
                System.out.println("t2 got it ");
            }
        };
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("end");

    }
}
