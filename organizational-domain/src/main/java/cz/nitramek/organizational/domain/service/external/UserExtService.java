package cz.nitramek.organizational.domain.service.external;

import com.google.gson.Gson;
import cz.nitramek.organizational.domain.classes.User;
import cz.nitramek.organizational.domain.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;


@Path("/user")
public class UserExtService extends Application {
    @EJB
    private UserService userService;

    private Gson gson;

    public UserExtService() {
        this.gson = new Gson();
    }


    @POST
    @Path("/add")
    public User add(String user) {
        return userService.add(this.gson.fromJson(user, User.class));
    }

    @POST
    @Path("/auth")
    public String auth(@FormParam("login") String login, @FormParam("password") String password) {
        return this.gson.toJson(userService.auth(login, password));
    }


    @GET
    @Path("/get/{userId}")
    public String get(@PathParam("userId") long id) {
        return this.gson.toJson(userService.get(id));
    }

    @GET
    @Path("/create")
    public String create() {
        return this.gson.toJson(userService.create());
    }

    @GET
    @Path("/get")
    @Produces("application/json")
    public List<User> get() {
        return this.userService.get();
    }
}
