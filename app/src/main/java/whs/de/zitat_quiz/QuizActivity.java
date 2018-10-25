package whs.de.zitat_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

        final RadioGroup rdGrAnswers = findViewById(R.id.rdGrAnswers);
        Button btnNextQuestion = findViewById(R.id.btnNextQuestion);


        // < - - Listeners Start - - >
        rdGrAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdBtnAnswer1:break;
                    case R.id.rdBtnAnswer2:break;
                    case R.id.rdBtnAnswer3:break;
                    case R.id.rdBtnAnswer4:break;
                }
            }
        });

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdGrAnswers.clearCheck();
                if (currentQuestion < questionList.size()) {
                    displayQuestion();
                } else {
                    Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(intent);
                }
            }
        });
        // < - - Listeners End - - >

        initDB();
        displayQuestion();

    }

    private void displayQuestion() {

        TextView txtQuestion = findViewById(R.id.txtQuestion);
        RadioButton answer1 = findViewById(R.id.rdBtnAnswer1);
        RadioButton answer2 = findViewById(R.id.rdBtnAnswer2);
        RadioButton answer3 = findViewById(R.id.rdBtnAnswer3);
        RadioButton answer4 = findViewById(R.id.rdBtnAnswer4);

        Question question = questionList.get(currentQuestion);
        String questionText = (currentQuestion + 1) + "/" + questionList.size() + ": " + question.getValue();
        txtQuestion.setText(questionText);

        answer1.setText(question.getCorrectAnswer().getValue());
        answer2.setText(question.getCorrectAnswer().getValue());
        answer3.setText(question.getCorrectAnswer().getValue());
        answer4.setText(question.getCorrectAnswer().getValue());

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
