package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	

	
	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath = "//span[@class='caret']")
	WebElement lnkMyacc;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	public void clickMyaccount() {
		
		lnkMyacc.click();
	}
	
	public void clickRegister() {
		
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	

}
