
import java.util.*;

public class build_graph
{ private int nodeCount;
    public List<node> solving(int[][] start,int[][] final_state, String type_heuristic)
    {
        PriorityQueue<node> nodes = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        Set<String> visited = new HashSet<>();

        node root = new node(start);
        if(Objects.equals(type_heuristic, "1"))
        {
        root.cost = factor(heuristic1(start, final_state), root.depth);}
        else if(Objects.equals(type_heuristic, "2"))
        {
            root.cost = factor(heuristic2(start, final_state), root.depth);
        }
        nodes.add(root);

        while (!nodes.isEmpty()) {
            nodeCount++;
            node current = nodes.poll();
            visited.add(Arrays.deepToString(current.puzzle));

            if (Arrays.deepEquals(current.puzzle, final_state)) {
                return reconstructPath(current);
            }

            List<node> children = children(current);
            for (node child : children) {
                if (!visited.contains(Arrays.deepToString(child.puzzle))) {
                    child.cost = factor(heuristic1(child.puzzle, final_state), child.depth);
                    nodes.add(child);
                }
            }
        }

        // No solution found
        return null;
    }
    private List<node> reconstructPath(node node) {
        List<node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.previous;
        }
        Collections.reverse(path);
        return path;
    }

    public List<node> children(node current)  //function for creating new nodes (possible moves for 0)
    {
        List<int[]> moves = get_moves(current.puzzle);
        List<node> nodes = new ArrayList<>();
        for(int[] move:moves)
        {
            int new_row = move[0];
            int new_column = move[1];
            int[][] new_puzzle = swapping(current.puzzle,find_zero(current.puzzle),new int[]{new_row,new_column});//new puzzle
            String move_puzzle = direction(current.puzzle,new_puzzle); //which move was done
            node new_node = new node( current,new_puzzle, move_puzzle);
            current.children.add(new_node); //adding to all nodes
            nodes.add(new_node);
        }
        return nodes;
    }
  public List<String> reconstructOperators(List<node> nodes) {
        List<String> operators = new ArrayList<>();

        for (node currentNode : nodes) {
            String operator = currentNode.operator;
            if (operator != null && !operator.isEmpty()) {
                operators.add(operator);
            }
        }

        return operators;
    }
    public String direction(int[][] previous, int[][] new_p) //function to define move
    {
        int[] previous_0 = find_zero(previous);
        int[] new_0 =find_zero(new_p);
        if(previous_0[0]<new_0[0])
        {
            return "Down";
        } else if (previous_0[0]>new_0[0])
        {
            return "Up";
        } else if (previous_0[1]<new_0[1]) {
            return "Right";
        } else if (previous_0[1]>new_0[1]) {
            return "Left";
        }
        else {
            return "Invalid";
        }
    }

    public int[] find_zero(int[][]puzzle) //find position of zeo
    {
        int[] pos = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j]==0)
                {
                    pos[0]=i;
                    pos[1]=j;
                }
            }
        }
        return pos;
    }
    public List<int[]> get_moves(int[][] puzzle) //list of all moves
    {
        List<int[]> moves = new ArrayList<>();
        int[] zeroPosition = find_zero(puzzle);
        int row = zeroPosition[0];
        int column = zeroPosition[1];


        int[][] possibleMoves = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        for (int[] move : possibleMoves) {
            int newRow = row + move[0];
            int newCol = column + move[1];

            if (isValidMove(newRow, newCol)) {
                moves.add(new int[]{newRow, newCol});
            }
        }

        return moves;

    }
    private static boolean isValidMove(int row, int col) { //checker
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }
    public static int[][] swapping(int[][] puzzle, int[] pos1, int[] pos2) { //clone and create puzzle
        int[][] newPuzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newPuzzle[i][j] = puzzle[i][j];
            }
        }
        int temp = newPuzzle[pos1[0]][pos1[1]];
        newPuzzle[pos1[0]][pos1[1]] = newPuzzle[pos2[0]][pos2[1]];
        newPuzzle[pos2[0]][pos2[1]] = temp;
        return newPuzzle;
    }


    public static int heuristic1(int[][] currentState, int[][] finalState) {
        if (currentState.length != finalState.length || currentState[0].length != finalState[0].length) {
            throw new IllegalArgumentException("States must have the same dimensions");
        }

        int misplacedCount = 0;
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[i].length; j++) {
                if (currentState[i][j] != finalState[i][j]&& currentState[i][j]!=0) {
                    misplacedCount++;
                }
            }
        }

        return misplacedCount;
    }

    public int heuristic2(int [][] curr, int[][]final_state) {
        int n = curr.length; // Assuming it's a square puzzle board.
        int distance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int el = curr[i][j];
                if (el != 0) { // Skip the empty tile (represented as 0).
                    int[] finalPosition = find_i(final_state, el);
                    int finalRow = finalPosition[0];
                    int finalCol = finalPosition[1];
                    distance += Math.abs(i - finalRow) + Math.abs(j - finalCol);
                }
            }
        }
        return distance;
    }
    public static int[] find_i(int[][] state, int value) {
        int n = state.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (state[i][j] == value) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    public int factor(int heuristic,int depth)
    {
        int f = heuristic+depth;
        return f;

    }
    public int getNodeCount() {
        return nodeCount;
    }
}
