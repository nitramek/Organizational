package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.CategoryMapper;
import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.data.util.MapperFactory;
import cz.nitramek.organizational.domain.classes.Category;
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

    private CategoryMapper categoryMapper;


    @PostConstruct
    public void init() {
        try {
            this.userMapper = MapperFactory.createMapper(UserMapper.class);
            this.categoryMapper = MapperFactory.createMapper(CategoryMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    public List<User> get() {
        return this.userMapper.select();
    }

    public User add(User user) {
        Category category = new Category();
        category.setName("DŽŽŽÍ, SON");

        categoryMapper.insert(category);
        return this.userMapper.insert(user);
    }
}
