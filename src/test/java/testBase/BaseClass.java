package testBase;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;// log4j manager
import org.apache.logging.log4j.Logger;// log4j 
//import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/*import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;*/
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {

public static WebDriver driver;

public Logger logger;

public Properties p;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		// Loading properties config file
		
		String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		FileReader file = new FileReader(path);
		p = new Properties();
		p.load(file);
		
		/*if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WINDOWS);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("No Matching OS ");
				return;
			}
			
		
		
		switch(br.toLowerCase())
		{
		
		case "chrome": capabilities.setBrowserName("chrome"); break;
		case "edge" : capabilities.setBrowserName("Microsoft Edge"); break;
		case "firefox" : capabilities.setBrowserName("firefox"); break;
		default: System.out.println("No Matching Browser"); return;
		}
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		*/
		switch(br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name"); return;
		}
		
		
		
		
	
		
		logger = LogManager.getLogger(this.getClass());
		
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appUrl")); // reading url from properties file
		//driver.get("https://tutorialsninja.com/demo/index.php?route=account/account");
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
	public void tearDown() {
		
		driver.quit();
	}
	
	public String randomeString() {
		
		String generatedString = RandomStringUtils.secure().nextAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber() {
		String generatedNumber = RandomStringUtils.secure().nextNumeric(10);
		return generatedNumber;
	}
	
	public String randomeAlphaNumeric() {
		String generatedAlphaNumeric = RandomStringUtils.secure().nextAlphanumeric(6);
		return generatedAlphaNumeric;
	}
	
    public String captureScreen(String tname) throws IOException { // capture Screen for test fail
        
        // Generate a clean timestamp for the image filename
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        
        // Cast the driver instance to TakesScreenshot interface
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        
        // Capture the raw screenshot file
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        
        // Define destination file path: project_root/screenshots/testName_timestamp.png
        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        
        // Move/rename source file to the targeted folder
        sourceFile.renameTo(targetFile);
        
        // Return the absolute file path string to the ExtentReport listener
        return targetFilePath;
    }

	
	
}
