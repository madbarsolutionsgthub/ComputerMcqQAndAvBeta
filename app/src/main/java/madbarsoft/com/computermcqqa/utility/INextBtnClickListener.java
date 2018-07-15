package madbarsoft.com.computermcqqa.utility;

import madbarsoft.com.computermcqqa.main.QuestionAnswerModel;

public interface INextBtnClickListener {
    void nextData(QuestionAnswerModel auestionAnswerModel, int currentDataPosition, int isCorrectAns, int isFinishTest);
}