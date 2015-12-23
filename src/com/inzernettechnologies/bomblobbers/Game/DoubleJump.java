package com.inzernettechnologies.bomblobbers.Game;

import com.inzernettechnologies.bomblobbers.libs.ParticleEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;

public class DoubleJump {

    private static HashMap<Player, Integer> players = new HashMap<>();

    public boolean canUse(Player player) {
        if (players.containsKey(player)) {
            if (players.get(player) < com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("game.doubleJump.usage")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void useJump(PlayerToggleFlightEvent event) {
        if (canUse(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.IRONGOLEM_THROW, 10, -10);
            event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(1.5).setY(1.25));
            event.getPlayer().setAllowFlight(false);
            ParticleEffect.CLOUD.display(0.5f, 0.1f, 0.5f, 0.01f, 100, event.getPlayer().getLocation(), 1000);
            if (players.containsKey(event.getPlayer())) {
                players.put(event.getPlayer(), players.get(event.getPlayer()) + 1);
            } else {
                players.put(event.getPlayer(), 1);
            }
        }
    }

}
