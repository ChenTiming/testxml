import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

public class FileCharsetDetector {
    private boolean found = false;
    private String encoding = null;

    public static void main(String[] argv) throws Exception {
        String path = "F:\\tmpFile\\linuxUtf_8";
        File file1 = new File(path);
        FileCharsetDetector fileCharsetDetector = new FileCharsetDetector();
        String coding = fileCharsetDetector.guessFileEncoding(file1);
        System.out.println(coding);
        if(coding.contains("UTF-8")){
            coding = "UTF-8";
            System.out.println(fileCharsetDetector.readFile(path,coding));
        }
        else{
            coding = "GBK";
            System.out.println(fileCharsetDetector.readFile(path,coding));
        }
    }

    /**
     * 传入一个文件(File)对象，检查文件编码
     *
     * @param file
     *            File对象实例
     * @return 文件编码，若无，则返回null
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String guessFileEncoding(File file) throws FileNotFoundException, IOException {
        return guessFileEncoding(file, new nsDetector());
    }

    /**
     * <pre>
     * 获取文件的编码
     * @param file
     *            File对象实例
     * @param languageHint
     *            语言提示区域代码 @see #nsPSMDetector ,取值如下：
     *             1 : Japanese
     *             2 : Chinese
     *             3 : Simplified Chinese
     *             4 : Traditional Chinese
     *             5 : Korean
     *             6 : Dont know(default)
     * </pre>
     *
     * @return 文件编码，eg：UTF-8,GBK,GB2312形式(不确定的时候，返回可能的字符编码序列)；若无，则返回null
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String guessFileEncoding(File file, int languageHint) throws FileNotFoundException, IOException {
        return guessFileEncoding(file, new nsDetector(languageHint));
    }

    /**
     * 获取文件的编码
     *
     * @param file
     * @param det
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private String guessFileEncoding(File file, nsDetector det) throws FileNotFoundException, IOException {
        // Set an observer...
        // The Notify() will be called when a matching charset is found.
        det.Init(new nsICharsetDetectionObserver() {
            public void Notify(String charset) {
                encoding = charset;
                found = true;
            }
        });

        BufferedInputStream imp = new BufferedInputStream(new FileInputStream(file));
        byte[] buf = new byte[1024];
        int len;
        boolean done = false;
        boolean isAscii = false;

        while ((len = imp.read(buf, 0, buf.length)) != -1) {
            // Check if the stream is only ascii.
            isAscii = det.isAscii(buf, len);
            if (isAscii) {
                break;
            }
            // DoIt if non-ascii and not done yet.
            done = det.DoIt(buf, len, false);
            if (done) {
                break;
            }
        }
        imp.close();
        det.DataEnd();

        if (isAscii) {
            encoding = "ASCII";
            found = true;
        }

        if (!found) {
            String[] prob = det.getProbableCharsets();
            //这里将可能的字符集组合起来返回
            for (int i = 0; i < prob.length; i++) {
                if (i == 0) {
                    encoding = prob[i];
                } else {
                    encoding += "," + prob[i];
                }
            }

            if (prob.length > 0) {
                // 在没有发现情况下,也可以只取第一个可能的编码,这里返回的是一个可能的序列
                return encoding;
            } else {
                return null;
            }
        }
        return encoding;
    }

    public  String readFile(String filePath,String encoding){
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            //System.out.println(code);
            fileInputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[1024];
            int readLen;
            if(fileInputStream!=null) {
                while ((readLen=fileInputStream.read(bytes)) > 0) {
                    stringBuffer.append(new String(new String(bytes,0, readLen,encoding)));
                }
                fileInputStream.close();
            }
            //System.out.println(stringBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public  String readFile(FileInputStream fileInputStream,String encoding){
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            //System.out.println(code);
            byte[] bytes = new byte[1024];
            int readLen;
            if(fileInputStream!=null) {
                while ((readLen=fileInputStream.read(bytes)) > 0) {
                    stringBuffer.append(new String(new String(bytes,0, readLen,encoding)));
                }
                fileInputStream.close();
            }
            //System.out.println(stringBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}