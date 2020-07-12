package service.implement;

import dao.IQuestionDao;
import dao.implement.QuestionDao;
import domain.Question;
import domain.Select;
import service.IQuestionService;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-17 03:52:59
 */
public class QuestionService implements IQuestionService {
    IQuestionDao questionDao=new QuestionDao();
    @Override
    public boolean insertQuestion(Question question) {
        return questionDao.insertQuestion(question);
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        return questionDao.deleteQuestion(questionId);
    }

    @Override
    public Long questionCount() {
        return null;
    }

    @Override
    public Long questionCount(Select select) {
        return null;
    }

    @Override
    public boolean updateIs_live(Question question) {
        return questionDao.updateIs_live(question);
    }

    @Override
    public int getIs_live(int questionId) {
        return questionDao.getIs_live(questionId);
    }

    @Override
    public boolean isExists(int questionId) {
        return questionDao.isExists(questionId);
    }

    @Override
    public List<Question> showQuestionById(int paperId) {
        return questionDao.showQuestionById(paperId);
    }

    @Override
    public boolean updateQuestion(Question question) {
        return questionDao.updateQuestion(question);
    }

    @Override
    public int getQuestionId(Question question,int paperId) {
        String questionId=questionDao.getQuestionId(question,paperId).toString();
        return Integer.parseInt(questionId);
    }

    @Override
    public boolean updateOrder(int order, int questionId, int childorder, int prevquestionId) {
        return questionDao.updateOrder(order,questionId,childorder,prevquestionId);
    }

    @Override
    public String count(String paperId) {
        return questionDao.count(paperId);
    }


}
