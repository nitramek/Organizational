package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Local
public class UserService extends AbstractService<User, UserMapper> {


    @EJB
    private RoleService roleService;

    @PostConstruct
    public void init() {
        try {
            super.init(User.class, UserMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param login    login
     * @param password hashedPassword
     * @return returns null if user is not authentificated
     */
    public User auth(String login, String password) {
        User u = this.mapper.select(login);
        if (u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    @Override
    public User add(User user) {
        user.getRoles()
            .addAll(user.getRolesToAdd().stream().map(this.roleService::getByName).collect(Collectors.toList()));
        return super.add(user);
    }

    public List<User> get() {
        return this.mapper.select();
    }

    public User getByLogin(String login) {
        return this.mapper.select(login);
    }


}
