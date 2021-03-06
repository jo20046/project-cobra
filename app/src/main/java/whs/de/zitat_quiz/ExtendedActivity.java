package whs.de.zitat_quiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExtendedActivity extends AppCompatActivity {


    private final int POSSIBLE_ANSWERS = 4; // number of answers for the user to choose from
    private int QUESTIONS_PER_GAME; // number of questions per game
    private int NUMBER_OF_QUESTIONS; // number of questions in this category
    private int NUMBER_OF_ANSWERS; // number of answers in this category
    private int CORRECT_ANSWER; // index of the current answer for the current question
    private int CHOSEN_ANSWER = -1; // index of the answer chosen by user (checked radio button)
    private List<Question> questionList;
    private List<Answer> answerList;
    static List<Question> usedQuestions = new ArrayList<>();
    static int currentQuestion = 0;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Wenn du die aktuelle Quiz-Runde beenden möchtest, drücke noch einmal auf die Zurück-Taste", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended);

        setTitle("");

        final RadioGroup rdGrAnswers = findViewById(R.id.rdGrAnswers);
        final Button btnNextQuestion = findViewById(R.id.btnNextQuestion);

        ResultActivity.MODUS = 5;

        // < - - Listeners Start - - >
        rdGrAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnNextQuestion.setEnabled(true);
                switch (checkedId) {
                    case R.id.rdBtnAnswer1:
                        CHOSEN_ANSWER = 0;
                        break;
                    case R.id.rdBtnAnswer2:
                        CHOSEN_ANSWER = 1;
                        break;
                    case R.id.rdBtnAnswer3:
                        CHOSEN_ANSWER = 2;
                        break;
                    case R.id.rdBtnAnswer4:
                        CHOSEN_ANSWER = 3;
                        break;
                }
            }
        });

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CORRECT_ANSWER != CHOSEN_ANSWER && Utils.currentCategory == 5){
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    startActivity(intent);
                    return;
                }

                Utils.USER_SCORE++;

                rdGrAnswers.clearCheck();
                CHOSEN_ANSWER = -1;
                if (currentQuestion < QUESTIONS_PER_GAME) {
                    displayQuestion();
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    startActivity(intent);
                }

                btnNextQuestion.setEnabled(false);
            }
        });
        // < - - Listeners End - - >

        initDB();
        displayQuestion();
        Utils.USER_SCORE = 0;
    }

    private void initDB() {

        questionList = new ArrayList<>();
        answerList = new ArrayList<>();

        questionList.addAll(Utils.questionsMovies);
        questionList.addAll(Utils.questionsPolitics);
        questionList.addAll(Utils.questionsScience);
        questionList.addAll(Utils.questionsSports);
        questionList.addAll(Utils.questionsTelevision);

        answerList.addAll(Utils.answersMovies);
        answerList.addAll(Utils.answersPolitics);
        answerList.addAll(Utils.answersScience);
        answerList.addAll(Utils.answersSports);
        answerList.addAll(Utils.answersTelevision);

        NUMBER_OF_QUESTIONS = questionList.size();
        NUMBER_OF_ANSWERS = answerList.size();
        QUESTIONS_PER_GAME = NUMBER_OF_QUESTIONS;
    }

    private void displayQuestion() {

        TextView txtQuestion = findViewById(R.id.txtQuestion);
        RadioButton answer1 = findViewById(R.id.rdBtnAnswer1);
        RadioButton answer2 = findViewById(R.id.rdBtnAnswer2);
        RadioButton answer3 = findViewById(R.id.rdBtnAnswer3);
        RadioButton answer4 = findViewById(R.id.rdBtnAnswer4);

        int rnd = (int) (Math.random() * NUMBER_OF_QUESTIONS);
        Question question = questionList.get(rnd);
        questionList.remove(rnd);
        usedQuestions.add(question);
        NUMBER_OF_QUESTIONS--;
        txtQuestion.setText(question.getValue());

        ArrayList<Answer> answersToChooseFrom = new ArrayList<>();
        ensureSize(answersToChooseFrom, POSSIBLE_ANSWERS);
        CORRECT_ANSWER = (int) (Math.random() * 4);
        answersToChooseFrom.add(CORRECT_ANSWER, question.getCorrectAnswer());

        for (int i = 0; i < 4; i++) {
            if (answersToChooseFrom.get(i) != null) {
                continue;
            }
            Answer newAnswer = answerList.get((int) (Math.random() * NUMBER_OF_ANSWERS));
            if (duplicateAnswers(answersToChooseFrom, newAnswer)) {
                i--;
                continue;
            }

            if(!(newAnswer.getCategory().equals(question.getCategory()))){
                i--;
                continue;
            }
            answersToChooseFrom.set(i, newAnswer);
        }

        answer1.setText(answersToChooseFrom.get(0).getValue());
        answer2.setText(answersToChooseFrom.get(1).getValue());
        answer3.setText(answersToChooseFrom.get(2).getValue());
        answer4.setText(answersToChooseFrom.get(3).getValue());

        currentQuestion++;
    }

    // ensure that a given list contains at least a certain amount (size) of elements
    private void ensureSize(ArrayList<?> list, int size) {
        list.ensureCapacity(size);
        while (list.size() < size) {
            list.add(null);
        }
    }

    private boolean duplicateAnswers(ArrayList<Answer> list, Answer answer) {
        for (Answer entry : list) {
            if (entry == null) {
                continue;
            }
            if (entry.getValue().equals(answer.getValue())) {
                return true;
            }
        }
        return false;
    }

}


