package madbarsoft.com.computermcqqa.selftesthistory;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

import java.text.SimpleDateFormat;

import madbarsoft.com.computermcqqa.MainActivity;
import madbarsoft.com.computermcqqa.R;
import madbarsoft.com.computermcqqa.practise.PractiseActivity;
import madbarsoft.com.computermcqqa.selftest.SelfTestModel;
import madbarsoft.com.computermcqqa.selftest.SelfTestService;
import madbarsoft.com.computermcqqa.utility.ChartService;

public class ShelfTestHistoryActivity extends AppCompatActivity {

    private PieChart pieChart;
    private  ChartService chartService;
    private SelfTestService selfTestService;
    private SelfTestModel selfTestModel;
    private TextView textView;
    private SimpleDateFormat outPutFormate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_test_history);
        outPutFormate = new SimpleDateFormat("dd MMM yyyy");
        chartService = new ChartService();

        selfTestService = new SelfTestService(this, 10);
        selfTestModel = selfTestService.getSelfTestResultFromShp();

        textView =  findViewById(R.id.showSelfTestHistorySubCategoryTitleTVctId_10);
        textView.setText("Correct Answer: "+selfTestModel.getNumberOfCorrectAns()+" Of "+selfTestModel.getTakenQuestions());
        textView =  findViewById(R.id.showSelfTestHistoryDateTVctId_10);
        if(selfTestModel.getTestDate()!=null){
            textView.setText("Date: "+outPutFormate.format(selfTestModel.getTestDate()));
        }
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_10);
            pieChart.setNoDataText("No Quiz Taken yet...!");
            chartService.pieDateForShowHistory(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns());
        }

        selfTestService = new SelfTestService(this, 11);
        selfTestModel = selfTestService.getSelfTestResultFromShp();
        textView =  findViewById(R.id.showSelfTestHistorySubCategoryTitleTVctId_11);
        textView.setText("Correct Answer: "+selfTestModel.getNumberOfCorrectAns()+" Of "+selfTestModel.getTakenQuestions());
        textView =  findViewById(R.id.showSelfTestHistoryDateTVctId_11);
        if(selfTestModel.getTestDate()!=null){
            textView.setText("Date: "+outPutFormate.format(selfTestModel.getTestDate()));
        }
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_11);
            pieChart.setNoDataText("No Quiz Taken yet...!");
            chartService.pieDateForShowHistory(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns());
        }

        selfTestService = new SelfTestService(this, 12);
        selfTestModel = selfTestService.getSelfTestResultFromShp();
        textView =  findViewById(R.id.showSelfTestHistorySubCategoryTitleTVctId_12);
        textView.setText("Correct Answer: "+selfTestModel.getNumberOfCorrectAns()+" Of "+selfTestModel.getTakenQuestions());
        textView =  findViewById(R.id.showSelfTestHistoryDateTVctId_12);
        if(selfTestModel.getTestDate()!=null){
            textView.setText("Date: "+outPutFormate.format(selfTestModel.getTestDate()));
        }
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_12);
            pieChart.setNoDataText("No Quiz Taken yet...!");
            chartService.pieDateForShowHistory(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns());
        }
    }

    public void gotToHome(View view) {
        Intent inten = new Intent(this, MainActivity.class );
        inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inten);
    }

    @Override
    public void onBackPressed(){
        Intent inten = new Intent(this, MainActivity.class );
        inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inten);
    }
}
