package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlock implements Listener {

    com.inzernettechnologies.bomblobbers.Game.main main = new main();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(EntityChangeBlockEvent e) {
        if (main.getGameState() == gameStates.STARTED || main.getGameState() == gameStates.WARMUP) {
            if (e.getEntity() instanceof FallingBlock) {
                //ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.ParticleData(Material.GOLD_BLOCK), 1, 1, 1, );
                e.setCancelled(true);
            }
        }
    }

}
