package utils;

import java.util.Map;

public class DistributionPrinter {
    public static void printDistribution(Map<Integer, Integer> distribution, int infinityCount) {
        System.out.println("| Bacon number | Frequency |");
        System.out.println("|---|---|");
        distribution.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("| %d    | %d    |%n", entry.getKey(), entry.getValue()));
        System.out.println("**infinity**");
        System.out.println(infinityCount);
    }
}