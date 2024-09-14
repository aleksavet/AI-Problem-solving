import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Genetic_alg {
    public int genetic_alg(int[][] placeCoordinates,String type){
        int numPlaces = placeCoordinates.length;

        int populationSize = 100;
        int numGenerations = 1000;
        double mutationRate = 0.01;

        int[][] population = new int[populationSize][numPlaces];
        for (int i = 0; i < populationSize; i++) {
            population[i] = tour(numPlaces);
        }

        for (int generation = 0; generation < numGenerations; generation++) {
         //find fitness
            double[] fitness = new double[populationSize];
            for (int i = 0; i < populationSize; i++) {
                fitness[i] = fitness(population[i], placeCoordinates);
            }
            if(Objects.equals(type, "1")) {
                //choosing the best one
                int[][] newPopulation = new int[populationSize][numPlaces];
                for (int i = 0; i < populationSize; i++) {
                    int parent1 = parent(fitness);
                    int parent2 = parent(fitness);
                    int[] child = crossover(population[parent1], population[parent2]);
                    mutation(child, mutationRate);
                    newPopulation[i] = child;
                }

                population = newPopulation;
            } else if (Objects.equals(type, "2")) {
                //choosing the best one
                int[][] newPopulation = new int[populationSize][numPlaces];
                for (int i = 0; i < populationSize; i++) {
                    int parent1 = parent2(fitness);
                    int parent2 = parent2(fitness);
                    int[] child = crossover(population[parent1], population[parent2]);
                    mutation(child, mutationRate);
                    newPopulation[i] = child;
                }

                population = newPopulation;
            }
        }
        //best solution
        double bestDistance = Double.MAX_VALUE;
        int bestRouteIndex = -1;
        for (int i = 0; i < populationSize; i++) {
            double distance = fitness(population[i], placeCoordinates);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestRouteIndex = i;
            }
        }
        int[] bestRoute = population[bestRouteIndex];

        System.out.println("Best route: ");
        for (int i = 0; i < numPlaces; i++) {
            int placeIndex = bestRoute[i];
            int[] place = placeCoordinates[placeIndex];
            System.out.printf("(%d,%d) -> ", place[0], place[1]);
        }
        System.out.printf("(%d,%d)%n", placeCoordinates[bestRoute[0]][0], placeCoordinates[bestRoute[0]][1]);
        System.out.println("Total distance: " + (int)bestDistance);
        return (int)bestDistance;
    }


    private static int[] tour(int numPlaces) { //generate tour
        int[] route = new int[numPlaces];
        for (int i = 0; i < numPlaces; i++) {
            route[i] = i;
        }
        new_array(route);
        return route;
    }


    private static void new_array(int[] array)//shuffle
    {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            int id = random.nextInt(i + 1);
            int temp = array[id];
            array[id] = array[i];
            array[i] = temp;
        }
    }


    private static double fitness(int[] route, int[][] placeCoordinates)
    {
        double distance = 0.0;
        for (int i = 0; i < route.length - 1; i++) {
            int place1 = route[i];
            int place2 = route[i + 1];
            int[] coord1 = placeCoordinates[place1];
            int[] coord2 = placeCoordinates[place2];
            distance += distance_points(coord1, coord2);
        }
        int lastPlace = route[route.length - 1];
        int firstPlace = route[0];
        int[] lastCoord = placeCoordinates[lastPlace];
        int[] firstCoord = placeCoordinates[firstPlace];
        distance += distance_points(lastCoord, firstCoord);
        return distance;
    }


    private static double distance_points(int[] coord1, int[] coord2) //distance between two places
    {   int dx = coord1[0] - coord2[0];
        int dy = coord1[1] - coord2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static int parent(double[] fitness) //random method
    {
        double totalFitness = Arrays.stream(fitness).sum();
        double randValue = Math.random() * totalFitness;
        for (int i = 0; i < fitness.length; i++) {
            if (randValue < fitness[i]) {
                return i;
            }
            randValue -= fitness[i];
        }
        return fitness.length - 1;
    }
    private static int parent2(double[] fitness) //tournament method
    {
        int tournamentSize = 5;
        int[] tournament = new int[tournamentSize];

        for (int i = 0; i < tournamentSize; i++) {
            tournament[i] = (int)(Math.random() * fitness.length);
        }

        int bestIndex = tournament[0];
        double bestFitness = fitness[bestIndex];
        for (int i = 1; i < tournamentSize; i++) {
            int currentIndex = tournament[i];
            double currentFitness = fitness[currentIndex];
            if (currentFitness > bestFitness) {
                bestIndex = currentIndex;
                bestFitness = currentFitness;
            }
        }
        return bestIndex;
    }

    private static int[] crossover(int[] parent1, int[] parent2) {
        int numPlaces = parent1.length;
        int[] child = new int[numPlaces];
        int startPos = (int) (Math.random() * numPlaces);
        int endPos = (int) (Math.random() * numPlaces);
        if (startPos > endPos) {
            int temp = startPos;
            startPos = endPos;
            endPos = temp;
        }
        for (int i = startPos; i <= endPos; i++) {
            child[i] = parent1[i];
        }
        for (int i = 0; i < numPlaces; i++) {
            if (child[i] == 0) {
                for (int j = 0; j < numPlaces; j++) {
                    if (!check(child, parent2[j])) {
                        for (int k = 0; k < numPlaces; k++) {
                            if (child[k] == 0) {
                                child[k] = parent2[j];
                                break;
                            }
                        }
                    }
                }
            }
        }
        return child;
    }
    private static boolean check(int[] array, int value) //check for element
    {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }
    private static void mutation(int[] individual, double mutationRate)
    {
        for (int i = 0; i < individual.length; i++) {
            if (Math.random() < mutationRate) {
                int j = (int) (Math.random() * individual.length);
                int temp = individual[i];
                individual[i] = individual[j];
                individual[j] = temp;
            }
        }
    }
}