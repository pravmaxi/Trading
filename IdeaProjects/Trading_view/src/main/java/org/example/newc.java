package org.example;

import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.module.FindException;
import java.time.Duration;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class newc {
    static WebDriver d;
    public static void main(String[] args) throws IOException, InterruptedException {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        d=new ChromeDriver(options);
        d.manage().window().maximize();


//        d.get("https://www.tradingview.com/");
//
//        Rm.xpathClick("//div[@class='tv-header__area tv-header__area--user']//*/following-sibling::button");
//        Rm.xpathClick("(//span[@class='itemInfo-mDJVFqQ3'])[2]");
//        Rm.xpathClick("//span[text()='Email']");
//
//        Rm.xpathSendKeys("//input[@id='id_username']","hitote3863@frnla.com");
//        Rm.xpathSendKeys("//input[@id='id_password']","TopCimlinb58");
//        Rm.xpathClick("//span[text()='Sign in']");
//
//        Scanner scan =new Scanner(System.in);
//        System.out.println("solve the captcha");
//        scan.nextLine();
//
//        Rm.xpathClick("//span[text()='Sign in']");
//        System.out.println("login");

//save
//        File file = new File("cookies.data2");
//        file.createNewFile();
//        FileWriter fileWriter = new FileWriter(file);
//        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//        // Get all cookies from the browser session
//        Set<Cookie> cookies = d.manage().getCookies();
//        for (Cookie cookie : cookies) {
//            bufferedWriter.write((cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" +
//                    cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure()));
//            bufferedWriter.newLine();
//        }
//        bufferedWriter.close();
//        fileWriter.close();
//        System.out.println("scook");
        lc.loc();

        lc.loc();
        Rm.xpathClick("//a[@data-main-menu-root-track-id='products']");


    }
}
