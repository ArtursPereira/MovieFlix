package br.com.movieflix.Service;

import br.com.movieflix.Entity.Streaming;
import br.com.movieflix.Repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final  StreamingRepository  streamingRepository;

    public List<Streaming> findall() {
        return streamingRepository.findAll();
    }

    public Optional<Streaming> findById(Long id) {
        return streamingRepository.findById(id);
    }

    public Streaming save(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public void deleteById(Long id) {
        streamingRepository.deleteById(id);
    }
}
