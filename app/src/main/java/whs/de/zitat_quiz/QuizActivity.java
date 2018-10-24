package whs.de.zitat_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final TextView txtQuestion = findViewById(R.id.txtQuestion);
        final TextView txtAnswer = findViewById(R.id.txtAnswer);
        Button btnNextQuestion = findViewById(R.id.btnNextQuestion);


        initDB();
        displayQuestion(txtQuestion, txtAnswer);


        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion < questionList.size()) {
                    displayQuestion(txtQuestion, txtAnswer);
                } else {
                    Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    private void displayQuestion(TextView txtQuestion, TextView txtAnswer) {
        Question question = questionList.get(currentQuestion);
        String questionText = (currentQuestion + 1) + "/" + questionList.size() + ": " + question.getValue();
        txtQuestion.setText(questionText);
        txtAnswer.setText(question.getCorrectAnswer().getValue());
        currentQuestion++;
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
                if (Character.isDigit(line.charAt(0))) {
                    Answer a = new Answer(line.substring(line.lastIndexOf(';') + 1), "category");
                    Question q = new Question(line.substring(line.indexOf(';') + 1, line.lastIndexOf(';')), "category", a.getValue());
                    answerList.add(a);
                    questionList.add(q);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
