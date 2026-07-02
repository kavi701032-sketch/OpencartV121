	package utilities;

	import java.awt.Desktop;
	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;

	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	import testBase.BaseClass; // Assuming captureScreen is defined here

public class ExtentReportManager implements ITestListener {
	    public ExtentSparkReporter sparkReporter;
	    public ExtentReports extent;
	    public ExtentTest test;

	    String repName;

	    
	    public void onStart(ITestContext testContext) {
	        // Generate a unique timestamp for the report name
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        repName = "Test-Report-" + timeStamp + ".html";
	        
	        // Specify the storage location of the report
	        sparkReporter = new ExtentSparkReporter("./reports/" + repName); 

	        // Configure UI look and feel
	        sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
	        sparkReporter.config().setReportName("opencart Functional Testing");   // Name of the report
	        sparkReporter.config().setTheme(Theme.DARK);

	        // Populate common system/environment details
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Application", "opencart");
	        extent.setSystemInfo("Module", "Admin");
	        extent.setSystemInfo("Sub Module", "Customers");
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environemnt", "QA");

	        // Dynamically fetch OS and Browser parameters from the testng.xml file
	        String os = testContext.getCurrentXmlTest().getParameter("os");
	        extent.setSystemInfo("Operating System", os);

	        String browser = testContext.getCurrentXmlTest().getParameter("browser");
	        extent.setSystemInfo("Browser", browser);

	        // Track executed TestNG test groups if applicable
	        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	        if (!includedGroups.isEmpty()) {
	            extent.setSystemInfo("Groups", includedGroups.toString());
	        }
	    }

	   
	    public void onTestSuccess(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.assignCategory(result.getMethod().getGroups()); // Displays groups in report
	        test.log(Status.PASS, result.getName() + " got successfully executed");
	    }

	    
	    public void onTestFailure(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.assignCategory(result.getMethod().getGroups());
	        
	        test.log(Status.FAIL, result.getName() + " got failed");
	        test.log(Status.INFO, result.getThrowable().getMessage()); // Log error stack trace/message

	        // Capture screenshot and link it to the report
	        try {
	            String imgPath = new BaseClass().captureScreen(result.getName());
	            test.addScreenCaptureFromPath(imgPath);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }

	    
	    public void onTestSkipped(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.assignCategory(result.getMethod().getGroups());
	        test.log(Status.SKIP, result.getName() + " got skipped");
	        test.log(Status.INFO, result.getThrowable().getMessage());
	    }

	    
	    public void onFinish(ITestContext testContext) {
	        // Write all logs and data out to the report file
	        extent.flush();

	        // Automatically open the generated HTML report file in the default browser
	        String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
	        File extentReport = new File(pathOfExtentReport);

	        try {
	            Desktop.getDesktop().browse(extentReport.toURI());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        /* // Optional: Email sending snippet highlighted in your final screenshot
	        try {
	            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
	            
	            // Create the email message
	            ImageHtmlEmail email = new ImageHtmlEmail();
	            email.setDataSourceResolver(new DataSourceUrlResolver(url));
	            email.setHostName("smtp.googlemail.com");
	            email.setSmtpPort(465);
	            email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
	            email.setSSLOnConnect(true);
	            email.setFrom("pavanoltraining@gmail.com"); // Sender email
	            email.setSubject("Test Results");
	            email.setMsg("Please find Attached Report....");
	            email.addTo("pavankumar.busyqa@gmail.com"); // Receiver email
	            email.attach(url, "extent report", "please check report...");
	            email.send(); // Send the email
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        */
	    }
	}

