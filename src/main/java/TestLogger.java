/**
 * Created by chentm on 2016/9/21.
 */
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TestLogger {
    private static Logger logger = Logger.getLogger(TestLogger.class);
    public  static void main(String args[]){
//        logger.setLevel(Level.INFO);
//
//        logger.info("xxxx");
        int a = 9;
        int b = 1;
        String x = "xx";
        String u = x + (a+b);
        System.out.println(u);

    }
}
