package net.vduuude.SurvivalGames.commands;

import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Addspectatorspawn implements CommandExecutor {
	public static SurvivalGames plugin;

	public Addspectatorspawn(SurvivalGames instance) {
		plugin = instance;
	}
	
	
	@SuppressWarnings("static-access")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if(args.length==1) {
			if(player.hasPermission("sg.admin") || player.isOp()) {
			plugin.arenamanager.addSpectatorSpawn(args[0], player.getLocation());
			player.sendMessage("Added spectator spawn");
			} else {
				player.sendMessage(ChatColor.DARK_RED + "Insufficient Rank");
			}
		}
		return false;
	}

}
