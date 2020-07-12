package dao.implement;

import dao.ISelectDao;
import domain.Select;
import until.untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-07 04:03:30
 */
public class SelectDao implements ISelectDao {
    @Override
    public boolean insertSelect(Select select) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "INSERT INTO `select`(questionId,selectContent) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,select.getQuestionId());
            preparedStatement.setObject(2,select.getSelectContent());
            if(preparedStatement.execute())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            untils.close(connection,preparedStatement);
        }
        return false;
    }

    @Override
    public boolean deleteSelect(int selectId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql ="UPDATE `select` SET is_live=0 where selectId=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,selectId);
            if (preparedStatement.execute())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            untils.close(connection,preparedStatement);
        }
        return false;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public boolean updateIs_live(Select select) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "UPDATE `select` SET is_live=1 WHERE selectId=? and questionId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, select.getSelectId());
            preparedStatement.setObject(2, select.getQuestionId());
            if (preparedStatement.execute())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public int getIs_live(int selectId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int is_live = 0;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT is_live FROM `select` WHERE selectId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, selectId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                is_live = resultSet.getInt("is_live");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return is_live;
    }

    @Override
    public boolean isExists(int selectId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT selectId FROM `select` WHERE selectId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, selectId);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public List<Select> showSelectById(int questionId) {
        List<Select> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "select * from `select` where questionId=? and is_live=1 order by selectId asc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,questionId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Select select=new Select();
                String selectContent = resultSet.getString("selectContent");
                int questionid = resultSet.getInt("questionId");
                int selectId = resultSet.getInt("selectId");
                int is_live=resultSet.getInt("is_live");
                select.setIs_live(is_live);
                select.setQuestionId(questionid);
                select.setSelectContent(selectContent);
                select.setSelectId(selectId);
                list.add(select);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public boolean updateSelect(Select select) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql ="UPDATE `select` SET selectContent=? WHERE selectId=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,select.getSelectContent());
            preparedStatement.setObject(2,select.getSelectId());
            if (preparedStatement.execute())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            untils.close(connection,preparedStatement);
        }
        return false;
    }

    @Override
    public Object getSelectId(Select select, int questionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object selectId = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT selectId FROM `select` WHERE selectContent=? and is_live=1 and questionId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, select.getSelectContent());
            preparedStatement.setObject(2, questionId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                selectId = resultSet.getObject("selectId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return selectId;
    }
}
