package br.com.movieflix.mapper;

import br.com.movieflix.Controller.request.MovieRequest;
import br.com.movieflix.Controller.response.CategoryResponse;
import br.com.movieflix.Controller.response.MovieResponse;
import br.com.movieflix.Controller.response.StreamingResponse;
import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Entity.Streaming;

import java.util.List;

public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();


        return Movie
                .builder()
                .title(movieRequest.title())
                .categories(categories)
                .description(movieRequest.description())
                .rating(movieRequest.rating())
                .streamings(streamings)
                .releaseDate(movieRequest.releaseDate())
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {

        List<CategoryResponse> categoriesResponse = movie.getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamingsResponse = movie.getStreamings()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse.builder()
                .title(movie.getTitle())
                .id(movie.getId())
                .rating(movie.getRating())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .categories(categoriesResponse)
                .streamings(streamingsResponse)
                .build();

    }
}
