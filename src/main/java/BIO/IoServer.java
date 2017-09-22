package BIO;

import java.net.ServerSocket;
import java.net.Socket;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
  
/** 
 * 服务端 
 * @author jason 
 * 
 */  
public class IoServer implements Runnable{  
    ExecutorService threadPool = Executors.newCachedThreadPool();  
    public void run() {
        try{  
            //建立服务端监听套接字，绑定的端口号是9090  
            ServerSocket serverSocket = new ServerSocket(9090);       
            while(true){  
                //监听客户端的的连接请求，会一直阻塞直到建立一个连接，并返回已连接的套接字  
                Socket socket = serverSocket.accept();  
                //为每一个连接从线程池分配一个线程  
                threadPool.execute(new Handler(socket));  
            }
        }catch(Exception e){  
            e.printStackTrace();  
        }  
          
    }  
      
    //处理线程  
    class Handler implements Runnable{  
        private Socket socket;        
        Handler(Socket s){  
            this.socket = s;  
        }  
        public void run() {
              
            try{  
                byte[] input = new byte[1024];  
                //读取客户端的请求数据，如果没有数据，会一直阻塞  
                socket.getInputStream().read(input);  
                //数据处理  
                System.out.println(new String(input));  
                //发送响应  
                socket.getOutputStream().write("nice to meet you too".getBytes());  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
              
        }  
          
    }  
      
    //启动服务端  
    public static void main(String[] args){  
        new Thread(new IoServer()).start();  
    }  
}  