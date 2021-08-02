package crud.Service;

import crud.dao.RoleDAO;
import crud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    private final RoleDAO roleDAO;

    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Set<Role> getRoleUser(){
        return Collections.singleton(roleDAO.getRole(2L));
    }

    public Set<Role> getRoleAdmUser(){
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getRole(1L));
        roles.add(roleDAO.getRole(2L));
        return roles;
    }
}
