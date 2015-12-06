package cz.nitramek.organizational.domain.service;

import cz.nitramek.organizational.data.mapper.MessageMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.data.util.MapperFactory;
import cz.nitramek.organizational.domain.classes.BorrowRequest;
import cz.nitramek.organizational.domain.classes.Message;
import cz.nitramek.organizational.domain.classes.Notification;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class MessageService implements Service<Message> {
    private MessageMapper msgMapper;

    @PostConstruct
    public void init() {
        try {
            this.msgMapper = MapperFactory.createMapper(MessageMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message add(Message message) {
        return this.msgMapper.insert(message);
    }

    @Override
    public Message create() {
        return new Message();
    }

    public BorrowRequest createBorrowRequest(){
        return new BorrowRequest();
    }

    public Notification createNotificaiton(){
        return new Notification();
    }

    @Override
    public Message get(long id) {
        return this.msgMapper.select(id);
    }

    @Override
    public Message update(Message message) {
        return this.msgMapper.update(message);
    }
}
