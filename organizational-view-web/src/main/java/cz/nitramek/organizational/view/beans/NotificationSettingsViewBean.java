package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.NotificationSetting;
import cz.nitramek.organizational.domain.service.NotificationSettingService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "notificationSettingsView")
@ViewScoped
public class NotificationSettingsViewBean implements Serializable {

    @EJB
    private NotificationSettingService nss;


    @Inject
    private SessionBackingBean ssb;
    private List<NotificationSetting> notificationSettings;

    @PostConstruct
    public void init() {
        this.notificationSettings = this.nss.getByUser(this.ssb.getLoggedUser().getId());
    }

    public List<NotificationSetting> getNotificationSettings() {
        return notificationSettings;
    }

    public void setNotificationSettings(
            List<NotificationSetting> notificationSettings) {
        this.notificationSettings = notificationSettings;
    }
}
