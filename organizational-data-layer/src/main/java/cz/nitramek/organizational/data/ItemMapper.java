package cz.nitramek.organizational.data;


import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.classes.User;

import java.util.List;

/**
 * Also Handles attributes for given item and permission too.
 */
public interface ItemMapper extends Mapper<Item> {
    List<Item> select(User user);
}
