package ru.dsstaroverov.mailApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.mailApp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT DISTINCT user FROM User user JOIN FETCH user.roles WHERE user.login=:login")
    User getByLogin(@Param("login") String login);

    @Modifying
    @Transactional
    @Query("DELETE FROM User WHERE id=:id")
    int delete(@Param("id") int id);
}
