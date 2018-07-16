package madbarsoft.com.computermcqqa.practise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computermcqqa.MainActivity;
import madbarsoft.com.computermcqqa.R;
import madbarsoft.com.computermcqqa.main.MainService;
import madbarsoft.com.computermcqqa.main.QuestionAnswerModel;

public class PractiseActivity extends AppCompatActivity {

    List<QuestionAnswerModel> questionAnswerModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practise);
        recyclerView = (RecyclerView) findViewById(R.id.practiseListShowRV);
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId",10);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            questionAnswerModelList =  new MainService().getQuestionAndAnsListFromJson(this, categoryId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerAdapter recyclerViewAdapter = new RecyclerAdapter(this, questionAnswerModelList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void gotToHome(View view) {
        Intent inten = new Intent(PractiseActivity.this, MainActivity.class );
        inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inten);
    }
}
