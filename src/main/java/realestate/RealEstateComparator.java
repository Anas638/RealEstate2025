package realestate;

import java.util.Comparator;

public class RealEstateComparator implements Comparator<RealEstate> {
    @Override
    public int compare(RealEstate a, RealEstate b) {
        // Sort by total price (change logic if you want sorting by another field)
        return Integer.compare(a.getTotalPrice(), b.getTotalPrice());
    }
}
