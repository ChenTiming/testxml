package UsagesClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentm on 2017/9/14.
 */
public class TestOne {
    public int i;
    public String s;
    List<String> list = new ArrayList<String>();

    public TestOne(int num,String str){
        i = num;
        s = str;
    }

    public List getList(){
        return this.list;
    }
}
