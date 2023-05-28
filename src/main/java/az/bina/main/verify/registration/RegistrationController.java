package az.bina.main.verify.registration;

import az.bina.main.verify.event.RegistrationCompleteEvent;
import az.bina.main.verify.registration.token.VerificationToken;
import az.bina.main.verify.registration.token.VerificationTokenRepository;
import az.bina.main.verify.user.User;
import az.bina.main.verify.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public String registerUser(@ModelAttribute("newUser") RegistrationRequest newUser,
                               final HttpServletRequest request,Model model){
        User user=userService.registerUser(newUser);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
        model.addAttribute("success",true);
        return "registration";
    }
    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("newUser",new RegistrationRequest());
        return "registration";
    }
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return "login";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "redirect:/verified";
        }
        return "redirect:/myLogin";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
