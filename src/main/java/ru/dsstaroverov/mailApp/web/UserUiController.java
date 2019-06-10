package ru.dsstaroverov.mailApp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsstaroverov.mailApp.model.User;
import ru.dsstaroverov.mailApp.service.UserService;
import ru.dsstaroverov.mailApp.to.UserTo;
import ru.dsstaroverov.mailApp.util.exception.IllegalRequestDataException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.dsstaroverov.mailApp.util.UserUtil.newFromTo;
import static ru.dsstaroverov.mailApp.util.ValidationUtil.assureIdConsistent;
import static ru.dsstaroverov.mailApp.web.SecurityUtil.*;

@RestController
@RequestMapping(value = UserUiController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserUiController {

    public static final String REST_URL = "/user";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAll() {
        if (isAdmin()) {
            log.info("getAll users");
            return userService.getAll();
        }
        throw new AccessDeniedException("");
    }


    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        if (isAdmin()) {
            log.info("get with id={}", id);
            return userService.get(id);
        }else {
            log.info("get with id={}", authUserId());
            return userService.get(authUserId());
        }

    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody UserTo user) {

            log.info("create user");
            User created = userService.create(newFromTo(user));
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        if (isAdmin()) {
            log.info("delete user with id={}", id);
            userService.delete(id);
        }else {
            log.info("user with id={} set enabled false", authUserId());
            userService.enable(authUserId(),false);
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo user, @PathVariable int id) {
        try {
            if (isAdmin()) {
                log.info("update {} with id={}", user, id);
                assureIdConsistent(user, id);
                userService.update(user);
            }else {
                assureIdConsistent(user, authUserId());
                log.info("update {} with id={}", user, authUserId());
                userService.update(user);
            }
        }catch (DataIntegrityViolationException e){
            throw new IllegalRequestDataException("email dublicate update");
        }
    }

}