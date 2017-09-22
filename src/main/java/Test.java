import util.TestPoJo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentm on 2017/6/13.
 */
public class Test {
    public static void main(String args[]){
        List<String> list = new ArrayList<String>(0);
        String srcPowerCode = "ASSETTYPE_QUERY";
        String srcType = "ASSETTYPE";
        String destType = "ASSET";
        String newPower = powerCodeChange(srcPowerCode,srcType,destType);
        System.out.println(srcPowerCode.replaceAll("ASSETTYPE",""));
        System.out.println("srcPowerCode:" + srcPowerCode + " new: + " + newPower);
    }

    private static String powerCodeChange(String srcPowerCode,String srcType,String destType){

        return srcPowerCode.replace(srcType, destType);
    }
}
