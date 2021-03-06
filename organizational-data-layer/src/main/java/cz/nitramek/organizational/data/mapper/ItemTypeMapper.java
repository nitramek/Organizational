package cz.nitramek.organizational.data.mapper;


import cz.nitramek.organizational.domain.classes.ItemType;

import java.util.List;

/**
 * This mapper also solves inner AttributeTypes of ItemType.
 */
public interface ItemTypeMapper extends Mapper<ItemType> {

    List<ItemType> select();
}
