package az.bina.main.contoller;

import az.bina.main.model.Agentlik;
import az.bina.main.model.Category;
import az.bina.main.rest.request.AgencyDto;
import az.bina.main.rest.request.CategoryDto;
import az.bina.main.rest.request.ElanDto;
import az.bina.main.rest.request.MailsDto;
import az.bina.main.service.AgencyService;
import az.bina.main.service.CategoryService;
import az.bina.main.service.ElanService;
import az.bina.main.service.MailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class GeneralController {
    private final MailsService mailsService;
    private final AgencyService agentlikService;
    private final ElanService elanService;
    private final CategoryService categoryService;
    @GetMapping("/")
    public String getHome(Model model){
        List<ElanDto> elanlar = elanService.getAllElan().getElans();
        model.addAttribute("elanlar",elanlar);

        List<CategoryDto> list=categoryService.getAllCategories().getCategories();
        for(CategoryDto c:list){
            if(c.getCategory_name()==null)throw new IllegalArgumentException("It is NUll");
        }
        model.addAttribute("categories",list);

        return "index";
    }
    @GetMapping("/elans")
    public String getElansPage(Model model) {
        List<ElanDto> elanlar = elanService.getAllElan().getElans();
        model.addAttribute("elanlar", elanlar);
        return "elans";
    }
    @GetMapping("/agencies")
    public String getAgentsPage(Model model){
        List<AgencyDto> agencies=agentlikService.getAllAgencies().getAgentlikler();
        model.addAttribute("agencies",agencies);
        return "agencies";
    }

    @GetMapping("/contact")
    public String getContact(Model model){
        MailsDto dto=new MailsDto();
        model.addAttribute("newMail",dto);
        return "contact";
    }
    @PostMapping("/sendEmail")
    public String insertMail(@ModelAttribute MailsDto dto){
        mailsService.insertMail(dto.getName(), dto.getEmail(), dto.getMessage());
        return "contact";
    }

    @GetMapping("/addElan")
    public String getAddPostPage(Model model){
        List<AgencyDto> agencies=agentlikService.getAllAgencies().getAgentlikler();
        model.addAttribute("agencies",agencies);
        List<CategoryDto> categories=categoryService.getAllCategories().getCategories();
        model.addAttribute("categories",categories);
        return "addElan";
    }
    
    @PostMapping("/addElan")
    @ResponseStatus(HttpStatus.CREATED)
    public String getAgency(@RequestParam("name")String name,
                            @RequestParam("price")Integer price,
                            @RequestParam("location")String location,
                            @RequestParam("contact") String contact,
                            @RequestParam("agent_id") Agentlik agent_id,
                            @RequestParam("category_id") Category category_id,
                            @RequestParam("image") MultipartFile image, Model model){
        elanService.saveToDB(name, price, location, contact, agent_id, category_id, image);
        List<AgencyDto> agencies=agentlikService.getAllAgencies().getAgentlikler();
        model.addAttribute("agencies",agencies);
        List<CategoryDto> categories=categoryService.getAllCategories().getCategories();
        model.addAttribute("categories",categories);
        return "addElan";
    }

    @GetMapping("/search")
    public String findByParams(@RequestParam("min")String minStr,
                               @RequestParam("max")String maxStr,
                               @RequestParam("category")String catNameStr,
                               @RequestParam("location")String locationStr,
                               Model model){
        int min=0;
        if(minStr!=null && !minStr.trim().isEmpty()){
            min=Integer.parseInt(minStr);
        }
        int max=2147483647;
        if(maxStr!=null && !maxStr.trim().isEmpty()){
            max=Integer.parseInt(maxStr);
        }
        String location="";
        if(!locationStr.isEmpty()){
            location=locationStr;
        }
        String catName="";
        if(!catNameStr.isEmpty()){
            catName=catNameStr;
        }
        List<ElanDto> elans = elanService.findByParams(min, max,catName,location).getElans();
        model.addAttribute("elanlar",elans);

        List<CategoryDto> list=categoryService.getAllCategories().getCategories();
        for(CategoryDto c:list){
            if(c.getCategory_name()==null)throw new IllegalArgumentException("Category Field is NUll");
        }
        model.addAttribute("categories",list);

        return "index";
    }
}