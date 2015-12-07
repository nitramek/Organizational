package cz.nitramek.organizational.data.implementation.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.io.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;

public class JsonStorage<T extends Identifiable> {
    private final Type collectionType;
    private Gson gson;

    private TreeMap<Long, T> dataMap;
    private long nextId;

    private String path;

    public JsonStorage(String path) {
        this.gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
                                     .excludeFieldsWithoutExposeAnnotation()
                                     .create();
        this.collectionType = new TypeToken<TreeMap<Long, T>>() {
        }.getType();
        this.path = path;
        this.dataMap = this.getData();
        this.save();

    }

    public T insert(T obj) {
        this.dataMap.put(this.nextId, obj);
        obj.setId(this.nextId);
        this.nextId++;
        return obj;
    }

    public void save() {
        try (JsonWriter writer = new JsonWriter(new FileWriter(path))) {
            this.gson.toJson(this.dataMap, this.collectionType, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T update(T obj) {
        if (obj.getId() < 0) {
            return this.insert(obj);
        }
        this.dataMap.put(obj.getId(), obj);
        return obj;
    }

    public T get(long id) {
        return this.dataMap.get(id);
    }

    private TreeMap<Long, T> getData() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path))) {
//            StringBuilder sb = new StringBuilder();
//            bufferedReader.lines().forEach(sb::append);
            TreeMap<Long, T> data = this.gson.fromJson(bufferedReader, this.collectionType);
            this.nextId = Optional.ofNullable(data.lastEntry()).map(Map.Entry::getKey).orElse(0L);
            this.nextId++;
            return data;
        } catch (IOException e) {
            this.nextId = 1;
            File file = new File(this.path);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return new TreeMap<>();
        }
    }

    public List<T> get() {
        Collection<T> values = this.dataMap.values();
        return new ArrayList<>(values);
    }

}

