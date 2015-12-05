package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.RoleDTO;
import cz.nitramek.organizational.data.implementation.dto.UserDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
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
        List<UserDTO> resultList = this.em.createNamedQuery("User.selectAll", UserDTO.class)
                                          .getResultList();

        return resultList.stream().map(Converters::createUser).collect(Collectors.toCollection(ArrayList<User>::new));
    }


    @Override
    public User insert(User user) {

        UserDTO userDTO = Converters.createUser(user);

        List<RoleDTO> rolesToAdd = user.getRolesToAdd().stream()
                                       .map(
                                               rName -> this.em.createNamedQuery("Role.selectByName", RoleDTO.class)
                                                               .setParameter("roleName", rName)
                                                               .getSingleResult()
                                           )
                                       .collect(Collectors.toCollection(ArrayList<RoleDTO>::new));
        userDTO.getRoles().addAll(rolesToAdd);
        this.em.persist(userDTO);
        userDTO.getRoles().stream().forEach(
                roleDTO -> {
                    this.em.persist(roleDTO);
                    roleDTO.getPermission().forEach(this.em::persist);
                }
                                           );
        return Converters.createUser(userDTO);
    }

    @Override
    public User update(User user) {
        return this.insert(user);
    }

    @Override
    public User select(long id) {
        return Converters.createUser(this.em.createNamedQuery("User.selectById", UserDTO.class)
                                            .setParameter("id", id)
                                            .getSingleResult());
    }


}
