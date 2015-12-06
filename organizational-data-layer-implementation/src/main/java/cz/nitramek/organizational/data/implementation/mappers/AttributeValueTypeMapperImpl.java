package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.util.JsonStorage;
import cz.nitramek.organizational.data.mapper.AttributeValueTypeMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.AttributeValueType;

import java.util.List;
import java.util.stream.Collectors;

@MapperImplementation(mapper = AttributeValueTypeMapper.class)
public class AttributeValueTypeMapperImpl implements AttributeValueTypeMapper {

    private static final String PATH = System.getProperty("organizational.dir.storage") + "/attributeValueType.json";
    private final JsonStorage<AttributeValueType> storage;

    public AttributeValueTypeMapperImpl() {
        this.storage = new JsonStorage<AttributeValueType>(PATH);
    }

    @Override
    public AttributeValueType insert(
            AttributeValueType attributeValueType) {
        AttributeValueType avt = this.storage.insert(attributeValueType);
        this.storage.save();
        return avt;
    }

    @Override
    public AttributeValueType update(AttributeValueType attributeValueType) {
        AttributeValueType avt = this.storage.update(attributeValueType);
        this.storage.save();
        return avt;

    }

    @Override
    public AttributeValueType select(long id) {
        return this.storage.get(id);
    }

    @Override
    public List<AttributeValueType> select() {
        return this.storage.get().stream().collect(Collectors.toList());
    }
}
