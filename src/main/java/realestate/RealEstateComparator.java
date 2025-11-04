package realestate;

import java.util.Comparator;

/**
 * Comparator for RealEstate objects, sorting them by total price.
 */
public class RealEstateComparator implements Comparator<RealEstate> {

    /**
     * Compares two RealEstate objects based on their total price.
     * @param a The first RealEstate object
     * @param b The second RealEstate object
     * @return a negative integer, zero, or a positive integer as the first argument's
     *         total price is less than, equal to, or greater than the second's.
     */
    @Override
    public int compare(RealEstate a, RealEstate b) {
        // Sort by total price (change logic if you want sorting by another field)
        return Integer.compare(a.getTotalPrice(), b.getTotalPrice());
    }
}
