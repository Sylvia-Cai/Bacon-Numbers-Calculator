package model;

import java.io.*;
import java.util.*;

public class GraphBuilder {
    public Map<String, Map<String, List<String>>> buildGraph(String filename) throws IOException {
        Map<String, Map<String, List<String>>> graph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("/");
                if (parts.length < 2) continue;

                String movie = parts[0].trim();
                List<String> actors = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    actors.add(parts[i].trim());
                }

                for (int i = 0; i < actors.size(); i++) {
                    String actor1 = actors.get(i);
                    for (int j = i + 1; j < actors.size(); j++) {
                        String actor2 = actors.get(j);

                        graph.putIfAbsent(actor1, new HashMap<>());
                        graph.putIfAbsent(actor2, new HashMap<>());

                        graph.get(actor1).putIfAbsent(actor2, new ArrayList<>());
                        graph.get(actor2).putIfAbsent(actor1, new ArrayList<>());

                        if (!graph.get(actor1).get(actor2).contains(movie)) {
                            graph.get(actor1).get(actor2).add(movie);
                        }
                        if (!graph.get(actor2).get(actor1).contains(movie)) {
                            graph.get(actor2).get(actor1).add(movie);
                        }
                    }
                }
            }
        }
        return graph;
    }
}