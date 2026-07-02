package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{
	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDrivern")// getting dataprovider in diff class
	public void verify_login(String email,String pwd,String exp) {
		

    	logger.info("******Starting TestLogin*****");
    	
    	try {
    	HomePage hp = new HomePage(driver);
    	
    	hp.clickMyaccount();
    	hp.clickLogin();
    	
    	LoginPage lp = new LoginPage(driver);
    	
    	lp.setEmailAddress(email);
    	lp.setPassword(pwd);
    	lp.clickLogin();
    	
    	MyAccountPage macc = new MyAccountPage(driver);
    	
    	boolean target = macc.isMyAccountPageExist();
    	
    	
    	/*
    	 * Data is valid = login success - test pass - logout
    	 *                 login fail - test fail
    	 * Data is invalid = login success - test fail - logout
    	 *                login fail - test pass 
    	 * */
    	
    	if(exp.equalsIgnoreCase("valid")) {
    		if(target==true) {
    			Assert.assertTrue(true);
    			macc.clickLogout();
    		}
    		else {
    			Assert.assertTrue(false);
    		}
    	}
    	if(exp.equalsIgnoreCase("invalid")) {
    		if(target==true) {
    			macc.clickLogout();
    			Assert.assertTrue(false);
    		}
    		else {
    			Assert.assertTrue(true);
    		}
    	}
    	}catch(Exception e) {
    		Assert.fail();
    	}
    	
    	logger.info("*****End of Test Login*******");
    	
	}
	
	

}
