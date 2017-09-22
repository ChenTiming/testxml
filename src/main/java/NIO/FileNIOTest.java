package NIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chentm on 2017/9/18.
 */
public class FileNIOTest {

    public static void main(String args[]){
        RandomAccessFile readFile = null;
        RandomAccessFile writeFile = null;
        try {
            readFile = new RandomAccessFile("F:\\readFile.txt","r");
            FileChannel readChannel = readFile.getChannel();

            writeFile = new RandomAccessFile("F:\\writeFile.txt","rw");
            FileChannel writeChannel = writeFile.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            ByteBuffer headBuffer = ByteBuffer.allocate(128);
            ByteBuffer bodyBuffer = ByteBuffer.allocate(1024);
            ByteBuffer[] bufferArray = {headBuffer,bodyBuffer};
            long readLen = readChannel.read(bufferArray);
            while (readLen != -1){
                readBuffer.flip();
                writeChannel.write(readBuffer);
                while(readBuffer.hasRemaining()){
                    System.out.print((char)readBuffer.get());
                }
                readBuffer.compact();
                readLen = readChannel.read(readBuffer);
            }
            writeChannel.close();
            readChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(readFile != null){
                    readFile.close();
                }
            } catch (IOException e){

            }

        }
    }
}
