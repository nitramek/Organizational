package cz.nitramek.organizational.data;


import cz.nitramek.organizational.domain.classes.Role;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    Role select(String roleName);

    List<Role> select();
}
