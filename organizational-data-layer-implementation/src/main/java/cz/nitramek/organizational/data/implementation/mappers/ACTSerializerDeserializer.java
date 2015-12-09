package cz.nitramek.organizational.data.implementation.mappers;

import com.google.gson.*;
import cz.nitramek.organizational.domain.classes.AttributeValueType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ACTSerializerDeserializer implements
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
}
