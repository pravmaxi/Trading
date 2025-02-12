package org.example;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.w3c.dom.ranges.Range;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static org.example.Base.rangeplot;

public class GoogleSheetsUpdater extends Runner{

     static final String APPLICATION_NAME = "Stock Price Scraper";
     static final String SPREADSHEET_ID = "1CW_QtixKJ1fWIp3s7G6dXQuqrb829OYhj3RsFve4Rc4";  // Replace with your actual Google Sheet ID
     static final String RANGE = "Sheet1!A1"; // Starting cell where data will be written (adjust based on sheet)
     static final String RANG2 = "Sheet1!A6"; // Starting cell where data will be written (adjust based on sheet)
//     static final int plot = 2; // Starting cell where data will be written (adjust based on sheet)
     static  String RANGEMAIN = "Sheet1!A"+rangeplot; // Starting cell where data will be written (adjust based on sheet)
     static final String SERVICE_ACCOUNT_FILE = "/Users/apple/Downloads/automation-project-429417-c51140fdff86.json";  // Replace with the path to your JSON key

     static void gsheetUpdate(List<List<Object>> values,String SPREADSHEET_ID,String RANGE) throws GeneralSecurityException, IOException {
         Sheets service = getSheetsService();


//         List<String> stockDat = new ArrayList<>();
//         stockDat.add("1");
//         stockDat.add("2");
//         stockDat.add("3");
//         stockDat.add("4");

//         List<List<Object>> values = new ArrayList<>();
//         values.add(new ArrayList<>(stockDat));

         ValueRange body1 = new ValueRange().setValues(values);
         service.spreadsheets().values().update(SPREADSHEET_ID, RANGE, body1)
                 .setValueInputOption("RAW").execute();
     }



    public static void main(String[] args) throws IOException, GeneralSecurityException {
        // Step 1: Authenticate with the Google Sheets API
        Sheets service = getSheetsService();

        // Step 2: Data to be written to Google Sheets (For demo, we'll write stock symbol and price)
        List<List<Object>> stockData = new ArrayList<>();
        stockData.add(Arrays.asList("Stock Symbol", "Price"));
        stockData.add(Arrays.asList("HAL", "2751","df"));
        stockData.add(Arrays.asList("REC", "190"));
        stockData.add(Arrays.asList("GAIL", "116"));



        // Step 3: Write data to the Google Sheet
        ValueRange body = new ValueRange().setValues(stockData);
        service.spreadsheets().values().update(SPREADSHEET_ID, RANGE, body)
                .setValueInputOption("RAW").execute();

//        List<String> stockDat = new ArrayList<>();
//        stockDat.add("1");
//        stockDat.add("2");
//        stockDat.add("3");
//        stockDat.add("4");
//
//        List<List<Object>> values = new ArrayList<>();
//        values.add(new ArrayList<>(stockDat));
//
//        ValueRange body1 = new ValueRange().setValues(values);
//        service.spreadsheets().values().update(SPREADSHEET_ID, RANG2, body1)
//                .setValueInputOption("RAW").execute();


        System.out.println("Stock prices successfully written to Google Sheets!");
    }

    // Method to create a Sheets API client service
    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        // Load credentials from the JSON file
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_FILE))
                .createScoped(Arrays.asList(SheetsScopes.SPREADSHEETS));

        // Build and return the Sheets API client service
        return new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}

