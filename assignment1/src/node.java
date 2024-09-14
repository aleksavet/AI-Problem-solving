import java.util.ArrayList;
import java.util.List;

public class node { //class to define node in graph
    public node previous;
    public int depth;
    public int cost;
    String operator;
    public int[][] puzzle;
    public List<node> children;

    public node( int[][] puzzle) {
        this.previous = null;
        this.puzzle = puzzle;
        this.children = new ArrayList<>();
        this.operator="";
        this.depth =0;
    }

    public node(node previous, int[][] puzzle,String operator) {
        this.previous = previous;
        this.puzzle = puzzle;
        this.children = new ArrayList<>();
        this.operator = operator;
        if (previous != null) {
            this.depth = previous.depth + 1;
        } else {
            this.depth = 0;
        }
    }

    public node getPrevious() {
        return previous;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public List<node> getChildren() {
        return children;
    }

    public int getDepth() {
        return depth;
    }
}