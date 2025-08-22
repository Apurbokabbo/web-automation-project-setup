package testcases;

import org.testng.Assert;
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
    homePageObj.findElement(homePageObj.login_button).click();
     boolean toaster_visible = homePageObj.isElementVisible(homePageObj.toaster_close_button,4);
     if(toaster_visible){
         homePageObj.clickOn(homePageObj.toaster_close_button);
     }
    Thread.sleep(2000);
    homePageObj.fluentWaitClickOnElement(homePageObj.resident_button,25);
    homePageObj.fluentWaitClickOnElement(homePageObj.add_resident_button,10);
    homePageObj.isElementVisible(homePageObj.add_resident_page_tittle_locator,10);
    homePageObj.writeText(homePageObj.first_name_input_field,"Apurbo");
    homePageObj.writeText(homePageObj.middle_name_input_field,"Kabbo");
    homePageObj.writeText(homePageObj.surename_input_field,"Hossain");
    homePageObj.clickOn(homePageObj.addmission_date_input_button);
    homePageObj.clickOn(homePageObj.select_adddnission_date);
    homePageObj.clickOn(homePageObj.gender_input_field);
    homePageObj.clickOn(homePageObj.gender_male);
    homePageObj.clickOn(homePageObj.sex_input_field);
    homePageObj.clickOn(homePageObj.gender_male);


    homePageObj.clickOn(homePageObj.cancel_button);
    homePageObj.assertionURL("https://app-dev.empathika.com/app/medication/resident");
    Assert.assertTrue(homePageObj.isElementVisible(homePageObj.save_and_submit_button,25));
    homePageObj.isElementVisible(homePageObj.add_resident_button,5);







    }
}
