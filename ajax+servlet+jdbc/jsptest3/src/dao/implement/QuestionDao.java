package dao.implement;

import com.google.gson.Gson;
import dao.IQuestionDao;
import domain.Count;
import domain.Question;
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
 * @create 2020-06-07 03:50:20
 */
public class QuestionDao implements IQuestionDao {
    @Override
    public boolean insertQuestion(Question question) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "INSERT INTO question(questionContent,paperId,questionType,questionOrder) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, question.getQuestionContent());
            preparedStatement.setObject(2, question.getPaperId());
            preparedStatement.setObject(3, question.getQuestionType());
            preparedStatement.setObject(4, question.getQuestionOrder());
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
    public boolean deleteQuestion(int questionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "UPDATE question SET is_live=0 where questionId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, questionId);
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
    public Long questionCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long count = Long.valueOf(0);
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT COUNT(*) FROM qustion and is_live=1";
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
    public Long questionCount(Select select) {
        return null;
    }

    @Override
    public boolean updateIs_live(Question question) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "UPDATE question SET is_live=1 WHERE questionId=? and paperId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, question.getQuestionId());
            preparedStatement.setObject(2, question.getPaperId());
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
    public int getIs_live(int questionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int is_live = 0;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT is_live FROM question WHERE questionId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, questionId);
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
    public boolean isExists(int questionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT questionId FROM question WHERE questionId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, questionId);
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
    public List<Question> showQuestionById(int paperId) {
        List<Question> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "select * from question where paperId=? and is_live=1 order by questionId asc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, paperId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                String questionContent = resultSet.getString("questionContent");
                int paperid = resultSet.getInt("paperId");
                int questionId = resultSet.getInt("questionId");
                int questionType = resultSet.getInt("questionType");
                int questionOrder = resultSet.getInt("questionOrder");
                int is_live = resultSet.getInt("is_live");
                question.setIs_live(is_live);
                question.setPaperId(paperid);
                question.setQuestionContent(questionContent);
                question.setQuestionId(questionId);
                question.setQuestionOrder(questionOrder);
                question.setQuestionType(questionType);
                list.add(question);
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
    public boolean updateQuestion(Question question) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "UPDATE question SET questionContent=?,questionType=?,questionOrder=? WHERE questionId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, question.getQuestionContent());
            preparedStatement.setObject(2, question.getQuestionType());
            preparedStatement.setObject(3, question.getQuestionOrder());
            preparedStatement.setObject(4, question.getQuestionId());
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
    public Object getQuestionId(Question question, int paperId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object questionId = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "SELECT questionId FROM question WHERE questionType=? AND questionContent=? AND questionOrder=? and is_live=1 and paperId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, question.getQuestionType());
            preparedStatement.setObject(2, question.getQuestionContent());
            preparedStatement.setObject(3, question.getQuestionOrder());
            preparedStatement.setObject(4, paperId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                questionId = resultSet.getObject("questionId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement, resultSet);
        }
        return questionId;
    }

    @Override
    public boolean updateOrder(int order, int questionId, int childorder, int prevquestionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            String sql = "UPDATE question SET questionOrder=? WHERE questionId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, order);
            preparedStatement.setObject(2, questionId);
            preparedStatement.addBatch();
            preparedStatement.setObject(1, childorder);
            preparedStatement.setObject(2, prevquestionId);
            preparedStatement.addBatch();
            if (preparedStatement.executeBatch().length > 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            untils.close(connection, preparedStatement);
        }
        return false;
    }

    public String count(String Paper_Id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet res;
        try {
            untils untils = new untils();
            connection = untils.getConnection();
            PreparedStatement prst = connection.prepareStatement("select a.questionId from question a where a.paperId=?");
            prst.setString(1, Paper_Id);
            res = prst.executeQuery();
            System.out.println("查询成功");
            //读入数据
            ArrayList<String> questionId = new ArrayList<>();
            while (res.next()) {
                questionId.add(res.getString("questionId"));
            }
            ArrayList<Count> count = new ArrayList<>();
            for (int i = 0; i < questionId.size(); i++) {
                PreparedStatement an = connection.prepareStatement("select count(User_Id) Number,a.Select_id,b.questionContent,c.selectContent from `count` a join question b on a.Question_Id=b.questionId join `select` c on c.selectId=a.Select_id where a.Paper_Id=? and a.Question_Id=? GROUP BY a.Select_id");
                an.setString(1, Paper_Id);
                an.setString(2, questionId.get(i));
                res = an.executeQuery();
                while (res.next()) {
                    //第一个是问题，第二个是选项，第三个是人数
                    Count l = new Count(questionId.get(i), res.getString("Select_id"), res.getString("Number"));
                    l.setQuestionContent(res.getString("questionContent"));
                    l.setSelectContent(res.getString("selectContent"));
                    count.add(l);
                }
            }
            //返回的字符串
            String ww = "";
            Gson gson = new Gson();
            ww = gson.toJson(count);
            return ww;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

}
