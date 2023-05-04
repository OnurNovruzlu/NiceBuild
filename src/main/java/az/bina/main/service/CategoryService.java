package az.bina.main.service;

import az.bina.main.rest.request.CategoryDto;
import az.bina.main.rest.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDto getCategory(int id);
    void insert(CategoryDto categoryDto);
    void delete(int id);
    void update(CategoryDto categoryDto,int id);
}
