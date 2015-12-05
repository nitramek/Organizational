package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.data.util.MapperFactory;
import cz.nitramek.organizational.domain.classes.User;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
@Local
public class UserService implements Serializable {

    private UserMapper userMapper;


    @PostConstruct
    public void init() {
        try {
            userMapper = MapperFactory.createMapper(UserMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    public List<User> get() {
        return userMapper.select();
    }

    public User add(User user) {
        return userMapper.insert(user);
    }
}
