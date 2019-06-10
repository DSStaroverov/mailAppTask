package ru.dsstaroverov.mailApp;


import ru.dsstaroverov.mailApp.model.Email;
import ru.dsstaroverov.mailApp.model.User;
import ru.dsstaroverov.mailApp.to.UserTo;
import ru.dsstaroverov.mailApp.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    private Email currentEmail;

    public AuthorizedUser(User user) {
        super(user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public UserTo getUser(){
        return userTo;
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public Email getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(Email currentEmail) {
        this.currentEmail = currentEmail;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}