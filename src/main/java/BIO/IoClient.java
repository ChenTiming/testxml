package BIO;

import java.io.IOException;
import java.net.InetAddress;  
import java.net.Socket;  
import java.net.UnknownHostException;  
  
public class IoClient{  
    public static void main(String[] args) throws UnknownHostException, IOException{  
        byte[] input = new byte[1024];  
        //连接服务端  
        Socket socket = new Socket(InetAddress.getByName("localhost"), 9090);  
        //发送请求  
        socket.getOutputStream().write("nice to meet you".getBytes());  
        //读取响应数据  
        socket.getInputStream().read(input);  
        System.out.println(new String(input));  
        //关闭连接  
        socket.close();  
    }  
}  