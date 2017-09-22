import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chentm on 2016/10/18.
 */
public class SSHManager {

    private JSch jSchChannel;

    private int port;

    private String ip;

    private String username;

    private String password;

    private int timeout;

    private Session session;

    private final int DEFAULT_TIMEOUT = 6000;

    public SSHManager(String ip, int port, String username, String password){
        jSchChannel = new JSch();
        try {
            jSchChannel.setKnownHosts("");
        } catch (JSchException e) {
            e.printStackTrace();
        }

        this.port = port;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.timeout = DEFAULT_TIMEOUT;
    }

    public String connect(){
        String errMsg = null;
        // TODO: 2016/10/18 execptions handle
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            session = jSchChannel.getSession(username,ip,port);
            session.setConfig(config);
            session.setPassword(password);
            session.connect();
        } catch (JSchException e) {
            //e.printStackTrace();
            errMsg = e.getMessage();
        }
        return errMsg;
    }

    public void close(){
        session.disconnect();
    }

    public String execCommand(String command){
        System.out.println("start exec rbd map");
        StringBuilder output = new StringBuilder();
        try {
            Channel channel = session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);

            InputStream commandOutput = channel.getInputStream();
            channel.connect();

            int readByte = commandOutput.read();
            while(readByte != 0xFFFFFFFF){
                output.append((char)readByte);
                readByte = commandOutput.read();
                System.out.print((char)readByte);
            }
            System.out.println("exec command ok");
            channel.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e){

        }

        return output.toString();
    }




}
