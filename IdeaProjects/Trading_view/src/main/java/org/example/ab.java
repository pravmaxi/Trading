package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ab {
static String RANGE ="";
    static WebDriver d;
//    @Test
//    static void driverInitialize(){
//
//        d=new ChromeDriver();
//
//    }
    @Test
    @Parameters("a")
    static void urlCalling(String a){

        WebDriver driver=new ChromeDriver();
//          RANGE = "Sheet1!A1:"+a; // Update range as needed



        driver.get("https://www.tradingview.com/chart/?symbol=NSE:"+a);
    }

//    @Test
//    static void urlCalling2(){
//
//        WebDriver dri=new ChromeDriver();
//
//
//        dri.get("https://www.tradingview.com/chart/?symbol=NSE:hal");
//    }



    public static void main(String[] args) {

        d=new ChromeDriver();



    }

}
