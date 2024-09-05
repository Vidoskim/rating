package ru.vidoskim.rating.service;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ru.vidoskim.rating.model.RatingUser;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public interface RatingUserService extends Service {
    RatingUser save(RatingUser user);

    void saveAll(List<RatingUser> users);

    Optional<RatingUser> findById(Long id);

    Optional<RatingUser> findByUsername(String username);

    List<RatingUser> findAll();

    RatingUser getUser(String username);

    RatingUser getUser(Player player);

    RatingUser getUser(OfflinePlayer player);

    int getPlayerRating(String username);

    int getPlayerRating(Player player);

    int getPlayerRating(OfflinePlayer player);

    void setPlayerRating(String username, int rating);

    void setPlayerRating(Player player, int rating);

    void setPlayerRating(OfflinePlayer player, int rating);

    void addPlayerRating(String username, int ratingToAdd);

    void addPlayerRating(Player player, int ratingToAdd);

    void addPlayerRating(OfflinePlayer player, int ratingToAdd);

    void takePlayerRating(String username, int ratingToTake);

    void takePlayerRating(Player player, int ratingToTake);

    void takePlayerRating(OfflinePlayer player, int ratingToTake);

    void resetPlayerRating(String username);

    void resetPlayerRating(Player player);

    void resetPlayerRating(OfflinePlayer player);
}
