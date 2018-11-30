package whs.de.zitat_quiz;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    static final int CATEGORY_MOVIES = 0;
    static final int CATEGORY_POLITICS = 1;
    static final int CATEGORY_SCIENCE = 2;
    static final int CATEGORY_SPORTS = 3;
    static final int CATEGORY_TELEVISION = 4;
    static final int CATEGORY_EVERYTHING = 5;
    static final int CATEGORY_TIME = 6;

    static String database_content="";

    static int currentCategory;

    static int USER_SCORE;


}
