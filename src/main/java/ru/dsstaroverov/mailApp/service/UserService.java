package ru.dsstaroverov.mailApp.service;




import ru.dsstaroverov.mailApp.model.User;
import ru.dsstaroverov.mailApp.to.UserTo;
import ru.dsstaroverov.mailApp.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();

    void enable(int id, boolean enable);

}