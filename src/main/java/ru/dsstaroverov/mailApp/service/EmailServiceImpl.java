package ru.dsstaroverov.mailApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.mailApp.model.Email;
import ru.dsstaroverov.mailApp.model.User;
import ru.dsstaroverov.mailApp.repository.EmailRepository;
import ru.dsstaroverov.mailApp.repository.UserRepository;
import ru.dsstaroverov.mailApp.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

import static ru.dsstaroverov.mailApp.web.SecurityUtil.authUserId;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Email create(Email email) {
        User user = userRepository.getOne(authUserId());
        email.setUser(user);
        if(email.getRegistered()==null){
            email.setRegistered(new Date());
        }
        return repository.save(email);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(get(id));
    }

    @Override
    public Email get(int id) throws NotFoundException {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Email email) {
        repository.save(email);
    }

    @Override
    public List<Email> getAll() {
        return repository.getAll(authUserId());
    }
}
