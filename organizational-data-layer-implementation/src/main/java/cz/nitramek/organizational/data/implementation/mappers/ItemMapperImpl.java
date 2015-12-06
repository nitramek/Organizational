package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.ItemDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.ItemMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.Item;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@MapperImplementation(mapper = ItemMapper.class)
public class ItemMapperImpl implements ItemMapper {

    private final EntityManager em;

    public ItemMapperImpl() throws NamingException {
        this.em = (EntityManager) new InitialContext().lookup("java:/org-em");
    }

    @Override
    public List<Item> selectByOwner(
            long userId) {
        return this.em.createNamedQuery("Item.selectByOwner", ItemDTO.class).setParameter("userId", userId)
                      .getResultList().stream()
                      .map(Converters::convert)
                      .collect(Collectors.toList());
    }

    @Override
    public Item insert(Item item) {
        ItemDTO iDTO = Converters.convert(item);
        this.em.persist(iDTO);
        iDTO.getAttributeDTOs().stream().forEach(this.em::persist);

        return Converters.convert(iDTO);
    }

    @Override
    public Item update(Item item) {
        return this.insert(item);
    }

    @Override
    public Item select(long id) {
        return Converters.convert(this.em.createNamedQuery("Item.selectById", ItemDTO.class)
                                         .setParameter("id", id)
                                         .getSingleResult());
    }
}
