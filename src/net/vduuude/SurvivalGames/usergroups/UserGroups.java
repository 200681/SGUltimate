package net.vduuude.SurvivalGames.usergroups;

import java.util.List;

import org.bukkit.ChatColor;

public interface UserGroups {
	public List<String> getPlayers();
	public ChatColor getColor();
	public String getName();
	void addPlayer(String name);
	void removePlayer(String name);

}
