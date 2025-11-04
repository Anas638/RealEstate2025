package realestate;

public class Main {
    public static void main(String[] args) throws Exception {
        RealEstateAgent.loadPropertiesFromFile("realestates.txt");
        RealEstateAgent.outputResults("outputRealEstate.txt");
    }
}
