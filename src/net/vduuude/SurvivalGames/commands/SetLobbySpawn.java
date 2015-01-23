package net.vduuude.SurvivalGames.commands;

import net.vduuude.SurvivalGames.SurvivalGames;
import net.vduuude.SurvivalGames.managers.ArenaManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbySpawn implements CommandExecutor {
	public static SurvivalGames plugin;
	public SetLobbySpawn(SurvivalGames instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("sg.admin")) {
			ArenaManager.setLobbySpawn(player.getLocation());
			player.sendMessage("Added Lobby Spawn");
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Insufficient Rank");
		}
		ArenaManager.setLobbySpawn(player.getLocation());
		return false;
	}

}
