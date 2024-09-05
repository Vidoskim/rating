package ru.vidoskim.rating.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.vidoskim.rating.model.RatingUser;
import ru.vidoskim.rating.service.RatingUserService;

import java.util.Map;

@RequiredArgsConstructor
public class JoinListener implements Listener {
    private final RatingUserService ratingUserService;
    private final Map<String, RatingUser> userMap;

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        userMap.put(player.getName(), ratingUserService.getUser(player));
    }
}
