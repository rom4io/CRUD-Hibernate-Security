package crud.Service;

import crud.dao.UserDAO;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List<User> allUsers(){
        return userDAO.allUsers();
    }

    @Transactional
    public User userByID(Long id){
        return userDAO.userById(id);
    }

    @Transactional
    public void save (User user){ userDAO.save(user);}

    @Transactional
    public void update (User updateUser) {userDAO.update(updateUser);}

    @Transactional
    public void delete (Long id) {userDAO.delete(id);}
}
