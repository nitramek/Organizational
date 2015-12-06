package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.service.ItemService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "itemView")
@RequestScoped
public class ItemViewBean implements Serializable {

    @EJB
    private ItemService itemService;

    @Inject
    private MainViewBean mainViewBean;

    public List<Item> getItems() {
        return this.itemService.getItemsByOwner(this.mainViewBean.getGuest().getId());
    }

}
