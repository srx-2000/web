package domain;

/**
 * @author srx
 * @description
 * @create 2020-06-06 15:23:18
 */
public class Select {
    private int selectId;
    private int questionId;
    private String selectContent;
    int is_live;

    public Select() {
    }

    public Select(int selectId, int questionId, String selectContent, int is_live) {
        this.selectId = selectId;
        this.questionId = questionId;
        this.selectContent = selectContent;
        this.is_live = is_live;
    }

    public int getSelectId() {
        return selectId;
    }

    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSelectContent() {
        return selectContent;
    }

    public void setSelectContent(String selectContent) {
        this.selectContent = selectContent;
    }

    public int getIs_live() {
        return is_live;
    }

    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }

    @Override
    public String toString() {
        return "Select{" +
                "selectId=" + selectId +
                ", questionId=" + questionId +
                ", selectContent='" + selectContent + '\'' +
                ", is_live=" + is_live +
                '}';
    }
}