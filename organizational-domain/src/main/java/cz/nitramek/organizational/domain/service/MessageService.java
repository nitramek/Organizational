package cz.nitramek.organizational.domain.service;

import cz.nitramek.organizational.data.mapper.MessageMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.BorrowRequest;
import cz.nitramek.organizational.domain.classes.Message;
import cz.nitramek.organizational.domain.classes.Notification;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class MessageService extends AbstractService<Message, MessageMapper> {


    @PostConstruct
    public void init() {
        try {
            super.init(Message.class, MessageMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message create() {
        return new Message();
    }

    public BorrowRequest createBorrowRequest() {
        return new BorrowRequest();
    }

    public Notification createNotification() {
        return new Notification();
    }
}
