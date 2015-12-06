package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.ItemTypeDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.ItemTypeMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.ItemType;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;


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
        itemType.getAttributeTypes()
                .stream()
                .forEach(attributeType -> this.em.persist(Converters.convert(attributeType)));
        return Converters.convert(itDTO);
    }

    @Override
    public ItemType update(ItemType itemType) {
        return this.insert(itemType);
    }

    @Override
    public ItemType select(long id) {
        return Converters.convert(this.em.createNamedQuery("ItemType.selectById", ItemTypeDTO.class)
                                         .setParameter("id", id)
                                         .getSingleResult());
    }
}
