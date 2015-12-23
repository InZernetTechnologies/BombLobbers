package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Signs.main;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChange implements Listener {

    com.inzernettechnologies.bomblobbers.Signs.main s = new com.inzernettechnologies.bomblobbers.Signs.main();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event (SignChangeEvent e){
        if (e.getLine(0).equals(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.registerPrefix"))) {
            e.setLine(0, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.firstLine")));
            e.setLine(1, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.secondLine")));
            e.setLine(2, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.thirdLine")));
            e.setLine(3, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.fourthLine")));
            Player player = e.getPlayer();
            player.sendMessage(s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("chat.prefix")) + "Sign registered");
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 10, 10);
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".type", "game");
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".world", e.getBlock().getLocation().getWorld().getName());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".x", e.getBlock().getLocation().getBlockX());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".y", e.getBlock().getLocation().getBlockY());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".z", e.getBlock().getLocation().getBlockZ());
            com.inzernettechnologies.bomblobbers.main.instance.saveConfig();
        }
        if (e.getLine(0).equals(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Blue.registerPrefix"))) {
            e.setLine(0, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Blue.firstLine")));
            e.setLine(1, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Blue.secondLine")));
            e.setLine(2, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Blue.thirdLine")));
            e.setLine(3, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Blue.fourthLine")));
            Player player = e.getPlayer();
            player.sendMessage(s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("chat.prefix")) + "Blue Team Join Sign registered");
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 10, 10);
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".type", "join-blue");
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".world", e.getBlock().getLocation().getWorld().getName());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".x", e.getBlock().getLocation().getBlockX());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".y", e.getBlock().getLocation().getBlockY());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".z", e.getBlock().getLocation().getBlockZ());
            com.inzernettechnologies.bomblobbers.main.instance.saveConfig();
        }
        if (e.getLine(0).equals(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Red.registerPrefix"))) {
            e.setLine(0, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Red.firstLine")));
            e.setLine(1, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Red.secondLine")));
            e.setLine(2, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Red.thirdLine")));
            e.setLine(3, s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.teamJoin-Red.fourthLine")));
            Player player = e.getPlayer();
            player.sendMessage(s.colorize(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("chat.prefix")) + "Red Team Join Sign registered");
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 10, 10);
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".type", "join-red");
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".world", e.getBlock().getLocation().getWorld().getName());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".x", e.getBlock().getLocation().getBlockX());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".y", e.getBlock().getLocation().getBlockY());
            com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.signs." + e.hashCode() + ".z", e.getBlock().getLocation().getBlockZ());
            com.inzernettechnologies.bomblobbers.main.instance.saveConfig();
        }
    }

}
