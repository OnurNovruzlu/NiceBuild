package az.bina.main.service;

import az.bina.main.rest.response.MailsResponse;

public interface MailsService {
    MailsResponse getAllMails();
    void deleteMail(int id);
    void insertMail( String name,String email,String message);
}
