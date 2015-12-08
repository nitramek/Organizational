package cz.nitramek.organizational.view.beans;

import javax.inject.Named;

@Named("navigation")
public class NavigationRules {
    public static final String HOME = "/pages/items";
    public static final String SELF = "";
    public static final String ITEM_TYPES = "/pages/itemTypes";
    public static final String NOTIFICATIONS_SETTING = "/pages/notificationSetting.xhtml";
}
