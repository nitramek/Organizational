package cz.nitramek.organizational.domain.service;

import cz.nitramek.organizational.data.mapper.ItemMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.Item;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
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
        return this.mapper.selectByOwner(userId);
    }
}
