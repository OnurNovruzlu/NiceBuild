package az.bina.main.repository;

import az.bina.main.model.Agentlik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentlikRepository extends JpaRepository<Agentlik,Integer> {
}
