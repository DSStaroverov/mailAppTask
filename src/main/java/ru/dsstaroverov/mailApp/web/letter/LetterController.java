package ru.dsstaroverov.mailApp.web.letter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dsstaroverov.mailApp.model.Letter;
import ru.dsstaroverov.mailApp.service.LetterService;
import ru.dsstaroverov.mailApp.to.LetterTo;

import java.util.List;

import static ru.dsstaroverov.mailApp.util.LetterUtil.*;

@RestController
@RequestMapping("/ajax/letter")
public class LetterController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LetterService service;

    @GetMapping(value = "/{id}")
    public LetterTo get(@PathVariable("id") int id) {
        log.info("get Letter: "+id);
        Letter letter = service.get(id);
        return asTo(letter);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LetterTo> getAll(){
        log.info("getAllLetter");
        return getListLetterTo(service.getAll());
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete letter: "+id);
        service.delete(id);
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(LetterTo letter) {
        if (letter.isNew()) {
            log.info("create letter");
            service.create(letter);
        }
    }
}
