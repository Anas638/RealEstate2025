package realestate;

import java.util.*;
import java.io.*;
import java.util.logging.*;

/**
 * Class responsible for managing a collection of real estate properties and providing
 * various operations like statistics, file input/output, and property analysis.
 */
public class RealEstateAgent {
    private static final Logger logger = Logger.getLogger(RealEstateAgent.class.getName());

    static {
        try {
            Handler fileHandler = new FileHandler("realEstateApp.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.err.println("Logger setup failed in RealEstateAgent: " + e.getMessage());
        }
    }

    /**
     * Set of all real estate properties, sorted by the supplied comparator.
     */
    public static TreeSet<RealEstate> properties = new TreeSet<>(new RealEstateComparator());

    /**
     * Calculates the average price per square meter across all properties.
     * @return Average price per square meter, or 0 if none
     */
    public static double averageSqmPrice() {
        logger.info("CALL: averageSqmPrice()");
        return properties.stream().mapToDouble(r -> r.getPrice()).average().orElse(0);
    }

    /**
     * Finds and returns the cheapest property in the collection.
     * @return The RealEstate object with the lowest price, or null if none exist
     */
    public static RealEstate cheapestProperty() {
        logger.info("CALL: cheapestProperty()");
        return properties.stream()
                .min(Comparator.comparingDouble(r -> r.getPrice()))
                .orElse(null);
    }

    /**
     * Calculates the sum of total prices for all properties.
     * @return The combined total price of all properties
     */
    public static int totalPriceAll() {
        logger.info("CALL: totalPriceAll()");
        return properties.stream().mapToInt(r -> r.getTotalPrice()).sum();
    }

    /**
     * Determines the average square meters per room for the most expensive property located in Budapest.
     * @return The average sqm per room, or 0 if no such property exists
     */
    public static double avgSqmRoomMostExpensiveBudapest() {
        logger.info("CALL: avgSqmRoomMostExpensiveBudapest()");
        RealEstate expensiveBp = properties.stream()
                .filter(r -> r.getCity().equalsIgnoreCase("Budapest"))
                .max(Comparator.comparingInt(r -> r.getTotalPrice()))
                .orElse(null);
        return expensiveBp == null ? 0 : expensiveBp.averageSqmPerRoom();
    }

    /**
     * Loads real estate properties from a text file and populates the properties set.
     * @param fileName The file to read property data from
     * @throws Exception if file operations or data parsing fail
     */
    public static void loadPropertiesFromFile(String fileName) throws Exception {
        logger.info("CALL: loadPropertiesFromFile(" + fileName + ")");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split("#");
                    if (parts[0].equals("REALSTATE")) {
                        properties.add(new RealEstate(
                                parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]),
                                Double.parseDouble(parts[4]), Genre.valueOf(parts[5])
                        ));
                        logger.info("Added REALSTATE property from file: " + line);
                    } else if (parts[0].equals("PANEL")) {
                        properties.add(new Panel(
                                parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]),
                                Double.parseDouble(parts[4]), Genre.valueOf(parts[5]),
                                Integer.parseInt(parts[6]), parts[7].equalsIgnoreCase("yes")
                        ));
                        logger.info("Added PANEL property from file: " + line);
                    }
                } catch (Exception e) {
                    logger.severe("Error processing line in loadPropertiesFromFile: " + line + " | " + e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.severe("Error in loadPropertiesFromFile opening file: " + fileName + " | " + e.getMessage());
            throw e;
        }
    }

    /**
     * Outputs summary statistics and property analysis to a text file and to the console.
     * @param outputFileName The name of the file to write the results to
     * @throws Exception if file operations fail
     */
    public static void outputResults(String outputFileName) throws Exception {
        logger.info("CALL: outputResults(" + outputFileName + ")");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String avgStr = "Average price per sqm: " + averageSqmPrice();
            logger.info(avgStr);
            writer.write(avgStr + "\n");
            System.out.println(avgStr);

            String cheapStr = "Cheapest property: " + cheapestProperty();
            logger.info(cheapStr);
            writer.write(cheapStr + "\n");
            System.out.println(cheapStr);

            String totalStr = "Total price of all properties: " + totalPriceAll();
            logger.info(totalStr);
            writer.write(totalStr + "\n");
            System.out.println(totalStr);

            // Optionally add more analysis/statistics here

        } catch (Exception e) {
            logger.severe("Error in outputResults: " + e.getMessage());
            throw e;
        }
    }
}
