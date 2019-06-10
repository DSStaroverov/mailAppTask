package ru.dsstaroverov.mailApp.util;

import ru.dsstaroverov.mailApp.model.Role;
import ru.dsstaroverov.mailApp.model.User;
import ru.dsstaroverov.mailApp.to.UserTo;


public class UserUtil {
    public static User newFromTo(UserTo user){
        return new User(null,user.getLogin(),user.getPassword(), user.getFirstName(), user.getLastName(), user.getBirthday(), user.getPhoneNumber(),Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getBirthday(), user.getPhoneNumber());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setFirstName(userTo.getFirstName());
        user.setLastName(userTo.getLastName());
        user.setBirthday(userTo.getBirthday());
        user.setPhoneNumber(userTo.getPhoneNumber());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
