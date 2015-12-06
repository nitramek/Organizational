package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.implementation.dto.MessageDTO;
import cz.nitramek.organizational.data.implementation.util.Converters;
import cz.nitramek.organizational.data.mapper.MessageMapper;
import cz.nitramek.organizational.data.util.MapperImplementation;
import cz.nitramek.organizational.domain.classes.Message;
import cz.nitramek.organizational.domain.classes.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MapperImplementation(mapper = MessageMapper.class)
public class MessageMapperImpl implements MessageMapper {

    private EntityManager em;

    public MessageMapperImpl() throws NamingException {
        this.em = (EntityManager) new InitialContext().lookup("java:/org-em");
    }

    @Override
    public List<Message> select(
            User user) {
        return this.em.createNamedQuery("Message.selectByUser", MessageDTO.class)
                      .setParameter("userId", user.getId())
                      .getResultList()
                      .stream()
                      .map(Converters::convert)
                      .collect(Collectors.toCollection(ArrayList<Message>::new));
    }

    @Override
    public Message insert(Message message) {
        MessageDTO mDTO = Converters.convert(message);

        this.em.persist(mDTO);

        return Converters.convert(mDTO);
    }

    @Override
    public Message update(Message message) {
        return this.insert(message);
    }

    @Override
    public Message select(long id) {
        return Converters.convert(this.em.createNamedQuery("Message.selectOne", MessageDTO.class)
                                         .getSingleResult());
    }
}
