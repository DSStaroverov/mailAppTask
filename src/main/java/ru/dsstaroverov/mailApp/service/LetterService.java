package ru.dsstaroverov.mailApp.service;

import ru.dsstaroverov.mailApp.model.Letter;
import ru.dsstaroverov.mailApp.to.LetterTo;
import ru.dsstaroverov.mailApp.util.exception.NotFoundException;

import java.util.List;


public interface LetterService {
    Letter create(LetterTo letter);

    void delete(int id) throws NotFoundException;

    Letter get(int id) throws NotFoundException;

    void update(Letter letter);

    List<Letter> getAll();
}
