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
    private int index;

    @PostConstruct
    public void init() {
        this.index = -1;
        this.avts = this.attributeValueTypeService.getAll();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                                                                      .getExternalContext()
                                                                      .getRequest();
        //načtení z parametru
        Optional.ofNullable(request.getParameter("itemType"))
                .map(p -> this.itemTypeService.get(Long.parseLong(p))
                    ).ifPresent(this::setItemType);
        if (this.itemType == null) this.itemType = this.itemTypeService.create();
        if (this.attribute == null) this.attribute = new AttributeType();
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
        this.index = -1;
    }

    public void saveAttribute() {
        if (this.index < 0) {
            this.itemType.getAttributeTypes().add(this.attribute);
        } else {
            this.itemType.getAttributeTypes().set(this.index, this.attribute);
        }

    }

    public void newItemType() {
        this.itemType = this.itemTypeService.create();
    }


    public String save() {
        this.itemTypeService.update(itemType);
        return NavigationRules.ITEM_TYPES;
    }

    public AttributeType getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeType attribute) {
        this.attribute = attribute;
        int index = 0;
        for (AttributeType at : this.itemType.getAttributeTypes()) {
            if (at.equals(this.attribute)) {
                this.index = index;
                break;
            }
            index++;
        }
    }

    public List<AttributeValueType> getAvts() {
        return avts;
    }

    public AttributeValueTypeService getAttributeValueTypeService() {
        return attributeValueTypeService;
    }

    public void setAttributeValueTypeService(
            AttributeValueTypeService attributeValueTypeService) {
        this.attributeValueTypeService = attributeValueTypeService;
    }
}
