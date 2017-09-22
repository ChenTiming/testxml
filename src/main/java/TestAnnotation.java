import Annotation.Man;
import Annotation.MemberNme;

import java.lang.annotation.Annotation;

/**
 * Created by chentm on 2017/5/5.
 */
@Man (name = "libai",age = 20)
public class TestAnnotation {

    public MemberNme name;

    public static void main(String args[]){
        TestAnnotation testAnnotation = new TestAnnotation();
        System.out.println(testAnnotation.name);
        Annotation[] annotations = TestAnnotation.class.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof Man){
                Man man = (Man) annotation;
                System.out.println(man.name());
            }
        }
    }
}
