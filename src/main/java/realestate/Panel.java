package realestate;

import java.util.logging.*;

/**
 * Represents a panel-type real estate property with additional attributes such as floor and insulation.
 */
public class Panel extends RealEstate implements PanelInterface {
    private static final Logger logger = Logger.getLogger(Panel.class.getName());

    static {
        try {
            Handler fileHandler = new FileHandler("realEstateApp.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.err.println("Logger setup failed in Panel: " + e.getMessage());
        }
    }

    /**
     * The floor number where the panel property is located.
     */
    private int floor;

    /**
     * Indicates whether the panel property is insulated.
     */
    private boolean isInsulated;

    /**
     * Constructs a new Panel object with specified parameters.
     * @param city The city where the property is located
     * @param price The price per square meter
     * @param sqm The total area in square meters
     * @param numberOfRooms The number of rooms
     * @param genre The genre (type) of real estate
     * @param floor The floor number
     * @param isInsulated Whether the panel is insulated
     */
    public Panel(String city, double price, int sqm, double numberOfRooms, Genre genre, int floor, boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre); // must be first!
        logger.info("Creating Panel: city=" + city + ", price=" + price + ", sqm=" + sqm
                + ", numberOfRooms=" + numberOfRooms + ", genre=" + genre
                + ", floor=" + floor + ", insulated=" + isInsulated);
        this.floor = floor;
        this.isInsulated = isInsulated;
    }

    /**
     * Calculates the total price of this panel, applying floor and insulation multipliers.
     * @return The total price as an integer
     */
    @Override
    public int getTotalPrice() {
        logger.info("Called getTotalPrice() on Panel");
        double total = super.getTotalPrice();
        if (floor >= 0 && floor <= 2) total *= 1.05;
        if (floor == 10) total *= 0.95;
        if (isInsulated) total *= 1.05;
        return (int) Math.round(total);
    }

    /**
     * Returns a string representation of this panel, including floor and insulation status.
     * @return String representation of the panel property
     */
    @Override
    public String toString() {
        logger.info("Called toString() on Panel");
        return super.toString() + ", Floor: " + floor + ", Insulated: " + isInsulated;
    }

    /**
     * Checks whether this panel and another real estate object have the same total price.
     * @param estate The other real estate object to compare with
     * @return true if the total prices are equal, false otherwise
     */
    public boolean hasSameAmount(RealEstate estate) {
        logger.info("Called hasSameAmount() on Panel");
        return this.getTotalPrice() == estate.getTotalPrice();
    }

    /**
     * Calculates the approximate price per room, based on total price and average sqm per room.
     * @return Room price as an integer, or 0 if no rooms
     */
    public int roomPrice() {
        logger.info("Called roomPrice() on Panel");
        double rooms = super.averageSqmPerRoom();
        if (rooms == 0) return 0;
        return (int) Math.round(getTotalPrice() / rooms);
    }
}
