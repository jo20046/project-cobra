package whs.de.zitat_quiz;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Database {

    private final String CATEGORY_MOVIES = "Movies";
    private final String CATEGORY_POLITICS = "Politics";
    private final String CATEGORY_SCIENCE = "Science";
    private final String CATEGORY_SPORTS = "Sports";
    private final String CATEGORY_TELEVISION = "Television";

    private List<Question> movieQuestions;
    private List<Answer> movieAnswers;
    private List<Question> politicsQuestions;
    private List<Answer> politicsAnswers;
    private List<Question> scienceQuestions;
    private List<Answer> scienceAnswers;
    private List<Question> sportsQuestions;
    private List<Answer> sportsAnswers;
    private List<Question> televisionQuestions;
    private List<Answer> televisionAnswers;

    public Database(int category) {
        switch (category) {
            case 0:
                initMovie();
                break;
            case 1:
                initPolitics();
                break;
            case 2:
                initScience();
                break;
            case 3:
                initSports();
                break;
            case 4:
                initTelevision();
                break;
            default:
                break;
        }
    }

    private void addQuestion(List<Question> questionList, List<Answer> answerList, String question, String category, String answer) {
        questionList.add(0, new Question(question, category, answer));
        answerList.add(questionList.get(0).getCorrectAnswer());
    }

    private void initMovie() {
        movieQuestions = new ArrayList<>();
        movieAnswers = new ArrayList<>();

        addQuestion(movieQuestions, movieAnswers,"I'll be back", CATEGORY_MOVIES, "Terminator");
        addQuestion(movieQuestions, movieAnswers, "Autobots, transformiert euch!", CATEGORY_MOVIES, "Transformers");


    }

    private void initPolitics() {

    }

    private void initScience() {

    }

    private void initSports() {

    }

    private void initTelevision() {

    }
}
