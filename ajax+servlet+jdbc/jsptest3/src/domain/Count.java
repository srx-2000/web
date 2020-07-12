package domain;

/**
 * @author srx
 * @description
 * @create 2020-06-20 16:19:25
 */
public class Count {
    private String Question_Id;
    private String Selecgt_Id;
    private String num;
    private String selectContent;
    private String questionContent;

    public String getSelectContent() {
        return selectContent;
    }

    public void setSelectContent(String selectContent) {
        this.selectContent = selectContent;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Count() {
    }

    public Count(String question_Id, String selecgt_Id, String num) {
        Question_Id = question_Id;
        Selecgt_Id = selecgt_Id;
        this.num = num;
    }

    public String getQuestion_Id() {
        return Question_Id;
    }

    public void setQuestion_Id(String question_Id) {
        Question_Id = question_Id;
    }

    public String getSelecgt_Id() {
        return Selecgt_Id;
    }

    public void setSelecgt_Id(String selecgt_Id) {
        Selecgt_Id = selecgt_Id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "count{" +
                "Question_Id='" + Question_Id + '\'' +
                ", Selecgt_Id='" + Selecgt_Id + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}

