package az.bina.main.verify.user;

import az.bina.main.verify.registration.RegistrationRequest;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUser();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken(User user, String token);
    String validateToken(String token);
}