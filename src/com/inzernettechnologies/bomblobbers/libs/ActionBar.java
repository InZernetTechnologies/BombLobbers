package com.inzernettechnologies.bomblobbers.libs;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void broadcastActionBar() {
        for (Player list : Bukkit.getOnlinePlayers()) {
            sendTo(list.getPlayer());
        }
    }

    public void sendTo(Player p) {
        CraftPlayer craftplayer = (CraftPlayer) p;
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message.replaceAll("%player%", p.getName())) + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(cbc, (byte) 2);
        craftplayer.getHandle().playerConnection.sendPacket(packet);
    }
}
