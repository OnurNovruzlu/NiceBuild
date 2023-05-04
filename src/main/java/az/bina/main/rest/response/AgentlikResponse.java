package az.bina.main.rest.response;

import az.bina.main.rest.request.AgencyDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentlikResponse {
    List<AgencyDto> agentlikler;
}
