package az.bina.main.repository;

import az.bina.main.model.Mails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mails,Integer> {
}
