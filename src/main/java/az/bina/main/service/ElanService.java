package az.bina.main.service;

import az.bina.main.model.Agentlik;
import az.bina.main.model.Category;
import az.bina.main.rest.request.ElanDto;
import az.bina.main.rest.response.ElanResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ElanService {
    ElanResponse getAllElan();
    ElanResponse findByParams(int min,int max,String catName,String location);
    void saveToDB(
            String name, int price, String location,
            String contact, Agentlik agent_id,
            Category category_id, MultipartFile image);
    void delete(int id);

    void insert(ElanDto elanDto);
    ElanDto getElan(int id);
}