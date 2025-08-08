package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
public LoginPage(WebDriver driver) {
	super(driver);
}

@FindBy(name="email") WebElement txt_Email;
@FindBy(name="password") WebElement txt_Password;
@FindBy(xpath="//input[@value='Login']") WebElement btn_Login;

public void setEmail(String email) {
	txt_Email.sendKeys(email);
}

public void setPassword(String pwd) {
	txt_Password.sendKeys(pwd);
}

public void clickLogin() {
	btn_Login.click();
}

}
