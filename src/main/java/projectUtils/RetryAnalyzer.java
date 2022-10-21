package projectUtils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int retry =0;	
	@Override
	public boolean retry(ITestResult result) {
		while(retry < 1) {
			retry ++;
			return true;
		}
		return false;
	}
	
}
