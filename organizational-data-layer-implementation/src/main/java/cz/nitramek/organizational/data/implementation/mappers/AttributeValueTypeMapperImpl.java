package cz.nitramek.organizational.data.implementation.mappers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import cz.nitramek.organizational.data.implementation.util.JsonStorage;
import cz.nitramek.organizational.data.mapper.AttributeValueTypeMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.AttributeValueType;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;
import java.util.TreeMap;

@MapperImplementation(mapper = AttributeValueTypeMapper.class)
public class AttributeValueTypeMapperImpl implements AttributeValueTypeMapper {

    private static final String PATH = System.getProperty("organizational.dir.storage") + "/attributeValueType.json";
    private final JsonStorage<AttributeValueType> storage;

    public AttributeValueTypeMapperImpl() {
        Type type = new TypeToken<TreeMap<Long, AttributeValueType>>() {
        }.getType();
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
                                     .excludeFieldsWithoutExposeAnnotation().
                                             registerTypeAdapter(
                                                     type,
                                                     new ACTSerializerDeserializer()
                                                                )
                                     .create();


        this.storage = new JsonStorage<>(PATH, gson, type);
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
        return this.storage.get();
    }

}

