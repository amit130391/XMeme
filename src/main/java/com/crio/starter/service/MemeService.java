package com.crio.starter.service;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.Meme;

public interface MemeService {
     Meme saveMeme(Meme meme);
     List<Meme> getAllMemes();
     List<Meme> getLatestMemes();
     Optional<Meme> getMemeById(String id);
     boolean memeExists(String name, String caption, String url);
}
