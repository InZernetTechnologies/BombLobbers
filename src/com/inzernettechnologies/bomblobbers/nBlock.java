package com.inzernettechnologies.bomblobbers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class nBlock {

    public Location l;
    public Material type;
    public String data;

    public nBlock(Block b){
        this.l = b.getLocation();
        this.type = b.getType();
        this.data = b.getData() + "";
    }

}
