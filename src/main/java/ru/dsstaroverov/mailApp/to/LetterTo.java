package ru.dsstaroverov.mailApp.to;

import java.time.LocalDateTime;

public class LetterTo extends BaseTo {
    private String sender;
    private String recipient;
    private String title;
    private String message;
    private LocalDateTime sendTime;

    public LetterTo() {
    }

    public LetterTo(Integer id, String sender, String recipient, String title, String message, LocalDateTime sendTime) {
        super(id);
        this.sender = sender;
        this.recipient = recipient;
        this.title = title;
        this.message = message;
        this.sendTime = sendTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "LetterTo{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message.substring(0,20) + "..." + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
