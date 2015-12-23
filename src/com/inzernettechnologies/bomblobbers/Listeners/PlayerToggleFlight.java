package com.inzernettechnologies.bomblobbers.Listeners;

import com.inzernettechnologies.bomblobbers.Game.DoubleJump;
import com.inzernettechnologies.bomblobbers.Game.main;
import com.inzernettechnologies.bomblobbers.Game.team;
import com.inzernettechnologies.bomblobbers.enums.gameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerToggleFlight implements Listener {

    main main = new main();
    team team = new team();
    DoubleJump doubleJump = new DoubleJump();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerToggleFlightEvent e) {
        if (main.getGameState() == gameStates.STARTED && team.getPlayers().contains(e.getPlayer())) {
            doubleJump.useJump(e);
        }
    }

}
