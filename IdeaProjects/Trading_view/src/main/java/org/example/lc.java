package org.example;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.util.StringTokenizer;

public class lc {

//   static  WebDriver  driver;
//
//    public  void  Loginpage(WebDriver driver) {
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }


    public static void loc() throws IOException, InterruptedException {



            // Set up ChromeDriver
//             Base.d = new ChromeDriver();

            // Navigate to the website but don't login
            newc.d.get("https://www.tradingview.com");

            // Load the cookies from the saved file
            File file = new File("cookies.data2");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, ";");
                String name = token.nextToken();
                String value = token.nextToken();
                String domain = token.nextToken();
                String path = token.nextToken();
                String expiry = token.nextToken();
                boolean isSecure = Boolean.parseBoolean(token.nextToken());

                // Add cookie back to browser session
                Cookie cookie = new Cookie.Builder(name, value).domain(domain).path(path).isSecure(isSecure).build();
                newc.d.manage().addCookie(cookie);
            }

            bufferedReader.close();

            Thread.sleep(1000);

            // Refresh the page to apply the cookies
            newc.d.navigate().refresh();

        System.out.println("lc");


            // Now you should be logged in automatically
            // Continue with your testing

    }



}
