package cz.nitramek.organizational.view.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "loginView")
@RequestScoped
public class LoginViewBean {

    @Inject
    private SessionBackingBean sessionBackingBean;

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String login() {
        this.sessionBackingBean.login(this.login, this.password);
        return NavigationRules.SELF;
    }
}
