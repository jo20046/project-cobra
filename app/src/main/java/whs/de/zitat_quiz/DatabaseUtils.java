package whs.de.zitat_quiz;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    private static final String CATEGORY_MOVIES = "Movies";
    private static final String CATEGORY_POLITICS = "Politics";
    private static final String CATEGORY_SCIENCE = "Science";
    private static final String CATEGORY_SPORTS = "Sports";
    private static final String CATEGORY_TELEVISION = "Television";

    static int currentCategory;

    static List<Question> movieQuestions;
    static List<Answer> movieAnswers;
    static List<Question> politicsQuestions;
    static List<Answer> politicsAnswers;
    static List<Question> scienceQuestions;
    static List<Answer> scienceAnswers;
    static List<Question> sportsQuestions;
    static List<Answer> sportsAnswers;
    static List<Question> televisionQuestions;
    static List<Answer> televisionAnswers;

    static String pullQuestionText(int index) {
        switch (currentCategory) {
            case 0: return movieQuestions.get(index).getValue();
            case 1: return politicsQuestions.get(index).getValue();
            case 2: return scienceQuestions.get(index).getValue();
            case 3: return sportsQuestions.get(index).getValue();
            case 4: return televisionQuestions.get(index).getValue();
            default:return "";
        }
    }

    static void initMovie() {
        movieQuestions = new ArrayList<>();
        movieAnswers = new ArrayList<>();

        currentCategory = 0;

        addQuestion(movieQuestions, movieAnswers,"I'll be back", CATEGORY_MOVIES, "Terminator");
        addQuestion(movieQuestions, movieAnswers, "Autobots, transformiert euch!", CATEGORY_MOVIES, "Transformers");


    }

    static void initPolitics() {
        politicsQuestions = new ArrayList<>();
        politicsAnswers = new ArrayList<>();

        currentCategory = 1;

        addQuestion(politicsQuestions, politicsAnswers, "Make America Great Again", CATEGORY_POLITICS, "Donald Trump");
    }

    static void initScience() {
        scienceQuestions = new ArrayList<>();
        scienceAnswers = new ArrayList<>();

        currentCategory = 2;
    }

    static void initSports() {
        sportsQuestions = new ArrayList<>();
        sportsAnswers = new ArrayList<>();

        currentCategory = 3;
    }

    static void initTelevision() {
        televisionQuestions = new ArrayList<>();
        televisionAnswers = new ArrayList<>();

        currentCategory = 4;
    }

    private static void addQuestion(List<Question> questionList, List<Answer> answerList, String question, String category, String answer) {
        questionList.add(0, new Question(question, category, answer));
        if (!containsAnswer(answerList, answer)) {
            answerList.add(questionList.get(0).getCorrectAnswer());
        }
    }

    private static boolean containsAnswer(List<Answer> answerList, String answer) {
        for (Answer listEntry : answerList) {
            if (listEntry.getValue().equals(answer)) {
                return true;
            }
        }
        return false;
    }
}
