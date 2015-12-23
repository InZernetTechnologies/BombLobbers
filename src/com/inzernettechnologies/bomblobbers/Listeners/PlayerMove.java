package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.DoubleJump;
import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.Game.team;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    com.inzernettechnologies.bomblobbers.Game.main main = new main();
    com.inzernettechnologies.bomblobbers.Game.team team = new team();
    DoubleJump doubleJump = new DoubleJump();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event (PlayerMoveEvent e){

        if (e.getPlayer().getLocation().getBlock().isLiquid() && main.getGameState() == gameStates.STARTED && team.getPlayers().contains(e.getPlayer())){
            e.getPlayer().damage(2);
        } else if (main.getGameState() == gameStates.WARMUP && team.getPlayers().contains(e.getPlayer()) && ((e.getFrom().getX() != e.getTo().getZ()) || (e.getFrom().getZ() != e.getTo().getZ()))){
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), e.getFrom().getX(), e.getPlayer().getLocation().getY(), e.getFrom().getZ(), e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch()));
        } else if (e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR && !e.getPlayer().isFlying() && main.getGameState() == gameStates.STARTED && doubleJump.canUse(e.getPlayer())) {
            e.getPlayer().setAllowFlight(true);
        }
    }

}
