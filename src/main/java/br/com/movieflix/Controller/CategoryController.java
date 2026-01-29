package br.com.movieflix.Controller;

import br.com.movieflix.Controller.request.CategoryRequest;
import br.com.movieflix.Controller.response.CategoryResponse;
import br.com.movieflix.Entity.Category;
import br.com.movieflix.Service.CategoryService;
import br.com.movieflix.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategorys() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList());


    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = CategoryMapper.toCategory(categoryRequest);
        category = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryId(@PathVariable Long id) {
        return categoryService.findById(id).
                map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category))).
                orElse(ResponseEntity.notFound().build());


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
