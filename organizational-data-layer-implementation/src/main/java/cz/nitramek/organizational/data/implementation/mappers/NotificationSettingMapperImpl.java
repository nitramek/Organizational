package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.NotificationSettingDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.NotificationSettingMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.NotificationSetting;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@MapperImplementation(mapper = NotificationSettingMapper.class)
public class NotificationSettingMapperImpl implements NotificationSettingMapper {


    private final EntityManager em;

    public NotificationSettingMapperImpl() throws NamingException {
        this.em = (EntityManager) new InitialContext().lookup("java:/org-em");
    }

    @Override
    public List<NotificationSetting> selectByUser(
            long userId) {
        return this.em.createNamedQuery("Item.selectByOwner", NotificationSettingDTO.class)
                      .setParameter("userId", userId)
                      .getResultList()
                      .stream()
                      .map(
                              Converters::convert)
                      .collect(Collectors.toList());
    }


    @Override
    public NotificationSetting insert(NotificationSetting notificationSetting) {
        NotificationSettingDTO nsDTO = Converters.convert(notificationSetting);
        this.em.persist(nsDTO);
        return Converters.convert(nsDTO);
    }

    @Override
    public NotificationSetting update(NotificationSetting notificationSetting) {
        return this.insert(notificationSetting);
    }

    @Override
    public NotificationSetting select(long id) {
        return Converters.convert(
                this.em.createNamedQuery("NotificationSetting.selectById", NotificationSettingDTO.class)
                       .setParameter("id", id)
                       .getSingleResult());
    }

}
