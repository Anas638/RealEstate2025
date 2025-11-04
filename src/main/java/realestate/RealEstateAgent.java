package realestate;

import java.util.*;
import java.io.*;

public class RealEstateAgent {
    public static TreeSet<RealEstate> properties = new TreeSet<>(new RealEstateComparator());

    public static double averageSqmPrice() {
        return properties.stream().mapToDouble(r -> r.getPrice()).average().orElse(0);
    }

    public static RealEstate cheapestProperty() {
        return properties.stream()
                .min(Comparator.comparingDouble(r -> r.getPrice()))
                .orElse(null);
    }

    public static int totalPriceAll() {
        return properties.stream().mapToInt(r -> r.getTotalPrice()).sum();
    }

    public static double avgSqmRoomMostExpensiveBudapest() {
        RealEstate expensiveBp = properties.stream()
                .filter(r -> r.getCity().equalsIgnoreCase("Budapest"))
                .max(Comparator.comparingInt(r -> r.getTotalPrice()))
                .orElse(null);
        return expensiveBp == null ? 0 : expensiveBp.averageSqmPerRoom();
    }

    public static void loadPropertiesFromFile(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("#");
            if (parts[0].equals("REALSTATE")) {
                properties.add(new RealEstate(
                        parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]),
                        Double.parseDouble(parts[4]), Genre.valueOf(parts[5])
                ));
            } else if (parts[0].equals("PANEL")) {
                properties.add(new Panel(
                        parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]),
                        Double.parseDouble(parts[4]), Genre.valueOf(parts[5]),
                        Integer.parseInt(parts[6]), parts[7].equalsIgnoreCase("yes")
                ));
            }
        }
        br.close();
    }

    public static void outputResults(String outputFileName) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

        writer.write("Average price per sqm: " + averageSqmPrice() + "\n");
        System.out.println("Average price per sqm: " + averageSqmPrice());

        writer.write("Cheapest property: " + cheapestProperty() + "\n");
        System.out.println("Cheapest property: " + cheapestProperty());

        writer.write("Total price of all properties: " + totalPriceAll() + "\n");
        System.out.println("Total price of all properties: " + totalPriceAll());

        // Repeat for other summary stats...

        writer.close();
    }
}
