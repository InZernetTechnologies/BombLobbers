package com.inzernettechnologies.bomblobbers.Regeneration;

import com.inzernettechnologies.bomblobbers.Database.MySQL;
import com.inzernettechnologies.bomblobbers.nBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class main {

    MySQL SQL = new MySQL();

    public void logBlock(List<nBlock> blocksList){
        Bukkit.getScheduler().runTaskAsynchronously(com.inzernettechnologies.bomblobbers.main.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    for (nBlock b : blocksList) {
                        PreparedStatement lBlock = SQL.getConnection().prepareStatement("INSERT INTO `bl_regen`(`world`, `x`, `y`, `z`, `type`, `data`) VALUES (?, ?, ?, ?, ?, ?)");
                        lBlock.setString(1, b.l.getWorld().getName());
                        lBlock.setString(2, b.l.getBlockX() + "");
                        lBlock.setString(3, b.l.getBlockY() + "");
                        lBlock.setString(4, b.l.getBlockZ() + "");
                        lBlock.setString(5, b.type.toString());
                        lBlock.setString(6, b.data);
                        lBlock.executeUpdate();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void regen(){
        /*Bukkit.getScheduler().runTaskAsynchronously(com.inzernettechnologies.bomblobbers.main.instance, new Runnable() {
            @Override
            public void run() {*/
                ResultSet blockSet = SQL.getBlocks();
                try {
                    while (blockSet.next()) {
                        Block newBlock = Bukkit.getWorld(blockSet.getString(2)).getBlockAt(new Location(Bukkit.getWorld(blockSet.getString(2)), blockSet.getInt(3), blockSet.getInt(4), blockSet.getInt(5)));
                        newBlock.setType(Material.getMaterial(blockSet.getString(6)));
                        newBlock.setData(blockSet.getByte(7));
                        //SQL.removeBlock(blockSet.getInt(1));
                        if (blockSet.isLast()){
                            break;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            /*}
        });*/
    }

}
