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
	public void validRegistration(String fName,String lName,String username,String password,String ppsn,String address,String eircode,String emailId) {
		UserAuthenticationControl userAuthenticationControl = new UserAuthenticationControl(fName, lName, username, password, ppsn, address, eircode, emailId);
		boolean result = userAuthenticationControl.register();
		Assertions.assertTrue(result);
	}
	@Test
	public void validCatalogDetails() {
		String catalogId = "1";
		DemoTest test = new DemoTest();
		test.validCatalogDetails(catalogId);
	}

}
