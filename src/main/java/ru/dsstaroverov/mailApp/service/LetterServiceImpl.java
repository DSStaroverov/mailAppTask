package ru.dsstaroverov.mailApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.mailApp.model.Email;
import ru.dsstaroverov.mailApp.model.Letter;
import ru.dsstaroverov.mailApp.repository.EmailRepository;
import ru.dsstaroverov.mailApp.repository.FolderRepository;
import ru.dsstaroverov.mailApp.repository.LetterRepository;
import ru.dsstaroverov.mailApp.to.LetterTo;
import ru.dsstaroverov.mailApp.util.exception.NotFoundException;

import java.util.List;

import static ru.dsstaroverov.mailApp.web.SecurityUtil.getAuthUser;

@Service
public class LetterServiceImpl implements LetterService {

    @Autowired
    private LetterRepository repository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    @Transactional
    public Letter create(LetterTo letter) {
        Email recipient = emailRepository.getByAddress(letter.getRecipient());
        if(recipient!=null) {
            Letter created = new Letter();

            created.setSender(getAuthUser().getCurrentEmail());
            created.setRecipient(recipient);
            created.setTitle(letter.getTitle());
            created.setMessage(letter.getMessage());
            Letter outbox = new Letter(created);

            created.setFolder(folderRepository.getByEmailAndName(recipient.getId(),"inbox"));
            outbox.setFolder(folderRepository.getByEmailAndName(getAuthUser().getCurrentEmail().getId(),"outbox"));

            repository.save(created);
            return repository.save(outbox);
        }


        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public Letter get(int id) throws NotFoundException {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Letter letter) {

    }

    @Override
    public List<Letter> getAll() {
        return repository.getAll(getAuthUser().getCurrentFolder().getId());
    }
}
