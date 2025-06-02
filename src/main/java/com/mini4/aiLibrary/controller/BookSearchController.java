package com.mini4.aiLibrary.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookSearchController {

    @Value("${book.client.id}")
    private String bookClientId;

    @Value("${book.client.secret}")
    private String bookClientSecret;

    @GetMapping("/api/booksearch")
    public ResponseEntity<String> searchBooks(
            @RequestParam("query") String query,
            @RequestParam(value = "display", defaultValue = "10") int display,
            @RequestParam(value = "start", defaultValue = "1") int start) {
        try {
            // Encode the query to make it URL-safe
            String encodedQuery = java.net.URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8);

            // Construct the URL for the API request
            String url = String.format("https://openapi.naver.com/v1/search/book.json?query=%s&display=%d&start=%d",
                    encodedQuery, display, start);

            // Set up the HTTP headers with the required API credentials
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept-Language", "ko");
            headers.add("X-Naver-Client-Id", bookClientId);
            headers.add("X-Naver-Client-Secret", bookClientSecret);

            // Create the HTTP entity with the headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Use RestTemplate to send the GET request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);

            // Return the response body
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(500).body("Error occurred while fetching book data: " + e.getMessage());
        }
    }
}
