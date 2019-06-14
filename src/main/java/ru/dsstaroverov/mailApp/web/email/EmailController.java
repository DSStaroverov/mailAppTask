package ru.dsstaroverov.mailApp.web.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dsstaroverov.mailApp.model.Email;
import ru.dsstaroverov.mailApp.model.Folder;
import ru.dsstaroverov.mailApp.service.EmailService;
import ru.dsstaroverov.mailApp.service.FolderService;
import ru.dsstaroverov.mailApp.to.EmailTo;

import javax.validation.Valid;
import java.util.List;

import static ru.dsstaroverov.mailApp.util.EmailUtil.*;
import static ru.dsstaroverov.mailApp.web.SecurityUtil.authUserId;
import static ru.dsstaroverov.mailApp.web.SecurityUtil.getAuthUser;

@RestController
@RequestMapping("/ajax/email")
public class EmailController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmailService service;

    @Autowired
    private FolderService folderService;


    @GetMapping(value = "/{id}")
    public EmailTo get(@PathVariable("id") int id) {
        log.info("get Email: "+id);
        Email email = service.get(id);
        if(email.getUser().getId()==authUserId()) {
            getAuthUser().setCurrentEmail(email);
        }
        return asTo(email);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmailTo> getAll(){
        log.info("getAllEmail");
        return getListEmailTo(service.getAll());
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete Email: "+id);
        service.delete(id);
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void create(EmailTo email) {
        log.info("create Email" +email.toString());
        if (email.isNew()) {
            Email created = service.create(fromTo(email));
            folderService.create(new Folder(null,"inbox",created));
            folderService.create(new Folder(null,"outbox",created));
        }
    }

}
