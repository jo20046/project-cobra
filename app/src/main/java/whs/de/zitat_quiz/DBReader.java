//package whs.de.zitat_quiz;
//
//import android.content.Context;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import static android.content.Context.MODE_PRIVATE;
//
//public class DBReader {
//
//    static void formatDBcontent() {
//
//        if (Utils.database_content.equals("")) {    // couldn't access DB
//
//        } else {
//
//            String[] data = Utils.database_content.split("\\|");
//
//            for (int i = 1; i < data.length; i++) {
//                String[] arrQuote = data[i].split(";");
//                String quote = arrQuote[0];
//                String person = arrQuote[1];
//                String category = arrQuote[2];
//
//                Question q = new Question(quote, category, person);
//                Answer a = new Answer(person, category);
//
//                switch (category) {
//                    case "Filme":
//                        Utils.questionsMovies.add(q);
//                        Utils.answersMovies.add(a);
//                        break;
//                    case "Politik":
//                        Utils.questionsPolitics.add(q);
//                        Utils.answersPolitics.add(a);
//                        break;
//                    case "Wissenschaft":
//                        Utils.questionsScience.add(q);
//                        Utils.answersScience.add(a);
//                        break;
//                    case "Sport":
//                        Utils.questionsSports.add(q);
//                        Utils.answersSports.add(a);
//                        break;
//                    case "Serien":
//                        Utils.questionsTelevision.add(q);
//                        Utils.answersTelevision.add(a);
//                        break;
//                }
//            }
//        }
//    }
//
//    private void writeLocalFiles() {
//
//        String filename = "everything.txt";
//        String filecontents = "Test Test 1 2 3 sgdrg";
//        FileOutputStream stream;
//
//        try {
//            stream = openFileOutput(filename, Context.MODE_PRIVATE);
//            stream.write(filecontents.getBytes());
//            stream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
