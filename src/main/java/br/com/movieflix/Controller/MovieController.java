package br.com.movieflix.Controller;

import br.com.movieflix.Controller.request.MovieRequest;
import br.com.movieflix.Controller.response.MovieResponse;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Service.MovieService;
import br.com.movieflix.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest movie) {
        Movie movieSaved = movieService.save(MovieMapper.toMovie(movie));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(movieSaved));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieId(@PathVariable  Long id){
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id,  @RequestBody MovieRequest request) {
        return movieService.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByMovieId(@PathVariable Long id) {
        Optional<Movie> optionalMovie = movieService.findById(id);
        if(optionalMovie.isPresent()) {
            movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
       return ResponseEntity.ok(movieService.findByCategory(category)
               .stream()
               .map(MovieMapper::toMovieResponse)
               .toList());
    }

}
