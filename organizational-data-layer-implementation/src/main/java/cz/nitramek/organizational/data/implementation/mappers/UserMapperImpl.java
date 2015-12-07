package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.UserDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        List<UserDTO> resultList = this.em.createNamedQuery("User.selectAll", UserDTO.class)
                                          .getResultList();

        return resultList.stream().map(Converters::convert).collect(Collectors.toCollection(ArrayList<User>::new));
    }

    @Override
    public User select(String login) {
        TypedQuery<UserDTO> query = this.em.createNamedQuery("User.selectByLogin", UserDTO.class)
                                           .setParameter("login", login);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return Converters.convert(query.getSingleResult());
    }


    @Override
    public User insert(User user) {

        UserDTO userDTO = Converters.convert(user);
        this.em.persist(userDTO);
        return Converters.convert(userDTO);
    }

    @Override
    public User update(User user) {
        return this.insert(user);
    }

    @Override
    public User select(long id) {
        return Converters.convert(this.em.createNamedQuery("User.selectById", UserDTO.class)
                                         .setParameter("id", id)
                                         .getSingleResult());
    }


}
