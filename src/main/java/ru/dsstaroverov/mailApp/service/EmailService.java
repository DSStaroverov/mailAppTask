package ru.dsstaroverov.mailApp.service;

import ru.dsstaroverov.mailApp.model.Email;
import ru.dsstaroverov.mailApp.util.exception.NotFoundException;

import java.util.List;


public interface EmailService {
    Email create(Email email);

    void delete(int id) throws NotFoundException;

    Email get(int id) throws NotFoundException;

    void update(Email email);

    List<Email> getAll();
}
