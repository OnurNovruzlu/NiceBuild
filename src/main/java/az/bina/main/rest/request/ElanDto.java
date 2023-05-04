package az.bina.main.rest.request;

import az.bina.main.model.Agentlik;
import az.bina.main.model.Category;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.awt.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElanDto {
    private int id;
    private String name;
    private int price;
    private String location;
    private String contact;
    private Agentlik agent_id;
    private Category category_id;
    private String image;

}
