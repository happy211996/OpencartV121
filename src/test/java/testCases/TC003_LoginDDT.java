package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass
{
@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
public void verify_LoginDDT(String email, String pwd, String exp) 
{
	logger.info("*****Starting TC_003_LoginDDT*****");

	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
   MyAccountPage macc=new MyAccountPage(driver);
   boolean targetPage=macc.isMyAccountPageExists();
   
if(exp.equalsIgnoreCase("Valid"))
{
	if(targetPage==true) {
		macc.clicLogout();
		Assert.assertTrue(true);
	}
	else {
		Assert.assertTrue(false);
	}
}

if(exp.equalsIgnoreCase("Invalid"))
{
	if(targetPage==true) {
		macc.clicLogout();
		Assert.assertTrue(false);
	}
	else {
		Assert.assertTrue(true);
	}
}
logger.info("*****Finished TC_003_LoginDDT*****");

}
}
