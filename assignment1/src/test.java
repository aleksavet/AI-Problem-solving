import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test { //testing class
    private int[][] start;
    private int[][] goal;

    public test(int[][] initial, int[][] goal) {
        this.start = initial;
        this.goal = goal;
    }

    public int[][] getInitial() {
        return start;
    }

    public int[][] getGoal() {
        return goal;
    }

}