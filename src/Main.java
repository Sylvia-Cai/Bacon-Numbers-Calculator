import model.*;
import utils.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String movieFile = "data/IMDB_Input.txt";  // 如果文件放在 data/ 目录下
        Map<String, Map<String, List<String>>> graph = new HashMap<>();
        Map<String, Integer> baconNumbers = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        Map<String, String> visitedMovies = new HashMap<>();

        try {
            System.out.println("Building actor graph...");
            GraphBuilder graphBuilder = new GraphBuilder();
            graph = graphBuilder.buildGraph(movieFile);

            System.out.println("Calculating Bacon numbers...");
            BaconCalculator calculator = new BaconCalculator();
            calculator.calculateBaconNumbers(graph, baconNumbers, predecessors, visitedMovies, "Bacon, Kevin");

            System.out.println("Generating distribution...");
            Map<Integer, Integer> distribution = new HashMap<>();
            for (int num : baconNumbers.values()) {
                distribution.put(num, distribution.getOrDefault(num, 0) + 1);
            }
            int infinityCount = getInfinityCount(graph.keySet(), baconNumbers);

            System.out.println("\nBacon Number Distribution:");
            DistributionPrinter.printDistribution(distribution, infinityCount);

            System.out.println("\nEnter actor names (one per line, type 'E' to exit):");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String actor = scanner.nextLine().trim();
                if (actor.equalsIgnoreCase("E")) {
                    break;
                }

                printActorInfo(actor, baconNumbers, predecessors, visitedMovies, calculator);
                System.out.println();
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }

    private static int getInfinityCount(Set<String> allActors, Map<String, Integer> baconNumbers) {
        int count = 0;
        for (String actor : allActors) {
            if (!baconNumbers.containsKey(actor)) {
                count++;
            }
        }
        return count;
    }

    private static void printActorInfo(
            String actor,
            Map<String, Integer> baconNumbers,
            Map<String, String> predecessors,
            Map<String, String> visitedMovies,
            BaconCalculator calculator) {

        if (!baconNumbers.containsKey(actor)) {
            System.out.printf("'%s' not found in database or has no connection to Kevin Bacon%n", actor);
            return;
        }

        int num = baconNumbers.get(actor);
        System.out.printf("'%s' has a Bacon number of %d%n", actor, num);

        List<PathStep> path = calculator.findBaconPath(actor, predecessors, visitedMovies);
        if (path != null) {
            for (PathStep step : path) {
                System.out.printf("'%s' was in \"%s\" with '%s'%n",
                        step.actor1, step.movie, step.actor2);
            }
        } else {
            System.out.printf("No path found for '%s'%n", actor);
        }
    }

}