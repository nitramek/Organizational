package cz.nitramek.organizational.data.mapper;

import cz.nitramek.organizational.domain.classes.NotificationSetting;
import cz.nitramek.organizational.domain.classes.User;

import java.util.List;


/**
 * Handles composite notifications as well.
 */
public interface NotificationSettingMapper extends Mapper<NotificationSetting<?>> {
    List<NotificationSetting> select(User user);

    List<NotificationSetting> selectActive();
}
