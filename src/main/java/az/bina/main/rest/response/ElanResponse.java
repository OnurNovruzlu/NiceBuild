package az.bina.main.rest.response;


import az.bina.main.rest.request.ElanDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElanResponse {
    List<ElanDto> elans;
}
