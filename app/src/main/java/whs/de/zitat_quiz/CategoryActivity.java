package whs.de.zitat_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Button btnCategoryMovies = findViewById(R.id.btnCategoryMovies);
        Button btnCategoryPolitics = findViewById(R.id.btnCategoryPolitics);
        Button btnCategoryScience = findViewById(R.id.btnCategoryScience);
        Button btnCategorySports = findViewById(R.id.btnCategorySports);
        Button btnCategoryTelevision = findViewById(R.id.btnCategoryTelevision);

        btnCategoryMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.currentCategory = Utils.CATEGORY_MOVIES;
                startQuizActivity();
            }
        });

        btnCategoryPolitics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.currentCategory = Utils.CATEGORY_POLITICS;
                startQuizActivity();
            }
        });

        btnCategoryScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.currentCategory = Utils.CATEGORY_SCIENCE;
                startQuizActivity();
            }
        });

        btnCategorySports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.currentCategory = Utils.CATEGORY_SPORTS;
                startQuizActivity();
            }
        });

        btnCategoryTelevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.currentCategory = Utils.CATEGORY_TELEVISION;
                startQuizActivity();
            }
        });
    }

    private void startQuizActivity(){
        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
        startActivity(intent);
    }
}
