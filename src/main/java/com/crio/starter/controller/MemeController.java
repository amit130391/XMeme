package com.crio.starter.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.crio.starter.data.Meme;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
public class MemeController {
    
    @Autowired
    private MemeService memeService;

    @PostMapping
    public ResponseEntity<Object> postMeme(@Valid @RequestBody Meme meme) {
        if (memeService.memeExists(meme.getName(), meme.getCaption(), meme.getUrl())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate meme post");
        }

        Meme savedMeme = memeService.saveMeme(meme);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"id\": \"" + savedMeme.getId() + "\"}");
    }

    @GetMapping
    public ResponseEntity<List<Meme>> getLatestMemes() {
        List<Meme> memes = memeService.getLatestMemes();
        if (memes.isEmpty()) {
            return ResponseEntity.ok().body(memes);
        } else {
            return ResponseEntity.ok().body(memes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemeById(@PathVariable String id) {
        Optional<Meme> meme = memeService.getMemeById(id);
        if (meme.isPresent()) {
            return ResponseEntity.ok(meme.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meme not found");
        }
    }
    
}
