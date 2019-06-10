package ru.dsstaroverov.mailApp.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.dsstaroverov.mailApp.model.User;
import ru.dsstaroverov.mailApp.service.UserService;
import ru.dsstaroverov.mailApp.to.UserTo;
import ru.dsstaroverov.mailApp.util.exception.IllegalRequestDataException;

import java.util.List;

import static ru.dsstaroverov.mailApp.util.ValidationUtil.assureIdConsistent;
import static ru.dsstaroverov.mailApp.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        try {
            log.info("create {}", user);
            checkNew(user);
            return service.create(user);
        }catch (DataIntegrityViolationException e){
            throw new IllegalRequestDataException("email dublicate create");
        }
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        try {
            log.info("update {} with id={}", user, id);
            assureIdConsistent(user, id);
            service.update(user);
        }catch (DataIntegrityViolationException e){
            throw new IllegalRequestDataException("email dublicate update");
        }
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }
}