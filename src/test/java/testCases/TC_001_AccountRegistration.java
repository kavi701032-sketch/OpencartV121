package testCases;

import org.testng.annotations.Test;

import org.testng.AssertJUnit;
import org.testng.Assert;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass {

	
	
	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration(){
		
		try {
		logger.info("*****Starting TC_001_AccountRegistration******");
		
		HomePage hp = new HomePage(driver);
		
		hp.clickMyaccount();
		logger.info("Clicked on My Account");
		
		hp.clickRegister();
		logger.info("Clicked Register");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details");
		regpage.setFirstname(randomeString().toUpperCase());
		regpage.setLastname(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTele(randomeNumber());
		
		String random = randomeAlphaNumeric();
		regpage.setPassword(random);
		regpage.setConfPass(random);
		
		regpage.setPrivpol();
		regpage.clkContinue();
		
		logger.info("Validating the register");
		
		
		String confmsg1 = regpage.getconfMsg();
		
		Assert.assertEquals(confmsg1, "Your Account Has Been Created!");
		
			
		/*if(confmsg1.equals("Your Account Has Been Created!!!"))// --> create wrong statement for debug purpose--> It is the correct-- Your Account Has Been Created!
		{
			AssertJUnit.assertTrue(true);
		}
		else {
			
			logger.error("Test Failed....");
			logger.debug("Debug logs....");
			AssertJUnit.assertTrue(false);
		}
		
		*/
		}
		catch(Exception e) {
			AssertJUnit.fail();
		}
		
	}
	
}
		
		
		
	
	

