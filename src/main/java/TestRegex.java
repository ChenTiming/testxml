/**
 * Created by chentm on 2017/3/2.
 */
public class TestRegex {

    public static void main(String args[]){
        if(isLetterDigit("_asd-f_")){
            System.out.println("yes");
        }

    }
    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z_-]+$";
        return str.matches(regex);
    }

}
