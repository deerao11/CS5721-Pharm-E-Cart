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
	

}