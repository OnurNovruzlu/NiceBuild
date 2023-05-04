package az.bina.main.service;

import az.bina.main.rest.request.AgencyDto;
import az.bina.main.rest.response.AgentlikResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AgencyService {
    void saveAgentToDB(String name,String contact,String desc,MultipartFile file);
    AgentlikResponse getAllAgencies();
    void delete(int id);
    AgencyDto getAgent(int id);
}
