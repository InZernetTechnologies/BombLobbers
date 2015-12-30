package com.inzernettechnologies.bomblobbers.Game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class team {

    public static HashMap<Player, com.inzernettechnologies.bomblobbers.enums.team> players = new HashMap<>();

    public List<Player> getPlayers() {
        return new ArrayList<>(players.keySet());
    }

    public void setTeam(Player player, com.inzernettechnologies.bomblobbers.enums.team team) {
        players.put(player, team);
    }

    public void removeFromTeam(Player player) {
        players.remove(player);
    }

    public int getTeamCount(com.inzernettechnologies.bomblobbers.enums.team name) {
        return Collections.frequency(new ArrayList<com.inzernettechnologies.bomblobbers.enums.team>(players.values()), name);
    }

    public com.inzernettechnologies.bomblobbers.enums.team getTeam(Player player) {
        return players.get(player);
    }

}
