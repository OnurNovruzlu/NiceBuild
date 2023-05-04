package az.bina.main.rest.response;

import az.bina.main.rest.request.MailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailsResponse {
    List<MailsDto> mails;
}
