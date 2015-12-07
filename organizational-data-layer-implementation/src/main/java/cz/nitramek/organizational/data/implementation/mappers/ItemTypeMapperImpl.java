package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.ItemTypeDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.ItemTypeMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.ItemType;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@MapperImplementation(mapper = ItemTypeMapper.class)
public class ItemTypeMapperImpl implements ItemTypeMapper {

    private EntityManager em;

    public ItemTypeMapperImpl() throws NamingException {
        this.em = (EntityManager) new InitialContext().lookup("java:/org-em");
    }

    @Override
    public ItemType insert(
            ItemType itemType) {
        ItemTypeDTO itDTO = Converters.convert(itemType);
        return Converters.convert(this.em.merge(itDTO));
    }

    @Override
    public ItemType update(ItemType itemType) {
        if (itemType.getId() == 0) {
            return this.insert(itemType);
        }
        ItemTypeDTO itDTO = Converters.convert(itemType);
        return Converters.convert(this.em.merge(itDTO));
    }

    @Override
    public ItemType select(long id) {
        return Converters.convert(this.em.createNamedQuery("ItemType.selectById", ItemTypeDTO.class)
                                         .setParameter("id", id)
                                         .getSingleResult());
    }

    @Override
    public List<ItemType> select() {
        return this.em.createQuery("SELECT it FROM ItemType it", ItemTypeDTO.class)
                      .getResultList()
                      .stream()
                      .map(Converters::convert)
                      .collect(
                              Collectors.toList());
    }
}
