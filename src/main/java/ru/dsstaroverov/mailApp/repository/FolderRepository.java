package ru.dsstaroverov.mailApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.mailApp.model.Folder;

import java.util.List;

@Transactional(readOnly = true)
public interface FolderRepository extends JpaRepository<Folder,Integer> {

    @Query("SELECT folder FROM Folder folder WHERE folder.email.id=:emailId AND folder.name=:name")
    Folder getByEmailAndName(@Param("emailId") int emailId, @Param("name") String name);

    @Query("SELECT folder FROM Folder folder WHERE folder.email.id=:emailId ORDER BY folder.name")
    List<Folder> getAll(@Param("emailId") int emailId);
}
