package br.com.movieflix.Service;

import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Entity.Streaming;
import br.com.movieflix.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final  MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;


    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> {
            categoryService.findById(category.getId()).ifPresent(categoriesFound::add);
        });
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> {
           streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add);
        });
        return streamingsFound;
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
