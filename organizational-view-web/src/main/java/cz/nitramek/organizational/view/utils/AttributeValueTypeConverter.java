package cz.nitramek.organizational.view.utils;

import cz.nitramek.organizational.domain.classes.AttributeValueType;
import cz.nitramek.organizational.domain.service.AttributeValueTypeService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "avtConverter")
@ApplicationScoped
public class AttributeValueTypeConverter implements Converter {

    @EJB
    private AttributeValueTypeService avtService;

    @Override
    public Object getAsObject(
            FacesContext facesContext, UIComponent uiComponent, String s) {
        return this.avtService.get(Long.parseLong(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((AttributeValueType) o).getId());
    }
}
