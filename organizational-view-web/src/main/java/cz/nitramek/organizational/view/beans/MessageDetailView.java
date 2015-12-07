package cz.nitramek.organizational.view.beans;


import cz.nitramek.organizational.domain.classes.BorrowRequest;
import cz.nitramek.organizational.domain.classes.Item;
import cz.nitramek.organizational.domain.classes.Message;
import cz.nitramek.organizational.domain.classes.Notification;
import cz.nitramek.organizational.domain.service.ItemService;
import cz.nitramek.organizational.domain.service.MessageService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Named(value = "messageDetail")
@ViewScoped
public class MessageDetailView implements Serializable {

    private Message current;

    @EJB
    private ItemService itemService;

    @EJB
    private MessageService msgService;


    @Inject
    private SessionBackingBean ssb;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                                                                      .getExternalContext()
                                                                      .getRequest();
        Optional.ofNullable(request.getParameter("msgId"))
                .map(p -> this.msgService.get(Long.parseLong(p))).ifPresent(this::setCurrent);
        Optional.ofNullable(request.getParameter("itemId"))
                .map(p -> this.itemService.get(Long.parseLong(p))).ifPresent(this::createRequest);

        this.current.setSender(ssb.getLoggedUser());
    }

    public Message getCurrent() {
        return current;
    }

    public void setCurrent(Message current) {
        this.current = current;
    }

    public void createRequest(Item item) {
        BorrowRequest borrowRequest = this.msgService.createBorrowRequest();
        borrowRequest.setRequestedItem(item);
        this.current.setRecipient(item.getOwner());
        this.current.setSubject(String.format("Požadavek na půjčku: %s", item.getName()));
    }

    public boolean isNotification() {
        return this.current instanceof Notification;
    }

    public boolean isRequest() {
        return this.current instanceof BorrowRequest;
    }

    public Item getRequestItem() {
        if (isRequest())
            return ((BorrowRequest) this.current).getRequestedItem();
        return null;
    }

    public String send() {
        this.current.setDateSend(new Date());
        this.msgService.add(this.current);
        return NavigationRules.HOME;
    }
}
