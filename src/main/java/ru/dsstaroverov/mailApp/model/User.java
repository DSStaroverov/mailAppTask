package ru.dsstaroverov.mailApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_login_idx")})
public class User extends AbstractBaseEntity {
    
    @Column(name = "login", nullable = false, unique = true)
    @NotBlank
    @Size(min = 5, max = 100)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date birthday;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<Role> roles;


    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getLogin(), u.getPassword(), u.isEnabled(), u.getRegistered(),u.getFirstName(),u.getLastName(),u.getBirthday(), u.getRoles());
    }

    public User(Integer id,  String login, String password, Role role,String firstName, String lastName, Date birthday,Role... roles) {
        this(id, login, password, true, new Date(),firstName,lastName,birthday, EnumSet.of(role, roles));
    }

    public User(Integer id, String login, String password, boolean enabled, Date registered, String firstName, String lastName, Date birthday, Collection<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthday=birthday;
        setRoles(roles);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login=" + login +
                ", name=" + firstName +" "+lastName+
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}