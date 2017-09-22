import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by chentm on 2017/5/31.
 */
public class TestUrlEncode {
    public static void main(String args[]){
        String s = null;
        String content = "haha你好";
        String encodeContent = "";
        String decodeContent = "";
        try {
            encodeContent = URLEncoder.encode(content,"utf-8");
            decodeContent = URLDecoder.decode(encodeContent,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("content length is " + s.length());
        System.out.println("encode content is " + encodeContent);
        System.out.println("decode content is " + decodeContent);
    }
}
