package realestate;
/**
 * Interface defining common property behaviors.
 */
public interface PropertyInterface {

    /**
     * Applies a discount to the property price.
     * @param percent Discount percentage
     */
    void makeDiscount(int percent);

    /**
     * Gets the total price of the property.
     * @return Total price in integer
     */
    int getTotalPrice();

    /**
     * Calculates the average square meters per room.
     * @return Average sqm per room
     */
    double averageSqmPerRoom();
}
