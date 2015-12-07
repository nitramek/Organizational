package cz.nitramek.organizational.data.implementation.mappers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import cz.nitramek.organizational.data.implementation.util.JsonStorage;
import cz.nitramek.organizational.data.mapper.AttributeValueTypeMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.AttributeValueType;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
                                                     new AttributeValueTypeMapperImpl.AVTDeserilializer()
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

    public static class AVTDeserilializer implements
                                          JsonDeserializer<TreeMap<Long, AttributeValueType>>,
                                          JsonSerializer<TreeMap<Long, AttributeValueType>> {
        @Override
        public TreeMap<Long, AttributeValueType> deserialize(
                JsonElement jsonElement, Type type, JsonDeserializationContext jdc) throws
                JsonParseException {
            JsonArray data = jsonElement.getAsJsonArray();
            List<AttributeValueType> list = new ArrayList<>(data.size());
            for (JsonElement e : data) {
                JsonObject jsonObject = e.getAsJsonObject();
                AttributeValueType avt = new AttributeValueType();
                avt.setId(jsonObject.get("id").getAsLong());
                try {
                    avt.setConvertingClass(Class.forName(jsonObject.get("className").getAsString()));
                } catch (NoSuchMethodException | ClassNotFoundException ee) {
                    ee.printStackTrace();
                }
                try {
                    avt.setMethodName((jsonObject.get("methodName").getAsString()));
                } catch (NoSuchMethodException ee) {
                    ee.printStackTrace();
                }
                avt.setName(jsonObject.get("name").getAsString());
                list.add(avt);

            }

            TreeMap<Long, AttributeValueType> avtMap = new TreeMap<>();
            list.stream().forEach(i -> avtMap.put(i.getId(), i));
            return avtMap;
        }

        @Override
        public JsonElement serialize(
                TreeMap<Long, AttributeValueType> avts, Type type,
                JsonSerializationContext jsonSerializationContext) {
            JsonArray array = new JsonArray();
            for (AttributeValueType avt : avts.values()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", avt.getId());
                jsonObject.addProperty("name", avt.getName());
                jsonObject.addProperty("className", avt.getConvertingClass().getName());
                jsonObject.addProperty("methodName", avt.getMethodName());
                array.add(jsonObject);
            }
            return array;
        }


//        @Override
//        public AttributeValueType deserialize(
//                JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws
//                JsonParseException {
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//            AttributeValueType avt = new AttributeValueType();
//            avt.setId(jsonObject.get("id").getAsLong());
//            try {
//                avt.setConvertingClass(Class.forName(jsonObject.get("className").getAsString()));
//            } catch (NoSuchMethodException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            try {
//                avt.setMethodName((jsonObject.get("methodName").getAsString()));
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            avt.setName(jsonObject.get("name").getAsString());
//            return avt;
//        }
//
//        @Override
//        public JsonElement serialize(
//                AttributeValueType avt, Type type, JsonSerializationContext jsonSerializationContext) {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("id", avt.getId());
//            jsonObject.addProperty("name", avt.getName());
//            jsonObject.addProperty("className", avt.getConvertingClass().getName());
//            jsonObject.addProperty("methodName", avt.getMethodName());
//            return jsonObject;
////        }
    }
}

