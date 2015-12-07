package cz.nitramek.organizational.data.mapper;


import cz.nitramek.organizational.domain.classes.Item;

import java.util.List;

/**
 * Also Handles attributes for given item and permission too.
 */
public interface ItemMapper extends Mapper<Item> {
    List<Item> selectByOwner(long userId);

    List<Item> selectPermitted(long userId);

    List<Item> selectByRole(long roleId);
}
