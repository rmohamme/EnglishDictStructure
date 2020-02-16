import java.util.*;

/**
 * Created by ritzbitz on 12/19/19. Template
 */
public class Node {
    Character letter;
    boolean isWord;
    String word = "";
    List<Node> children;

    public Node(Character c, boolean isWord) {
        this.letter = c;
        this.isWord = isWord;
        children = new ArrayList<>();
    }
}
