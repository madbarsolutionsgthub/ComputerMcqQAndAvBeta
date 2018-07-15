package madbarsoft.com.computermcqqa.selftest;

import java.util.Date;

public class SelfTestModel {

    private int categoryId;
    private int takenQuestions;
    private int numberOfCorrectAns;
    private Date testDate;


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTakenQuestions() {
        return takenQuestions;
    }

    public void setTakenQuestions(int takenQuestions) {
        this.takenQuestions = takenQuestions;
    }

    public int getNumberOfCorrectAns() {
        return numberOfCorrectAns;
    }

    public void setNumberOfCorrectAns(int numberOfCorrectAns) {
        this.numberOfCorrectAns = numberOfCorrectAns;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }
}


