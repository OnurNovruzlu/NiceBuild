package az.bina.main.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDto {
    private int agent_id;
    private String agent_name;
    private String description;
    private String contact;
    private String image;
}
