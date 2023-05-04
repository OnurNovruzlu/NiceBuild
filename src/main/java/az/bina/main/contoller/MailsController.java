package az.bina.main.contoller;

import az.bina.main.rest.request.MailsDto;
import az.bina.main.service.MailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailsController {
    private final MailsService service;

    @GetMapping
    public String getAllMails(Model model){
        List<MailsDto> mails=service.getAllMails().getMails();
        model.addAttribute("mails",mails);
        return "forAdmin/mail/mails";
    }
    @GetMapping("/delete")
    public String deleteMail(@RequestParam("mailId")Integer mailId){
        service.deleteMail(mailId);
        return "redirect:/mail";
    }
}