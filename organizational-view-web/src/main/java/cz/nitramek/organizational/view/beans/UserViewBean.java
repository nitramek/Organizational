package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.Role;
import cz.nitramek.organizational.domain.classes.User;
import cz.nitramek.organizational.domain.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "userView")
@SessionScoped
public class UserViewBean implements Serializable {
    @EJB
    private UserService userService;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setNickname("martin");
        ArrayList<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("Lojza");
        role.setDisplayName("Lojza mojmírovič");
        roles.add(role);
        user.setRoles(roles);
        userService.add(user);
    }

    public List<User> getUsers() {
        return userService.get();
    }


}
