package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.RoleMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.Role;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class RoleService extends AbstractService<Role, RoleMapper> {

    @PostConstruct
    public void init() {
        try {
            super.init(Role.class, RoleMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

}
