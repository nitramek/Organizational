package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.ItemType;
import cz.nitramek.organizational.domain.service.ItemTypeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "itemTypeView")
@RequestScoped
public class ItemTypeViewBean {

    @EJB
    private ItemTypeService service;

    @Inject
    private SessionBackingBean sessionBackingBean;

    private List<ItemType> itemTypes;


    @PostConstruct
    public void init() {

    }

    public List<ItemType> getItemTypes() {
        if (this.itemTypes == null) {
            this.itemTypes = this.service.get();
            if (this.itemTypes == null) {
                this.itemTypes = new ArrayList<>();
            }
        }
        return this.itemTypes;
    }


}
