package whs.de.zitat_quiz;

public class Question {

    private String value;
    private String category;
    private Answer correctAnswer;

    public Question(String value, String category, String answer) {
        setValue(value);
        setCategory(category);
        this.correctAnswer = new Answer(answer, category);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }
}
