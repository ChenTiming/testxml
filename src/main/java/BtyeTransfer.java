import java.io.UnsupportedEncodingException;

/**
 * Created by chentm on 2016/12/9.
 */
public class BtyeTransfer {

    public static String x = "test content \"lalala\"\n" +
            "this is testfile contentzhong\n" +
            "涓\uE15F枃瀛楃\uE0C1\n";

    public static void main(String args[]){
        try {
            byte[] b = BtyeTransfer.x.getBytes("gbk");
            String sa = new String(b, "utf-8");
            System.out.println(sa);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

