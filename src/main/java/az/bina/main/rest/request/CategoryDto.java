package az.bina.main.rest.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int category_id;
    private String category_name;
}
