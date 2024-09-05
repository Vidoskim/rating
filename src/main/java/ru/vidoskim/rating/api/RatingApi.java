package ru.vidoskim.rating.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public interface RatingApi {
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
