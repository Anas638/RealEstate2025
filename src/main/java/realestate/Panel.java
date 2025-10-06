package realestate;

public class Panel extends RealEstate implements PanelInterface {
    private int floor;
    private boolean isInsulated;

    public Panel(String city, double price, int sqm, double numberOfRooms, Genre genre, int floor, boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre);
        this.floor = floor;
        this.isInsulated = isInsulated;
    }

    @Override
    public int getTotalPrice() {
        double total = super.getTotalPrice();
        if (floor >= 0 && floor <= 2) {
            total *= 1.05;
        }
        if (floor == 10) {
            total *= 0.95;
        }
        if (isInsulated) {
            total *= 1.05;
        }
        return (int) Math.round(total);
    }

    @Override
    public String toString() {
        return super.toString() + ", Floor: " + floor + ", Insulated: " + isInsulated;
    }

    @Override
    public boolean hasSameAmount(RealEstate estate) {
        return this.getTotalPrice() == estate.getTotalPrice();
    }

    @Override
    public int roomPrice() {
        double rooms = super.averageSqmPerRoom();
        if (rooms == 0) return 0;
        return (int) Math.round(getTotalPrice() / rooms);
    }
}
