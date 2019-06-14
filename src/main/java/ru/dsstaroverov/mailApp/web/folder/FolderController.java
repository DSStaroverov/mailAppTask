package ru.dsstaroverov.mailApp.web.folder;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dsstaroverov.mailApp.model.Folder;
import ru.dsstaroverov.mailApp.service.FolderService;
import ru.dsstaroverov.mailApp.to.FolderTo;

import java.util.List;

import static ru.dsstaroverov.mailApp.util.FolderUtil.fromTo;
import static ru.dsstaroverov.mailApp.web.SecurityUtil.getAuthUser;

@RestController
@RequestMapping("/ajax/folder")
public class FolderController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private FolderService service;

    @GetMapping(value = "/{id}")
    public Folder get(@PathVariable("id") int id) {
        log.info("get Folder: "+id);
        Folder folder = service.get(id);
        if(folder.getEmail().equals(getAuthUser().getCurrentEmail())){
            getAuthUser().setCurrentFolder(folder);
        }
        return folder;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Folder> getAll(){
        log.info("getAllFolder");
        return service.getAll();
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete folder: "+id);
        service.delete(id);
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(FolderTo folder) {
        if (folder.isNew()) {
            log.info("create folder" +folder.toString());
            service.create(fromTo(folder));
        }else{
            log.info("update folder" +folder.toString());
            service.update(fromTo(folder));
        }
    }
}
