package whs.de.zitat_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView txtQuestion = findViewById(R.id.quizText);
        txtQuestion.setText(DatabaseUtils.pullQuestionText(0));
    }

}
