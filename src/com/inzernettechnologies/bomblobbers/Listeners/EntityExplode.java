package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import com.inzernettechnologies.bomblobbers.nBlock;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class EntityExplode implements Listener {

    com.inzernettechnologies.bomblobbers.Game.main main = new main();
    com.inzernettechnologies.bomblobbers.Regeneration.main regen = new com.inzernettechnologies.bomblobbers.Regeneration.main();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event (EntityExplodeEvent e){
        if (main.getGameState() == gameStates.STARTED || main.getGameState() == gameStates.WARMUP){
            List<nBlock> blockList = new ArrayList<nBlock>();
            for (Block b : e.blockList()){
                blockList.add(new nBlock(b));
                FallingBlock fallingblock = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
                fallingblock.setDropItem(false);
                fallingblock.setVelocity(new Vector((float) -2 + (float) (Math.random() * ((2 - -2) + 1)), (float) -3 + (float) (Math.random() * ((3 - -3) + 1)), (float) -2 + (float) (Math.random() * ((2 - -2) + 1))));
            }
            regen.logBlock(blockList);
        }
    }

}
