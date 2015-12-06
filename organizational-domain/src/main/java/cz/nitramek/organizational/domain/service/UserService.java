package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.data.util.MapperFactory;
import cz.nitramek.organizational.domain.classes.User;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class UserService implements Service<User> {

    private UserMapper userMapper;

    @PostConstruct
    public void init() {
        try {
            userMapper = MapperFactory.createMapper(UserMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User add(User user) {
        return this.userMapper.insert(user);
    }

    @Override
    public User create() {
        return new User();
    }

    @Override
    public User get(long id) {
        return this.userMapper.select(id);
    }

    @Override
    public User update(User user) {
        return this.userMapper.update(user);
    }

    /**
     * @param login    login
     * @param password hashedPassword
     * @return returns null if user is not authentificated
     */
    public User auth(String login, String password) {
        User u = this.userMapper.select(login);
        if (u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}
