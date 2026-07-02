package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
    public void LoginTest() {
    	
    	logger.info("******Starting TestLogin*****");
    	try {
    	HomePage hp = new HomePage(driver);
    	
    	hp.clickMyaccount();
    	hp.clickLogin();
    	
    	LoginPage lp = new LoginPage(driver);
    	
    	lp.setEmailAddress(p.getProperty("email"));
    	lp.setPassword(p.getProperty("password"));
    	lp.clickLogin();
    	
    	MyAccountPage macc = new MyAccountPage(driver);
    	
    	boolean target = macc.isMyAccountPageExist();
    	
    	Assert.assertTrue(target);
    	//Assert.assertEquals(target, true,"login is failed");
    	}
    	catch(Exception e){
    		Assert.fail();
    	}
    	logger.info("*****End of Test Login*******");
    	
    	
    	
    }
}
