package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.service.ItemService;
import cz.nitramek.organizational.domain.service.MessageService;

import javax.annotation.PostConstruct;
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

    @EJB
    private MessageService messageService;

    @Inject
    private MainViewBean mainViewBean;

    @Inject
    private SessionBackingBean ssb;

    private List<Item> items;

    @PostConstruct
    public void init() {
        mainViewBean.getGuest();
    }

    public List<Item> getItems() {
        if (this.items == null) {
            if (this.ssb.isLogged()) {
                this.items = this.itemService.getItemsByUser(this.ssb.getLoggedUser().getId());
                this.items.addAll(this.itemService.getItemsByRole(this.mainViewBean.getGuest().getId()));
            } else {
                this.items = this.itemService.getItemsByRole(this.mainViewBean.getGuest().getId());
            }
        }
        return this.items;
    }


}
