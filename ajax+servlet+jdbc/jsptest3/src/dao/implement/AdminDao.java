package dao.implement;

import dao.IAdminDao;
import domain.Admin;
import until.untils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author srx
 * @description
 * @create 2020-06-06 16:08:02
 */
public class AdminDao extends Thread implements IAdminDao{

    @Override
    public Admin findAdmin(String adminName, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        ResultSetMetaData resultSetMetaData = null;
        try {
            untils untils=new untils();
            connection = untils.getConnection();
            String sql = "select * from admin where adminName=? and password=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1, adminName);
            preparedStatement.setString(2, password);
            resultset = preparedStatement.executeQuery();
            resultSetMetaData = resultset.getMetaData();
            if (resultset.next()) {
                Admin admin = Admin.class.newInstance();
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    Object value = resultset.getObject(i + 1);
                    String Label = resultSetMetaData.getColumnLabel(i + 1);
                    Field field = Admin.class.getDeclaredField(Label);
                    field.setAccessible(true);
                    field.set(admin, value);
                }
                return admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultset);
        }
        return null;
    }


    @Override
    public Boolean addAdmin(Admin admin) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            untils untils=new untils();
            connection=untils.getConnection();
            String sql ="INSERT INTO admin(adminName,`password`,realName) values(?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
//            preparedStatement.setObject(1,admin.getAdminId());
            preparedStatement.setObject(1,admin.getAdminName());
            preparedStatement.setObject(2,admin.getPassword());
            preparedStatement.setObject(3,admin.getRealName());
            if (preparedStatement.execute())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            untils.close(connection,preparedStatement);
        }
        return false;
    }

}
