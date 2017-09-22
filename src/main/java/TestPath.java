import java.io.*;

/**
 * Created by chentm on 2016/10/9.
 */
public class TestPath {

    public static void main(String args[]){
        TestPath testPath = new TestPath();
        testPath.getFile();
    }

    public void getFile(){
        InputStream inputStream = this.getClass().getResourceAsStream("ceph.conf");
        //System.out.println(this.getClass().getResource(""));
        //System.out.println(this.getClass().getResource(""));
        if(inputStream == null){
            System.out.println("inputStream is null");
        }
        File x = new File("xx.txt");
        inputStreamToFile(inputStream,x);
    }

    public void inputStreamToFile(InputStream ins,File file){
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
