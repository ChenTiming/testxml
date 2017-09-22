package NIO;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * Created by chentm on 2017/9/19.
 */
public class SelectorTest {

    public static void main(String args[]){
        try {
            Selector selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

