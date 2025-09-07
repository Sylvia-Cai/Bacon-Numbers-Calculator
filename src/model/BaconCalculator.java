package model;

import java.util.*;

public class BaconCalculator {
    public void calculateBaconNumbers(
            Map<String, Map<String, List<String>>> graph,
            Map<String, Integer> baconNumbers,
            Map<String, String> predecessors,
            Map<String, String> visitedMovies,
            String startActor) {

        Queue<String> queue = new LinkedList<>();
        queue.add(startActor);
        baconNumbers.put(startActor, 0);
        predecessors.put(startActor, null);
        visitedMovies.put(startActor, null);

        while (!queue.isEmpty()) {
            String currentActor = queue.poll();
            int currentNum = baconNumbers.get(currentActor);

            if (!graph.containsKey(currentActor)) continue;

            for (Map.Entry<String, List<String>> entry : graph.get(currentActor).entrySet()) {
                String neighbor = entry.getKey();
                if (!baconNumbers.containsKey(neighbor)) {
                    baconNumbers.put(neighbor, currentNum + 1);
                    predecessors.put(neighbor, currentActor);
                    visitedMovies.put(neighbor, entry.getValue().get(0));
                    queue.add(neighbor);
                }
            }
        }
    }

    public List<PathStep> findBaconPath(
            String actor,
            Map<String, String> predecessors,
            Map<String, String> visitedMovies) {

        if (!predecessors.containsKey(actor)) {
            return null;
        }

        List<PathStep> path = new ArrayList<>();
        String current = actor;

        while (current != null && !current.equals("Bacon, Kevin")) {
            String prev = predecessors.get(current);
            if (prev == null) return null;

            String movie = visitedMovies.get(current);
            path.add(new PathStep(current, prev, movie));
            current = prev;
        }

        return path;
    }
}