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
                DatabaseUtils.initMovie();
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        btnCategoryPolitics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtils.initPolitics();
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        btnCategoryScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtils.initScience();
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        btnCategorySports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtils.initSports();
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        btnCategoryTelevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtils.initTelevision();
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
