package cz.nitramek.organizational.domain.service.util;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GsonUtil {
    public static final Gson gson;


    static {
        Type type = new TypeToken<Class>() {
        }.getType();
        gson = new GsonBuilder()
                .registerTypeAdapter(type, (JsonSerializer<Class>) (aClass, type1, jsonSerializationContext)
                        -> new JsonPrimitive(aClass.getName()))
                .registerTypeAdapter(type, (JsonDeserializer<Class>) (aClass, type1, jsonSerializationContext) -> {
                    try {
                        return Class.forName(aClass.getAsString());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .create();
    }
}
