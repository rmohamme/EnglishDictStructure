import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.*;
import java.util.Scanner;

/**
 * Created by ritzbitz on 1/9/20. Template
 */
public class CleanFiles {

    private String INPUTFILEPATHPREFIX = "/Users/ritzbitz/Documents/projects/Word_lists_in_csv/";
    private String INPUTFILEPATHSUFFIX = "word.csv";

    private String OUTPUTFILEPATHPREFIX = "Clean_word_lists_in_csv/";
    private String OUTPUTFILEPATHSUFFIX = "cleanWord.csv";

    /**
     * goes through old files and rewrites new, cleaned out files.
     * @param inputFilePathPrefix
     * @param inputFilePathSuffix
     * @param outputFilePathPrefix
     * @param outputFilePathSuffix
     * @return
     * @throws IOException
     */
    public boolean removeIllegalCharacters(String inputFilePathPrefix, String inputFilePathSuffix,
                                            String outputFilePathPrefix, String outputFilePathSuffix) throws IOException {

        // go through ever letter
        for (int i = 0; i < 3; i++) {
            // input file
            String inputFilePath = inputFilePathPrefix + (char)(i + 65) + inputFilePathSuffix;

            // output file
            String outputFilePath = outputFilePathPrefix + (char)(i + 65) + outputFilePathSuffix;

            // input reader
            Scanner scan = new Scanner(new File(inputFilePath));

            // output writer
            Writer writer = new FileWriter(outputFilePath);

            System.out.println("here");
            try {

                // scan through input file
                while (scan.hasNext()) {
                    // clean word
                    String word = scan.nextLine().toLowerCase().trim();
                    if (!word.matches("[a-z]+")) {
                        word = testRemoveNonAlphaPrefix(word);
                        word = testRemoveNonAlphaSuffix(word);
                    }

                    // write cleaned word
                    writer.write(word);
                    writer.write("\n");
                }
            } catch (Exception e) {
                System.out.println("Error in reading or writing files");
            } finally {
                // close files
                scan.close();
                writer.close();

                // increment file
                i++;
            }
        }

        return true;
    }

    private String testRemoveNonAlphaSuffix(String word) {
        return word.replaceAll("\\b[^a-z]+", "");
    }

    private String testRemoveNonAlphaPrefix(String word) {
        return word.replaceAll("[^a-z]+\\b", "");
    }

    private void testWrite(String outputFile) throws IOException {
        String file =  outputFile + "output.csv";
        Writer writer = new FileWriter(file);;

        try {
            writer.write("Working");
        } catch (Exception e) {
            System.out.println(e);
        }

        writer.close();
    }

    public static void main(String[] args) {
        CleanFiles cl = new CleanFiles();

        // test strings
//        String t1 = "\"\"\"cymbling \"";
//        cl.testRemoveNonAlphaPrefix(t1);
//        cl.testRemoveNonAlphaSuffix(t1);

        try {
            // cl.removeIllegalCharacters(cl.INPUTFILEPATHPREFIX, cl.INPUTFILEPATHSUFFIX, cl.OUTPUTFILEPATHPREFIX, cl.OUTPUTFILEPATHSUFFIX);
            cl.testWrite(cl.OUTPUTFILEPATHPREFIX);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("failed to read or write file in main");
        }
    }
}