package pages;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.TestListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import static org.testng.Assert.assertEquals;
import static utilities.BaseDriver.getDriver;

public class BasePage {

    public File _FILE_PATH = new File("src/test/resources/file.txt");

//    //Credentials
////    Dotenv dotenv = Dotenv.load();
//    public  String USER_EMAIL= dotenv.get("USER_EMAIL");
//    public  String USER_PASSWORD = dotenv.get("USER_PASSWORD");


    public String USER_EMAIL= ("zalam+org@tulip-tech.com");
    public  String USER_PASSWORD = ("Bangladesh1#");



    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERIC_CHARACTERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "$%&_+-.@#";


    public static SoftAssert softAssert = new SoftAssert();
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    public WebElement findElement(By locator)
    {
        return getDriver().findElement(locator);
    }

    public void clickOn(By locator)
    {
        findElement(locator).click();
    }

    public void writeText(By locator , String text) throws InterruptedException
    {
        findElement(locator).click();
        hardTextClear(locator);
        findElement(locator).sendKeys(text);
        Thread.sleep(500);
    }

    public void hardTextClear(By locator){
        findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a")); // Select all existing text
        findElement(locator).sendKeys(Keys.DELETE); // Delete existing text
    }

    public String getText(By locator)
    {
        return findElement(locator).getText();
    }

    public String getUrl()
    {
        return getDriver().getCurrentUrl();
    }

    public void hover(By locator)
    {
        Actions action =  new Actions(getDriver());
        action.moveToElement(findElement(locator)).perform();
    }

    public void assertionHard(By locator, String expected)
    {
        assertEquals(getText(locator), expected);
    }

    public void assertionURL(String expected)
    {
        softAssert.assertEquals(getUrl(), expected);
    }

    public void assertionSoft(By locator, String expected)
    {
        softAssert.assertEquals(getText(locator), expected, "Text did not match!");
    }

    public void alertAccept(By locator)
    {
        clickOn(locator);
        getDriver().switchTo().alert().accept();
    }

    public void alertCancel(By locator)
    {
        clickOn(locator);
        getDriver().switchTo().alert().dismiss();
    }

    public void alertInput(By locator, String text)
    {
        clickOn(locator);
        getDriver().switchTo().alert().sendKeys(text);

    }

    public void dropDownSelectByVisibleText(By locator, String text)
    {
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(text);
    }

    public void dropDownSelectByValue(By locator, String text)
    {
        Select select = new Select(findElement(locator));
        select.selectByValue(text);
    }

    public void dropDownSelectByIndex(By locator, int index)
    {
        Select select = new Select(findElement(locator));
        select.selectByIndex(index);

    }

    public void dropDownDeselectByVisibleText(By locator, String text)
    {
        Select select = new Select(findElement(locator));
        select.deselectByVisibleText(text);
    }

    public void dropDownDeselectByValue(By locator, String text)
    {
        Select select = new Select(findElement(locator));
        select.deselectByValue(text);
    }

    public void dropDownDeselectByIndex(By locator, int index)
    {
        Select select = new Select(findElement(locator));
        select.deselectByIndex(index);

    }

    public void newTab()
    {
        getDriver().switchTo().newWindow(WindowType.TAB);
    }

    public void newWindow()
    {
        getDriver().switchTo().newWindow(WindowType.WINDOW);
    }

    public List<String> windowHandling()
    {
        Set<String> windowHandles = getDriver().getWindowHandles();
        ArrayList<String> windowHandlesList = new ArrayList<String>(windowHandles);
        int size = windowHandlesList.size();
        System.out.println("Window/Tab numbers: "+size);
        return windowHandlesList;
    }

    public void windowSwitch(int tabnumber)
    {
        try
        {
            getDriver().switchTo().window(windowHandling().get(tabnumber));
        }
        catch (Exception e)
        {
            System.out.println("Window tab number not exist!!!" +"Tab number is given :"+ tabnumber);
        }

    }

    public void navigaterForward()
    {
        getDriver().navigate().forward();
    }

    public void navigaterBack()
    {
        getDriver().navigate().back();
    }

    public void navigaterRefresh()
    {

        getDriver().navigate().refresh();
    }

    public void navigaterTo(String url)
    {

        getDriver().navigate().to(url);
    }



    public void findTotalElement(String tagName)
    {
        List<WebElement> elements = getDriver().findElements(By.tagName(tagName));
        System.out.println(elements.size());
    }

    public void keyboardTab()
    {
        Actions action =  new Actions(getDriver());
        action.sendKeys(Keys.TAB);
        action.build().perform();
    }

    public void tabOnEnterOnKeyboard()
    {
        Actions action =  new Actions(getDriver());
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }

    public void keyboardSelectAll(By locator)
    {
        Actions action =  new Actions(getDriver());
        findElement(locator).click();
        action.keyDown(Keys.CONTROL);
        action.sendKeys("a");
        action.keyUp(Keys.CONTROL);
        action.build().perform();

    }

    public void keyboardBackspace()
    {
        Actions action =  new Actions(getDriver());
        action.keyDown(Keys.BACK_SPACE);
        action.build().perform();
    }

    public void keyboardCopy(By locator)
    {
        Actions action =  new Actions(getDriver());
        keyboardSelectAll(locator);
        action.keyDown(Keys.CONTROL);
        action.sendKeys("c");
        action.keyUp(Keys.CONTROL);
        action.build().perform();

    }

    public void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public void keyboardPaste()
    {
        Actions action =  new Actions(getDriver());
        action.keyDown(Keys.CONTROL);
        action.sendKeys("v");
        action.keyUp(Keys.CONTROL);
        action.build().perform();
    }

    public void scrollUntilElementFound(By targetLocator, By... optionalContainerLocator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement container = null;
        boolean isContainerScroll = optionalContainerLocator.length > 0;

        if (isContainerScroll) {
            container = findElement(optionalContainerLocator[0]);
        }

        int maxScrolls = 30;
        int scrollStep = 200;
        boolean elementFound = false;

        for (int i = 0; i < maxScrolls; i++) {
            try {
                WebElement target = findElement(targetLocator);
                if (target.isDisplayed()) {
                    elementFound = true;
                    break;
                }
            } catch (java.util.NoSuchElementException e) {
                // element not yet in DOM or not visible
            }

            if (isContainerScroll) {
                js.executeScript("arguments[0].scrollTop += arguments[1];", container, scrollStep);
            } else {
                js.executeScript("window.scrollBy(0, arguments[0]);", scrollStep);
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!elementFound) {
            throw new RuntimeException("Element not found after scrolling: " + targetLocator.toString());
        }
    }




    public void scrollHorizontallyLeft(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", getDriver().findElement(locator));

    }

    public void scrollHorizontallyUntilVisible(By containerLocator, By targetLocator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement container = getDriver().findElement(containerLocator);
        WebElement target = getDriver().findElement(targetLocator);

        int maxScroll = container.getSize().width * 10; // Avoid infinite loops
        int scrolled = 0;
        int step = 50;

        while (!isElementInView(container, target) && scrolled < maxScroll) {
            js.executeScript("arguments[0].scrollLeft += arguments[1];", container, step);
            scrolled += step;
        }
    }

    private boolean isElementInView(WebElement container, WebElement target) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (Boolean) js.executeScript(
                "const container = arguments[0], elem = arguments[1];" +
                        "const containerRect = container.getBoundingClientRect();" +
                        "const elemRect = elem.getBoundingClientRect();" +
                        "return elemRect.left >= containerRect.left && elemRect.right <= containerRect.right;"
                , container, target);
    }

    public void scrollToElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void takeScreenShotAllureAttach(String screenshotname) {
        Allure.addAttachment(screenshotname, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    public void waitForVisibilityOfElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fluentWaitClickOnElement(By locator, int holdInSeconds) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(holdInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(java.util.NoSuchElementException.class);

        WebElement element = wait.until(driver -> {
            WebElement el = driver.findElement(locator);
            return (el != null && el.isDisplayed() && el.isEnabled()) ? el : null;
        });

        element.click();
    }

    public static WebElement fluentWaitForElement(By locator, int timeoutInSeconds, int pollingTimeInMillis) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingTimeInMillis))
                .ignoring(java.util.NoSuchElementException.class); // Ignore NoSuchElementException

        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            return (element.isDisplayed()) ? element : null;
        });
    }

    public boolean isElementVisible(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void toasterMessageAssertion(By toaster_locator , String expectMessage){
        isElementVisible(toaster_locator,7);
        assertionSoft(toaster_locator,expectMessage);
    }

    public  String generateRandomEmail() {
        String[] domains = { "gmail.com", "yahoo.com", "outlook.com", "example.com", "domain.com" };
        Random random = new Random();

        // Generate a random username
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            username.append(randomChar);
        }

        // Select a random domain
        String domain = domains[random.nextInt(domains.length)];

        // Combine username and domain to form the email address
        return username.toString() + "@" + domain;
    }

    public  String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one character from each character set
        password.append(LOWERCASE_CHARACTERS.charAt(random.nextInt(LOWERCASE_CHARACTERS.length())));
        password.append(UPPERCASE_CHARACTERS.charAt(random.nextInt(UPPERCASE_CHARACTERS.length())));
        password.append(NUMERIC_CHARACTERS.charAt(random.nextInt(NUMERIC_CHARACTERS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Generate the remaining characters
        for (int i = 4; i < length; i++) {
            String allCharacters = LOWERCASE_CHARACTERS + UPPERCASE_CHARACTERS + NUMERIC_CHARACTERS + SPECIAL_CHARACTERS;
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // Shuffle the generated characters for better randomness
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = passwordArray[index];
            passwordArray[index] = passwordArray[i];
            passwordArray[i] = temp;
        }
        String pass = new String(passwordArray);
        return pass;
    }


    public void logInfo(String info){
        String border = "-------------------------------------------------";
        logger.info(border);
        logger.info(info);
        logger.info(border);
    }




    public  void assertPlaceholderText(By locator, String expectedText) {

        try{
            String actualPlaceholder = getDriver().findElement(locator).getAttribute("placeholder");
            Assert.assertEquals(actualPlaceholder, expectedText, "Placeholder text does not match!");
        } catch (Exception e) {
            assertionHard(locator,expectedText);
        }
    }

    public void calendarInput(By calendarInputField, String inputText) throws InterruptedException {
        copyToClipboard(inputText);
        fluentWaitClickOnElement(calendarInputField,5);
        Thread.sleep(1000);
        keyboardSelectAll(calendarInputField);
        keyboardPaste();
        Thread.sleep(1000);
    }


    public void saveToFile(String value ,File FILE_PATH) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(value));

        }
        catch (IOException e) {

            System.err.println("Error saving balance to file: " + e.getMessage());

        }
    }

    public String readFile(File filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine(); // Read and return only the first line
        }
    }



    public Map<String, String> getTableRowData(int rowNumber, List<String> columnNames) {
        Map<String, String> rowDataMap = new LinkedHashMap<>();

        // Build XPath for the specific row
        String rowXPath = "//tbody/tr[" + rowNumber + "]";

        try {
            // Find the row
            WebElement row = findElement(By.xpath(rowXPath));

            // Get all <td> elements in the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Start from index 1 to skip the first column if needed, and skip the last if it's "Action"
            int startIndex = 1;
            int endIndex = startIndex + columnNames.size();

            for (int i = startIndex; i < endIndex && (i - startIndex) < columnNames.size(); i++) {
                String cellText = cells.get(i).getText().replaceAll("\\s+", " ").trim();
                String columnName = columnNames.get(i - startIndex);
                rowDataMap.put(columnName, cellText);
            }

        } catch (NoSuchElementException e) {
            System.out.println("Row not found at index: " + rowNumber);
        }

        return rowDataMap;
    }

    public boolean isRadioChecked(By radioLocator, String value) {
        WebElement radioInput = findElement(radioLocator);
        WebElement parent = radioInput.findElement(
                By.xpath("./ancestor::span[contains(@class,'MuiRadio-root')]")
        );
        return parent.getAttribute("class").contains("Mui-checked");
    }

    public void forceWriteText(By locator, String text) throws InterruptedException {
        WebElement element = findElement(locator);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
        Thread.sleep(500);
    }

    public void assertContains(By locator, String partialText){
        softAssert.assertTrue(findElement(locator).getText().contains(partialText), "Text does not contain!");
    }



    public String getAssociatedLabelTextByInputId(By inputLocator) {
        WebElement inputElement = getDriver().findElement(inputLocator);
        String inputId = inputElement.getAttribute("id");

        // Now find the corresponding label using the `for` attribute
        By labelLocator = By.xpath("//label[@for='" + inputId + "']");
        WebElement labelElement = getDriver().findElement(labelLocator);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return arguments[0].innerText;", labelElement);
    }

    public String getLabelTextUsingAriaLabelledBy(By inputLocator) {
        WebElement inputElement = getDriver().findElement(inputLocator);
        String ariaLabelledBy = inputElement.getAttribute("aria-labelledby");

        if (ariaLabelledBy == null || ariaLabelledBy.isEmpty()) {
            throw new RuntimeException("aria-labelledby attribute not found!");
        }

        String labelId = ariaLabelledBy.split(" ")[0]; // use the first ID only
        By labelLocator = By.id(labelId);
        WebElement labelElement = getDriver().findElement(labelLocator);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return arguments[0].innerText;", labelElement);
    }

    public String getInputValue(By locator) {
        WebElement input = getDriver().findElement(locator);
        return input.getAttribute("value");
    }



    public boolean isFileDownloaded(String dirPath, String fileName, int timeoutInSeconds) {
        File dir = new File(dirPath);
        int elapsed = 0;
        while (elapsed < timeoutInSeconds) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().equals(fileName)) {
                        return true;
                    }
                }
            }
            try {
                Thread.sleep(1000);
                elapsed++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }



}
