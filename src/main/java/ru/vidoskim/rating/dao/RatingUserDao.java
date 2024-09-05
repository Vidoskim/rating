package ru.vidoskim.rating.dao;

import com.j256.ormlite.dao.Dao;
import ru.vidoskim.rating.model.RatingUser;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RatingUserDao extends Dao<RatingUser, Long> {
    RatingUser save(RatingUser user) throws SQLException;

    void saveAll(List<RatingUser> users) throws SQLException;

    Optional<RatingUser> findById(Long id) throws SQLException;

    Optional<RatingUser> findByUsername(String username) throws SQLException;

    List<RatingUser> findAll() throws SQLException;
}
