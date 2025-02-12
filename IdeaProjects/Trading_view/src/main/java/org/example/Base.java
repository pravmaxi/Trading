package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import static org.example.GoogleSheetsUpdater.SPREADSHEET_ID;

//import static org.example.Runners.rangeplot;

public class Base extends Runner{
    static WebDriver d;
    static String csvFilePath = "/Users/apple/Downloads/symbols - Sheet1.csv";

    static List<String> stockData;
//    static int rangeplot = 2;
    static String RANGEMAIN = "Sheet1!A"; // Starting cell where data will be written (adjust based on sheet)


    // Method for initial login and saving cookies
    static void initialLogin() throws IOException {
        d.get("https://www.tradingview.com/");

        // Handle login elements
        Rm.xpathClick("//div[@class='tv-header__area tv-header__area--user']//*/following-sibling::button");
        Rm.xpathClick("(//span[@class='itemInfo-mDJVFqQ3'])[2]");
        Rm.xpathClick("//span[text()='Email']");
        System.out.println("Logged in 1.");


        // Input credentials
        Rm.xpathSendKeys("//input[@id='id_username']", "wqgozuhnkhmgfmlopy@ytnhy.com");
        Rm.xpathSendKeys("//input[@id='id_password']", "TopCimlinb58");
        Rm.xpathClick("//span[text()='Sign in']");
        System.out.println("Logged in 2.");


        // Wait for user to solve CAPTCHA manually
        System.out.println("Solve the CAPTCHA and press Enter...");
        new BufferedReader(new InputStreamReader(System.in)).readLine();

        // Save cookies after login
        try {
            saveCookies();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Login completed and cookies saved.");
    }

    // Method to save cookies
    static void saveCookies() throws IOException {
        File file = new File("cookies.data");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Get cookies from the browser session
        Set<Cookie> cookies = d.manage().getCookies();
        for (Cookie cookie : cookies) {
            bufferedWriter.write((cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" +
                    cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure()));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
        System.out.println("Cookies saved.");
    }

    // Method to load cookies from saved file
    static void loadCookies() throws IOException {
        File file = new File("cookies.data");
        if (!file.exists()) {
            System.out.println("No cookies file found. Please login first.");
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            StringTokenizer token = new StringTokenizer(line, ";");
            String name = token.nextToken();
            String value = token.nextToken();
            String domain = token.nextToken();
            String path = token.nextToken();
            String expiry = token.nextToken();
            boolean isSecure = Boolean.parseBoolean(token.nextToken());

            // Create and add cookie
            Cookie cookie = new Cookie.Builder(name, value).domain(domain).path(path).isSecure(isSecure).build();
            d.manage().addCookie(cookie);
        }

        bufferedReader.close();
        System.out.println("Cookies loaded.");
    }

    // Method to perform the login check
    static void checkLoginStatus() throws IOException {
        d.get("https://www.tradingview.com");

        // Try loading cookies
        loadCookies();

        // Wait for cookies to take effect
        d.navigate().refresh();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Check if the login was successful by looking for a specific element that appears after login
        try {
            WebElement profileIcon = d.findElement(By.xpath("//div[@class='tv-header__area tv-header__area--user']"));
            System.out.println("Logged in with cookies.");
        } catch (NoSuchElementException e) {
            System.out.println("Not logged in, proceeding with manual login.");
            initialLogin();
        }
        System.out.println("Logged in successfully.");
        //test load url
        d.get("https://www.tradingview.com/chart/?symbol=NSE:IRFC");


    }


    static String mdevMainData() throws IOException, InterruptedException {
        try {
            // Try to get text from the first element

            Rm.xpathGetText("//div[@title='Força Alma']");
            while ((Rm.getText.equals("∅") || Rm.getText.isEmpty())) {
                Thread.sleep(5000); // Wait 1 second
                Rm.xpathGetText("//div[@title='Força Alma']");
//                Rm.getText = d.findElement(By.xpath(webElement)).getText().trim();
//                retries--;
            }

            Rm.mdevmaindata1 = Rm.getText;
        } catch (TimeoutException | NoSuchElementException e) {
            // If element is not found, click the fallback button
            System.out.println("Element not found, clicking fallback button...");
            Rm.xpathClick("//button[@aria-label='Object Tree and Data Window']");
            Rm.xpathClick("//button[@aria-label='Object Tree and Data Window']");
//            Rm.xpathClick("//span[@title='Data Window']");
        }
        return Rm.getText;

    }

    static String mdevMainData2() {
        try {
            // Try to get text from the first element
            Rm.xpathGetText("//div[@title='Cruzamento1']");
            Rm.mdevmaindata2 = Rm.getText;
        } catch (TimeoutException | NoSuchElementException e) {
            // If element is not found, click the fallback button
            System.out.println("Element not found, clicking fallback button...");
            Rm.xpathClick("//button[@aria-label='Object Tree and Data Window']");
            Rm.xpathClick("//button[@aria-label='Object Tree and Data Window']");
//            Rm.xpathClick("//span[@title='Data Window']");
        }
        return Rm.getText;

    }

    public static String getRangeMain() {
        return RANGEMAIN + ;
    }


    static WebDriver driverSetup() {

        // Set up ChromeDriver with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // Disable GPU acceleration (recommended)
        options.addArguments("--window-size=1920,1080"); // Set window size
        options.addArguments("--ignore-certificate-errors"); // Ignore SSL errors


        d = new ChromeDriver(options);
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return d;

    }

    static void moveCursor(){

        Actions actions = new Actions(d);

        // Move cursor to (X: 300, Y: 400) on the screen
        actions.moveByOffset(1492, 190).perform();
        System.out.println("Cursor moved to (1164, 176)");
    }

    static void dataFetcher() throws GeneralSecurityException, IOException, InterruptedException {

        for (String symbol : GoogleSheetsReader.greader()) {
            String url = "https://www.tradingview.com/chart/?symbol=NSE:" + symbol;
            stockData = new ArrayList<>();
            stockData.add(symbol);
            d.get(url);

            mdevMainData();
            stockData.add(Rm.getText);

            System.out.print(symbol);
            System.out.print(Rm.getText);

            mdevMainData2();
            stockData.add(Rm.getText);
            System.out.println(Rm.getText);


            List<List<Object>> values = new ArrayList<>();
            values.add(new ArrayList<>(stockData));
            GoogleSheetsUpdater.gsheetUpdate(values,SPREADSHEET_ID, getRangeMain());
            Runner.rangeplot++;


        }
    }

    public void tearDown() {
        if (d != null) {
            d.quit();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException, GeneralSecurityException {

        Runner run=new Runner();

        driverSetup();

        // First attempt to load cookies and log in
        checkLoginStatus();
        moveCursor();

        // Do your actions here...
        dataFetcher();


//        d.quit();
    }

}
