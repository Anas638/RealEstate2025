package realestate;
import java.util.logging.*;




/**
 * Class representing a real estate property.
 */
public class RealEstate implements PropertyInterface {
    private static final Logger logger = Logger.getLogger(RealEstate.class.getName());

    // Static block to set up file logging
    static {
        try {
            Handler fileHandler = new FileHandler("realEstateApp.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.err.println("Logger setup failed in RealEstate: " + e.getMessage());
        }
    }

    /**
     * City where the property is located.
     */
    private String city;
    /**
     * Price per square meter.
     */
    private double price;
    /**
     * Area in square meters.
     */
    private int sqm;
    /**
     * Number of rooms.
     */
    private double numberOfRooms;
    /**
     * Genre (type) of the property.
     */
    private Genre genre;

    /**
     * Constructs a real estate object.
     * @param city City name
     * @param price Price per sqm
     * @param sqm Area in sqm
     * @param numberOfRooms Number of rooms
     * @param genre Property type
     */
    public RealEstate(String city, double price, int sqm, double numberOfRooms, Genre genre) {
        logger.info("CALL: RealEstate constructor with city=" + city + ", price=" + price + ", sqm=" + sqm
                + ", numberOfRooms=" + numberOfRooms + ", genre=" + genre);
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }

    /**
     * Applies a discount to the property price.
     * @param percent Discount percentage
     */
    @Override
    public void makeDiscount(int percent) {
        logger.info("CALL: makeDiscount(" + percent + ")");
        price = price * (1 - percent / 100.0);
    }

    /**
     * Calculates the total price of this property, applying a city-specific multiplier.
     * @return Total price with city multiplier
     */
    @Override
    public int getTotalPrice() {
        logger.info("CALL: getTotalPrice()");
        double total = price * sqm;
        if (city.equalsIgnoreCase("Budapest")) {
            total *= 1.30;
        } else if (city.equalsIgnoreCase("Debrecen")) {
            total *= 1.20;
        } else if (city.equalsIgnoreCase("Nyíregyháza")) {
            total *= 1.15;
        }
        return (int) Math.round(total);
    }

    /**
     * Calculates the average square meter per room.
     * @return Average sqm per room
     */
    @Override
    public double averageSqmPerRoom() {
        logger.info("CALL: averageSqmPerRoom()");
        if (numberOfRooms == 0) return 0;
        return sqm / numberOfRooms;
    }

    /**
     * Returns a string representation of the property.
     * @return String with all property fields
     */
    @Override
    public String toString() {
        logger.info("CALL: toString()");
        return "City: " + city +
                ", Price/sqm: " + price +
                ", Area: " + sqm +
                ", Rooms: " + numberOfRooms +
                ", Genre: " + genre +
                ", Total Price: " + getTotalPrice() +
                ", Avg sqm/room: " + String.format("%.2f", averageSqmPerRoom());
    }

    /**
     * Gets the city.
     * @return City name
     */
    public String getCity() {
        logger.info("CALL: getCity()");
        return city;
    }

    /**
     * Gets the price per sqm.
     * @return price per sqm
     */
    public double getPrice() {
        logger.info("CALL: getPrice()");
        return price;
    }

    /**
     * Gets the area in sqm.
     * @return area in sqm
     */
    public int getSqm() {
        logger.info("CALL: getSqm()");
        return sqm;
    }

    /**
     * Gets the number of rooms.
     * @return number of rooms
     */
    public double getNumberOfRooms() {
        logger.info("CALL: getNumberOfRooms()");
        return numberOfRooms;
    }

    /**
     * Gets the genre (type) of property.
     * @return genre
     */
    public Genre getGenre() {
        logger.info("CALL: getGenre()");
        return genre;
    }
}
