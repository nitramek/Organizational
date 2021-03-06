package cz.nitramek.organizational.data.implementation.dto;


import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "message_typ")
@DiscriminatorValue(value = "MSG")
@Table(name = "Message")
@NamedQueries({
        @NamedQuery(name = "Message.selectByUser",
                query = "SELECT m FROM MessageDTO m" +
                        " WHERE sender = :userId OR recipient  = :userId "),
        @NamedQuery(name = "Message.selectAll", query = "SELECT m FROM MessageDTO m"),
        @NamedQuery(name = "Message.selectOne", query = "SELECT m FROM MessageDTO m WHERE m.id = :id")
})
public class MessageDTO {

    @Id
    @GeneratedValue
    private long id;

    private String subject;
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSend;

    @ManyToOne
    @JoinColumn(name = "sender")
    private UserDTO sender;

    @ManyToOne
    @JoinColumn(name = "recipient")
    private UserDTO recipient;


    public UserDTO getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDTO recipient) {
        this.recipient = recipient;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
