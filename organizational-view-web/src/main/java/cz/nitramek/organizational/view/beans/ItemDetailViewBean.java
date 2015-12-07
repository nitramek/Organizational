package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.classes.ItemType;
import cz.nitramek.organizational.domain.service.ItemService;
import cz.nitramek.organizational.domain.service.ItemTypeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Named(value = "itemDetailView")
@ViewScoped
public class ItemDetailViewBean implements Serializable {
    @Inject
    private SessionBackingBean ssb;

    @EJB
    private ItemService itemService;

    @EJB
    private ItemTypeService itemTypeService;

    private Item item;

    private List<ItemType> itemTypes;


    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                                                                      .getExternalContext()
                                                                      .getRequest();
        Optional.ofNullable(request.getParameter("item"))
                .map(p -> this.itemService.get(Long.parseLong(p))).ifPresent(this::setItem);
        if (this.item == null) {
            this.item = this.itemService.create();
        }
        this.itemTypes = this.itemTypeService.get();
        this.item.setType(this.itemTypes.get(0));
        onItemTypeSelect();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String save() {
        this.item.setDateAdded(new Date());
        this.item.setDateChanged(new Date());
        this.itemService.add(item);

        return NavigationRules.HOME;
    }

    public void onItemTypeSelect() {
        this.item.setAttributes(this.itemService.createAttributes(this.item.getType()));
    }

    public List<ItemType> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(List<ItemType> itemTypes) {
        this.itemTypes = itemTypes;
    }
}

