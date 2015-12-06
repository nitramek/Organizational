package cz.nitramek.organizational.domain.service;

import cz.nitramek.organizational.data.mapper.ItemMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.Item;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

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
//    public List<Item> getItems() {
//        return this.mapper.se(userId);
//    }
}

