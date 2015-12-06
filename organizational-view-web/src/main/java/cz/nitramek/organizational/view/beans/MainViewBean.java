package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.Role;
import cz.nitramek.organizational.domain.classes.User;
import cz.nitramek.organizational.domain.service.RoleService;
import cz.nitramek.organizational.domain.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.StringTokenizer;

@Named(value = "mainViewBean")
@ApplicationScoped
public class MainViewBean implements Serializable {

    public static final String PROPERTIES_PATH = "/META-INF/application.properties";
    public static final String ROLES_DISPLAY_KEY = "roles-display";
    public static final String ROLES_NAME_KEY = "roles-name";
    @EJB
    private RoleService roleService;

    @EJB
    private UserService userService;

    private Role guest;

    @PostConstruct
    public void init() {
        this.guest = this.roleService.getByName("guest");

        if (this.guest == null) {
            this.guest = this.roleService.create();
            this.guest.setName("guest");
            this.guest.setDisplayName("Host");
            this.guest = this.roleService.add(this.guest);
        }

        try {
            Properties init = new Properties();
            init.load(MainViewBean.class.getResourceAsStream(PROPERTIES_PATH));
            StringTokenizer roleDisplayTokenizer = new StringTokenizer(init.getProperty(ROLES_DISPLAY_KEY), "/");
            StringTokenizer roleNameTokenizer = new StringTokenizer(init.getProperty(ROLES_NAME_KEY), "/");
            while (roleDisplayTokenizer.hasMoreTokens()) {
                String displayName = roleDisplayTokenizer.nextToken();
                String name = roleNameTokenizer.nextToken();
                if (this.roleService.getByName(name) == null) {
                    Role r = this.roleService.create();
                    r.setName(name);
                    r.setDisplayName(displayName);
                    this.roleService.add(r);
                }
            }

            if (this.userService.getByLogin(init.getProperty("administrator.password")) == null) {
                User admin = this.userService.create();
                admin.getRolesToAdd().add("administrator");
                admin.setNickname(init.getProperty("administrattor.login"));
                admin.setPassword(init.getProperty("administrator.password"));
                this.userService.add(admin);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(this.userService.getByLogin(""))
    }

    public Role getGuest() {
        return guest;
    }
}
