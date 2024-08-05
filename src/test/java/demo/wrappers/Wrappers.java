package demo.wrappers;

import java.time.*;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    ChromeDriver driver;

    public static boolean click(WebElement elementToClick, ChromeDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(elementToClick));

            Actions act = new Actions(driver);
            act.moveToElement(elementToClick).perform();

            Thread.sleep(500);

            act.click(elementToClick).perform();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                elementToClick.click();
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public static  boolean sendKeys(ChromeDriver driver, WebElement inputBox, String keysToSend){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(inputBox));
        try{if(inputBox.isDisplayed()){
            inputBox.sendKeys(keysToSend);
            return true;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }

    public static String getsevendaysback(){
        LocalDate currentdate = LocalDate.now();
        LocalDate datesevendaysback = currentdate.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        String formatteddate = datesevendaysback.format(formatter);
        return formatteddate;
    }

    public  static String getcurrenttime(){
        LocalTime currenttime = LocalTime.now();

        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattime = currenttime.format(timeformatter);
        return  formattime;
    }
}

