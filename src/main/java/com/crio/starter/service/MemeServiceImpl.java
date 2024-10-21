package com.crio.starter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.starter.data.Meme;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeServiceImpl implements MemeService{

    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Meme saveMeme(Meme meme) {
        meme.setId(sequenceGeneratorService.generateSequence());
        return memeRepository.save(meme);
    }

    public List<Meme> getAllMemes() {
        return memeRepository.findAll();
    }

    public List<Meme> getLatestMemes() {
        return memeRepository.findAll()
                .stream()
                .sorted((a, b) -> Long.compare(Long.parseLong(b.getId()), Long.parseLong(a.getId())))
                .limit(100)
                .collect(Collectors.toList());
    }

    public Optional<Meme> getMemeById(String id) {
        return memeRepository.findById(id);
    }

    public boolean memeExists(String name, String caption, String url) {
        return memeRepository.findByNameAndCaptionAndUrl(name, caption, url).isPresent();
    }
}