import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by ritzbitz on 12/19/19. Template
 */
public class BFS {

    public ArrayList<String> uniqueLettersSearch(RootNode root, Set<Character> letters) {
        ArrayList<String> ans = new ArrayList<>();

        // implement search

        // get the root for each letter in the set
        ArrayList<Node> nodes = getNode(root, letters);

        // do a bfs search of each node
        for (Node n : nodes) {
            ArrayList<String> currNode = bfsMapSearch(n, letters);
            for (String str : currNode) {
                ans.add(str);
            }
        }

        return ans;
    }

    public ArrayList<String> simpleSearch(RootNode root, char start, int length) {
        ArrayList<String> ans = new ArrayList<>();
        length--;

        // grab the node that we're working with
        Node head = root.headNodes.get(start);

        // set up bfs
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        int level = 1;

        while (!q.isEmpty()) {
            int num = q.size();

            while (num > 0) {
                Node curr = q.poll();

                for (Node n : curr.children) {

                    if (level == length && n.isWord) {
                         ans.add(n.word);
                    } else if (level < length){
                        q.add(n);
                    }
                }
                num--;
            }

            level++;
        }

        return ans;
    }

    /****************************************
     * HELPER FUNCTIONS
    *****************************************/

    private ArrayList<String> bfsMapSearch(Node head, Set<Character> letters) {
        Set<Character> usedLetters = new HashSet<>();
        ArrayList<String> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();

        q.add(head);
        int level = 1;

        while (!q.isEmpty()) {
            int num = q.size();

            while (num > 0) {
                Node curr = q.poll();

                for (Node n : curr.children) {
                    if (letters.contains(n.letter) && level < letters.size()) {
                        q.add(n);
                        if (n.isWord) {
                            ans.add(n.word);
                        }
                    }
                }
                num--;
            }

            level++;
        }

        return ans;
    }

    private ArrayList<Node> getNode(RootNode root, Set<Character> letters) {
        ArrayList<Node> nodes = new ArrayList<>();

        for (Character c : letters) {
            if (root.headNodes.containsKey(c)) {
                nodes.add(root.headNodes.get(c));
            }
        }

        return nodes;
    }

    public void BFSPrint(RootNode root) {
        Queue<Node> q = new LinkedList<>();

        // print all the roots and add them to the queue
        for (Node n : root.headNodes.values()) {
            // add to queue
            q.add(n);
        }

        while (!q.isEmpty()) {

            // get the curr size of queue
            int size = q.size();

            while (size > 0) {
                Node curr = q.poll(); // node at the top of queue

                // add all children to queue
                for (Node n : curr.children) {
                    q.add(n);
                }

                // print out the current letter
                System.out.printf("%c ", curr.letter);
                size--;
            }
            System.out.println();
        }
    }
}
