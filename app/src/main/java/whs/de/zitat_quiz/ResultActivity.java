package whs.de.zitat_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView txtResult = findViewById(R.id.txtResult);
        Button btnReturnToCategories = findViewById(R.id.btnReturnToCategories);

        String resultText ="";

        if(Utils.currentCategory < 5){
            resultText = Utils.USER_SCORE + "/10 Fragen richtig beantwortet";
            txtResult.setText(resultText);
        } else {
            resultText = "Glückwunsch du hast " + Utils.USER_SCORE + " Fragen richtig beantwortet!";
            txtResult.setText(resultText);
        }



        btnReturnToCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
