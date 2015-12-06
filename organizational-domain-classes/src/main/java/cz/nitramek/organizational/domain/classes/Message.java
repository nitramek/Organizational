package cz.nitramek.organizational.domain.classes;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.util.Date;

public class Message implements Identifiable {
    private long id;
    private String subject;
    private String text;
    private Date dateSend;

    public Message(String subject) {
        this.subject = subject;
    }

    public Message() {
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (getId() != message.getId()) return false;
        return getSubject().equals(message.getSubject());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getSubject().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Message{");
        sb.append("dateSend=").append(dateSend);
        sb.append(", id=").append(id);
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
