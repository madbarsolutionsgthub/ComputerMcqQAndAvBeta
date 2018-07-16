package madbarsoft.com.computermcqqa.selftest;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computermcqqa.MainActivity;
import madbarsoft.com.computermcqqa.R;
import madbarsoft.com.computermcqqa.category.CategoryModel;
import madbarsoft.com.computermcqqa.category.CategoryService;
import madbarsoft.com.computermcqqa.main.AnswerModel;
import madbarsoft.com.computermcqqa.main.MainService;
import madbarsoft.com.computermcqqa.main.QuestionAnswerModel;
import madbarsoft.com.computermcqqa.utility.INextBtnClickListener;

public class SelfTestHolderFragment extends Fragment {
    private Button btnNext, btnHome;
    private RadioGroup radioBtnGroupId;
    private INextBtnClickListener iNextBtnClickListener;
    private Context context;
    private int currentDataPosition = 0;
    private int isCorrectAns = 0;
    private int takenQuestions = 0;
    private int numberOfCorrectAns = 0;
    private int currentAnsId;
    private QuestionAnswerModel questionAnswerModel;
    private List<QuestionAnswerModel> questionAndAnsList;
    private CategoryModel currentCategory;
    private List<CategoryModel> categoryList = new ArrayList<>();


    public SelfTestHolderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        iNextBtnClickListener = (INextBtnClickListener) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        questionAnswerModel = (QuestionAnswerModel) getArguments().getSerializable("questionAnswerModel");
        currentDataPosition = getArguments().getInt("currentDataPosition");
        takenQuestions = getArguments().getInt("takenQuestions");
        numberOfCorrectAns = getArguments().getInt("numberOfCorrectAns");

        try {
            questionAndAnsList = new MainService().getQuestionAndAnsListFromJson((AppCompatActivity) context, questionAnswerModel.getCategoryId());
            categoryList = new CategoryService().getCategoryJsonData((AppCompatActivity) this.getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (CategoryModel category : categoryList) {
            if (category.getId() == questionAnswerModel.getCategoryId()) {
                this.currentCategory = category;
            }
        }
        View vu = inflater.inflate(R.layout.fragment_self_test_holder, container, false);
        radioBtnGroupId = (RadioGroup)vu.findViewById(R.id.mcqRadioBtnGroupId);

        TextView testQuestionTitleTV = vu.findViewById(R.id.testQuestionTitleTV);
        TextView currentStatusTV = vu.findViewById(R.id.currentStatusTV);
        TextView categoryNameHolderTV = vu.findViewById(R.id.categoryNameHolderTV);

        if(questionAnswerModel !=null){
            if(currentCategory !=null){
                categoryNameHolderTV.setText(currentCategory.getTitle().toString());
            }
            currentStatusTV.setText("Question : "+takenQuestions+" of "+questionAndAnsList.size());
            testQuestionTitleTV.setText(questionAnswerModel.getTitle());
            for(AnswerModel obj:questionAndAnsList.get(currentDataPosition).getMcqAnswerModelList()){
                if(obj.getIsRightAns()==1){
                    currentAnsId = obj.getId();
                }
                final RadioButton  radioButton = new RadioButton(context);
                radioButton.setText(obj.getTitle().toString());
                radioButton.setId(obj.getId());
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(radioButton.getId()==currentAnsId) {
                            isCorrectAns = 1;
                            Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                        } else{
                            isCorrectAns = 0;
                            Toast.makeText(getContext(), "False Answer" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                radioBtnGroupId.addView(radioButton);
            }
        }

        btnNext = (Button)vu.findViewById(R.id.btnNextId);
        btnHome = (Button)vu.findViewById(R.id.btnHomeId);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((questionAndAnsList.size()-1) != currentDataPosition) {
                    iNextBtnClickListener.nextData(questionAndAnsList.get((currentDataPosition + 1)), (currentDataPosition + 1), isCorrectAns, 0);
                    return;
                }
                iNextBtnClickListener.nextData(questionAndAnsList.get((currentDataPosition)), (currentDataPosition), isCorrectAns,1);
                btnNext.setEnabled(false);
                btnNext.setBackgroundColor(Color.GRAY);

            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Do you want quit from Test ?");
                //  builder.setMessage("This will be closed application");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent inten = new Intent(context, MainActivity.class );
                        inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(inten);
                    }
                });
                AlertDialog alertDialog = builder.show();
            }
        });
        return vu;
    }



}
