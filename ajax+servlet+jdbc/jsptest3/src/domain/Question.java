package domain;

/**
 * @author srx
 * @description
 * @create 2020-06-06 15:21:14
 */
public class Question {
    private int questionId;
    private String questionContent;
    private int paperId;
    private int questionType;
    private int questionOrder;
    int is_live;

    public Question() {
    }

    public Question(int questionId, String questionContent, int paperId, int questionType, int questionOrder, int is_live) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.paperId = paperId;
        this.questionType = questionType;
        this.questionOrder = questionOrder;
        this.is_live = is_live;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    public int getIs_live() {
        return is_live;
    }

    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }
}
