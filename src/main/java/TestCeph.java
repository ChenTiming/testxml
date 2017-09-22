///**
// * Created by chentm on 2016/10/8.
// */
//import com.ceph.rados.IoCTX;
//import com.ceph.rados.Rados;
//import com.ceph.rados.exceptions.RadosException;
//import com.ceph.rbd.Rbd;
//import com.ceph.rbd.RbdException;
//
//import java.io.File;
//import java.util.Arrays;
//import java.util.List;
//
public class TestCeph {
//
//    private static Rados rados;
//    private static String ENV_ID = System.getenv("RADOS_JAVA_ID");
//    private static final String CONFIG_FILE ="resource/ceph.conf";
//    private static final String ID = ENV_ID == null ? "admin" : ENV_ID;
//    private static IoCTX ioctx;
//    private static final String POOL = "pool_test";
//    private static final String IMAGE = "image_test";
//    private static final int IMAGE_SIZE = 0x400*0x0400;
//
//
//    public static String command = "rbd map pool_test/image_test";
//    public static String userName = "root";
//    public static String password = "r#dcenter9";
//    public static String connectionIP = "192.168.48.103";
//    public static int port = 22;
//
//
//    public static void main(String args[]){
//        TestCeph testCeph = new TestCeph();
//        testCeph.buildConnect();
//        testCeph.createPool(POOL);
//        testCeph.createImage(POOL,IMAGE);
//        System.out.println("create image ok");
//        SSHManager instance = new SSHManager(connectionIP, port, userName, password);
//        instance.connect();
//        instance.execCommand(command);
//    }
//
//    public void buildConnect(){
//        if(rados == null) {
//            rados = new Rados(ID);
//            try {
//                //String projectPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//                rados.confSet("mon_host", "192.168.48.101:6789");
//                rados.confSet("keyring",  "/etc/ceph/ceph.client.admin.keyring");
//                rados.connect();
//                //if()
//            } catch (RadosException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void disconnect(){
//        if(rados != null ){
//            rados.shutDown();
//        }
//    }
//
//    /**
//     *  @param poolName
//     *      poolName -> mysqlServiceId
//     */
//    public void createPool(String poolName){
//        if(rados == null){
//            buildConnect();
//        }
//        try {
//            rados.poolCreate(poolName);
//        } catch (RadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void createImage(String poolName,String imageName){
//        if(rados == null){
//            buildConnect();
//        }
//        try {
//            IoCTX ioCTX = rados.ioCtxCreate(poolName);
//            Rbd rbd = new Rbd(ioCTX);
//            rbd.create(imageName,IMAGE_SIZE,1,0);
////            rbd.cr
//            //rbd.
//
//        } catch (RadosException e) {
//            e.printStackTrace();
//        } catch (RbdException e){
//
//        }
//    }
//
//    public void deletePool(String poolName){
//        if(rados == null){
//            buildConnect();
//        }
//        try {
//            rados.poolDelete(poolName);
//        } catch (RadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteImage(String poolName,String imageName){
//
//        if(rados == null){
//            buildConnect();
//        }
//
//        IoCTX ioCTX = null;
//        try {
//            ioCTX = rados.ioCtxCreate(poolName);
//        } catch (RadosException e) {
//            e.printStackTrace();
//        }
//        Rbd rbd = new Rbd(ioCTX);
//        try {
//            rbd.remove(imageName);
//        } catch (RbdException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public List<String> testEmptyListImages(){
//        if(rados == null){
//            buildConnect();
//        }
//        try {
//            Rbd rbd = new Rbd(ioctx);
//            List<String> imageList = Arrays.asList(rbd.list());
//            if(imageList != null && imageList.size() >0){
//                for(String image : imageList){
//                    System.out.println(image);
//                }
//            }
//            return imageList;
//        } catch (RbdException e) {
//            return null;
//        }
//    }
//
//
//    public void testPath(){
//        String x = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        System.out.println("path is "+ x);
//    }
}
