/**
 * Created by chentm on 2016/11/16.
 */
import org.apache.commons.httpclient.*;

import org.apache.commons.httpclient.auth.*;
import org.apache.commons.httpclient.methods.*;

import java.io.File;
import java.io.IOException;

public class TestJenkinsBuild {

    public static void  buidJob(){
        String server = "citest.hundsun.com";
        String jenkinsHost = "http://citest.hundsun.com/jenkins/";

        String username = "chentm";//chentm
        String apiToken = "ecf1c698535bfa36c99356f97f558568";

        HttpClient client = new HttpClient();
        client.getState().setCredentials(
                new AuthScope( server, 80, "realm"),
                new UsernamePasswordCredentials( username, apiToken )
        );

        // Jenkins does not do any authentication negotiation,
        // ie. it does not return a 401 (Unauthorized)
        // but immediately a 403 (Forbidden)
        client.getParams().setAuthenticationPreemptive(true);//authenticationPreemptive = true;

        PostMethod post = new PostMethod( jenkinsHost+ "job/Python_X64/build?delay=0sec");
        post.setDoAuthentication(true);//doAuthentication = true;

        //File input = new File(configurationFile);
        //RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=UTF-8");
        //post.setRequestEntity(entity);
        try {
            try {
                System.out.println("-----------------");
                int result = client.executeMethod(post);
                System.out.println("return code is " + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //println "Return code: ${result}"
            //post.responseHeaders.each{ println it.toString().trim() }

            //println post.getResponseBodyAsString();
        } finally {
            post.releaseConnection();
        }
    }

    public static void main(String args[]){
        TestJenkinsBuild.buidJob();
    }
}
