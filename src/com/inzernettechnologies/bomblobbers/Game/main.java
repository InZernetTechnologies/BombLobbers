package com.inzernettechnologies.bomblobbers.Game;

import com.inzernettechnologies.bomblobbers.enums.gameStates;
import com.inzernettechnologies.bomblobbers.libs.ActionBar;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {
    public static String currentMap = "WAITING";
    private static gameStates gameState;
    private static int progressBarID;
    private static int givePlayerTNTID;
    private static double progress;
    com.inzernettechnologies.bomblobbers.Database.main DB = new com.inzernettechnologies.bomblobbers.Database.main();
    com.inzernettechnologies.bomblobbers.Game.team team = new com.inzernettechnologies.bomblobbers.Game.team();
    com.inzernettechnologies.bomblobbers.libs.TNTParticle particle = new com.inzernettechnologies.bomblobbers.libs.TNTParticle();

    public gameStates getGameState() {
        return gameState;
    }

    public void setGameState(gameStates gs) {
        gameState = gs;
    }

    public void gameWarmup() {
        preStart();
        assignArmour();
        spawnPlayers();
        setGameState(gameStates.WARMUP);
        ActionBar ab = new ActionBar();
        double max = com.inzernettechnologies.bomblobbers.main.instance.getConfig().getDouble("game.automation.gameWarmup");
        progress = 0;
        progressBarID = Bukkit.getScheduler().scheduleSyncRepeatingTask(com.inzernettechnologies.bomblobbers.main.instance, new BukkitRunnable() {
            @Override
            public void run() {
                double percent = 0;
                NumberFormat nf = NumberFormat.getInstance();
                nf.setMaximumFractionDigits(1);
                nf.setMinimumFractionDigits(1);

                String bar = "";
                percent = progress / max;

                int i = 0;
                for (; i <= (int) (percent * 24); i++) {
                    bar += ChatColor.GREEN + "▌";
                }
                for (; i <= 24; i++) {
                    bar += ChatColor.RED + "▌";
                }

                ab.setMessage("Game Start " + bar + " " + org.bukkit.ChatColor.RESET + ((Double.parseDouble(nf.format(progress - max)) < 0) ? -Double.parseDouble(nf.format(progress - max)) : Double.parseDouble(nf.format(progress - max))) + " Seconds");
                for (Player p : team.getPlayers()) {
                    ab.sendTo(p);
                    if (Double.parseDouble(nf.format(progress)) % 1 == 0) {
                        p.playNote(p.getLocation(), Instrument.PIANO, new Note(12));
                    }
                }
                if (Double.parseDouble(nf.format(progress)) == max) {

                    for (Player pl : team.getPlayers()) {
                        pl.playNote(pl.getLocation(), Instrument.PIANO, new Note(14));
                        bar = "";
                        for (int b = 0; b <= 24; b++) {
                            bar += "▌";
                        }
                        ab.setMessage("Game Start " + org.bukkit.ChatColor.GREEN + bar + org.bukkit.ChatColor.RESET + " 0.0" + " Seconds");
                        ab.sendTo(pl);
                    }
                    startGame();
                    Bukkit.getScheduler().cancelTask(progressBarID);
                }
                progress += .1;
            }
        }, 2, 2);
    }

    public void givePlayerTNT() {
        if (getGameState().equals(gameStates.STARTED) || getGameState().equals(gameStates.STARTING)) {
            givePlayerTNTID = Bukkit.getScheduler().scheduleSyncRepeatingTask(com.inzernettechnologies.bomblobbers.main.instance, new Runnable() {
                @Override
                public void run() {
                    for (Player player : team.getPlayers()) {

                        player.setFoodLevel(20);

                        if (!team.getTeam(player).equals(com.inzernettechnologies.bomblobbers.enums.team.Spectator)) {

                            float playerXP = player.getExp();
                            float newExp = playerXP + 0.025f;

                            if (newExp < .99999999999999999999) {
                                player.setExp(newExp);
                            } else {
                                if (player.getInventory().getItem(0) != null) {
                                    if (player.getInventory().getItem(0).getAmount() < player.getLevel()) {
                                        player.getInventory().addItem(TNT());
                                        player.setExp(0f);
                                    }
                                } else {
                                    player.getInventory().addItem(TNT());
                                    player.setExp(0f);
                                }
                            }
                        }
                    }
                }
            }, 0, 2);
        }
    }

    public ItemStack TNT(){
        ItemStack tnt = new ItemStack(Material.TNT, 1);
        ItemMeta tntmeta = tnt.getItemMeta();
        tntmeta.setDisplayName(ChatColor.YELLOW + "Throwing TNT");
        tnt.setItemMeta(tntmeta);
        return tnt;
    }

    public void preStart() {
        for (Player player : team.getPlayers()) {
            player.getInventory().clear();
            player.setExp(0f);
            player.setLevel(3);
        }
    }

    public void startGame() {
        setGameState(gameStates.STARTING);
        particle.particleLoop();
        givePlayerTNT();
        setGameState(gameStates.STARTED);
    }

    public void endGame() {

        /*

        TODO: End game

         */

    }

    public void spawnPlayers() {
        List<String> redSpawns = new ArrayList<String>(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getStringList("config.maps." + currentMap + ".red"));
        List<String> blueSpawns = new ArrayList<String>(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getStringList("config.maps." + currentMap + ".blue"));

        Random rand = new Random();

        for (Player player : team.getPlayers()) {
            if (team.getTeam(player).equals(com.inzernettechnologies.bomblobbers.enums.team.Red)) {
                int i = rand.nextInt(redSpawns.size());
                String[] split = redSpawns.get(i).split("_");
                Location loc = new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
                player.teleport(loc);
                redSpawns.remove(i);
            } else {
                int i = rand.nextInt(blueSpawns.size());
                String[] split = blueSpawns.get(i).split("_");
                Location loc = new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
                player.teleport(loc);
                blueSpawns.remove(i);
            }
        }

    }

    public void assignArmour() {
        for (Player p : team.getPlayers()) {
            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

            LeatherArmorMeta helmetmeta = (LeatherArmorMeta) helmet.getItemMeta();
            LeatherArmorMeta chestplatemeta = (LeatherArmorMeta) chestplate.getItemMeta();
            LeatherArmorMeta leggingsmeta = (LeatherArmorMeta) leggings.getItemMeta();
            LeatherArmorMeta bootsmeta = (LeatherArmorMeta) boots.getItemMeta();

            helmetmeta.setColor((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? Color.BLUE : Color.RED));
            chestplatemeta.setColor((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? Color.BLUE : Color.RED));
            leggingsmeta.setColor((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? Color.BLUE : Color.RED));
            bootsmeta.setColor((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? Color.BLUE : Color.RED));

            helmetmeta.setDisplayName((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? "Blue" : "Red") + " helmet");
            chestplatemeta.setDisplayName((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? "Blue" : "Red") + " chestplate");
            leggingsmeta.setDisplayName((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? "Blue" : "Red") + " leggings");
            bootsmeta.setDisplayName((team.getTeam(p) == com.inzernettechnologies.bomblobbers.enums.team.Blue ? "Blue" : "Red") + " boots");

            helmet.setItemMeta(helmetmeta);
            chestplate.setItemMeta(chestplatemeta);
            leggings.setItemMeta(leggingsmeta);
            boots.setItemMeta(bootsmeta);

            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chestplate);
            p.getInventory().setLeggings(leggings);
            p.getInventory().setBoots(boots);
        }
    }


    public String selectMap() {
        List<String> aMaps = new ArrayList<String>();
        ConfigurationSection cs = com.inzernettechnologies.bomblobbers.main.instance.getConfig().getConfigurationSection("config.maps");
        for (String s : cs.getKeys(false)) {
            if (com.inzernettechnologies.bomblobbers.main.instance.getConfig().getBoolean("config.maps." + s + ".enabled")) {
                aMaps.add(s);
            }
        }
        Random rand = new Random();
        if (aMaps.size() == 0) {
            return "WAITING";
        } else {
            return aMaps.get(rand.nextInt(aMaps.size()));
        }
    }

    public boolean isMinimumMet() {
        if (team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Red) >= 1 && team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Blue) >= 1){
            return true;
        } else {
            return false;
        }
    }

    public void autoStart() {
        new BukkitRunnable() {
            int countdown = com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("game.automation.waitTime");

            @Override
            public void run() {
                if (isMinimumMet()) {

                    if (countdown != 0 && countdown >= 0) {

                        if (currentMap == "WAITING") {
                            currentMap = selectMap();
                        }

                        com.inzernettechnologies.bomblobbers.libs.ActionBar ab = new ActionBar();

                        ab.setMessage("Starting in: " + countdown + " " + (countdown == 1 ? "second" : "seconds"));

                        for (Player p : team.getPlayers()) {
                            ab.sendTo(p);
                        }

                        countdown--;
                    } else {

                        cancel();

                        if (isMinimumMet()) {
                            gameWarmup();
                        } else {
                            autoStart();
                        }
                    }
                } else {
                    com.inzernettechnologies.bomblobbers.libs.ActionBar ab = new ActionBar();

                    ab.setMessage(ChatColor.DARK_BLUE + "Blue: " + team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Blue) + ChatColor.RESET + " | " + ChatColor.RED + "Red: " + team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Red));

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        ab.sendTo(p);
                    }
                }
            }
        }.runTaskTimer(com.inzernettechnologies.bomblobbers.main.instance, 20, 20);
    }

}
