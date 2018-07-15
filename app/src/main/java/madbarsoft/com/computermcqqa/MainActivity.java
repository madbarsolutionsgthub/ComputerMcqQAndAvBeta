package madbarsoft.com.computermcqqa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import madbarsoft.com.computermcqqa.practise.PractiseActivity;
import madbarsoft.com.computermcqqa.selftest.SelfTestActivity;
import madbarsoft.com.computermcqqa.selftesthistory.ShelfTestHistoryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToPractiseBtn(View view) {
        Intent inten = new Intent(MainActivity.this, PractiseActivity.class );
        int categoryId = Integer.parseInt(view.getTag().toString());
        inten.putExtra("categoryId",categoryId);
        inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inten);
    }

    public void goToSelfTestBtn(View view){
        Intent inten = new Intent(MainActivity.this, SelfTestActivity.class );
        int categoryId = Integer.parseInt(view.getTag().toString());
        inten.putExtra("categoryId",categoryId);
        inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inten);
    }

    public void showSelfTestHistory(View view) {
        Intent inten = new Intent(MainActivity.this, ShelfTestHistoryActivity.class );
        inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inten);
    }
}
