package az.bina.main.verify.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/verified")
    public String verified(Model model){
        model.addAttribute("success",true);
        return "login";
    }

    @GetMapping("/myLogout")
    public String logout(Model model){
        model.addAttribute("logout",true);
        return "login";
    }
    @GetMapping("/myLogin")
    public String failLogin(Model model){
        model.addAttribute("error",true);
        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "forAdmin/admin";
    }
}
