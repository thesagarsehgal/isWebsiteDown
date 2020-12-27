package com.thesagarsehgal.isWebsiteDown.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class WebCheckController {
    private static final String WEBSITE_UP = "URL UP";
    private static final String WEBSITE_DOWN = "URL DOWN";
    private static final String MALFORMED_URL = "MALFORMED URL";
    @GetMapping("/check")
    public ResponseEntity checkIfWebsiteDown(@RequestParam String url){
        try {
            URL request_url = new URL(url);
            HttpURLConnection con = (HttpURLConnection) request_url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if(responseCode>=200 && responseCode<=399){
                return ResponseEntity.status(HttpStatus.OK).body(WEBSITE_UP);
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WEBSITE_DOWN);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MALFORMED_URL);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WEBSITE_DOWN);
        }
    }
}
