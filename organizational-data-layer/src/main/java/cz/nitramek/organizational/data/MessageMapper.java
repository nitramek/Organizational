package cz.nitramek.organizational.data;

import cz.nitramek.organizational.domain.classes.Message;
import cz.nitramek.organizational.domain.classes.User;

import java.util.List;

/**
 * This mapper handles borrow request and notifications along as Messages.
 */
public interface MessageMapper extends Mapper<Message> {
    List<Message> select(User user);
}
