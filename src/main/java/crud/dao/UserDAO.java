package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> allUsers(){
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    public User userById(Long id) {
        TypedQuery<User> q = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    public void delete(Long id){
        entityManager.remove(userById(id));
    }

    public void save (User user){
        entityManager.persist(user);
    }

    public void update (User updateUser){
        entityManager.merge(updateUser);
    }


}
