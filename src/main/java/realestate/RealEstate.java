package realestate;

public class RealEstate implements PropertyInterface {
    private String city;
    private double price;
    private int sqm;
    private double numberOfRooms;
    private Genre genre;

    public RealEstate(String city, double price, int sqm, double numberOfRooms, Genre genre) {
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }

    @Override
    public void makeDiscount(int percent) {
        price = price * (1 - percent / 100.0);
    }

    @Override
    public int getTotalPrice() {
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

    @Override
    public double averageSqmPerRoom() {
        if (numberOfRooms == 0) return 0;
        return sqm / numberOfRooms;
    }

    @Override
    public String toString() {
        return "City: " + city +
                ", Price/sqm: " + price +
                ", Area: " + sqm +
                ", Rooms: " + numberOfRooms +
                ", Genre: " + genre +
                ", Total Price: " + getTotalPrice() +
                ", Avg sqm/room: " + String.format("%.2f", averageSqmPerRoom());
    }

    // Add these getter methods:
    public String getCity() {
        return city;
    }

    public double getPrice() {
        return price;
    }

    public Genre getGenre() {
        return genre;
    }
}
