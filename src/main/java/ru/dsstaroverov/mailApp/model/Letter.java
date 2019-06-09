package ru.dsstaroverov.mailApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "letters")
public class Letter extends AbstractBaseEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", nullable = false)
    @NotNull
    private Email sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id", nullable = false)
    @NotNull
    private Email recipient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    public Letter() {
    }

    public Letter(Email sender, Email recipient, String title, String message) {
        this(sender,recipient,null,title,message);
    }

    public Letter(Email sender, Email recipient, Folder folder, String title, String message) {
        this(null,sender,recipient,folder,LocalDateTime.now(),title,message);
    }

    public Letter(Integer id, Email sender, Email recipient, Folder folder, LocalDateTime sendTime, String title, String message) {
        super(id);
        this.sender = sender;
        this.recipient = recipient;
        this.folder=folder;
        this.sendTime = sendTime;
        this.title = title;
        this.message = message;
    }

    public Email getSender() {
        return sender;
    }

    public void setSender(Email sender) {
        this.sender = sender;
    }

    public Email getRecipient() {
        return recipient;
    }

    public void setRecipient(Email recipient) {
        this.recipient = recipient;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "sender=" + sender +
                ", recipient=" + recipient +
                ", folder=" + folder +
                ", sendTime=" + sendTime +
                ", title='" + title + '\'' +
                ", message='" + message.substring(0,20) + "..." + '\'' +
                '}';
    }
}
