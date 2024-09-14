import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("For testing press t:\n For running program press p ");
        String l = scanner.next();
        if (Objects.equals(l, "p")) {
         running();
        } else if  (Objects.equals(l, "t")) {
            System.out.println("For testing Tabu Search press s:\n For testing Genetic algorithm press g:");
            String k = scanner.next();
            if(Objects.equals(k, "s")) {
                testing_t();
            } else if (Objects.equals(k, "g")) {
                testing_g();
            }
        }

    }
    public static void testing_t() {
        int[] places = {20, 25, 30, 35, 40};
        int width = 300;
        int height = 300;
        Generating_places generator = new Generating_places();
        int[] tabuList = {5, 10, 15, 20, 25, 30, 35};

        for (int k = 0; k < places.length; k++) {
            int numberOfPlaces = places[k];
            for (int j : tabuList) {
                if (j < numberOfPlaces) {
                    int total = 0;
                    long startTime = System.currentTimeMillis();
                    for (int i = 0; i < 100; i++) {
                        TabuSearch tabuSearch = new TabuSearch(j);
                        int[][] placeCoordinates = generator.info(width, height, numberOfPlaces);
                        int dist = tabuSearch.tabu_search(placeCoordinates);
                        total += dist;
                    }
                    long endTime = System.currentTimeMillis();
                    int best_dist = total / 100;
                    long totalTime = (endTime - startTime)/100;
                    System.out.println("Best distance: " + best_dist + " for " + j + " tabu variables for " + numberOfPlaces + " places");
                    System.out.println("Total time taken: " + totalTime + " milliseconds");
                }
            }
        }
    }


    public static void testing_g() {
        Genetic_alg geneticAlg = new Genetic_alg();
        int[] places = {20, 25, 30, 35, 40};
        int width = 300;
        int height = 300;
        Generating_places generator = new Generating_places();
        for (int i = 0; i < places.length; i++) {
            String type = "2"; //change type
            int total = 0;
            int[][] placeCoordinates = generator.info(width, height, places[i]);
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                int dist = geneticAlg.genetic_alg(placeCoordinates, type);
                total += dist;
            }
            long endTime = System.currentTimeMillis();
            total = total / 100;
            System.out.println("Best distance: " + total + " for this places: " + places[i]);
            long elapsedTime = (endTime - startTime)/100;
            System.out.println("Time taken for this iteration: " + elapsedTime + " ms");
        }
    }

    public static void running() {
        Genetic_alg geneticAlg = new Genetic_alg();
        TabuSearch tabuSearch =new TabuSearch(10);
        System.out.println("Enter width of map:");
        Scanner scanner =new Scanner(System.in);
        int width =scanner.nextInt();
        System.out.println("Enter height of map:");
        int height = scanner.nextInt();
        System.out.println("Enter number of places:");
        int places = scanner.nextInt();
        Generating_places generator = new Generating_places();
        int[][] placeCoordinates = generator.info(width,height,places);
        System.out.println("Enter algorithm (g or t)");
        String alg = scanner.next();
        if (Objects.equals(alg, "g")){
            System.out.println("Enter type of generation 1 or 2");
            String type= scanner.next();
            geneticAlg.genetic_alg(placeCoordinates,type);
        } else if (Objects.equals(alg, "t") ){
            tabuSearch.tabu_search(placeCoordinates);
        }

    }

}