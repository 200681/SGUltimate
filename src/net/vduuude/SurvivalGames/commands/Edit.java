package net.vduuude.SurvivalGames.commands;

import net.md_5.bungee.api.ChatColor;
import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Edit implements CommandExecutor {
	public static SurvivalGames plugin;

	public Edit(SurvivalGames instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if(args.length==1) {
			if(player.hasPermission("sg.admin") || player.isOp()) {
				plugin.getServer().createWorld(new WorldCreator(args[0]));
				player.teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
			} else {
				player.sendMessage(ChatColor.DARK_RED + "Insufficient Rank");
			}

		}
		return false;
		
	}
	
	
	

}
