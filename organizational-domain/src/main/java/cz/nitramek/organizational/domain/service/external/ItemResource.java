package cz.nitramek.organizational.domain.service.external;


import com.google.gson.Gson;
import cz.nitramek.organizational.domain.classes.Attribute;
import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.classes.ItemType;
import cz.nitramek.organizational.domain.service.ItemService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.List;

@Path("/item")
public class ItemResource extends Application {

    @EJB
    private ItemService itemService;

    private Gson gson;

    public ItemResource() {
        this.gson = new Gson();
    }

    @GET
    @Path("/createAttr/{itemType}")
    @Produces("application/json")
    public List<Attribute> createAttributes(
            @PathParam("itemType") String itemType) {
        return itemService.createAttributes(this.gson.fromJson(itemType, ItemType.class));
    }


    @GET
    @Path("/get/{id}")
    @Produces("application/json")
    public Item get(@PathParam("id") long id) {
        return itemService.get(id);
    }


    @GET
    @Path("/create")
    @Produces("application/json")
    public Item create() {
        return itemService.create();
    }

    @POST
    @Path("/update")
    @Produces("application/json")
    public Item update(String item) {
        return itemService.update(this.gson.fromJson(item, Item.class));
    }

    @GET
    @Path("/getItemsByRole/{roleId}")
    @Produces("application/json")
    public List<Item> getItemsByRole(@PathParam("roleId") long roleId) {
        return itemService.getItemsByRole(roleId);
    }

    @GET
    @Path("/getItemsByUser/{userId}")
    @Produces("application/json")
    public List<Item> getItemsByUser(@PathParam("userId") long userId) {
        return itemService.getItemsByUser(userId);
    }

    @GET
    @Path("/getItemsByOwner/{userId}")
    @Produces("application/json")
    public List<Item> getItemsByOwner(@PathParam("userId") long userId) {
        return itemService.getItemsByOwner(userId);
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Item add(Item type) {
        return itemService.add(type);
    }
}
