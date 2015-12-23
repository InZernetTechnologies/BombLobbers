package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.Game.team;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItem implements Listener {

    main main = new main();
    team team = new team();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerPickupItemEvent e) {
        if (main.getGameState() == gameStates.STARTED || main.getGameState() == gameStates.WARMUP && team.getPlayers().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

}
