package whs.de.zitat_quiz;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

    static List<Question> questionsMovies = new ArrayList<>();
    static List<Question> questionsPolitics = new ArrayList<>();
    static List<Question> questionsScience = new ArrayList<>();
    static List<Question> questionsSports = new ArrayList<>();
    static List<Question> questionsTelevision = new ArrayList<>();
    static List<Answer> answersMovies = new ArrayList<>();
    static List<Answer> answersPolitics = new ArrayList<>();
    static List<Answer> answersScience = new ArrayList<>();
    static List<Answer> answersSports = new ArrayList<>();
    static List<Answer> answersTelevision = new ArrayList<>();


    public static String isoToUTF8(String s) {
        String r;
        try {
            byte[] arr = s.getBytes("ISO-8859-15");
            r = new String(arr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            r = "Exception";
            e.printStackTrace();
        }
        return r;
    }


}
