package cz.nitramek.organizational.data.mapper;

import cz.nitramek.organizational.domain.classes.NotificationSetting;

import java.util.List;


/**
 * Handles composite notifications as well.
 */
public interface NotificationSettingMapper extends Mapper<NotificationSetting> {
    List<NotificationSetting> selectByUser(long userId);

}
