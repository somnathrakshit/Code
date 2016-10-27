import java.util.HashMap;

public class SuffixTree {
    private static class Node extends HashMap<Character, Node> {
        /**
         * Follows a link to get a child node.  If no such link
         * exists, then create and attach an empty child node.
         */
        Node getOrPut(char c) {
            Node child = this.get(c);
            if (child == null) {
                this.put(c, child = new Node());
            }
            return child;
        }
    }

    private Node root;

    /**
     * Creates the suffix tree from the given string.
     */
    public SuffixTree(CharSequence source) {
        this.root = new Node();
        for (int i = 0; i < source.length(); i++) {
            Node n = this.root.getOrPut(source.charAt(i));
            for (int j = i + 1; j < source.length(); j++) {
                n = n.getOrPut(source.charAt(j));
            }
        }
    }

    public boolean contains(CharSequence target) {
        Node n = this.root;
        for (int i = 0; i < target.length(); i++) {
            n = n.get(target.charAt(i));
            if (n == null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SuffixTree sTree = new SuffixTree("bananas");
        String[] input = new String[] {
            "ba",
            "ban",
            "ana",
            "anas",
            "nan",
            "anans",
            "ananas",
            "n",
            "s",
            "as",
            "naab",
            "baan",
            "aan",
        };
        for (String s : input) {
            String exists = sTree.contains(s) ? "exists" : "doesn't exist";
            System.out.printf("Input: %s %s\n", s, exists);
        }
    }
}
