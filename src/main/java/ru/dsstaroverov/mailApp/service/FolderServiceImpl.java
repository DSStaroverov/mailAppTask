package ru.dsstaroverov.mailApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.mailApp.model.Folder;
import ru.dsstaroverov.mailApp.repository.FolderRepository;
import ru.dsstaroverov.mailApp.util.exception.NotFoundException;
import ru.dsstaroverov.mailApp.web.SecurityUtil;

import java.util.List;

import static ru.dsstaroverov.mailApp.web.SecurityUtil.getAuthUser;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository repository;

    @Override
    @Transactional
    public Folder create(Folder folder) {
        if(folder.getEmail()==null){
            folder.setEmail(getAuthUser().getCurrentEmail());
        }
        return repository.save(folder);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public Folder get(int id) throws NotFoundException {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Folder folder) {
        if(checkDefaultFolder(folder)) {
            if (folder.getEmail() == null) {
                folder.setEmail(getAuthUser().getCurrentEmail());
            }
            repository.save(folder);
        }
    }

    @Override
    public List<Folder> getAll() {
        return repository.getAll(getAuthUser().getCurrentEmail().getId());
    }

    private boolean checkDefaultFolder(Folder folder){
        if(folder.getName().contains("inbox")){
            return false;
        }

        if(folder.getName().contains("outbox")){
            return false;
        }

        return true;
    }
}
