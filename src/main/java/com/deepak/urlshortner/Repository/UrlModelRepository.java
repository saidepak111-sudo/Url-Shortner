package com.deepak.urlshortner.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.urlshortner.model.UrlModel;

public interface UrlModelRepository  extends JpaRepository<UrlModel, Long> {
    Optional<UrlModel> findByShortUrl(String shortUrl);

   Optional<UrlModel> findByLongUrl(String longUrl);

}
