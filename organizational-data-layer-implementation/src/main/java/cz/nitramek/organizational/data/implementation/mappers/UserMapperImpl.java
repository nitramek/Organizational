package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.converters.Converters;
import cz.nitramek.organizational.data.implementation.dto.UserDTO;
import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MapperImplementation(mapper = UserMapper.class)
public class UserMapperImpl implements UserMapper {


    private EntityManager em;

    public UserMapperImpl() throws NamingException {
        this.em = (EntityManager) new InitialContext().lookup("java:/org-em");
    }

    @Override
    public List<User> select() {
        List<UserDTO> resultList = em.createQuery("SELECT u FROM User u", UserDTO.class).getResultList();

        return resultList.stream().map(Converters::createUser).collect(Collectors.toCollection(ArrayList<User>::new));
    }

    @Override
    public User insert(User user) {
        UserDTO userDTO = Converters.createUser(user);

        return Converters.createUser(this.em.merge(userDTO));
    }

    @Override
    public User update(User user) {
        return Converters.createUser(this.em.merge(Converters.createUser(user)));
    }

    @Override
    public User select(long id) {
        return null;
    }


}
