package whs.de.zitat_quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String[] names = {"Filme", "Politik", "Serien", "Sport", "Wissenschaft", "Alle Kategorien", "Zeitmodus", "Arcade"};
        String[] descriptions = {"10 Fragen zum Thema Filme", "10 Fragen zum Thema Politik", "10 Fragen zum Thema Serien", "10 Fragen zum Thema Sport", "10 Fragen zum Thema Wissenschaft", "Eine falsche Antwort und das war's", "So viele Fragen wie möglich in 2 Minuten","Für jede richtige Antwort gibt es einen Zeitbonus"};
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, String> entry = new HashMap<>(2);
            entry.put("name", names[i]);
            entry.put("desc", descriptions[i]);
            data.add(entry);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "desc"},
                new int[]{android.R.id.text1, android.R.id.text2});
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
                    case 7:
                        Utils.currentCategory = Utils.CATEGORY_EVERYTHING;
                        startArcadeActivtity();
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

    private void startArcadeActivtity(){
        Intent intent = new Intent (getApplicationContext(),ArcadeActivity.class);
        startActivity(intent);
    }
}
