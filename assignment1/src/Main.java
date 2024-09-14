import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<test> easyPuzzles = new ArrayList<>();
        List<test> mediumPuzzles = new ArrayList<>();
        List<test> hardPuzzles = new ArrayList<>();

        easyPuzzles.add(new test(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                },
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                }
        ));
        easyPuzzles.add(new test(
                new int[][]{
                        {1, 2, 3},
                        {4, 0, 5},
                        {6, 7, 8}
                },

                new int[][]{
                        {1, 2, 3},
                        {4, 5, 0},
                        {6, 7, 8}
                }
        ));


        easyPuzzles.add(new test(
                new int[][]{
                        {1, 3, 0},
                        {4, 2, 5},
                        {7, 8, 6}
                },
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                }
        ));


        mediumPuzzles.add(new test(
                new int[][]{
                        {2, 8, 3},
                        {1, 6, 4},
                        {7, 0, 5}
                },
                new int[][]{
                        {1, 2, 3},
                        {8, 0, 4},
                        {7, 6, 5}
                }
        ));

        mediumPuzzles.add(new test(
                new int[][]{
                        {4, 3, 5},
                        {1, 2, 0},
                        {7, 8, 6}
                },
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                }
        ));


        hardPuzzles.add(new test(
                new int[][]{
                        {8, 7, 1},
                        {2, 0, 4},
                        {6, 5, 3}
                },
                new int[][]{
                        {1, 2, 3},
                        {8, 0, 4},
                        {7, 6, 5}
                }
        ));

        hardPuzzles.add(new test(
                new int[][]{
                        {8, 7, 0},
                        {1, 6, 2},
                        {4, 5, 3}
                },
                new int[][]{
                        {1, 2, 3},
                        {8, 0, 4},
                        {7, 6, 5}
                }
        ));
        hardPuzzles.add(new test(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 0},
                        {6, 7, 8}
                },

                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                }
        ));
        hardPuzzles.add(new test(
                new int[][]{
                        {2, 8, 3},
                        {1, 0, 4},
                        {7, 6, 5}
                },

                new int[][]{
                        {1, 2, 3},
                        {8, 0, 4},
                        {7, 6, 5}
                }
        ));
       // testing_time(easyPuzzles,mediumPuzzles,hardPuzzles);
   test_simple(easyPuzzles);
//    test_simple(hardPuzzles);
    }
    public static void test_simple(List<test> puzzles)
    {  Scanner scanner = new Scanner(System.in);
        System.out.print("Enter type if heuristic: ");
        String type = scanner.nextLine();

                for (test testCase : puzzles) {
                    checking check = new checking();
                    boolean solve = check.can_solve(testCase.getInitial(), testCase.getGoal());
                    if (solve) {
                        build_graph build_graph = new build_graph();
                        List<node> res = null;
                        if (Objects.equals(type, "1")) {

                            res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), type);

                        } else  if(Objects.equals(type, "2")) {

                            res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), type);

                        }
                        if (res != null) {
                            System.out.println("Puzzle");
                            List<String> op = build_graph.reconstructOperators(res);
                            System.out.println("Operators: " + op);
                            System.out.println("Memory: "+ build_graph.getNodeCount());
                        }
                    } else {
                        System.out.println("No solution found.");
                    }

                }

            }

public static void testing_time(List<test> easyPuzzles,List<test> mediumPuzzles,List<test> hardPuzzles)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter level of solving: ");
        String level = scanner.nextLine();
        System.out.print("Enter type if heuristic: ");
        String type = scanner.nextLine();
        long elapsedTime = 0;

        if (Objects.equals(level, "easy")) {
            long total = 0;

            for (int i = 0; i < 100; i++)
            {
                for (test testCase : easyPuzzles) {
                    checking check = new checking();
                    boolean solve = check.can_solve(testCase.getInitial(), testCase.getGoal());
                    if (solve) {
                        build_graph build_graph = new build_graph();
                        long startTime = 0;
                        long endTime = 0;

                        List<node> res = null;
                        if (Objects.equals(type, "1")) {
                            startTime = System.currentTimeMillis();
                            res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), type);
                            endTime = System.currentTimeMillis();
                        } else  if(Objects.equals(type, "2")) {
                            startTime = System.currentTimeMillis();
                            res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), "2");
                            endTime = System.currentTimeMillis();
                        }
                        if (res != null) {
                            System.out.println("Easy");
                            List<String> op = build_graph.reconstructOperators(res);
                            //System.out.println("Operators: " + op);
                            elapsedTime = endTime - startTime;
                            total += elapsedTime;
                        }
                    } else {
                        System.out.println("No solution found.");
                    }

                }

            }

            long average = total/100;
            System.out.println("Time taken: " +average/1000 + "µs");
        }

    if(Objects.equals(level, "medium")) {
        long total =0;
        List<node> res = null;
        for (int i = 0; i < 100; i++)
        {
            for (test testCase : mediumPuzzles) {
                long startTime =0;
                long endTime =0;
                checking check = new checking();
                boolean solve = check.can_solve(testCase.getInitial(), testCase.getGoal());
                if (solve) {
                    build_graph build_graph = new build_graph();
                    if(Objects.equals(type, "1")) {
                        startTime = System.currentTimeMillis();
                        res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), "1");
                        endTime = System.currentTimeMillis();
                    }
                    else  if(Objects.equals(type, "2")) {
                        startTime = System.nanoTime();
                        res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), "2");
                        endTime = System.nanoTime();
                    }
                    if (res != null) {
                        System.out.println("Medium");
                        List<String> op = build_graph.reconstructOperators(res);
                        System.out.println("Operators: " + op);
                    } else {
                        System.out.println("No solution found.");
                    }
                    long time = endTime - startTime;
                    total+=time;
                }
            }
        }
        long average = total/100;
        System.out.println("Time taken: " +average/1000 + "µs");
    }
        if (Objects.equals(level, "hard")) {
            long total =0;

            List<node> res = null;
            for (int i = 0; i < 100; i++)
              {
                for (test testCase : hardPuzzles) {
                    long startTime =0;
                    long endTime =0;
                    checking check = new checking();
                    boolean solve = check.can_solve(testCase.getInitial(), testCase.getGoal());
                    if (solve) {
                        build_graph build_graph = new build_graph();
                        if(Objects.equals(type, "1")) {
                            startTime = System.currentTimeMillis();
                            res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), "1");
                            endTime = System.currentTimeMillis();
                        }
                        else if(Objects.equals(type, "2")) {
                            startTime = System.currentTimeMillis();
                            res = build_graph.solving(testCase.getInitial(), testCase.getGoal(), "2");
                            endTime = System.currentTimeMillis();
                        }
                        if (res != null) {
                            System.out.println("Hard");
                            List<String> op = build_graph.reconstructOperators(res);
                            System.out.println("Operators: " + op);
                        } else {
                            System.out.println("No solution found.");
                        }
                        long time = endTime - startTime;
                        total+=time;
                    }
                }
            }
            long average = total/100;
            System.out.println("Time taken: " + average / 1000 + "µs");

        }
    }

}