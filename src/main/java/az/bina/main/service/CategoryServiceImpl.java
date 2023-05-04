package az.bina.main.service;

import az.bina.main.model.Category;
import az.bina.main.repository.CategoryRepository;
import az.bina.main.rest.request.CategoryDto;
import az.bina.main.rest.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;
    @Override
    public CategoryResponse getAllCategories() {
        List<Category> list=repository.findAll();
        List<CategoryDto> listDto=new ArrayList<>();
        for(Category c:list){
            CategoryDto cDto=convertToDto(c);
            listDto.add(cDto);
        }
        return CategoryResponse.builder().categories(listDto).build();
    }

    @Override
    public CategoryDto getCategory(int id) {
        Category c=repository.getReferenceById(id);
        return convertToDto(c);
    }

    @Override
    public void insert(CategoryDto dto) {
    Category c=new Category();
    c.setCategory_id(dto.getCategory_id());
    c.setCategory_name(dto.getCategory_name());
    repository.save(c);
    }

    @Override
    public void delete(int id) {
        Category cat = repository.getReferenceById(id);
        repository.delete(cat);
    }

    @Override
    public void update(CategoryDto categoryDto,int id) {
    Category c=repository.getReferenceById(id);
    c.setCategory_name(categoryDto.getCategory_name());
    repository.save(c);
    }
    private CategoryDto convertToDto(Category category){
        return CategoryDto.builder().category_id(category.getCategory_id())
                .category_name(category.getCategory_name()).build();
    }
}
