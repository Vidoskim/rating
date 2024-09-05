package ru.vidoskim.rating.api.impl;

import lombok.RequiredArgsConstructor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ru.vidoskim.rating.api.RatingApi;
import ru.vidoskim.rating.service.RatingUserService;

@RequiredArgsConstructor
public class RatingApiImpl implements RatingApi {
    private final RatingUserService ratingUserService;

    @Override
    public int getPlayerRating(String username) {
        return ratingUserService.getPlayerRating(username);
    }

    @Override
    public int getPlayerRating(Player player) {
        return ratingUserService.getPlayerRating(player);
    }

    @Override
    public int getPlayerRating(OfflinePlayer player) {
        return ratingUserService.getPlayerRating(player);
    }

    @Override
    public void setPlayerRating(String username, int rating) {
        ratingUserService.setPlayerRating(username, rating);
    }

    @Override
    public void setPlayerRating(Player player, int rating) {
        ratingUserService.setPlayerRating(player, rating);
    }

    @Override
    public void setPlayerRating(OfflinePlayer player, int rating) {
        ratingUserService.setPlayerRating(player, rating);
    }

    @Override
    public void addPlayerRating(String username, int ratingToAdd) {
        ratingUserService.addPlayerRating(username, ratingToAdd);
    }

    @Override
    public void addPlayerRating(Player player, int ratingToAdd) {
        ratingUserService.addPlayerRating(player, ratingToAdd);
    }

    @Override
    public void addPlayerRating(OfflinePlayer player, int ratingToAdd) {
        ratingUserService.addPlayerRating(player, ratingToAdd);
    }

    @Override
    public void takePlayerRating(String username, int ratingToTake) {
        ratingUserService.takePlayerRating(username, ratingToTake);
    }

    @Override
    public void takePlayerRating(Player player, int ratingToTake) {
        ratingUserService.takePlayerRating(player, ratingToTake);
    }

    @Override
    public void takePlayerRating(OfflinePlayer player, int ratingToTake) {
        ratingUserService.takePlayerRating(player, ratingToTake);
    }

    @Override
    public void resetPlayerRating(String username) {
        ratingUserService.resetPlayerRating(username);
    }

    @Override
    public void resetPlayerRating(Player player) {
        ratingUserService.resetPlayerRating(player);
    }

    @Override
    public void resetPlayerRating(OfflinePlayer player) {
        ratingUserService.resetPlayerRating(player);
    }
}
