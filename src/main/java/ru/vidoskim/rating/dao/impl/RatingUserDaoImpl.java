package ru.vidoskim.rating.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import ru.vidoskim.rating.dao.RatingUserDao;
import ru.vidoskim.rating.model.RatingUser;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@SuppressWarnings("unused")
public class RatingUserDaoImpl extends BaseDaoImpl<RatingUser, Long> implements RatingUserDao {
    public RatingUserDaoImpl(ConnectionSource connectionSource, Class<RatingUser> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public RatingUser save(RatingUser user) throws SQLException {
        createOrUpdate(user);
        return user;
    }

    @Override
    public void saveAll(List<RatingUser> users) throws SQLException {
        callBatchTasks((Callable<Void>) () -> {
            for (RatingUser user : users) {
                createOrUpdate(user);
            }
            return null;
        });
    }

    @Override
    public Optional<RatingUser> findById(Long id) throws SQLException {
        RatingUser result = queryForId(id);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<RatingUser> findByUsername(String username) throws SQLException {
        if (username == null) return Optional.empty();

        QueryBuilder<RatingUser, Long> queryBuilder = queryBuilder();
        Where<RatingUser, Long> where = queryBuilder.where();
        String columnName = "username";

        SelectArg selectArg = new SelectArg(SqlType.STRING, username.toLowerCase());
        where.raw("LOWER(" + columnName + ")" + " = LOWER(?)", selectArg);
        return Optional.ofNullable(queryBuilder.queryForFirst());
    }

    @Override
    public List<RatingUser> findAll() throws SQLException {
        return queryForAll();
    }
}
