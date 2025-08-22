package pages;

import org.openqa.selenium.By;

public class HomePageAndLoginPage extends BasePage{

    public  String ADD_RESIDENT_PAGE_TITTLE_LOCATOR = ("Add Resident");

    public By email_input_field =By.xpath("//input[@id='userName']");
    public By password_input_field =By.xpath("//input[@id='password']");
    public By login_button = By.xpath("//button[@type='submit']");


    //Resident
    public By resident_button = By.xpath("//a[normalize-space()='Resident']");
    public By add_resident_button =By.xpath("//a[contains(@class,'hidden lg:block')]//button[contains(@type,'button')]");
    public By add_resident_page_tittle_locator =By.xpath("//div[@class='first-step flex flex-col gap-y-5']//div[@class='text-h3-medium text-gray'][normalize-space()='Add Resident']");
    public By first_name_input_field =By.xpath("(//input[@id='firstName'])[1]");
    public By middle_name_input_field =By.xpath("(//input[@id='middleName'])[1]");
    public By surename_input_field =By.xpath("//input[@id='lastName']");
    public By addmission_date_input_button =By.xpath("//input[@id='dateOfAdmission']");
    public By select_adddnission_date =By.xpath("//ul[@class='ant-picker-ranges']");
    public By gender_input_field=By.xpath("//body[1]/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[5]/div[1]/div[1]");
    public By gender_male =By.xpath("//div[@class='ant-select-item ant-select-item-option ant-select-item-option-active']//div[@class='ant-select-item-option-content'][normalize-space()='Male']");
    public By save_and_submit_button =By.xpath("//div[@class='flex justify-end mt-6 gap-x-6']//button[@type='submit']");
    public By cancel_button =By.xpath("//div[@class='flex justify-end mt-6 gap-x-6']//a");
    public By sex_input_field =By.xpath("//body[1]/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[6]/div[1]/div[1]/div[1]");
    public By sex_input_field_male =By.xpath("//div[contains(text(),'Male')]");
    public By a =By.xpath("");
}
