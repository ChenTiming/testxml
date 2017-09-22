package NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by chentm on 2017/9/19.
 */
public class ChannelTransfer {

    public static void main(String args[]){
        //transferTo();
        transferFrom();
    }

    public static void transferTo(){
        try {
            RandomAccessFile fromFile = new RandomAccessFile("F:\\readFile.txt","rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("F:\\trnasferToFile.txt","rw");
            FileChannel toChannel = toFile.getChannel();

            int position = 0;
            long size = fromChannel.size();

            fromChannel.transferTo(position,size,toChannel);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void transferFrom(){
        try {
            RandomAccessFile fromFile = new RandomAccessFile("F:\\readFile.txt","rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("F:\\trnasferToFile.txt","rw");
            FileChannel toChannel = toFile.getChannel();

            int position = 0;
            long size = fromChannel.size();

            toChannel.transferFrom(fromChannel,position,size);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
