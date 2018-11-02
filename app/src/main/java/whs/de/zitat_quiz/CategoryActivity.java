package whs.de.zitat_quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends Activity {

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

        ListView listView = findViewById(R.id.listViewCategory);
        String[] strings = {"Filme", "Politik", "Serien", "Sport", "Wissenschaft", "Alle Kategorien", "Zeitmodus"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_category, strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Utils.currentCategory = Utils.CATEGORY_MOVIES;
                        startQuizActivity();
                        break;
                    case 1:
                        Utils.currentCategory = Utils.CATEGORY_POLITICS;
                        startQuizActivity();
                        break;
                    case 2:
                        Utils.currentCategory = Utils.CATEGORY_TELEVISION;
                        startQuizActivity();
                        break;
                    case 3:
                        Utils.currentCategory = Utils.CATEGORY_SPORTS;
                        startQuizActivity();
                        break;
                    case 4:
                        Utils.currentCategory = Utils.CATEGORY_SCIENCE;
                        startQuizActivity();
                        break;
                    case 5:
                        Utils.currentCategory = Utils.CATEGORY_EVERYTHING;
                        startExtendedActivity();
                        break;
                    case 6:
                        Utils.currentCategory = Utils.CATEGORY_EVERYTHING;
                        startTimeActivity();
                        break;

                }
            }
        });
    }

    private void startQuizActivity() {
        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
        startActivity(intent);
    }

    private void startExtendedActivity() {
        Intent intent = new Intent(getApplicationContext(), ExtendedActivity.class);
        startActivity(intent);
    }

    private void startTimeActivity() {
        Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
        startActivity(intent);
    }
}
