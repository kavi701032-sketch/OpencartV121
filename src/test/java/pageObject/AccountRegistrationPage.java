package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	
	
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_lastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_confPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement set_priv_pol;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement clk_Continue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confMsg;
	
	public void setFirstname(String fname) {
		txt_firstname.sendKeys(fname);
	}
	
	public void setLastname(String lname) {
		txt_lastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txt_email.sendKeys(email);
	}
	public void setTele(String no) {
		txt_telephone.sendKeys(no);
	}
	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	public void setConfPass(String cpwd) {
		txt_confPassword.sendKeys(cpwd);
	}
	public void setPrivpol() {
		set_priv_pol.click();
	}
	public void clkContinue(){
		clk_Continue.click();
	}
	
	public String getconfMsg() {
		try {
			return(confMsg.getText());
		}
		catch(Exception e){
			return(e.getMessage());
		}
	}
	

	
	
}
