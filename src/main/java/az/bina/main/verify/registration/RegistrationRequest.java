package az.bina.main.verify.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest{
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String role;
}