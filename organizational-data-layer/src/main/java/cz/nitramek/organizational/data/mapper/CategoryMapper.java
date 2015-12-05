package cz.nitramek.organizational.data.mapper;


import cz.nitramek.organizational.domain.classes.Category;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {
    List<Category> select();
}
