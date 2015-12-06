package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.Role;
import cz.nitramek.organizational.domain.service.RoleService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "userView")
@RequestScoped
public class UserViewBean implements Serializable {
    @EJB
    private RoleService roleService;

    @PostConstruct
    public void init() {
        Role type = new Role();
        type.setName("martin");
        roleService.add(type);
    }

    public List<Role> getRoles() {
        return this.roleService.get();
    }


}
