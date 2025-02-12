package org.example;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.testng.IResultMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleSheetsReader extends Runner{
    private static final String APPLICATION_NAME = "Google Sheets Data Reader";
    private static final String SPREADSHEET_ID = "1k4qp7X4g0G8uWd5enzKIGJv-p3RKXlBVDo2Uj3iKwQE"; // Replace with actual Sheet ID
//    private static final String RANGE = "Sheet1!A1:B2"; // Update range as needed
    private static final String CREDENTIALS_FILE_PATH = "/Users/apple/Downloads/automation-project-429417-c51140fdff86.json"; // JSON key file path

    static List<List<Object>> sheetData ;
    static List<List<Object>> data ;
    static Object op ;
    static List<String> nlist ;

    static List<String> greader() throws GeneralSecurityException, IOException {

        Sheets sheetsService = getSheetsService();

        // Read data from Google Sheets
        sheetData = readSheet(sheetsService, SPREADSHEET_ID, RANGE);

        List<List<String>> stringA = convertSheetData(sheetData);
         nlist = new ArrayList<>();



        for (List<String> row : stringA) {
            op = row.toString().replace("[", "").replace("]", "");
            nlist.add(op.toString());


        }
        return nlist;


    }

    public static List<List<String>> convertSheetData(List<List<Object>> sheetData) {
        return sheetData.stream()
                .map(row -> row.stream()
                        .map(Object::toString) // Convert each Object to String
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        // Authenticate & create Sheets API client
//        Sheets sheetsService = getSheetsService();
//
//        // Read data from Google Sheets
//        List<List<Object>> sheetData = readSheet(sheetsService, SPREADSHEET_ID, RANGE);
//
//        // Print data (for debugging)
//        for (List<Object> row : sheetData) {
//            System.out.println(row);
//        }

        greader();
    }

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // Load credentials
        FileInputStream serviceAccountStream = new FileInputStream(CREDENTIALS_FILE_PATH);
        ServiceAccountCredentials credentials = (ServiceAccountCredentials) ServiceAccountCredentials
                .fromStream(serviceAccountStream)
                .createScoped(List.of("https://www.googleapis.com/auth/spreadsheets.readonly"));

        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static List<List<Object>> readSheet(Sheets sheetsService, String spreadsheetId, String range) throws IOException {
        ValueRange response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
        return response.getValues();
    }
}

