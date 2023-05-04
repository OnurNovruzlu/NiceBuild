package az.bina.main.service;

import az.bina.main.model.Agentlik;
import az.bina.main.repository.AgentlikRepository;
import az.bina.main.rest.request.AgencyDto;
import az.bina.main.rest.response.AgentlikResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgentlikRepository repository;

    @Override
    public void saveAgentToDB(String agent_name,String contact,String description,MultipartFile image) {
    AgencyDto agentDto=new AgencyDto();
    String fileName= StringUtils.cleanPath(image.getOriginalFilename());
    if(fileName.contains("..")){
        System.out.println("Not a valid file");
    }
        try {
            agentDto.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        agentDto.setAgent_name(agent_name);
        agentDto.setDescription(description);
        agentDto.setContact(contact);
        Agentlik agent=new Agentlik();
        agent.setAgent_id(agentDto.getAgent_id());
        agent.setDescription(agentDto.getDescription());
        agent.setContact(agentDto.getContact());
        agent.setAgent_name(agentDto.getAgent_name());
        agent.setImage(agentDto.getImage());
        repository.save(agent);
    }

    @Override
    public AgentlikResponse getAllAgencies() {
        List<Agentlik> agentlikler=repository.findAll();
        List<AgencyDto> agentlikDtolar=new ArrayList<>();
        for (Agentlik a:agentlikler) {
            AgencyDto agentlikDto=convertToDto(a);
            agentlikDtolar.add(agentlikDto);
        }
        return AgentlikResponse.builder().agentlikler(agentlikDtolar).build();
    }

    @Override
    public void delete(int id) {
    repository.deleteById(id);
    }

    @Override
    public AgencyDto getAgent(int id) {
        Agentlik a=repository.getReferenceById(id);
        return convertToDto(a);
    }

    private AgencyDto convertToDto(Agentlik agentlik){
        return AgencyDto.builder().agent_id(agentlik.getAgent_id()).agent_name(agentlik.getAgent_name())
                .description(agentlik.getDescription()).contact(agentlik.getContact()).
                image(agentlik.getImage()).build();
    }
}
