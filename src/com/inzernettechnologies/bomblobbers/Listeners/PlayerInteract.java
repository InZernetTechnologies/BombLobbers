package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.enums.gameStates;
import com.inzernettechnologies.bomblobbers.enums.team;
import com.inzernettechnologies.bomblobbers.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerInteract implements Listener {

    com.inzernettechnologies.bomblobbers.Signs.main s = new com.inzernettechnologies.bomblobbers.Signs.main();
    com.inzernettechnologies.bomblobbers.Game.team team = new com.inzernettechnologies.bomblobbers.Game.team();
    com.inzernettechnologies.bomblobbers.Game.main game = new com.inzernettechnologies.bomblobbers.Game.main();
    com.inzernettechnologies.bomblobbers.libs.TNTParticle particle = new com.inzernettechnologies.bomblobbers.libs.TNTParticle();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event (PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN)) {
            Sign sign = (Sign) e.getClickedBlock().getState();
            if (sign.getLine(0).equals(s.colorize(main.instance.getConfig().getString("signs.gameJoin.firstLine")))) {
                // join game
            }
            if (sign.getLine(0).equals(s.colorize(main.instance.getConfig().getString("signs.teamJoin-Blue.firstLine")))) {
                team.setTeam(e.getPlayer(), com.inzernettechnologies.bomblobbers.enums.team.Blue);
                e.getPlayer().sendMessage("You joined blue");
            }
            if (sign.getLine(0).equals(s.colorize(main.instance.getConfig().getString("signs.teamJoin-Red.firstLine")))) {
                team.setTeam(e.getPlayer(), com.inzernettechnologies.bomblobbers.enums.team.Red);
                e.getPlayer().sendMessage("You joined red");
            }
        } else if (game.getGameState() == gameStates.STARTED && e.getPlayer().getItemInHand().getType() == Material.TNT && team.getPlayers().contains(e.getPlayer())) {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);
                e.getPlayer().getInventory().removeItem(new ItemStack(Material.TNT, 1));
                TNTPrimed tnt = (TNTPrimed) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getEyeLocation().add(0, 1, 0), EntityType.PRIMED_TNT);
                tnt.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(main.instance.getConfig().getInt("game.TNT.multiplier")));
                if (team.getTeam(e.getPlayer()).equals(com.inzernettechnologies.bomblobbers.enums.team.Blue)) {
                    tnt.setMetadata("b", new FixedMetadataValue(main.instance, 255));
                    tnt.setMetadata("r", new FixedMetadataValue(main.instance, 1));
                } else if (team.getTeam(e.getPlayer()).equals(com.inzernettechnologies.bomblobbers.enums.team.Red)) {
                    tnt.setMetadata("r", new FixedMetadataValue(main.instance, 255));
                    tnt.setMetadata("b", new FixedMetadataValue(main.instance, 0));
                }
                tnt.setMetadata("g", new FixedMetadataValue(main.instance, 0));
                tnt.setMetadata("player", new FixedMetadataValue(main.instance, e.getPlayer().getName()));
                particle.addTNT(tnt);
            }
        }
    }

}
