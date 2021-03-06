package madbarsoft.com.computermcqqa.selftest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

import org.json.JSONException;

import java.io.IOException;
import java.util.Date;

import madbarsoft.com.computermcqqa.MainActivity;
import madbarsoft.com.computermcqqa.R;
import madbarsoft.com.computermcqqa.category.CategoryModel;
import madbarsoft.com.computermcqqa.category.CategoryService;
import madbarsoft.com.computermcqqa.selftesthistory.ShelfTestHistoryActivity;
import madbarsoft.com.computermcqqa.utility.ChartService;


public class SelfTestHolderFinishFragment extends Fragment {
    Button btnHome, btnShowAllTest;
    private Context context;
    private int categoryId=0;
    private int numberOfCorrectAns=0;
    private int takenQuestions;
    PieChart pieChart;
    ChartService chartService;
    SelfTestService selfTestService;
    SelfTestModel selfTestModel;
    private CategoryModel currentCategory;


    public SelfTestHolderFinishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        categoryId = getArguments().getInt("categoryId");
        takenQuestions = getArguments().getInt("takenQuestions");
        numberOfCorrectAns = getArguments().getInt("numberOfCorrectAns");
        chartService = new ChartService();

        selfTestModel = new SelfTestModel();
        selfTestModel.setCategoryId(categoryId);
        selfTestModel.setTakenQuestions(takenQuestions);
        selfTestModel.setNumberOfCorrectAns(numberOfCorrectAns);
        selfTestModel.setTestDate(new Date());

        try {
            currentCategory = new CategoryService().getCategoryById((AppCompatActivity) this.getActivity(), categoryId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        selfTestService = new SelfTestService(context.getApplicationContext(), selfTestModel.getCategoryId());
        selfTestService.createSelfTestResultToShp(selfTestModel.getCategoryId(), selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns(), selfTestModel.getTestDate());
     //   selfTestModel = selfTestService.getSelfTestResultFromShp();

        Toast.makeText(context, "Taken Question: "+selfTestModel.getTakenQuestions()+"\nCorrect Ans : "+selfTestModel.getNumberOfCorrectAns()+"\nCategory Id"+selfTestModel.getCategoryId(), Toast.LENGTH_SHORT).show();


        View vu = inflater.inflate(R.layout.fragment_self_test_holder_finish, container, false);
        TextView categoryNameHolderTV = vu.findViewById(R.id.categoryNameHolderTV);
        TextView currentStatusTV = vu.findViewById(R.id.currentStatusTV);

        categoryNameHolderTV.setText(currentCategory.getTitle());
        currentStatusTV.setText("Correct Answer: "+numberOfCorrectAns+" Of "+takenQuestions);


        pieChart = (PieChart) vu.findViewById(R.id.selfTestResultPieChardHolderPC);
        chartService.setPieChart(pieChart, takenQuestions, numberOfCorrectAns, categoryId);


        btnHome = (Button)vu.findViewById(R.id.btnHomeId);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(context, MainActivity.class );
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
            }
        });
        btnShowAllTest = (Button)vu.findViewById(R.id.btnShowAllTest);
        btnShowAllTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(context, ShelfTestHistoryActivity.class );
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
            }
        });

        return vu;
    }


}
