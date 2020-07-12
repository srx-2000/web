package dao;

import domain.Paper;
import domain.Question;
import domain.Select;

import javax.management.Query;
import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-07 03:41:56
 */
public interface IQuestionDao {

    /**
     * 用于添加问题
     *
     * @param question
     * @return
     */
    boolean insertQuestion(Question question);

    /**
     * 删除问题
     * @param questionId
     * @return
     */
    boolean deleteQuestion(int questionId);

    /**
     * 统计问题数目
     *
     * @return 返回问题数目
     */
    Long questionCount();

    /**
     * 用于统计一道题目，不同用户选出来的相同选项的数目
     *
     * @param select
     * @return
     */
    Long questionCount(Select select);

    /**
     * 作用同paperdao中的同名方法
     *
     * @param question
     * @return
     */
    boolean updateIs_live(Question question);

    /**
     * 作用同paperdao中的同名方法
     * @param questionId
     * @return
     */
    int getIs_live(int questionId);

    /**
     * 作用同paperdao中的同名方法
     *
     * @param questionId
     * @return
     */
    boolean isExists(int questionId);

    /**
     * 返回全部问题
     *
     * @param paperId
     * @return
     */
    List<Question> showQuestionById(int paperId);

    /**
     * 用来更新问题的，主要用在问题的编辑上
     * @param question
     * @return
     */
    boolean updateQuestion(Question question);

    /**
     * 用来获取当前question的id
     * @param question
     * @return
     */
    Object getQuestionId(Question question,int paperId);

    /**
     * 用来更新问卷顺序的
     * @param order
     * @param questionId
     * @return
     */
    boolean updateOrder(int order,int questionId,int childorder,int prevquestionId);
    String count(String paperId);
}
