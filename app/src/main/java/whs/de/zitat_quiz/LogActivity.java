package whs.de.zitat_quiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class LogActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private int LIMIT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        TextView txtLog = findViewById(R.id.txtLog);
        txtLog.setMovementMethod(new ScrollingMovementMethod());


        switch (ResultActivity.MODUS){
            case 5:

                LIMIT = ExtendedActivity.currentQuestion;

                for(int i=0; i < LIMIT; i++){
                Question temp = ExtendedActivity.usedQuestions.get(i);
                txtLog.append(temp.getValue() + "  -" + temp.getCorrectAnswer().getValue() + "\n");
                txtLog.append("\n");
            }
                break;
            case 6:

                LIMIT = TimeActivity.currentQuestion;

                for(int i=0; i < LIMIT; i++){
                Question temp = TimeActivity.usedQuestions.get(i);
                txtLog.append(temp.getValue() + "  -" + temp.getCorrectAnswer().getValue() + "\n");
                txtLog.append("\n");
            }
                break;
            case 7:

                LIMIT = ArcadeActivity.currentQuestion;

                for(int i=0; i < LIMIT; i++){
                Question temp = ArcadeActivity.usedQuestions.get(i);
                txtLog.append(temp.getValue() + "  -" + temp.getCorrectAnswer().getValue() + "\n");
                txtLog.append("\n");
            }
            break;
                default:

                    LIMIT = 9;

                    for(int i=0; i <= LIMIT; i++){
                        Question temp = QuizActivity.usedQuestions.get(i);
                        txtLog.append(temp.getValue() + "  -" + temp.getCorrectAnswer().getValue() + "\n");
                        txtLog.append("\n");
                    }
        }


    }
}
