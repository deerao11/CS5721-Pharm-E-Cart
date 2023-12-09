package Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
	@Test
	public void loginTest() {
	DemoTest test = new DemoTest();
	test.validLogin("sheeth", "sheeth");
	test.inValidLogin("Sheethal", "Sheethal");
	}

	@Test
	public void registrationTest() {
	DemoTest test = new DemoTest();
	test.validRegistration("Sheethal", "H", "SheeThal"+Math.random(), "Sheet", "121324", "Banglore", "etfrgwsf", "sheetha"+Math.random()+"@gmail.com");
	}

	@Test
	public void validCatalogDetails() {
		String catalogId = "1";
		DemoTest test = new DemoTest();
		test.validCatalogDetails(catalogId);
	}


	@Test
	public void isValidCatalog() {
		DemoTest test = new DemoTest();
		boolean cat = test.isValidCatalog( " 1");
		Assertions.assertFalse(cat);
		
	}

}
