package com.inzernettechnologies.bomblobbers.libs;

import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.Bukkit;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class TNTParticle {

    private static ArrayList<TNTPrimed> tntArray = new ArrayList<>();

    public void addTNT(TNTPrimed tntPrimed){
        tntArray.add(tntPrimed);
    }

    public void removeTNT(TNTPrimed tntPrimed){
        tntArray.remove(tntPrimed);
    }

    public void particleLoop(){
        com.inzernettechnologies.bomblobbers.Game.main game = new com.inzernettechnologies.bomblobbers.Game.main();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (game.getGameState().equals(gameStates.STARTED)) {
                    for (TNTPrimed tnt : tntArray) {
                        if (!tnt.isOnGround()) {
                            ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(tnt.getMetadata("r").get(0).asInt(), tnt.getMetadata("g").get(0).asInt(), tnt.getMetadata("b").get(0).asInt()), tnt.getLocation().add(0, 1, 0), 100);
                        } else {
                            tnt.setVelocity(new Vector(0, 0, 0));
                        }
                        if (tnt.isDead()) {
                            removeTNT(tnt);
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(com.inzernettechnologies.bomblobbers.main.instance, com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("game.particles"), com.inzernettechnologies.bomblobbers.main.instance.getConfig().getInt("game.particles"));
    }

}
