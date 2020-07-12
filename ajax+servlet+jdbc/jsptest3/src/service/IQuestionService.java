package service;

import domain.Question;
import domain.Select;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-17 03:52:13
 */
public interface IQuestionService {
    /**
     * 用于添加问题
     *
     * @param question
     * @return
     */
    boolean insertQuestion(Question question);

    /**
     * 用于删除问题
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
     * 用来获取该question的id
     * @param question
     * @return
     */
    int getQuestionId(Question question,int paperId);
    /**
     * 用来更新问卷顺序的
     * @param order
     * @param questionId
     * @return
     */
    boolean updateOrder(int order,int questionId,int childorder,int prevquestionId);

    /**
     * 用来统计各个题的答题情况
     * @param paperId
     * @return
     */
    String count(String paperId);
}
