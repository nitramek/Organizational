package cz.nitramek.organizational.view.beans;


import cz.nitramek.organizational.domain.classes.User;
import cz.nitramek.organizational.domain.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "sessionBacking")
@SessionScoped
public class SessionBackingBean implements Serializable {

    @EJB
    private UserService userService;

    private User loggedUser;

    public boolean isLogged() {
        return this.loggedUser != null;
    }

    public String login(String name, String password) {
        this.loggedUser = this.userService.auth(name, password);
        return NavigationRules.SELF;
    }

    public String logout() {
        this.loggedUser = null;
        return NavigationRules.SELF;
    }

    public boolean isAdministrator() {
        return this.isLogged() && this.loggedUser.isAdministrator();
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
