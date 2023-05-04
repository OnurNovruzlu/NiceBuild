package az.bina.main.contoller;

import az.bina.main.rest.request.CategoryDto;
import az.bina.main.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public String allCategories(Model model){
        List<CategoryDto> list = service.getAllCategories().getCategories();
        model.addAttribute("allCategories",list);
        return "forAdmin/category/all-categories";
    }
    @GetMapping("/insert")
    public String goInsertCategory(Model model){
        CategoryDto dto=new CategoryDto();
        model.addAttribute("newCategory",dto);
        return "forAdmin/category/save-category";
    }
    @PostMapping("/insert")
    public String insertCategory(@ModelAttribute CategoryDto dto){
        service.insert(dto);
        return "redirect:/category";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("categoryId")Integer categoryId){
        service.delete(categoryId);
        return "redirect:/category";
    }
    @GetMapping("/update")
    public String updateCategory(@RequestParam Integer categoryId,Model model){
        model.addAttribute("newCategory", service.getCategory(categoryId));
        return "forAdmin/category/save-category";
    }
}