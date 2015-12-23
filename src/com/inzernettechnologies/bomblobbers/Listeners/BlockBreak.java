package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.Game.team;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    com.inzernettechnologies.bomblobbers.Game.main main = new main();
    com.inzernettechnologies.bomblobbers.Game.team team = new team();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event (BlockBreakEvent e){
        if (main.getGameState() == gameStates.STARTED || main.getGameState() == gameStates.WARMUP && team.getPlayers().contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }

}
