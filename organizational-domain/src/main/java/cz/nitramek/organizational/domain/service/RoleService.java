package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.RoleMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.Role;

import javax.annotation.PostConstruct;

public class RoleService extends AbstractService<Role,RoleMapper> {

    @PostConstruct
    public void init(){
        try {
            super.init(RoleMapper.class, Role.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

}
