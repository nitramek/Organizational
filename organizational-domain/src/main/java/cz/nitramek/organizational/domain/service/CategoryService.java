package cz.nitramek.organizational.domain.service;


import cz.nitramek.organizational.data.mapper.CategoryMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.Category;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class CategoryService extends AbstractService<Category, CategoryMapper> {

    @PostConstruct
    public void init() {
        try {
            super.init(Category.class, CategoryMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAll() {
        return this.mapper.select();
    }
}
