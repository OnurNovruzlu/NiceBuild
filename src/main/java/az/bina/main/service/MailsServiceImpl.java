package az.bina.main.service;

import az.bina.main.model.Mails;
import az.bina.main.repository.MailRepository;
import az.bina.main.rest.request.MailsDto;
import az.bina.main.rest.response.MailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailsServiceImpl implements MailsService{
    private final MailRepository repository;

    @Override
    public MailsResponse getAllMails() {
        List<Mails>  mails=repository.findAll();
        List<MailsDto> dtos=new ArrayList<>();
        for(Mails m:mails){
            MailsDto dto=convertToDto(m);
            dtos.add(dto);
        }
        return MailsResponse.builder().mails(dtos).build();
    }

    @Override
    public void deleteMail(int id) {
        repository.deleteById(id);
    }

    @Override
    public void insertMail(String name, String email, String message) {
    Mails m=new Mails();
    m.setEmail(email);
    m.setMessage(message);
    m.setName(name);
    repository.save(m);
    }

    private MailsDto convertToDto(Mails mails){
        return MailsDto.builder().id(mails.getId()).name(mails.getName())
                .email(mails.getEmail()).message(mails.getMessage()).build();
    }

}