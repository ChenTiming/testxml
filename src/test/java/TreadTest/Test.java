package TreadTest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * Created by chentm on 2017/9/13.
 */
public class Test {

    //    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    //public static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    public static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//"com.microsoft.jdbc.sqlserver.SQLServerDriver";

    //    public static final String URL = "jdbc:mysql://127.0.0.1:3306/chentm?serverTimezone=UTC";
    //public static final String URL = "jdbc:oracle:thin:@192.168.75.76:1521:hsoradb";
    public static final String URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=testuser;";

    //jdbc:oracle:thin:@localhost:1521:orcl
    public static final String USERNAME = "proceTest";//"system";//"root";
    public static final String PASSWORD = "Aaa111111";//"oracle";//"r#dcenter9";

    @org.junit.Test
    public void testProce(){
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws Exception
    {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql = "{CALL dbo.PR_sum(1,2,?)}"; //调用存储过程
        CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm
//        cstm.setInt(1, 1); //存储过程输入参数
//        cstm.setInt(2, 2);
        //cstm.setInt(2, 2); // 存储过程输入参数
        //cstm.registerOutParameter(1, Types.INTEGER); // 设置返回值类型 即返回值
        cstm.registerOutParameter(1, Types.VARCHAR);
//        cstm.registerOutParameter(2, Types.VARCHAR);
        cstm.execute(); // 执行存储过程

        //System.out.println(cstm.getString(2));
        System.out.println(cstm.getString(1));
        cstm.close();
        connection.close();
    }


}
