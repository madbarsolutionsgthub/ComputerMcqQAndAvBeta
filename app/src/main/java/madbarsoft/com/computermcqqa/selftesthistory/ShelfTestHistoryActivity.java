package madbarsoft.com.computermcqqa.selftesthistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

import java.text.SimpleDateFormat;

import madbarsoft.com.computermcqqa.R;
import madbarsoft.com.computermcqqa.selftest.SelfTestModel;
import madbarsoft.com.computermcqqa.selftest.SelfTestService;
import madbarsoft.com.computermcqqa.utility.ChartService;

public class ShelfTestHistoryActivity extends AppCompatActivity {

    private PieChart pieChart;
    private  ChartService chartService;
    private SelfTestService selfTestService;
    private SelfTestModel selfTestModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_test_history);

        chartService = new ChartService();

        selfTestService = new SelfTestService(this, 10);
        selfTestModel = selfTestService.getSelfTestResultFromShp();

        textView =  findViewById(R.id.showSelfTestHistorySubCategoryTitleTVctId_10);
        textView.setText("Correct Answer: "+selfTestModel.getNumberOfCorrectAns()+" Of "+selfTestModel.getTakenQuestions());

        textView =  findViewById(R.id.showSelfTestHistoryDateTVctId_10);
        SimpleDateFormat outPutFormate = new SimpleDateFormat("dd MMM yyyy");
        textView.setText("Date: "+outPutFormate.format(selfTestModel.getTestDate()));

        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_10);
            pieChart.setNoDataText("No Quiz Taken yet...!");
            chartService.pieDateForShowHistory(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns());
        }
        selfTestService = new SelfTestService(this, 11);
        selfTestModel = selfTestService.getSelfTestResultFromShp();
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_11);
            pieChart.setNoDataText("No Quiz Taken yet...!");
            chartService.pieDateForShowHistory(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns());
        }
        selfTestService = new SelfTestService(this, 12);
        selfTestModel = selfTestService.getSelfTestResultFromShp();
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_12);
            pieChart.setNoDataText("No Quiz Taken yet...!");
            chartService.pieDateForShowHistory(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns());
        }
    }
}
