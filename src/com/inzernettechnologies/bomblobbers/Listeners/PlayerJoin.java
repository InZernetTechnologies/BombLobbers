package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.Game.team;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    com.inzernettechnologies.bomblobbers.Game.main main = new main();
    com.inzernettechnologies.bomblobbers.Game.team team = new team();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerJoinEvent e) {

        /*

            TODO: Fix if player has a team, rejoin them on that team instead of giving another team

         */
        if (main.getGameState() == gameStates.WARMUP || main.getGameState() == gameStates.STARTING || main.getGameState() == gameStates.STARTED){
            team.setTeam(e.getPlayer(), com.inzernettechnologies.bomblobbers.enums.team.Spectator);
            /*

              TODO: put in spectator mode

             */
        } else {
            if (Math.abs(team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Blue) - team.getTeamCount(com.inzernettechnologies.bomblobbers.enums.team.Red)) == 1){
                team.setTeam(e.getPlayer(), com.inzernettechnologies.bomblobbers.enums.team.Blue);
            } else {
                team.setTeam(e.getPlayer(), com.inzernettechnologies.bomblobbers.enums.team.Red);
            }
        }
    }

}
