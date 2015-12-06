package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.NotificationSettingMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.CompositeNotificationSetting;
import cz.nitramek.organizational.domain.classes.NotificationSetting;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class NotificationSettingService extends AbstractService<NotificationSetting, NotificationSettingMapper> {

    @PostConstruct
    public void init() {
        try {
            this.init(NotificationSetting.class, NotificationSettingMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    public CompositeNotificationSetting compose(
            NotificationSetting first, NotificationSetting second,
            CompositeNotificationSetting.CompositeOperation compositeOperation) {
        return new CompositeNotificationSetting(
                first,
                compositeOperation
        );
    }

    public NotificationSetting decompose(CompositeNotificationSetting cns) {
        return new NotificationSetting(cns);
    }

    public List<NotificationSetting> getByUser(long userId) {
        return this.mapper.selectByUser(userId);
    }
}
