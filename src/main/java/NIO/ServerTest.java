package NIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by chentm on 2017/9/19.
 */
public class ServerTest {

    public static void main(String args[]){
        server();
    }

    public static void server(){
        ServerSocket serverSocket = null;
        InputStream in = null;
        try
        {
            serverSocket = new ServerSocket(8080);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while(true){
                Socket clntSocket = serverSocket.accept();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client at "+clientAddress);
                //in = clntSocket.getInputStream();
                SocketChannel recvChannel =  clntSocket.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (recvChannel.read(buffer)!=-1){
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.print((char)buffer.get());
                    }
                    buffer.clear();
                }
//                while((recvMsgSize=in.read(recvBuf))!=-1){
//                    byte[] temp = new byte[recvMsgSize];
//                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
//                    System.out.println(new String(temp));
//                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(serverSocket!=null){
                    serverSocket.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}
