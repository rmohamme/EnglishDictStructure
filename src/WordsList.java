import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ritzbitz on 12/12/19. Template
 */
public class WordsList{

    public ArrayList<String> wordList;

    // default strings
    private String FILEPATHPREFIX = "/Users/ritzbitz/IdeaProjects/Wordscapes/Word_lists_in_csv/";
    private String FILEPATHSUFFIX = "word.csv";

    private ArrayList<String> generateList(String filePathPrefix, String filePathSuffix) throws FileNotFoundException {
        ArrayList<String> wordsList = new ArrayList<>();

        // total count
        int i = 0;
        while (i < 26) {
            // build file path
            String filePath = filePathPrefix + (char)(i + 65) + filePathSuffix;
            File file = new File(filePath);

            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {
                wordsList.add(scan.nextLine().toLowerCase().trim());
            }
            scan.close();
            i++;
        }
        return wordsList;
    }

    /**
     * Cleans the words list to only contain words with letters and no spaces
     * @param wordsList
     * @return
     */
    private ArrayList<String> cleanList(ArrayList<String> wordsList) {
        ArrayList<String> cleanList = new ArrayList<>();

        // TODO: this is getting rid of anyword that appears more than once
        // gets rid of repeats and words that aren't alphanumeric
        for (int i = 0; i < wordsList.size() - 1; i++) {
            String first = wordsList.get(i);
            String next = wordsList.get(i + 1);

            if (first.compareTo(next) != 0 && first.length() > 2) {
                if (first.matches("[a-z]+"))
                    cleanList.add(first);
            }
        }

        if (wordsList.get(wordsList.size() - 2).compareTo(wordsList.get(wordsList.size() - 1)) != 0)
            cleanList.add(wordsList.get(wordsList.size() - 1));
        return cleanList;

    }

    public boolean writeFile(ArrayList<String> list) throws IOException {
        Writer writer = new FileWriter("output.txt");;

        try {
            for (String str : list) {
                writer.write(str);
                writer.write("\n");
            }
        } catch (Exception e) {
            return false;
        } finally {
            writer.close();
        }

        return true;
    }

    void run() {

        try {
            ArrayList<String> list = generateList(FILEPATHPREFIX, FILEPATHSUFFIX);
            writeFile(list);
            list = cleanList(list);

            // set clean list as global list
            wordList = list;
        } catch (Exception e) {
            System.out.println("Failed to read word files");
        }
    }

    static void printList(ArrayList<String> wordsList) {
        for (String str : wordsList)
            System.out.println(str);
    }

    public static void main(String[] args) {
        WordsList wl = new WordsList();
        wl.run();
    }
}
