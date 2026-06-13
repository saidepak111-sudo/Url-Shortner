package com.deepak.urlshortner.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.urlshortner.Service.UrlModelService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class UrlModelController {
@Autowired
private UrlModelService urlModelService;
@PostMapping ("/shortUrl")
public ResponseEntity<?> urlShortner(@RequestParam String longurl) {
    return ResponseEntity.ok(urlModelService.urlShortner(longurl));
}
@GetMapping("/longUrl")
public ResponseEntity<?> getLongUrl(@RequestParam String shortUrl) {
    return  ResponseEntity.status(302)
                          .header("Location", urlModelService.getShortUrl(shortUrl))
                           .build();
}
}