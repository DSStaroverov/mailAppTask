package ru.dsstaroverov.mailApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "folders",uniqueConstraints = {@UniqueConstraint(columnNames = {"name","email_id"}, name = "folders_unique_idx")})
public class Folder extends AbstractBaseEntity {

    @JoinColumn(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email_id", nullable = false)
    @NotNull
    private Email email;

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    @OrderBy("sendTime DESC")
    private List<Letter> letters;

    public Folder() {
    }

    public Folder(String name, Email email) {
        this(null, name,email);
    }

    public Folder(Integer id, String name, Email email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                ", email=" + email +
                '}';
    }
}
