package az.bina.main.contoller;

import az.bina.main.rest.request.AgencyDto;
import az.bina.main.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("agency")
public class AgencyController {
    private final AgencyService service;

    @GetMapping
    public String allAgencies(Model model){
        List<AgencyDto> list = service.getAllAgencies().getAgentlikler();
        model.addAttribute("agencies",list);
        return "forAdmin/agency/all-agencies";
    }
    @GetMapping("/save")
    public String saveAgencyPage(Model model){
        AgencyDto dto=new AgencyDto();
        model.addAttribute("newAgency",dto);
        return "forAdmin/agency/save-agency";
    }

    @PostMapping("/save")
    public String saveAgency(@RequestParam("name") String name,
                             @RequestParam("contact") String contact,
                             @RequestParam("desc") String desc,
                             @RequestParam("file") MultipartFile file){
        service.saveAgentToDB(name,contact,desc,file);
        ResponseEntity.status(HttpStatus.CREATED).build();
        return "redirect:/agency";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("agencyId")Integer agencyId){
    service.delete(agencyId);
    return "redirect:/agency";
    }
}
