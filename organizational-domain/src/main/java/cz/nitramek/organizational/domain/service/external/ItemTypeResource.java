package cz.nitramek.organizational.domain.service.external;

import cz.nitramek.organizational.domain.classes.AttributeType;
import cz.nitramek.organizational.domain.classes.ItemType;
import cz.nitramek.organizational.domain.service.ItemTypeService;
import cz.nitramek.organizational.domain.service.util.GsonUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.List;

@Path("/itemType")
public class ItemTypeResource extends Application {
    @EJB
    private ItemTypeService service;


    @GET
    @Path("/createAttribute")
    @Produces("application/json")
    public AttributeType createAttribute() {
        return service.createAttribute();
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json")
    public ItemType get(@PathParam("id") long id) {
        return service.get(id);
    }

    @GET
    @Path("/create")
    @Produces("application/json")
    public ItemType create() {
        return service.create();
    }

    @GET
    @Path("/get")
    @Produces("application/json")
    public List<ItemType> get() {
        return service.get();
    }

    @POST
    @Path("/update")
    @Produces("application/json")
    public ItemType update(
            String itemType) {
        return service.update(GsonUtil.gson.fromJson(itemType, ItemType.class));
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public ItemType add(String itemType) {
        return service.add(GsonUtil.gson.fromJson(itemType, ItemType.class));
    }
}
