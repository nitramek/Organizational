package cz.nitramek.organizational.view.utils;

import cz.nitramek.organizational.domain.classes.ItemType;
import cz.nitramek.organizational.domain.service.ItemTypeService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "itemTypeConverter")
@ApplicationScoped
public class ItemTypeConverter implements Converter {
    @EJB
    private ItemTypeService its;


    @Override
    public Object getAsObject(
            FacesContext facesContext, UIComponent uiComponent, String s) {
        return its.get(Long.valueOf(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((ItemType) o).getId());
    }
}
