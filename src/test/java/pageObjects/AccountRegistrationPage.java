package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage 
{
public AccountRegistrationPage(WebDriver driver)
{
	super(driver);
}

@FindBy(name="firstname") WebElement txt_FirstName;
@FindBy(name="lastname") WebElement txt_LastName;
@FindBy(name="email") WebElement txt_Email;
@FindBy(name="telephone") WebElement txt_Telephone;
@FindBy(name="password") WebElement txt_Password;
@FindBy(name="confirm") WebElement txt_ConfirmPassword;
@FindBy(name="agree") WebElement chk_Policy;
@FindBy(xpath="//input[@value='Continue']") WebElement btn_Continue;
@FindBy(xpath="//div[@id='content']//h1") WebElement msg_Confirmation;

public void setFirstName(String fname)
{
	txt_FirstName.sendKeys(fname);
}

public void setLastName(String lname) 
{
	txt_LastName.sendKeys(lname);
}

public void setEmail(String email)
{
	txt_Email.sendKeys(email);
}

public void setTelephone(String tel)
{
	txt_Telephone.sendKeys(tel);
}

public void setPassword(String password) 
{
	txt_Password.sendKeys(password);
}

public void setConfirmPassword(String password)
{
	txt_ConfirmPassword.sendKeys(password);
}

public void clickPolicy() 
{
	chk_Policy.click();
}

public void clickContinue() 
{
	btn_Continue.click();
}
public String confirmationMessage()
{
try {	
return (msg_Confirmation.getText());	
}
catch(Exception e) {
	return (e.getMessage());
}
}}
