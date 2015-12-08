package cz.nitramek.organizational.view.utils;

import cz.nitramek.organizational.domain.classes.NotificationSetting;
import cz.nitramek.organizational.domain.service.NotificationSettingService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "notificationConverter")
@ApplicationScoped
public class NotificationConverter implements Converter {

    @EJB
    private NotificationSettingService ns;


    @Override
    public Object getAsObject(
            FacesContext facesContext, UIComponent uiComponent, String s) {
        return this.ns.get(Long.parseLong(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((NotificationSetting) o).getId());
    }
}
