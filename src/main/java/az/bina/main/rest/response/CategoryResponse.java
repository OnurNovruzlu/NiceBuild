package az.bina.main.rest.response;


import az.bina.main.rest.request.CategoryDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    List<CategoryDto> categories;
}
