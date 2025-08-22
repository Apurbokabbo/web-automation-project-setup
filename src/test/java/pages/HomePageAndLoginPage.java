package pages;

import org.openqa.selenium.By;

public class HomePageAndLoginPage extends BasePage{
    public  String USER_EMAIL= "zalam+org@tulip-tech.com";
    public  String USER_PASSWORD = "Bangladesh1#";

    public By email_input_field =By.xpath("//input[@id='userName']");
    public By password_input_field =By.xpath("///input[@id='password']");
    public By login_button = By.xpath("//button[@type='submit']");
}
