package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("Starting Test case 01");

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        String formheaderxpath = "(//span[normalize-space()='QA Assignment - Automate Google Forms'])[1]";
        String Namexpath = "(//input[@type='text'])[1]";

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formheaderxpath)));

        WebElement name = driver.findElement(By.xpath(Namexpath));
        wait.until(ExpectedConditions.visibilityOf(name));
        Wrappers.sendKeys(driver, name, "Crio Learner");

        long epochtime = System.currentTimeMillis() / 1000L;
        String textbox2value = "I want to be the best QA Engineer! "+epochtime;
        WebElement automationpracticequestion = driver.findElement(By.xpath("(//textarea[@aria-label='Your answer'])[1]"));
        Wrappers.sendKeys(driver,automationpracticequestion, textbox2value);
// "(//div[@class='nWQGrd zwllIb'])//span"
        WebElement radioButton = driver.findElement(By.id("i13"));
        radioButton.click();

        List<WebElement> Checkboxes = driver.findElements(By.xpath("//div[@class='Y6Myld']//div[@role='list'] //div[@role='listitem'] //span"));

        for(WebElement checkbox : Checkboxes){
            String Checkboxvalue = checkbox.getText();
            if(Checkboxvalue.equals("Java") || Checkboxvalue.equals("Selenium") || Checkboxvalue.equals("TestNG") ){
                checkbox.click();
            }

        }


        WebElement salutation = driver.findElement(By.xpath("//span[normalize-space()='Choose']"));
        salutation.click();
        Thread.sleep(5000);

        String salvar ="Mr";
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='presentation'])[5]")));
        String salutationtextxpath="(//div[@class='OA0qNb ncFHed QXL7Te'])//div[@data-value='"+salvar+"']";
//       List<WebElement> salutationsoptions = driver.findElements(By.xpath(salutationtextxpath));
        WebElement Salutationoption = driver.findElement(By.xpath(salutationtextxpath));
        Salutationoption.click();



//        for(WebElement option : salutationsoptions) {
//            String Soption = option.getText();
//            if (Soption.equals("Mr")) {
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
//                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
//                break;
//            }
//        }


        WebElement inputdate = driver.findElement(By.xpath("//input[@type='date']"));
        String sevendaybackdate = Wrappers.getsevendaysback();

        inputdate.sendKeys(sevendaybackdate);


        WebElement Hour = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement Minutes = driver.findElement(By.xpath("//input[@aria-label='Minute']"));

        String currenttime = Wrappers.getcurrenttime();
        String[] currenttimeHHMM = currenttime.split(":");
        String HH =currenttimeHHMM[0];
        String MM =currenttimeHHMM[1];

        Wrappers.sendKeys(driver,Hour,HH);
        Wrappers.sendKeys(driver,Minutes,MM);


        WebElement Submitbutton = driver.findElement(By.xpath("//div[@aria-label='Submit']"));
        Submitbutton.click();



        WebElement Successmessage = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        wait.until(ExpectedConditions.visibilityOf(Successmessage));
        String successmessagetext = Successmessage.getText();
        if(Successmessage.isDisplayed()){
            System.out.println("Form has been submitted " + successmessagetext);
        }
        else{
            System.out.println("Form not submitted");
        }


    }

    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }



    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}