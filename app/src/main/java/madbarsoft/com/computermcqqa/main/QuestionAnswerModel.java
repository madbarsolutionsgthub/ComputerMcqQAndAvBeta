package madbarsoft.com.computermcqqa.main;

import java.io.Serializable;
import java.util.List;

public class QuestionAnswerModel implements Serializable{

    private int id;
    private int categoryId;
    private String title;
    private List<AnswerModel> answerModelList;

    public QuestionAnswerModel(int id, int categoryId, String title, List<AnswerModel> answerModelList) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.answerModelList = answerModelList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnswerModel> getMcqAnswerModelList() {
        return answerModelList;
    }

    public void setMcqAnswerModelList(List<AnswerModel> mcqAnswerModelList) {
        this.answerModelList = mcqAnswerModelList;
    }
}
