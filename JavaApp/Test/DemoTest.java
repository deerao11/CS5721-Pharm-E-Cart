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
	            userId, password);
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
	public void validRegistration(String fName,String lName,String username,String password,String ppsn,String address,String eircode,String emailId) {
		UserAuthenticationControl userAuthenticationControl = new UserAuthenticationControl(fName, lName, username, password, ppsn, address, eircode, emailId);
		boolean result = userAuthenticationControl.register();
		Assertions.assertTrue(result);
   }	
	@Test
	public void validCatalogDetails(int catalogId) {
		ProductRepository pf = new ProductRepository();
		String catalogId1 =String.valueOf(catalogId);
		List<ProductDetail> pd = pf.fetchData(catalogId1);
			ProductDetail product = pd.get(0);
			Assertions.assertEquals(product.getId(), "1");
			Assertions.assertEquals(product.getName(), "crocin");
		    Assertions.assertEquals(product.getPrice(), 200);
			Assertions.assertEquals(product.getQuantity(), 19);			
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