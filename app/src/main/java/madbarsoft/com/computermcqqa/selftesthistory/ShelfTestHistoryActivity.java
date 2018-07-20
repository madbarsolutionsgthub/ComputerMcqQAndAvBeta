package madbarsoft.com.computermcqqa.selftesthistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

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
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_10);
            chartService.setPieChart(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns(), 10);
        }
        selfTestService = new SelfTestService(this, 11);
        selfTestModel = selfTestService.getSelfTestResultFromShp();
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_11);
            chartService.setPieChart(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns(), 11);
        }
        selfTestService = new SelfTestService(this, 12);
        selfTestModel = selfTestService.getSelfTestResultFromShp();
        if(selfTestModel.getTakenQuestions()>0){
            pieChart = (PieChart) findViewById(R.id.selfTestResultPCctId_12);
            chartService.setPieChart(pieChart, selfTestModel.getTakenQuestions(), selfTestModel.getNumberOfCorrectAns(), 12);
        }
    }
}
