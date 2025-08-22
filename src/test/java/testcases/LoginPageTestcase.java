package testcases;

import org.testng.annotations.Test;
import pages.HomePageAndLoginPage;
import utilities.BaseDriver;

public class LoginPageTestcase extends BaseDriver {
    HomePageAndLoginPage homePageObj = new HomePageAndLoginPage();
@Test
    public void registrationFlowVerify() throws InterruptedException {

    homePageObj.isElementVisible(homePageObj.login_button,4);
    homePageObj.findElement(homePageObj.email_input_field).sendKeys(homePageObj.USER_EMAIL);
    homePageObj.findElement(homePageObj.password_input_field).sendKeys(homePageObj.USER_PASSWORD);

    Thread.sleep(2000);
    }
}
