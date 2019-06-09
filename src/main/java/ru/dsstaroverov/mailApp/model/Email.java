package ru.dsstaroverov.mailApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "emails", uniqueConstraints = {@UniqueConstraint(columnNames = "address", name = "email_unique_idx")})
public class Email extends AbstractBaseEntity {
    @Column(name = "address", nullable = false, unique = true)
    @javax.validation.constraints.Email
    @NotBlank
    @Size(max = 100)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    public Email() {
    }

    public Email(String address, User user) {
        this(null,address,user, new Date());
    }

    public Email(Integer id, String address, User user, Date registered) {
        super(id);
        this.address = address;
        this.user = user;
        this.registered = registered;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                ", user=" + user +
                ", registered=" + registered +
                '}';
    }
}
