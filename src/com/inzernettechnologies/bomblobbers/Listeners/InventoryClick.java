package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.Game.team;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    com.inzernettechnologies.bomblobbers.Game.main main = new main();
    com.inzernettechnologies.bomblobbers.Game.team team = new team();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(InventoryClickEvent e) {
        if (main.getGameState() == gameStates.STARTED || main.getGameState() == gameStates.WARMUP && team.getPlayers().contains((Player) e.getWhoClicked())) {
            e.setCancelled(true);
        }
    }

}
