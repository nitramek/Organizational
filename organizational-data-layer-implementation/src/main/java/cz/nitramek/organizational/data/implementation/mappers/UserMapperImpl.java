package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.User;

import java.util.List;

@MapperImplementation(mapper = UserMapper.class)
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> select() {
        return null;
    }

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User select(long id) {
        return null;
    }
}
