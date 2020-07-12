package until;

import domain.Admin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

/**
 * @author srx
 * @description
 * @create 2020-06-06 15:01:44
 */
public class untils {
    public untils() {
    }
    public Connection getConnection(){
        InputStream inputStream=null;
        try {
            inputStream=untils.class.getClassLoader().getResourceAsStream("until//driver.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
//            properties.load(inputStream);
            String username=properties.getProperty("username");
            String password=properties.getProperty("password");
            String driver=properties.getProperty("driver");
            String url=properties.getProperty("url");
            Connection conn=DriverManager.getConnection(url,username,password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void dicConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection connection, PreparedStatement preparedStatement){
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet){
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
