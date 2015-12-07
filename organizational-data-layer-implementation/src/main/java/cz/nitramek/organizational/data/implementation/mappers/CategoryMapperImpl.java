package cz.nitramek.organizational.data.implementation.mappers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.nitramek.organizational.data.implementation.util.JsonStorage;
import cz.nitramek.organizational.data.mapper.CategoryMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.Category;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@MapperImplementation(mapper = CategoryMapper.class)
public class CategoryMapperImpl implements CategoryMapper {
    private static final String PATH = System.getProperty("organizational.dir.storage") + "/categories.json";

    private final JsonStorage<Category> storage;

    public CategoryMapperImpl() {
        this.storage = new JsonStorage<>(PATH, new Gson(), new TypeToken<TreeMap<Long, Category>>() {
        }.getType());

    }

    @Override
    public List<Category> select() {
        return this.storage.get().stream().collect(Collectors.toList());
    }

    @Override
    public Category insert(Category category) {
        this.storage.insert(category);
        this.storage.save();
        return category;

    }

    @Override
    public Category update(Category category) {
        this.storage.update(category);
        this.storage.save();
        return category;
    }

    @Override
    public Category select(long id) {
        return this.storage.get(id);
    }
}
