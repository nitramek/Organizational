package cz.nitramek.organizational.view.beans;

import cz.nitramek.organizational.domain.classes.Attribute;
import cz.nitramek.organizational.domain.classes.CompositeNotificationSetting;
import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.classes.NotificationSetting;
import cz.nitramek.organizational.domain.service.ItemService;
import cz.nitramek.organizational.domain.service.NotificationSettingService;
import cz.nitramek.organizational.view.utils.Utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "notificationView")
@ViewScoped
public class NotificationSettingDetailViewBean implements Serializable {

    @EJB
    private NotificationSettingService nss;

    @EJB
    private ItemService itemService;

    @Inject
    private SessionBackingBean ssb;

    private NotificationSetting ns;

    private CompositeNotificationSetting.CompositeOperation compositeOperation;

    /**
     * For select menu.
     */
    private List<Item> items;
    /**
     * We are selectings one of this items attributes
     */
    private Item selectedItem;

    /**
     * Yep these attributes.
     */
    private List<Attribute> attributes;

    private List<NotificationSetting> otherNotificationSettings;
    private NotificationSetting second;
    private long selectedAttributeId;


    @PostConstruct
    public void init() {
        Utils.getParameter("nsId").ifPresent(nsId -> this.ns = this.nss.get(Long.parseLong(nsId)));
        this.ns = this.nss.create();
        final long userId = this.ssb.getLoggedUser().getId();

        this.items = this.itemService.getItemsByOwner(userId);
        this.setSelectedItem(this.items.get(0));
        this.otherNotificationSettings = this.nss.getByUser(userId);
        this.otherNotificationSettings.remove(this.ns);
        if (this.otherNotificationSettings.size() > 0)
            this.second = this.otherNotificationSettings.get(0);
        this.compositeOperation = CompositeNotificationSetting.CompositeOperation.AND;


    }

    public void compose() {
        this.ns = this.nss.compose(this.ns, this.second, this.compositeOperation);
    }

    public void decompose() {
        this.ns = this.nss.decompose((CompositeNotificationSetting) this.ns);
    }

    public ItemService getItemService() {
        return itemService;
    }

    public NotificationSetting getSecond() {
        return second;
    }

    public void setSecond(NotificationSetting second) {
        this.second = second;
    }


    public NotificationSetting getNs() {
        return ns;
    }

    public void setNs(NotificationSetting ns) {
        this.ns = ns;
    }

    public CompositeNotificationSetting getCompositeSetting() {
        return (CompositeNotificationSetting) this.ns;
    }

    public boolean isComposite() {
        return this.ns instanceof CompositeNotificationSetting;
    }

    public String save() {
        this.ns.setUser(this.ssb.getLoggedUser());
        Attribute selectedAttribute = null;
        for (Attribute attribute : this.attributes) {
            if (attribute.getId() == this.selectedAttributeId) {
                selectedAttribute = attribute;
                break;
            }
        }
        if (this.ns instanceof CompositeNotificationSetting) {
            CompositeNotificationSetting cns = (CompositeNotificationSetting) ns;
            cns.setCompositeOperation(this.compositeOperation);
        }
        this.ns.setWatchedAttribute(selectedAttribute);
        this.nss.update(this.ns);
        return NavigationRules.NOTIFICATIONS_SETTING;
    }

    public NotificationSetting.Operation[] getOperations() {
        return NotificationSetting.Operation.values();
    }

    public CompositeNotificationSetting.CompositeOperation[] getCompositeOperations() {
        return CompositeNotificationSetting.CompositeOperation.values();
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
        this.attributes = this.selectedItem.getAttributes();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public CompositeNotificationSetting.CompositeOperation getCompositeOperation() {
        return compositeOperation;
    }

    public void setCompositeOperation(
            CompositeNotificationSetting.CompositeOperation compositeOperation) {
        this.compositeOperation = compositeOperation;
    }

    public List<NotificationSetting> getOtherNotificationSettings() {
        return otherNotificationSettings;
    }

    public void setSelectedAttributeId(long selectedAttributeId) {
        this.selectedAttributeId = selectedAttributeId;
    }

    public long getSelectedAttributeId() {
        return selectedAttributeId;
    }
}
