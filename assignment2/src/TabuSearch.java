import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuSearch {
    public int tabuListSize;
    public TabuSearch(int tabuListSize) {
        this.tabuListSize = tabuListSize;
    }
    public int tabu_search(int[][] placeCoordinates) {

        int numPlaces = placeCoordinates.length;
        int[][] distanceMatrix = new int[numPlaces][numPlaces];

        for (int i = 0; i < numPlaces; i++) {
            for (int j = 0; j < numPlaces; j++) {
                int[] place1 = placeCoordinates[i];
                int[] place2 = placeCoordinates[j];
                distanceMatrix[i][j] = distance(place1, place2);
            }
        }

        int numIterations = 1000;
        int[] bestRoute = random_tour(numPlaces);
        int bestDistance = cost_distance(bestRoute, distanceMatrix);

        List<int[]> tabuList = new ArrayList<>();

        for (int iteration = 0; iteration < numIterations; iteration++) {
            int[] currentRoute = bestRoute;
            int currentDistance = bestDistance;
            int[] nextRoute = null;
            int nextDistance = Integer.MAX_VALUE;

            for (int i = 0; i < numPlaces; i++) {
                for (int j = i + 1; j < numPlaces; j++) {
                    int[] newRoute = swap(currentRoute, i, j);
                    int newDistance = cost_distance(newRoute, distanceMatrix);

                    if (newDistance < nextDistance && !tabuList.contains(newRoute)) {
                        nextRoute = newRoute;
                        nextDistance = newDistance;
                    }
                }
            }

            if (nextDistance < currentDistance) {
                currentRoute = nextRoute;
                currentDistance = nextDistance;

                if (currentDistance < bestDistance) {
                    bestRoute = currentRoute;
                    bestDistance = currentDistance;
                }

                tabuList.add(nextRoute);
                if (tabuList.size() > tabuListSize) {
                    tabuList.remove(0);
                }
            }
        }
        System.out.println("Best route: ");

        for (int i = 0; i < numPlaces; i++) {
            int placeIndex = bestRoute[i];
            int[] place = placeCoordinates[placeIndex];
            System.out.printf("(%d,%d) -> ", place[0], place[1]);
        }
        System.out.printf("(%d,%d)%n", placeCoordinates[bestRoute[0]][0], placeCoordinates[bestRoute[0]][1]);
//        System.out.println("Total distance: " + (int)bestDistance);
        System.out.println("Total distance: " + bestDistance);

     return bestDistance;
    }




    private static int distance(int[] place1, int[] place2) //distance between two places
    {   int dx = place1[0] - place2[0];
        int dy = place1[1] - place2[1];
        return (int) Math.sqrt(dx * dx + dy * dy);
    }


    private static int cost_distance(int[] route, int[][] distanceMatrix) {
        int distance = 0;
        for (int i = 0; i < route.length - 1; i++) {
            int from = route[i];
            int to = route[i + 1];
            distance += distanceMatrix[from][to];
        }

        distance += distanceMatrix[route[route.length - 1]][route[0]];
        return distance;
    }


    private static int[] random_tour(int numPlaces) {
        int[] route = new int[numPlaces];
        for (int i = 0; i < numPlaces; i++) {
            route[i] = i;
        }
        shuffleArray(route);
        return route;
    }

    private static int[] swap(int[] route, int i, int j) { //replace places
        int[] newRoute = route.clone();
        int temp = newRoute[i];
        newRoute[i] = newRoute[j];
        newRoute[j] = temp;
        return newRoute;
    }


    private static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

}
