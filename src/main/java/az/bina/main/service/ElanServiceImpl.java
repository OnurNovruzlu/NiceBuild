package az.bina.main.service;

import az.bina.main.model.Agentlik;
import az.bina.main.model.Category;
import az.bina.main.model.Elan;
import az.bina.main.repository.ElanRepository;
import az.bina.main.rest.request.ElanDto;
import az.bina.main.rest.response.ElanResponse;
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
public class ElanServiceImpl implements ElanService{
    private final ElanRepository elanRepository;
    @Override
    public ElanResponse getAllElan() {
        List<Elan> eList  = elanRepository.findAll();
        List<ElanDto> edtoList = new ArrayList<>();
        for(Elan elan :eList){
            ElanDto empDto = convertToDto(elan);
            edtoList.add(empDto);
        }
        return ElanResponse.builder().elans(edtoList).build();
    }

    @Override
    public ElanResponse findByParams(int min, int max,String catName,String location) {
    List<Elan> list = elanRepository.findByParams(min,max,catName,location);
    List<ElanDto> listDto = new ArrayList<>();
    for(Elan e: list){
        ElanDto dto=convertToDto(e);
        listDto.add(dto);
    }
    return ElanResponse.builder().elans(listDto).build();
    }

    @Override
    public ElanDto getElan(int id) {
        return convertToDto(elanRepository.getReferenceById(id));
    }

    @Override
    public void insert(ElanDto dto) {
    Elan e=new Elan();
    e.setId(dto.getId());
    e.setName(dto.getName());
    e.setPrice(dto.getPrice());
    e.setLocation(dto.getLocation());
    e.setContact(dto.getContact());
    e.setAgent_id(dto.getAgent_id());
    e.setCategory_id(dto.getCategory_id());
    e.setImage(dto.getImage());
    elanRepository.save(e);
    }

    @Override
    public void saveToDB(String name, int price, String location, String contact, Agentlik agent_id,
                         Category category_id, MultipartFile image) {
       ElanDto dto=new ElanDto();
        String fileName= StringUtils.cleanPath(image.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("Not a valid file");
        }
        try {
            dto.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dto.setName(name);
        dto.setPrice(price);
        dto.setLocation(location);
        dto.setContact(contact);
        dto.setAgent_id(agent_id);
        dto.setCategory_id(category_id);
        Elan e=new Elan();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setPrice(dto.getPrice());
        e.setLocation(dto.getLocation());
        e.setContact(dto.getContact());
        e.setAgent_id(dto.getAgent_id());
        e.setCategory_id(dto.getCategory_id());
        e.setImage(dto.getImage());
        elanRepository.save(e);
    }

    @Override
    public void delete(int id) {
    elanRepository.deleteById(id);
    }

    private  ElanDto convertToDto(Elan elan){
     return   ElanDto.builder()
                .id(elan.getId())
                .name(elan.getName())
                .price(elan.getPrice())
                .contact(elan.getContact())
                .image(elan.getImage())
                .location(elan.getLocation())
                .agent_id(elan.getAgent_id()==null?  null:elan.getAgent_id())
                .category_id(elan.getCategory_id()==null?  null:elan.getCategory_id())
                .build();
    }
}
