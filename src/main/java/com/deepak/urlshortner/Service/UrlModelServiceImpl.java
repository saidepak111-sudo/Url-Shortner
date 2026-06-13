package com.deepak.urlshortner.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.urlshortner.Exception.UrlNotFoundException;
import com.deepak.urlshortner.Repository.UrlModelRepository;
import com.deepak.urlshortner.model.UrlModel;
@Service
public class UrlModelServiceImpl  implements UrlModelService{
    @Autowired
    private UrlModelRepository urlModelRepository;
    @Override
    public String urlShortner(String longurl) {

             urlModelRepository.findByLongUrl(longurl).ifPresent(url -> {
            throw new RuntimeException("This url is already present!");});
        UrlModel url=new UrlModel();
        url.setLongUrl(longurl);
        url.setCreatedAt(LocalDateTime.now());
        url.setClickCount(Long.valueOf(0));
        urlModelRepository.save(url);
        String shortUrl=encoder(url.getId());
        shortUrl="http://localhost:8080/"+shortUrl;
        url.setShortUrl(shortUrl);
        urlModelRepository.save(url);
       return shortUrl;
    }
    private String encoder(Long byLongUrl) {
       String chars =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      StringBuilder sb=new StringBuilder();
      while (byLongUrl>0) {
        int val=(int)(byLongUrl%62);
        sb.append(chars.charAt(val));
        byLongUrl=byLongUrl/62;
      }
      return sb.reverse().toString();

    }
    @Override
    public  String getShortUrl(String shortUrl) {
        UrlModel url=urlModelRepository.findByShortUrl(shortUrl).orElseThrow(()-> new UrlNotFoundException("This url is not found!"));
        url.setClickCount(url.getClickCount()+1);
        urlModelRepository.save(url);
        return url.getLongUrl();
    }
}
