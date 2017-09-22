/**
 * Created by chentm on 2017/6/27.
 */
public class TestFloat {

    public static void main(String args[]){
        String x = "1.1.7";
        String z = x.replace(".","");
        int y = Integer.valueOf(z);
        System.out.println("args = [" + y + "]");
    }
}
