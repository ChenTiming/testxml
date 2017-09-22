import java.util.Date;

/**
 * Created by chentm on 2017/2/10.
 */
public class TestTime {
    public static void main(String args[]){
        System.out.println(new Date());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }
}
