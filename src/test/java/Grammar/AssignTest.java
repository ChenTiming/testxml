package Grammar;

import UsagesClass.TestOne;
import org.junit.Test;

import java.util.List;


/**
 * Created by chentm on 2017/9/14.
 */
public class AssignTest {

    @Test
    public void test(){
        TestOne t1 = new TestOne(1,"aa");
        List tmp = t1.getList();
        tmp.add("hello");
        deal(tmp);
        System.out.println(t1.getList().toString());
    }

    public static void deal(List<String> list){
        System.out.println(list.toString());

        list.add("word");
    }
}
