package realestate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RealEstateTest {
    RealEstate r1, r2;

    @BeforeEach
    void setUp() {
        r1 = new RealEstate("Budapest", 2000, 50, 2, Genre.FAMILYHOUSE);
        r2 = new RealEstate("Debrecen", 1500, 40, 1.5, Genre.CONDOMINIUM);
    }

    @Test
    void testMakeDiscount() {
        r1.makeDiscount(10);
        assertEquals(1800, r1.getPrice(), 0.01);
    }

    @Test
    void testTotalPrice() {
        int expected = (int)(r1.getPrice() * r1.getSqm() * 1.30);
        assertEquals(expected, r1.getTotalPrice());
    }
}
