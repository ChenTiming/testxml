import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by chentm on 2016/9/21.
 */
public class TestLog {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

//    public static void main(String[] args) {
//        FileHandler fileHandler = null;
//        try {
//            fileHandler = new FileHandler("testlog%g.log");
//            LOGGER.addHandler(fileHandler);
//            fileHandler.setLevel(Level.INFO);
//
//            LOGGER.info("Logging an INFO-level message");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
