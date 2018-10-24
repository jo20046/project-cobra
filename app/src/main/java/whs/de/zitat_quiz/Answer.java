package whs.de.zitat_quiz;

public class Answer {

    private String value;
    private String category;

    public Answer(String value, String category) {
        this.setValue(value);
        this.setCategory(category);
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
}
