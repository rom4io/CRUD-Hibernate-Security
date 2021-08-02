package crud.dao;

import crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Role getRole(Long id){
        return entityManager.find(Role.class, id);
    }

}
