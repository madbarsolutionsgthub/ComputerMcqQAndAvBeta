package madbarsoft.com.computermcqqa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to exit ?");
        //  builder.setMessage("This will be closed application");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.show();

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
