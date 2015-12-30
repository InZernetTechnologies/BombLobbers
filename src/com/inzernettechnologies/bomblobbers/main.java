package com.inzernettechnologies.bomblobbers;

import com.inzernettechnologies.bomblobbers.Database.MySQL;
import com.inzernettechnologies.bomblobbers.Listeners.*;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    public static main instance;

    com.inzernettechnologies.bomblobbers.Game.main main = new com.inzernettechnologies.bomblobbers.Game.main();

    public void onEnable() {

        saveDefaultConfig();

        instance = this;

        MySQL.openConnection();

        getCommand("bl").setExecutor(new com.inzernettechnologies.bomblobbers.Command.main());
        getCommand("bla").setExecutor(new com.inzernettechnologies.bomblobbers.Command.main());
        getCommand("bls").setExecutor(new com.inzernettechnologies.bomblobbers.Command.main());

        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new EntityExplode(), this);
        Bukkit.getPluginManager().registerEvents(new EntityChangeBlock(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerToggleFlight(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPickupItem(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        //Bukkit.getPluginManager().registerEvents(new SignChange(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);

        main.autoStart();
        main.setGameState(gameStates.WAITING_FOR_PLAYERS);

    }

    public void onDisable() {
        MySQL.closeConnection();
    }

}
