package whs.de.zitat_quiz;

public class DBReader {



    static void formatDBcontent() {

        String[] data = MainActivity.database_content.split("\\|");

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
}
