import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ritzbitz on 12/19/19. Template
 */
public class RootNode {
    Map<Character, Node> headNodes;
    Boolean isWord;

    public RootNode() {
        headNodes = new HashMap<>();
        isWord = false;
    }

    /**
     *
     * @param wordList
     */
    public void buildTree(ArrayList<String> wordList) {
        // go through wordList
        for (String str : wordList) {
            // extract each letter in the string
            char[] word = str.toCharArray();

            // check if rootNode has node for the letter
            if (!headNodes.containsKey(word[0])) {
                headNodes.put(word[0], new Node(word[0], false));
            }

            Node curr = headNodes.get(word[0]);

            // build tree with words
            for (int i = 1; i < word.length; i++) {

                boolean found = false;
                for (Node n : curr.children) {
                    if (n.letter == word[i]) {
                        curr = n;
                        found = true;
                        break;
                    }
                }

                // item wasn't found
                if (!found) {
                    Node next = new Node(word[i], false);
                    curr.children.add(next);
                    curr = next;
                }

                if (i == word.length - 1) {
                    curr.isWord = true;
                    curr.word = str;
                }
            }
        }
    }
}
