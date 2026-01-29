package br.com.movieflix.mapper;


import br.com.movieflix.Controller.request.CategoryRequest;
import br.com.movieflix.Controller.response.CategoryResponse;
import br.com.movieflix.Entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
                .builder()
                .name(categoryRequest.name()).
                build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .id(category.getId())
                .build();
    }
}
