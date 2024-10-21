package com.crio.starter.repository;

import java.util.Optional;
import com.crio.starter.data.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<Meme, String> {
    Optional<Meme> findByNameAndCaptionAndUrl(String name, String caption, String url);
}