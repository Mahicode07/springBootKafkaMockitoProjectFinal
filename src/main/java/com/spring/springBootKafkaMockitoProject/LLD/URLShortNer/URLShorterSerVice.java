package com.spring.springBootKafkaMockitoProject.LLD.URLShortNer;

import java.util.HashMap;
import java.util.Map;

public class URLShorterSerVice {

    private static final String DOMAIN = "http://short.url/";
    private Map<String, String> shortToLongMap = new HashMap<>();
    private Map<String, String> longToShortMap = new HashMap<>();
    private long idCounter = 1;

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Shortens a given long URL, returns the short URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return DOMAIN + longToShortMap.get(longURL);
        }
        String shortKey = encode(idCounter++);
        shortToLongMap.put(shortKey, longURL);
        longToShortMap.put(longURL, shortKey);
        return DOMAIN + shortKey;
    }

    // Retrieves the original long URL for a given short URL
    public String getLongURL(String shortURL) {
        String key = shortURL.replace(DOMAIN, "");
        return shortToLongMap.getOrDefault(key, null);
    }

    // Base62 encoding of a numeric ID
    private String encode(long num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) return "0";
        while (num > 0) {
            sb.append(BASE62.charAt((int) (num % 62)));
            num /= 62;
        }
        return sb.reverse().toString();
    }

    // Decode method (not mandatory, but useful)
    private long decode(String shortKey) {
        long num = 0;
        for (char c : shortKey.toCharArray()) {
            num = num * 62 + BASE62.indexOf(c);
        }
        return num;
    }

    // Main method to test URL shortener
    public static void main(String[] args) {
        URLShorterSerVice service = new URLShorterSerVice();

        String url1 = "https://www.example.com/some/very/long/url";
        String url2 = "https://openai.com/blog/chatgpt";

        String shortUrl1 = service.shortenURL(url1);
        String shortUrl2 = service.shortenURL(url2);
        String shortUrl3 = service.shortenURL(url1); // Duplicate for testing

        System.out.println("Original URL: " + url1);
        System.out.println("Shortened URL: " + shortUrl1);

        System.out.println("Original URL: " + url2);
        System.out.println("Shortened URL: " + shortUrl2);

        System.out.println("Shortened URL for duplicate long URL: " + shortUrl3);

        System.out.println("Redirect " + shortUrl1 + " -> " + service.getLongURL(shortUrl1));
        System.out.println("Redirect " + shortUrl2 + " -> " + service.getLongURL(shortUrl2));
        System.out.println("Redirect " + "http://short.url/xyz" + " -> " + service.getLongURL("http://short.url/xyz")); // null test
    }
}
