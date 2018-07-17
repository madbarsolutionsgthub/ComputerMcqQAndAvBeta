package madbarsoft.com.computermcqqa.selftest;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computermcqqa.R;
import madbarsoft.com.computermcqqa.main.MainService;
import madbarsoft.com.computermcqqa.main.QuestionAnswerModel;
import madbarsoft.com.computermcqqa.utility.INextBtnClickListener;

public class SelfTestActivity extends AppCompatActivity implements INextBtnClickListener {

    private int numberOfCorrectAns;
    private int categoryId;
    private int takenQuestions;
    private List<QuestionAnswerModel> questionAndAnsList;
    private QuestionAnswerModel questionAnswerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test);
        questionAndAnsList = new ArrayList<>();
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", 10);
        try {
            questionAndAnsList = new MainService().getQuestionAndAnsListFromJson(this, categoryId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        questionAnswerModel = questionAndAnsList.get(0);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SelfTestHolderFragment mcqTestFragment = new SelfTestHolderFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("takenQuestions", takenQuestions);
        bundle.putInt("currentDataPosition", 0);
        bundle.putSerializable("questionAnswerModel", questionAnswerModel);
        mcqTestFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.selfTestFragmentContainerId, mcqTestFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void nextData(QuestionAnswerModel questionAnswerModel, int currentDataPosition, int isCorrectAns, int isFinishTest) {
        if (isFinishTest == 0) {
            takenQuestions++;
            this.numberOfCorrectAns += isCorrectAns;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SelfTestHolderFragment selfTestHolderFragment = new SelfTestHolderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("currentDataPosition", currentDataPosition);
            bundle.putInt("takenQuestions", takenQuestions);
            bundle.putInt("numberOfCorrectAns", numberOfCorrectAns);
            bundle.putSerializable("questionAnswerModel", questionAnswerModel);
            selfTestHolderFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.selfTestFragmentContainerId, selfTestHolderFragment);
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, "Exam End", Toast.LENGTH_SHORT).show();
            this.numberOfCorrectAns += isCorrectAns;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SelfTestHolderFinishFragment selfTestHolderFinishFragment = new SelfTestHolderFinishFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("currentDataPosition", currentDataPosition);
            bundle.putInt("takenQuestions", takenQuestions);
            bundle.putInt("numberOfCorrectAns", numberOfCorrectAns);
            bundle.putSerializable("questionAnswerModel", questionAnswerModel);
            selfTestHolderFinishFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.selfTestFragmentContainerId, selfTestHolderFinishFragment);
            fragmentTransaction.commit();

        }
    }
}