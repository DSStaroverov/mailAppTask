package ru.dsstaroverov.mailApp.service;

import ru.dsstaroverov.mailApp.model.Folder;
import ru.dsstaroverov.mailApp.util.exception.NotFoundException;

import java.util.List;


public interface FolderService {
    Folder create(Folder folder);

    void delete(int id) throws NotFoundException;

    Folder get(int id) throws NotFoundException;

    void update(Folder folder);

    List<Folder> getAll();
}
