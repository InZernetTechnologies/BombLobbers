package com.inzernettechnologies.bomblobbers.Command;

import com.inzernettechnologies.bomblobbers.Game.team;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class main implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("bl")) {
            if (args[0].isEmpty()){
                sender.sendMessage("/bl <join|leave>");
                return true;
            }
            if (args[0].equalsIgnoreCase("join")) {
                if (args[1].isEmpty()){
                    sender.sendMessage("/bl join <Blue|Read");
                    return true;
                }
                team team = new team();
                team.setTeam((Player) sender, com.inzernettechnologies.bomblobbers.enums.team.valueOf(args[1]));
                sender.sendMessage("You joined the: " + com.inzernettechnologies.bomblobbers.enums.team.valueOf(args[1]) + " team");
            } else if (args[0].equalsIgnoreCase("leave")) {

                /*

                team team = new team();
                team.removeFromTeam((Player) sender);

                TODO: Leave team

                 */

            }
        } else if (label.equalsIgnoreCase("bls")) {
            if (args.length < 2){
                sender.sendMessage("/bls <create|addspawn>");
                return true;
            }
            if (args[0].equalsIgnoreCase("create")) {
                if (args[1].isEmpty()){
                    sender.sendMessage("/bls create <map>");
                    return true;
                }
                com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.maps." + args[1] + ".enabled", false);
                sender.sendMessage("Added map!");
                com.inzernettechnologies.bomblobbers.main.instance.saveConfig();
            } else if (args[0].equalsIgnoreCase("addspawn")) {
                if (args[2].isEmpty()){
                    sender.sendMessage("/bls addspawn <map> <Blue|Red>");
                    return true;
                }
                List<String> spawns = com.inzernettechnologies.bomblobbers.main.instance.getConfig().getStringList("config.maps." + args[1] + "." + args[2].toLowerCase());
                spawns.add(Bukkit.getPlayer(sender.getName()).getPlayer().getLocation().getWorld().getName() + "_" + Bukkit.getPlayer(sender.getName()).getPlayer().getLocation().getX() + "_" + Bukkit.getPlayer(sender.getName()).getPlayer().getLocation().getY() + "_" + Bukkit.getPlayer(sender.getName()).getPlayer().getLocation().getZ() + "_" + Bukkit.getPlayer(sender.getName()).getPlayer().getLocation().getYaw() + "_" + Bukkit.getPlayer(sender.getName()).getPlayer().getLocation().getPitch());
                com.inzernettechnologies.bomblobbers.main.instance.getConfig().set("config.maps." + args[1] + "." + args[2].toLowerCase(), spawns);
                sender.sendMessage("Added spawn!");
                // /bls addspawn <map[1]> <red|blue[2]>
                com.inzernettechnologies.bomblobbers.main.instance.saveConfig();
            }
        } else if (label.equalsIgnoreCase("bla")) {
            if (args[0].isEmpty()){
                sender.sendMessage("/bla <regen>");
                return true;
            }
            if (args[0].equalsIgnoreCase("regen")) {
                com.inzernettechnologies.bomblobbers.Regeneration.main regen = new com.inzernettechnologies.bomblobbers.Regeneration.main();
                regen.regen();
            }
        } else {
            sender.sendMessage("Unknown command");
        }
        return true;
    }
}
