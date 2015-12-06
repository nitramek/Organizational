package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.User;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class UserService extends AbstractService<User, UserMapper> {


    @PostConstruct
    public void init() {
        try {
            super.init(UserMapper.class, User.class);
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
}
