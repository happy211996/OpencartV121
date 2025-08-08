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

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;// Responsible for UI of the Report
	public ExtentReports extent;//Responsible for Common information of the report
	public ExtentTest test;//Responsible for the updating the testing case entries
	String repName;

	public void onStart(ITestContext testContext) 
	{
		/*SimpleDateFormat df=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		String timestamp=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-" + timestamp+ ".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\" +repName);
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");//title of the report
		sparkReporter.config().setReportName(" Opencart Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
	
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//To display groups in reports
		test.log(Status.PASS,result.getName()+ "got successfully executed");
	}
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		String imgPath=new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		
		 

	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		    File extentReport = new File(pathOfExtentReport);
	
	try {
		Desktop.getDesktop().browse(extentReport.toURI());
	}
	catch(IOException e1) 
	{
		e1.printStackTrace();
	}
	}

}
