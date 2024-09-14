public class checking  //class for checking if puzzle solvable
{
    static int inver(int[] arr) {
        int inv_count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = i + 1; j < 9; j++)


                if (arr[i] > 0 &&
                        arr[j] > 0 && arr[i] > arr[j])
                    inv_count++;
        return inv_count;
    }

    static boolean can_solve(int[][] initial, int[][] finalState) {
        int new_p[] = new int[9];
        int new_p2[] = new int[9];
        int k = 0;

        // Converting 2-D puzzles to linear form
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                new_p[k] = initial[i][j];
                new_p2[k] = finalState[i][j];
                k++;
            }
        }


        int inver1 = inver(new_p);
        int inver2 = inver(new_p2);


        return (inver1 % 2 == inver2 % 2);
    }
}