package az.bina.main.repository;


import az.bina.main.model.Elan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElanRepository extends JpaRepository<Elan,Integer> {
    @Query(value = "select agentlik.agent_name from agentlik inner join elan on " +
            "agentlik.agent_id=elan.agent_id where id= :id",nativeQuery = true)
    String agentNameForElan(@Param("id")int id);

    @Query(value = "select category.category_name from category inner join elan on " +
            "category.category_id=elan.category_id where id= :id",nativeQuery = true)
    String categoryNameForElan(@Param("id")int id);

    @Query(value = "select elan.* from elan inner join category on elan.category_id=category.category_id where category_name like %:catName% and price between :min and :max and location LIKE %:location% ",nativeQuery = true)
    List<Elan> findByParams(@Param("min")int min,
                            @Param("max")int max,
                            @Param("catName")String catName,
                            @Param("location")String location);

}
