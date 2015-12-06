package cz.nitramek.organizational.data.mapper;

import cz.nitramek.organizational.domain.classes.AttributeValueType;

import java.util.List;


public interface AttributeValueTypeMapper extends Mapper<AttributeValueType> {
    List<AttributeValueType> select();
}
