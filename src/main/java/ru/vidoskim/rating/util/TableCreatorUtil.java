package ru.vidoskim.rating.util;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.experimental.UtilityClass;
import ru.vidoskim.rating.model.RatingUser;

import java.sql.SQLException;

@UtilityClass
public class TableCreatorUtil {
    public void create(JdbcPooledConnectionSource connectionSource) throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, RatingUser.class);
    }
}
