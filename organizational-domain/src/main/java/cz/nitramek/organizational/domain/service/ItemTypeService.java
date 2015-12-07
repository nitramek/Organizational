package cz.nitramek.organizational.domain.service;

import cz.nitramek.organizational.data.mapper.ItemTypeMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.AttributeType;
import cz.nitramek.organizational.domain.classes.ItemType;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class ItemTypeService extends AbstractService<ItemType, ItemTypeMapper> {

    @PostConstruct
    public void init() {
        try {
            super.init(ItemType.class, ItemTypeMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    public List<ItemType> get() {
        return this.mapper.select();
    }

    public AttributeType createAttribute() {
        return new AttributeType();
    }

}
