package org.example;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Runner {

     String RANGE;
    int rangeplot;

    private static ThreadLocal<Base> helper = new ThreadLocal<>();



    @BeforeMethod
    public void setup() {
        Base testHelper = new Base();
        Base.driverSetup();
        helper.set(testHelper); // Assign separate helper for each thread
    }

    @Test
    @Parameters({"a","b"})
    static void tvData(String a,int b) throws IOException, GeneralSecurityException, InterruptedException {

        Base testHelper = helper.get(); // Get thread-specific helper instance

//        testHelper.driverSetup();

        // First attempt to load cookies and log in
        testHelper.checkLoginStatus();
         RANGE = a; // Update range as needed

        testHelper.moveCursor();

        // Do your actions here...
         rangeplot = b;

        testHelper.dataFetcher();

    }

    @AfterMethod
    public void tearDown() {
        if (helper.get() != null) {
            helper.get().tearDown();
            helper.remove();
        }
    }



}
