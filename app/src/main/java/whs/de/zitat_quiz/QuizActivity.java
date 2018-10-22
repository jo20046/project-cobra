package whs.de.zitat_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private List<Question> questionList;
    private List<Answer> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView txtQuestion = findViewById(R.id.quizText);
        txtQuestion.setText(DatabaseUtils.pullQuestionText(0));

    }

    private void initDB() {

        questionList = new ArrayList<>();
        answerList = new ArrayList<>();

        InputStream is;
        switch (DatabaseUtils.currentCategory) {
            case 0:
                is = getResources().openRawResource(R.raw.filme);
                break;
            case 1:
                is = getResources().openRawResource(R.raw.politik);
                break;
            case 2:
                is = getResources().openRawResource(R.raw.wissenschaft);
                break;
            case 3:
                is = getResources().openRawResource(R.raw.sport);
                break;
            default: // uses "serien.csv" as default so that InputStream is initialized in any case, could be better but works for testing
                is = getResources().openRawResource(R.raw.serien);
                break;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        try {
            while ((line = reader.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
