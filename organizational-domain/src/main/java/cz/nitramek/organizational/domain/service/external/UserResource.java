package cz.nitramek.organizational.domain.service.external;

import com.google.gson.Gson;
import cz.nitramek.organizational.domain.classes.User;
import cz.nitramek.organizational.domain.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.List;


@Path("/user")
public class UserResource extends Application {
    @EJB
    private UserService userService;

    private Gson gson;

    public UserResource() {
        this.gson = new Gson();
    }


    @POST
    @Path("/add")
    @Produces("application/json")
    public User add(String user) {
        return userService.add(this.gson.fromJson(user, User.class));
    }

    @POST
    @Path("/auth")
    @Produces("application/json")
    public User auth(@FormParam("login") String login, @FormParam("password") String password) {
        User auth = userService.auth(login, password);
        return auth;
    }


    @GET
    @Path("/get/{userId}")
    @Produces("application/json")
    public String get(@PathParam("userId") long id) {
        return this.gson.toJson(userService.get(id));
    }

    @GET
    @Path("/create")
    @Produces("application/json")
    public User create() {
        return userService.create();
    }

    @GET
    @Path("/get")
    @Produces("application/json")
    public List<User> get() {
        return this.userService.get();
    }
}
