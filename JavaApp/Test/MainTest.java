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


}
