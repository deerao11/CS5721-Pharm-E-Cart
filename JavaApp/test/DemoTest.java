package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Boundary.ProductCatalogPage;
import org.junit.jupiter.api.Test;

public class DemoTest {



        @Test
        void addition() {
            assertEquals(2, 2);
        }


    @Test
    void isValidCatalogTest() {
            assertEquals(ProductCatalogPage.isValidCatalog("56"),false);
        assertEquals(ProductCatalogPage.isValidCatalog("5"),true);
    }


}
