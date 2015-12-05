package cz.nitramek.organizational.data.implementation.mappers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import cz.nitramek.organizational.data.mapper.CategoryMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.Category;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@MapperImplementation(mapper = CategoryMapper.class)
public class CategoryMapperImpl implements CategoryMapper {

    private final Type collectionType;
    private Gson gson;
    private static final String PATH = System.getProperty("organizational.dir.storage") + "/categories.json";

    private TreeMap<Long, Category> categoryMap;
    private long nextId;


    public CategoryMapperImpl() {
        this.gson = new Gson();
        this.collectionType = new TypeToken<TreeMap<Long, Category>>() {
        }.getType();
        this.categoryMap = this.getData();

    }

    @Override
    public List<Category> select() {
        return this.categoryMap.values().stream().collect(Collectors.toList());
    }

    private TreeMap<Long, Category> getData() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH))) {
//            StringBuilder sb = new StringBuilder();
//            bufferedReader.lines().forEach(sb::append);
            TreeMap<Long, Category> data = this.gson.fromJson(bufferedReader, this.collectionType);
            this.nextId = Optional.ofNullable(data.lastEntry()).map(Map.Entry::getKey).orElse(0L);
            this.nextId++;
            return data;
        } catch (IOException e) {
            this.nextId = 1;
            this.save();
            return new TreeMap<>();
        }
    }

    private void save() {
        try (JsonWriter writer = new JsonWriter(new FileWriter(PATH))) {
            this.gson.toJson(this.categoryMap, this.collectionType, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category insert(Category category) {
        category.setId(this.nextId);
        this.nextId++;
        this.categoryMap.put(category.getId(), category);
        this.save();
        return category;

    }

    @Override
    public Category update(Category category) {
        this.categoryMap.put(category.getId(), category);
        this.save();
        return category;
    }

    @Override
    public Category select(long id) {
        return this.categoryMap.get(id);
    }
}
