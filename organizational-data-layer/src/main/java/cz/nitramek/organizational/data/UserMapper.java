package cz.nitramek.organizational.data;


import cz.nitramek.organizational.domain.classes.User;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<User> select();
}
