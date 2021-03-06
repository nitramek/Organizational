package cz.nitramek.organizational.data.implementation.mappers;


import cz.nitramek.organizational.data.implementation.dto.RoleDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.RoleMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.Role;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MapperImplementation(mapper = RoleMapper.class)
public class RoleMapperImpl implements RoleMapper {


    private EntityManager em;

    public RoleMapperImpl() throws NamingException {
        this.em = (EntityManager) new InitialContext().lookup("java:/org-em");
    }

    @Override
    public Role select(String roleName) {

        TypedQuery<RoleDTO> searchQuery = this.em.createNamedQuery("Role.selectByName", RoleDTO.class)
                                                 .setParameter("roleName", roleName);
        if (searchQuery.getResultList().size() == 0) {
            return null;
        }
        RoleDTO foundROle = searchQuery.getSingleResult();

        return Converters.convert(foundROle);
    }

    @Override
    public List<Role> select() {
        return this.em.createNamedQuery("Role.selectAll", RoleDTO.class).
                getResultList().stream().
                              map(Converters::convert).
                              collect(Collectors.toCollection(ArrayList<Role>::new));
    }

    @Override
    public Role insert(Role role) {
        RoleDTO roleDTO = Converters.convert(role);
        this.em.persist(roleDTO);
        return Converters.convert(roleDTO);
    }

    @Override
    public Role update(Role role) {
        RoleDTO roleDTO = Converters.convert(role);
        roleDTO = this.em.merge(roleDTO);
        return Converters.convert(roleDTO);
    }

    @Override
    public Role select(long id) {
        return Converters.convert(this.em.createNamedQuery("Role.selectOne", RoleDTO.class)
                                         .setParameter("id", id)
                                         .getSingleResult());
    }
}
