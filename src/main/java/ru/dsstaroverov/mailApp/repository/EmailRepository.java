package ru.dsstaroverov.mailApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.mailApp.model.Email;

import java.util.List;

@Transactional(readOnly = true)
public interface EmailRepository extends JpaRepository<Email,Integer> {

    @Query("SELECT email FROM Email email WHERE email.address=:address")
    Email getByAddress(@Param("address") String address);

    @Query("SELECT email FROM Email email WHERE email.user.id=:userId ORDER BY email.address DESC")
    List<Email> getAll(@Param("userId") int userId);

}
