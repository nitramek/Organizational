package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.ItemTypeDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.ItemTypeMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.ItemType;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
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
        this.em.persist(itDTO);
        itDTO.setAttributeTypes(itDTO.getAttributeTypes().stream()
                                     .map(this.em::merge)
                                     .collect(Collectors.toList()));
        return Converters.convert(itDTO);
    }

    @Override
    public ItemType update(ItemType itemType) {
        ItemTypeDTO itDTO = Converters.convert(itemType);
        itDTO.setAttributeTypes(itDTO.getAttributeTypes().stream()
                                     .map(this.em::merge)
                                     .collect(Collectors.toList()));
        return Converters.convert(itDTO);
    }

    @Override
    public ItemType select(long id) {
        return Converters.convert(this.em.createNamedQuery("ItemType.selectById", ItemTypeDTO.class)
                                         .setParameter("id", id)
                                         .getSingleResult());
    }
}
