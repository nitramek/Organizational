package cz.nitramek.organizational.domain.service;

import cz.nitramek.organizational.data.mapper.ItemMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.Attribute;
import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.classes.ItemType;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Local
public class ItemService extends AbstractService<Item, ItemMapper> {

    @PostConstruct
    public void init() {
        try {
            super.init(Item.class, ItemMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemsByOwner(long userId) {
        List<Item> items = this.mapper.selectByOwner(userId);
        if (items == null) {
            items = new ArrayList<>(0);
        }
        return items;
    }

    public List<Item> getItemsByUser(long userId) {
        return this.mapper.selectPermitted(userId);
    }

    public List<Item> getItemsByRole(long roleId) {
        return this.mapper.selectPermitted(roleId);
    }

    public List<Attribute> createAttributes(ItemType itemType) {
        return itemType.getAttributeTypes().stream().map(at -> new Attribute("", at)).collect(Collectors.toList());
    }
}

