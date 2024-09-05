package ru.vidoskim.rating.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import ru.vidoskim.rating.model.RatingUser;
import ru.vidoskim.rating.service.RatingUserService;

@RequiredArgsConstructor
public class PreJoinListener implements Listener {
    private final RatingUserService ratingUserService;
    private final FileConfiguration config;

    @EventHandler
    private void onJoin(AsyncPlayerPreLoginEvent event) {
        String username = event.getName();

        if(username.isEmpty()) {
            return;
        }

        if(ratingUserService.findByUsername(username).isPresent()) {
            return;
        }

        RatingUser user = RatingUser.builder()
                .username(username)
                .rate(config.getInt("other.default-rate"))
                .build();
        ratingUserService.save(user);
    }
}
