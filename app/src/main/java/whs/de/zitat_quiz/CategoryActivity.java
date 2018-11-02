package whs.de.zitat_quiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Wenn du die App verlassen möchtest, drücke noch einmal auf die Zurück-Taste", Toast.LENGTH_SHORT).show();

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
        setContentView(R.layout.activity_category);

        Button btnCategoryMovies = findViewById(R.id.btnCategoryMovies);
        Button btnCategoryPolitics = findViewById(R.id.btnCategoryPolitics);
        Button btnCategoryScience = findViewById(R.id.btnCategoryScience);
        Button btnCategorySports = findViewById(R.id.btnCategorySports);
        Button btnCategoryTelevision = findViewById(R.id.btnCategoryTelevision);
        Button btnCategoryEverything = findViewById(R.id.btnEverything);

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

        btnCategoryEverything.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.currentCategory = Utils.CATEGORY_EVERYTHING;
                startExtendedActivity();
            }
        });

    }

    private void startQuizActivity() {
        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
        startActivity(intent);
    }

    private void startExtendedActivity(){
        Intent intent = new Intent (getApplicationContext(), ExtendedActivity.class);
        startActivity(intent);
    }
}
