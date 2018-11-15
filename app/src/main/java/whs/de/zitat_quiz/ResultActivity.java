package whs.de.zitat_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView txtResult = findViewById(R.id.txtResult);
        TextView txtHighscore = findViewById(R.id.txtHighscore);
        Button btnReturnToCategories = findViewById(R.id.btnReturnToCategories);

        String resultText ="";

        if(Utils.currentCategory < 5){
            resultText = Utils.USER_SCORE + "/10 Fragen richtig beantwortet";
            txtResult.setText(resultText);
        } else {
            resultText = "Glückwunsch du hast " + Utils.USER_SCORE + " Fragen richtig beantwortet!";
            txtResult.setText(resultText);

            int highscore = 0;

            switch (Utils.currentCategory){
                case 5:
                    highscore = getHighscoreExtended();
                    break;
                case 6:
                    highscore = getHighscoreTime();
                    break;
                case 7:
                    highscore = getHighscoreArcade();
                    break;
            }


            if(highscore < Utils.USER_SCORE){

                switch (Utils.currentCategory){
                    case 5:
                        setHighscoreExtended(Utils.USER_SCORE);
                        break;
                    case 6:
                        setHighscoreTime(Utils.USER_SCORE);
                        break;
                    case 7:
                        setHighscoreArcade(Utils.USER_SCORE);
                        break;
                }

            }

            if(highscore > 0)
                txtHighscore.setText(Integer.toString(highscore));
        }

        btnReturnToCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private int getHighscoreExtended(){

        this.pref = this.getSharedPreferences("extended",MODE_PRIVATE);
        return pref.getInt("extended",0);
    }

    private int getHighscoreTime(){

        this.pref = this.getSharedPreferences("time",MODE_PRIVATE);
        return pref.getInt("extended",0);
    }

    private int getHighscoreArcade(){

        this.pref = getSharedPreferences("arcade",MODE_PRIVATE);
        return pref.getInt("arcade",0);
    }

    private void setHighscoreExtended(int score){

        this.pref = getSharedPreferences("extended",MODE_PRIVATE);
        this.prefEditor = pref.edit();

        prefEditor.putInt("extended",score);
        prefEditor.commit();
    }

    private void setHighscoreTime(int score){

        this.pref = getSharedPreferences("time",MODE_PRIVATE);
        this.prefEditor = pref.edit();

        prefEditor.putInt("time",score);
        prefEditor.commit();
    }

    private void setHighscoreArcade(int score){

        this.pref = getSharedPreferences("extended",MODE_PRIVATE);
        this.prefEditor = pref.edit();

        prefEditor.putInt("arcade",score);
        prefEditor.commit();
    }
}
