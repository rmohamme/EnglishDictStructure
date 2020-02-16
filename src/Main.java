import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by ritzbitz on 1/6/20. Template
 */
public class Main {

    private void startingLetter() {

    }

    private void uniqueLettersSearch(Scanner in, RootNode root) {
        // test words: phtlig
        BFS bfs = new BFS();

        ArrayList<String> ans;
        Set<Character> set = new HashSet<>();
        int numLetters = 0;

        System.out.println("How many letters are there?");
        numLetters = in.nextInt();
        in.nextLine();

        String letters;
        do {
            System.out.println("What are the letters?");
            letters = in.nextLine();
        } while (letters.length() != numLetters);

        for (char c : letters.toCharArray())
            set.add(c);

        ans = bfs.uniqueLettersSearch(root, set);
        System.out.println(ans.size());
        System.out.println(ans);
    }

    private void simpleSearch(Scanner in, RootNode root) {
        BFS bfs = new BFS();

        ArrayList<String> ans;

        Character letter;
        int len = 0;

        System.out.println("what is the starting letter?");
        letter = in.nextLine().charAt(0);
        System.out.print("What is the word length?");
        len = in.nextInt();

        ans = bfs.simpleSearch(root, letter, len);

        System.out.println(ans);
    }

    private void run() {
        // initiate objects
        Scanner in = new Scanner(System.in);
        WordsList wl = new WordsList();
        RootNode root = new RootNode();

        // build rootNode
        wl.run();
        root.buildTree(wl.wordList);

        int option;
        // prompt the search options
        System.out.printf("Select the type of search\n1. Simple Search\n" +
                "2.Search with unique letters\n");

        option = in.nextInt();
        switch (option) {
            case 1:
                simpleSearch(in, root);
            case 2:
                uniqueLettersSearch(in, root);
        }
    }

    public void testUniqueSearch() {
        Scanner in = new Scanner(System.in);
        WordsList wl = new WordsList();
        RootNode root = new RootNode();

        // build rootNode
        wl.run();
        root.buildTree(wl.wordList);

        BFS bfs = new BFS();
        ArrayList<String> ans;
        Set<Character> set = new HashSet<>();

        // add the letters
        String t1 = "csaros";

        for (char c : t1.toCharArray())
            set.add(c);

        ans = bfs.uniqueLettersSearch(root, set);

        System.out.println(ans);
    }

    public static void main(String[] args) {
        Main m = new Main();
        // m.run();

        // m.testUniqueSearch();

        // simple test
        m.testSimpleSearch('c', 5);
    }


    /****************************************
     * TEST FUNCTIONS
     *****************************************/

    public void testSimpleSearch(Character letter, int len) {
        Scanner in = new Scanner(System.in);
        WordsList wl = new WordsList();
        RootNode root = new RootNode();

        // build rootNode
        wl.run();
        root.buildTree(wl.wordList);

        BFS bfs = new BFS();

        ArrayList<String> ans;

        ans = bfs.simpleSearch(root, letter, len);

        System.out.println(ans);
    }
}
