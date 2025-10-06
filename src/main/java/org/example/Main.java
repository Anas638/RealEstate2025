package realestate;

public class Main {
    public static void main(String[] args) {
        RealEstate estate = new RealEstate("Budapest", 1000, 60, 3, Genre.CONDOMINIUM);
        System.out.println("RealEstate:\n" + estate);

        estate.makeDiscount(10); // Apply 10% discount
        System.out.println("After Discount:\n" + estate);

        Panel panel = new Panel("Debrecen", 1200, 50, 2, Genre.FAMILYHOUSE, 1, true);
        System.out.println("Panel:\n" + panel);

        System.out.println("Panel room price: " + panel.roomPrice());
        System.out.println("Panel same as estate? " + panel.hasSameAmount(estate));
    }
}
