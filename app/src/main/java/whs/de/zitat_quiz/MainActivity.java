package whs.de.zitat_quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String database_content = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");

        read_database();

        Button btnCategoryActivity = findViewById(R.id.btnCategoryActivity);
        btnCategoryActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDB();
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void read_database() {

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Utils.database_content = database_content;
            }

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL url = new URL("http://192.168.5.42/quizapp/everything.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                    long timestamp = Long.parseLong(bufferedReader.readLine());
                    if (isCacheOutdated(timestamp)) {
                        database_content = bufferedReader.readLine();
                        SharedPreferences preferences = getSharedPreferences("timestamp", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putLong("timestamp", timestamp);
                        editor.commit();
                    }
                    return database_content.trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }

        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private boolean isCacheOutdated(long serverTime) {
        SharedPreferences preferences = getSharedPreferences("timestamp", MODE_PRIVATE);
        long cacheTime = preferences.getLong("timestamp", 0);
        return serverTime > cacheTime;

    }

    private void readDB() {

        if (Utils.database_content.equals("")) {    // couldn't access DB

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("everything.txt")));
                formatDBcontent(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            formatDBcontent(Utils.database_content);

            writeLocalFiles();
        }
    }

    private void formatDBcontent(String unformatted) {
        String[] data = unformatted.split("\\|");

        for (int i = 1; i < data.length; i++) {
            String[] arrQuote = data[i].split(";");
            String quote = arrQuote[0];
            String person = arrQuote[1];
            String category = arrQuote[2];

            Question q = new Question(quote, category, person);
            Answer a = new Answer(person, category);

            switch (category) {
                case "Filme":
                    Utils.questionsMovies.add(q);
                    Utils.answersMovies.add(a);
                    break;
                case "Politik":
                    Utils.questionsPolitics.add(q);
                    Utils.answersPolitics.add(a);
                    break;
                case "Wissenschaft":
                    Utils.questionsScience.add(q);
                    Utils.answersScience.add(a);
                    break;
                case "Sport":
                    Utils.questionsSports.add(q);
                    Utils.answersSports.add(a);
                    break;
                case "Serien":
                    Utils.questionsTelevision.add(q);
                    Utils.answersTelevision.add(a);
                    break;
            }
        }
    }

    private void writeLocalFiles() {
        try {
            FileOutputStream stream = openFileOutput("everything.txt", Context.MODE_PRIVATE);
            stream.write(Utils.database_content.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
