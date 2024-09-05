package ru.vidoskim.rating.service.impl;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.vidoskim.rating.Rating;
import ru.vidoskim.rating.dao.RatingUserDao;
import ru.vidoskim.rating.listener.JoinListener;
import ru.vidoskim.rating.listener.PreJoinListener;
import ru.vidoskim.rating.listener.QuitListener;
import ru.vidoskim.rating.model.RatingUser;
import ru.vidoskim.rating.service.RatingUserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class RatingUserServiceImpl implements RatingUserService {
    private final Map<String, RatingUser> userMap;
    private final RatingUserDao ratingUserDao;
    private final FileConfiguration config;
    private final Rating plugin;

    @Override
    public void enable() {
        Bukkit.getServer().getPluginManager().registerEvents(new PreJoinListener(this, config), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(this, userMap), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(userMap), plugin);
    }

    @Override
    public RatingUser save(RatingUser user) {
        try {
            return ratingUserDao.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(List<RatingUser> users) {
        try {
            ratingUserDao.saveAll(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<RatingUser> findById(Long id) {
        try {
            return ratingUserDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<RatingUser> findByUsername(String username) {
        try {
            return ratingUserDao.findByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RatingUser> findAll() {
        try {
            return ratingUserDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RatingUser getUser(String username) {
        return findByUsername(username).orElseGet(() -> RatingUser.builder()
                .username(username)
                .rate(config.getInt("other.default-rate"))
                .build());
    }

    @Override
    public RatingUser getUser(Player player) {
        return findByUsername(player.getName()).orElseGet(() -> RatingUser.builder()
                .username(player.getName())
                .rate(config.getInt("other.default-rate"))
                .build());
    }

    @Override
    public RatingUser getUser(OfflinePlayer player) {
        return findByUsername(player.getName()).orElseGet(() -> RatingUser.builder()
                .username(player.getName())
                .rate(config.getInt("other.default-rate"))
                .build());
    }

    @Override
    public int getPlayerRating(String username) {
        return userMap.get(username).getRate();
    }

    @Override
    public int getPlayerRating(Player player) {
        return userMap.get(player.getName()).getRate();
    }

    @Override
    public int getPlayerRating(OfflinePlayer player) {
        RatingUser user = findByUsername(player.getName()).orElseGet(() -> RatingUser.builder()
                .username(player.getName())
                .rate(config.getInt("other.default-rate"))
                .build());
        return user.getRate();
    }

    @Override
    public void setPlayerRating(String username, int rating) {
        setUserRate(username, rating);
    }

    @Override
    public void setPlayerRating(Player player, int rating) {
        setUserRate(player.getName(), rating);
    }

    @Override
    public void setPlayerRating(OfflinePlayer player, int rating) {
        setUserRate(player.getName(), rating);
    }

    @Override
    public void addPlayerRating(String username, int ratingToAdd) {
        RatingUser user = userMap.get(username);
        setUserRate(username, user.getRate() + ratingToAdd);
    }

    @Override
    public void addPlayerRating(Player player, int ratingToAdd) {
        RatingUser user = userMap.get(player.getName());
        setUserRate(player.getName(), user.getRate() + ratingToAdd);
    }

    @Override
    public void addPlayerRating(OfflinePlayer player, int ratingToAdd) {
        RatingUser user = userMap.get(player.getName());
        setUserRate(player.getName(), user.getRate() + ratingToAdd);
    }

    @Override
    public void takePlayerRating(String username, int ratingToTake) {
        RatingUser user = userMap.get(username);
        setUserRate(username, user.getRate() - ratingToTake);
    }

    @Override
    public void takePlayerRating(Player player, int ratingToTake) {
        RatingUser user = userMap.get(player.getName());
        setUserRate(player.getName(), user.getRate() - ratingToTake);
    }

    @Override
    public void takePlayerRating(OfflinePlayer player, int ratingToTake) {
        RatingUser user = userMap.get(player.getName());
        setUserRate(player.getName(), user.getRate() - ratingToTake);
    }

    @Override
    public void resetPlayerRating(String username) {
        setUserRate(username, config.getInt("other.default-rate"));
    }

    @Override
    public void resetPlayerRating(Player player) {
        setUserRate(player.getName(), config.getInt("other.default-rate"));
    }

    @Override
    public void resetPlayerRating(OfflinePlayer player) {
        setUserRate(player.getName(), config.getInt("other.default-rate"));
    }

    private void setUserRate(String username, int rate) {
        RatingUser user = userMap.get(username);
        user.setRate(rate);
        save(user);
    }
}
