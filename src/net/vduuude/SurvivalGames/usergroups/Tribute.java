package net.vduuude.SurvivalGames.usergroups;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Tribute implements UserGroups {
	private List<String> players = new ArrayList<String>();

	@Override
	public List<String> getPlayers() {
		return players;
	}

	@Override
	public ChatColor getColor() {
		return ChatColor.BLUE;
	}

	@Override
	public String getName() {
		return "Tribute";
	}
	
	@Override
	public void addPlayer(String name) {
		players.add(name);
	}

	@Override
	public void removePlayer(String name) {
		players.remove(name);
		
	}

	
}
