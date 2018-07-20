package madbarsoft.com.computermcqqa.utility;

import android.graphics.Color;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;

public class ChartService {

    public void setPieChart(PieChart pieChart, int takenQuestions, int numberOfCurrectAns, int categoryId){

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(10f);

        ArrayList<PieEntry> yValues2 = new ArrayList<>();
        yValues2.add(new PieEntry(takenQuestions-numberOfCurrectAns, "False"));
        yValues2.add(new PieEntry(numberOfCurrectAns, "Correct"));

        PieDataSet pieDataSet = new PieDataSet(yValues2,"");
        pieDataSet.setSliceSpace(3f);
        //  pieDataSet2.setSelectionShift(5f);
        pieDataSet.setColors(Color.RED, Color.LTGRAY);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(20f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);
        // return  pieChart;
    }

}