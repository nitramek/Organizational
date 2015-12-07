package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.AttributeType;
import cz.nitramek.organizational.domain.classes.AttributeValueType;
import cz.nitramek.organizational.domain.classes.ItemType;
import cz.nitramek.organizational.domain.service.AttributeValueTypeService;
import cz.nitramek.organizational.domain.service.ItemTypeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named(value = "itemTypeDetailView")
@ViewScoped
public class ItemTypeDetailViewBean implements Serializable {

    @EJB
    private ItemTypeService itemTypeService;

    @EJB
    private AttributeValueTypeService attributeValueTypeService;


    /**
     * Chooser in attribute dialog
     */
    private List<AttributeValueType> avts;
    private AttributeValueType selectedAvt;

    @PostConstruct
    public void init() {
        this.avts = this.attributeValueTypeService.getAll();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                                                                      .getExternalContext()
                                                                      .getRequest();
        //načtení z parametru
        Optional.ofNullable(request.getParameter("itemType"))
                .map(p -> this.itemTypeService.get(Long.parseLong(p))
                    ).ifPresent(this::setItemType);
        if (this.itemType == null) this.itemType = this.itemTypeService.create();
        if (this.avts == null) this.avts = new ArrayList<>(0);
    }

    private ItemType itemType;
    /**
     * Represents attribute editing dialog
     */
    private AttributeType attribute;


    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void addAttribute() {
        this.attribute = this.itemTypeService.createAttribute();
    }

    public void saveAttribute() {
        this.itemType.getAttributeTypes().add(this.attribute);
    }

    public void newItemType() {
        this.itemType = this.itemTypeService.create();
    }


    public String save() {
        this.itemTypeService.add(itemType);
        return NavigationRules.ITEM_TYPES;
    }

    public AttributeType getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeType attribute) {
        this.attribute = attribute;
    }

    public List<AttributeValueType> getAvts() {
        return avts;
    }
}
