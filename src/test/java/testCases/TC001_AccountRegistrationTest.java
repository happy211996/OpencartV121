package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass
{
@Test(groups={"Regression", "Master"})
public void verify_Account_Registration()  
{
try {
logger.info("*****Starting TC001_AccountRegistrationTest*****  ");
HomePage hp=new HomePage(driver);	
hp.clickMyAccount();
logger.info("Clicked on the My Account Link...");

hp.clickRegister();
logger.info("Clicked on the Register Link...");

AccountRegistrationPage repage=new AccountRegistrationPage(driver);

logger.info("Providing Customer details...");
repage.setFirstName(randomString().toUpperCase());
repage.setLastName(randomString().toUpperCase());
repage.setEmail(randomString()+"@gmail.com");
repage.setTelephone(randowmNumber());

String password=randomAlphaNumeric();
repage.setPassword(password);
repage.setConfirmPassword(password);

repage.clickPolicy();
repage.clickContinue();

logger.info("Validating expected message...");
String conf_msg=repage.confirmationMessage();

if(conf_msg.equals("Your Account Has Been Created!")) 
{
	Assert.assertTrue(true);
//Assert.assertEquals(conf_msg, "Your Account Has Been Created!");

}
else
{
logger.error("Test Failed...");
logger.debug("Debug logs...");
Assert.assertTrue(false);
}
}

catch(Exception e) 
{
	Assert.fail();
}

logger.info("*****Finished TC001_AccountRegistrationTest*****  ");

}



}
