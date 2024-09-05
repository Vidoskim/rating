package ru.vidoskim.rating.util;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import lombok.experimental.UtilityClass;
import ru.vidoskim.rating.model.RatingUser;

import java.sql.SQLException;

@UtilityClass
public class DaoCreatorUtil {
    public void create(JdbcPooledConnectionSource connectionSource) throws SQLException {
        DaoManager.createDao(connectionSource, RatingUser.class);
    }
}
