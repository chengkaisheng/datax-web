package com.wugui.datax.admin.datashare.tools;

import java.sql.*;

public class ConnectUtil {
    /*private static final String URL = "jdbc:mysql://59.202.16.231:3306/db_019_res?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
    static final String USER = "dbquery";
    static final String PASSWORD = "db@2019";*/
   private static final String URL = "jdbc:mysql://localhost:3306/db_019_res?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
   static final String USER = "root";
   static final String PASSWORD = "root";
    //获取连接对象
    public static Connection getConnection(){
        Connection conn = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection getNewConnection(String url,String user,String password,String driver){
        Connection conn = null;
        try {
            //1.加载驱动程序
            Class.forName(driver);
            //2. 获得数据库连接
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 关闭连接，释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void CloseConn(Connection conn, PreparedStatement st, ResultSet rs) {
        try{
            if(rs != null) {
                rs.close();
            }
            if(st != null) {
                st.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
