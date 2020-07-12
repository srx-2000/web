package dao.implement;

import dao.IPaperDao;
import domain.Admin;
import domain.Paper;
import until.untils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-07 03:06:54
 */
public class PaperDao implements IPaperDao {
    @Override
    public boolean insertPaper(Paper paper) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "insert into paper(title,adminId,createDate,openDate,closeDate) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, paper.getTitle());
            preparedStatement.setObject(2, paper.getAdminId());
            preparedStatement.setObject(3, paper.getCreateDate());
            preparedStatement.setObject(4, paper.getOpenDate());
            preparedStatement.setObject(5, paper.getCloseDate());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement);
        }
        return false;
    }


    @Override
    public boolean deletePaper(int adminId, int paperId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "UPDATE paper SET is_live=0 WHERE adminId=? and paperId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, adminId);
            preparedStatement.setObject(2, paperId);
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
    public Long paperCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long count = Long.valueOf(0);
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT COUNT(*) FROM paper and is_live=1";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            count = (Long) resultSet.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<Paper> showPaperById(int adminid) {
        List<Paper> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "select * from paper where adminId=? and is_live=1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,adminid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Paper paper = new Paper();
                String title = resultSet.getString("title");
                int paperId = resultSet.getInt("paperId");
                int adminId = resultSet.getInt("adminId");
                String createDate = resultSet.getObject("createDate").toString();
                String openDate = resultSet.getObject("openDate").toString();
                String closeDate = resultSet.getObject("closeDate").toString();
                int is_live=resultSet.getInt("is_live");
                paper.setAdminId(adminId);
                paper.setTitle(title);
                paper.setCloseDate(closeDate);
                paper.setCreateDate(createDate);
                paper.setPaperId(paperId);
                paper.setOpenDate(openDate);
                paper.setIs_live(is_live);
                list.add(paper);
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
    public Object findPaperId(String title, String openDate, String closeDate, int adminId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object paperId = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT paperId FROM paper WHERE title=? AND openDate=? AND closeDate=? AND adminId=? and is_live=1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, title);
            preparedStatement.setObject(2, openDate);
            preparedStatement.setObject(3, closeDate);
            preparedStatement.setObject(4, adminId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                paperId = resultSet.getObject("paperId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return paperId;
    }

    @Override
    public boolean isExists(String title, String openDate, String closeDate, int adminId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT paperId FROM paper WHERE title=? AND openDate=? AND closeDate=? AND adminId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, title);
            preparedStatement.setObject(2, openDate);
            preparedStatement.setObject(3, closeDate);
            preparedStatement.setObject(4, adminId);
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
    public Paper findPaper(String title, String openDate, String closeDate, int adminId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT * FROM paper WHERE title=? AND openDate=? AND closeDate=? AND adminId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, title);
            preparedStatement.setObject(2, openDate);
            preparedStatement.setObject(3, closeDate);
            preparedStatement.setObject(4, adminId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Paper paper = new Paper();
                String Title = resultSet.getString("title");
                int paperid = resultSet.getInt("paperId");
                int adminid = resultSet.getInt("adminId");
                String createdate = resultSet.getObject("createDate").toString();
                String opendate = resultSet.getObject("openDate").toString();
                String closedate = resultSet.getObject("closeDate").toString();
                paper.setAdminId(adminid);
                paper.setTitle(Title);
                paper.setCloseDate(closedate);
                paper.setCreateDate(createdate);
                paper.setPaperId(paperid);
                paper.setOpenDate(opendate);
                return paper;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public boolean updateIs_live(Paper paper) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "UPDATE paper SET is_live=1 WHERE adminId=? and paperId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, paper.getAdminId());
            preparedStatement.setObject(2, paper.getPaperId());
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
    public int getIs_live(Paper paper) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int is_live = 0;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT is_live FROM paper WHERE title=? AND openDate=? AND closeDate=? AND adminId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, paper.getTitle());
            preparedStatement.setObject(2, paper.getOpenDate());
            preparedStatement.setObject(3, paper.getCloseDate());
            preparedStatement.setObject(4, paper.getAdminId());
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
}
