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

//    @PostMapping
//    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
//        User user=userService.registerUser(registrationRequest);
//        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
//        return "Success!  Please, check your email to complete your registration";
//    }
    @PostMapping
    public String registerUser(@ModelAttribute RegistrationRequest newUser,
                               final HttpServletRequest request){
        User user=userService.registerUser(newUser);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
        return "redirect:/login";
    }
    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new RegistrationRequest());
        return "registration";
    }
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
