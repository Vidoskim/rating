package ru.vidoskim.rating;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.vidoskim.rating.api.RatingApi;
import ru.vidoskim.rating.model.RatingUser;
import ru.vidoskim.rating.service.RatingUserService;
import ru.vidoskim.rating.service.Service;
import ru.vidoskim.rating.service.impl.RatingUserServiceImpl;
import ru.vidoskim.rating.util.DaoCreatorUtil;
import ru.vidoskim.rating.util.TableCreatorUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class Rating extends JavaPlugin {
    private final Map<String, RatingUser> userMap =
            new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    @SuppressWarnings("all")
    private final Map<Class<?>, Object> serviceMap = new HashMap<>();

    private final FileConfiguration config = getConfig();
    private JdbcPooledConnectionSource connectionSource;

    @Getter
    @SuppressWarnings("unused")
    private static RatingApi api;

    @Override
    public void onEnable() {
        // Сохраняем стоковый конфиг из resources
        saveDefaultConfig();

        // Создаем подключение в БД, ЕСЛИ В КОНФИГЕ УКАЗАНО ЧТО-ТО НЕ ВЕРНО ВЕСЬ ПЛАГИН ОТПАДЁТ!!!!
        connectToDatabase(
                config.getString("mysql.host"),
                config.getString("mysql.database"),
                config.getString("mysql.user"),
                config.getString("mysql.pass"));

        // Регистрируем сервис юзеров (в нем же и регистрируются ивенты...)
        registerService(RatingUserService.class, new RatingUserServiceImpl(userMap,
                getDao(RatingUser.class),
                config, this));
    }

    @Override
    @SneakyThrows
    public void onDisable() {
        // Отключаемся от БД если вообще подключены
        if(connectionSource == null) return;
        connectionSource.close();
    }

    @SneakyThrows
    private void connectToDatabase(String host, String database, String user, String pass) {
        connectionSource = new JdbcPooledConnectionSource("jdbc:mysql://" + host + "/" + database + "?useSSL=true&autoReconnect=true");
        connectionSource.setUsername(user);
        connectionSource.setPassword(pass);
        connectionSource.setMaxConnectionsFree(5);

        TableCreatorUtil.create(connectionSource);
        DaoCreatorUtil.create(connectionSource);
    }

    @SuppressWarnings("all")
    private void registerService(Class<?> serviceClass, Object service) {
        ((Service) service).enable();
        serviceMap.put(serviceClass, service);
    }

    @SuppressWarnings("all")
    private  <D extends Dao<T, ?>, T> D getDao(Class<T> daoClass) {
        return DaoManager.lookupDao(connectionSource, daoClass);
    }
}
