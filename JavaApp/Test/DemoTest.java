package Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import Boundary.ProductRepository;
import Entity.CustomerDetail;
import Entity.ProductDetail;
import Control.UserAuthenticationControl;

import org.dbunit.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DemoTest {
	@Test
    public void validLogin(String userId, String password) {
		UserAuthenticationControl userAuthenticationControl = new UserAuthenticationControl(
	            userId, password
	        );

	        CustomerDetail cd = userAuthenticationControl.authenticate();
	        Assertions.assertNotNull(cd);
    }
	
	@Test
    public void inValidLogin(String userId, String password) {
		UserAuthenticationControl userAuthenticationControl = new UserAuthenticationControl(
	            userId, password
	        );

	        CustomerDetail cd = userAuthenticationControl.authenticate();
	        Assertions.assertNull(cd);
	        
	}
	
	@Test
	public void registrationTest() {
	DemoTest test = new DemoTest();
	test.validRegistration("Sheethal", "H", "SheeThal"+Math.random(), "Sheet", "121324", "Banglore", "etfrgwsf", "sheetha"+Math.random()+"@gmail.com");
	}
 
    	@Test
	public void validCatalogDetails(String catalogId) {
		ProductRepository pf = new ProductRepository();
		List<ProductDetail> pd = pf.fetchData(catalogId);
			ProductDetail product = pd.get(0);
			Assertions.assertEquals(product.getId(), "1");
			Assertions.assertEquals(product.getName(), "crocin");
//		Assert.assertEquals(product.getPrice(), "200.0");
//		Assert.assertEquals(product.getQuantity(), "31");
	}
		@Test
	public boolean isValidCatalog(String catId) {
		ProductRepository pf = new ProductRepository();
		List<ProductDetail> pd = pf.fetchData(catId);
		for(int i=0; i<pd.size(); i++) {
			if(pd.get(i).toString().contains("Category ID: "+ catId)) {
				System.out.println("Found the catalog");

			}else {
				return false;
			}
		}
		
  return true;
 }
	
}