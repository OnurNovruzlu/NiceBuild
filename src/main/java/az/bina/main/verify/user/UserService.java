package az.bina.main.verify.user;

import az.bina.main.verify.exception.UserAlreadyExistsException;
import az.bina.main.verify.registration.RegistrationRequest;
import az.bina.main.verify.registration.token.VerificationToken;
import az.bina.main.verify.registration.token.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user=this.findByEmail(request.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyExistsException(
                    "User with email "+request.getEmail()+"already exists");
        }
        var newUser=new User();
        newUser.setFirst_name(request.getFirst_name());
        newUser.setLast_name(request.getLast_name());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken=new VerificationToken(token,theUser);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken) {
        VerificationToken token = verificationTokenRepository.findByToken(theToken);
        if(token==null){
            return "Invalid verification token";
        }
        User user=token.getUser();
        Calendar calendar= Calendar.getInstance();
        if((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            verificationTokenRepository.delete(token);
            return "Token already expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
}
