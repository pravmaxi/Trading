package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.TimeUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Rm {

    static String getText;
    static String mdevmaindata1;
    static String mdevmaindata2;

//    static WebDriver driver;
//
//    public Loginpage(WebDriver driver) {
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }

    static void xpathClick(String webElement){
        WebDriverWait wait=new WebDriverWait(Base.d,Duration.ofSeconds(10));
        Base.d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOf(Base.d.findElement(By.xpath(webElement)))).click();

//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(webElement))).click();
//        Base.d.findElement(By.xpath(webElement)).click();

    }

    static String xpathGetText(String webElement){
        WebDriverWait wait=new WebDriverWait(Base.d,Duration.ofSeconds(50));
        Base.d.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
//        getText=(wait.until(ExpectedConditions.visibilityOf(Base.d.findElement(By.xpath(webElement))))).getText();
        getText=(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElement)))).getText();

        return getText;


    }


    static void xpathSendKeys(String webElement, String sendKeys){

        WebDriverWait wait=new WebDriverWait(Base.d,Duration.ofSeconds(10));



        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(webElement))).sendKeys(sendKeys);



    }
}
