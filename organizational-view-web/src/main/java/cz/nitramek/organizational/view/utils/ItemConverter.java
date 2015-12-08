package cz.nitramek.organizational.view.utils;

import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.service.ItemService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named("itemConverter")
@ApplicationScoped
public class ItemConverter implements Converter {

    @EJB
    private ItemService itemService;

    @Override
    public Object getAsObject(
            FacesContext facesContext, UIComponent uiComponent, String s) {
        return this.itemService.get(Long.parseLong(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((Item) o).getId());
    }
}
