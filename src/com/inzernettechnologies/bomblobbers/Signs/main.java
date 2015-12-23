package com.inzernettechnologies.bomblobbers.Signs;

import net.minecraft.server.v1_8_R3.BlockWallSign;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

public class main {

    com.inzernettechnologies.bomblobbers.Game.team team = new com.inzernettechnologies.bomblobbers.Game.team();

    public String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public String replace(String input) {

        input = input.replaceAll("%max%", "16");
        input = input.replaceAll("%cur%", "" + team.getPlayers().size());
        input = input.replaceAll("%map%", com.inzernettechnologies.bomblobbers.Game.main.currentMap);
        input = input.replaceAll("%blue%", "" + team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Blue));
        input = input.replaceAll("%red%", "" + team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Red));

        return input;

    }

    public void updateSigns() {
        new BukkitRunnable() {

            @Override
            public void run() {

                ConfigurationSection cs = com.inzernettechnologies.bomblobbers.main.instance.getConfig().getConfigurationSection("config.signs");

                for (String s : cs.getKeys(false)) {
                    if (Bukkit.getWorld(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".world")).getBlockAt(new Location(Bukkit.getWorld(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".world")), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".x"), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".y"), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".z"))).getState() instanceof Sign || Bukkit.getWorld(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".world")).getBlockAt(new Location(Bukkit.getWorld(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".world")), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".x"), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".y"), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".z"))).getState() instanceof BlockWallSign) {
                        Sign sign = (Sign) Bukkit.getWorld(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".world")).getBlockAt(new Location(Bukkit.getWorld(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".world")), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".x"), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".y"), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("config.signs." + s + ".z"))).getState();
                        if (com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".type").equals("game")) {
                            sign.setLine(0, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.firstLine"))));
                            sign.setLine(1, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.secondLine"))));
                            sign.setLine(2, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.thirdLine"))));
                            sign.setLine(3, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs.gameJoin.fourthLine"))));
                            sign.update();
                        } else {
                            sign.setLine(0, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs." + (com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".type").equals("join-blue") ? "teamJoin-Blue" : "teamJoin-Red") + ".firstLine"))));
                            sign.setLine(1, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs." + (com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".type").equals("join-blue") ? "teamJoin-Blue" : "teamJoin-Red") + ".secondLine"))));
                            sign.setLine(2, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs." + (com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".type").equals("join-blue") ? "teamJoin-Blue" : "teamJoin-Red") + ".thirdLine"))));
                            sign.setLine(3, colorize(replace(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("signs." + (com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("config.signs." + s + ".type").equals("join-blue") ? "teamJoin-Blue" : "teamJoin-Red") + ".fourthLine"))));
                            sign.update();
                        }
                    }
                }
            }

        }.runTaskTimer(com.inzernettechnologies.bomblobbers.main.instance, 20, 20);

    }

}
