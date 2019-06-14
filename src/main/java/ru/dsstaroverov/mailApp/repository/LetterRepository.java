package ru.dsstaroverov.mailApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.mailApp.model.Letter;

import java.util.List;

@Transactional(readOnly = true)
public interface LetterRepository  extends JpaRepository<Letter,Integer> {
    @Query("SELECT letter FROM Letter letter WHERE letter.folder.id=:folderId ORDER BY letter.sendTime DESC")
    List<Letter> getAll(@Param("folderId") int folderId);
}
