import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chentm on 2017/1/3.
 */
public class SelfStream extends FileInputStream{

    public SelfStream(String name) throws FileNotFoundException {
        super(name);
    }

    public boolean markSupported(){
        return true;
    }
}
