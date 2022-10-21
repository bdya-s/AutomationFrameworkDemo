package test.automation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import projectUtils.Constants;

public class BaseTest {
	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;	
	
	@BeforeTest
	public void beforeTestTask() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Results/AutomationReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Framework Demo Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	//	extent.setSystemInfo("Automation owner", "Bdya S");
	}
	
	
	@BeforeMethod
	@Parameters(value ={"browserName"})
	public void beforeMethodTask(String browserName, Method testMethod) {
		logger = extent.createTest(testMethod.getName());
		setUpDriver(browserName);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void afterMethodTask(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "";
		
		if(result.getStatus()==ITestResult.SUCCESS) {	
			logText = "Test Case: " + methodName + "Passed!";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		}else if(result.getStatus()== ITestResult.FAILURE) {
			logText = "Test Case: " + methodName + "Failed!";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		}
		driver.quit();
	}
	
	@AfterTest
	public void afterTestTask() {
		extent.flush();		
	}	
	
	public void setUpDriver(String browserName) {
		
		switch(browserName){
			case "chrome":
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
				System.setProperty("webdriver.chrome.whitelistedIps", "192.168.0.18");
				driver = new ChromeDriver();
				break;
				
			case "firefox":
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "//Drivers//geckodriver.exe");
				driver = new FirefoxDriver();
				break;
				
			default:
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
				driver = new ChromeDriver();		
		}
	}
	
}
