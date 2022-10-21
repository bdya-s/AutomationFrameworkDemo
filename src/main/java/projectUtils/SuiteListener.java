package projectUtils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import test.automation.BaseTest;

public class SuiteListener implements ITestListener, IAnnotationTransformer{
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String fileName = System.getProperty("user.dir") + "/Screenshots/"+ iTestResult.getMethod().getMethodName() + System.currentTimeMillis();
		File f = ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(f, new File(fileName + ".png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void transform(ITestAnnotation iTestAnn, Class aClass, Constructor constr, Method method) {
		iTestAnn.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
