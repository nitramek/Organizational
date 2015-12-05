package cz.nitramek.organizational.data.implementation.dto;


import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "message_typ")
@Table(name = "Message")
public class MessageDTO {

    @Id
    @GeneratedValue
    private long id;

    private String subject;
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSend;

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
