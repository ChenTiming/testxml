/**
 * Created by chentm on 2017/6/14.
 */
public class TestStaticThread {
    static{
        Thread x = new Thread(){
            public void run(){
                while(true) {
                    try {
                        sleep(500);
                        System.out.println("I am running");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        x.setDaemon(true);
        x.start();
    }
    public static void main(String args[]){
        int a = 0;
        System.out.printf("I am main");
        
        while (true){
            a++;
        }
    }
}
