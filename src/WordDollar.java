import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordDollar {

    final static int DOLLAR = 500;
    public static List fetchWordsInFile(String filePath, String delimeter) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String strLine;
        List<String> words = new ArrayList<String>();
        try {
            while ((strLine = reader.readLine()) != null) {
                words.addAll(Arrays.asList(strLine.split(delimeter)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }

    /**
     * Naive solution
     *
     * @param words
     * @return
     */
    public static List find(List<String> words) {
        List dollarWords = new ArrayList();
        for (String word : words) {
            if (isWordDollar(word)) {
                dollarWords.add(word);
            }
        }
        return dollarWords;
    }

    /**
     * Check if param word ascii characters totaled to DOLLAR value.
     * Reference to ascii int value http://www.asciitable.com/
     *
     * @param word
     * @return
     */
    public static boolean isWordDollar(String word) {
        int remaining = DOLLAR;
        for (int i=0; i<word.length(); i++) {
            int asciiInt = (int) word.charAt(i);
            remaining = remaining - asciiInt;
        }

        return remaining == 0;
    }

    public static void main(String[] args) {
        List words = Arrays.asList("cazaU", "zacaU", "zzzz");
        System.out.println(WordDollar.find(words));  // Should print cazaU, zacaU

        words = fetchWordsInFile("src/wordfile.txt", ",");
        System.out.println(WordDollar.find(words));  // Should print cazaU, zacaU
    }
}