package cz.nitramek.organizational.data;


import cz.nitramek.organizational.domain.classes.Category;

import java.util.List;

public interface CategoryMapper extends Mapper<CategoryMapper> {
    List<Category> select();
}
